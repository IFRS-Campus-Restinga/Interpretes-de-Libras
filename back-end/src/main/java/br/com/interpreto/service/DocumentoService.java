package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioRepository;
import br.com.interpreto.model.documento.Documento;
import br.com.interpreto.model.documento.DocumentoRepository;
import br.com.interpreto.model.solicitacao.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DocumentoService {
    //Configuracao caminho para salvar o arquivo
    @Value("${interpreto.documentos.raiz}")
    private String raiz;
  /*  @Value("${interpreto.documentos.diretorio-documentos}")
    private String diretorioDocumento;*/
    //FIM

    final private DocumentoRepository documentoRepository;
    final private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public DocumentoService(DocumentoRepository documentoRepository, AvaliacaoUsuarioRepository avaliacaoUsuarioRepository) {
        this.documentoRepository = documentoRepository;
        this.avaliacaoUsuarioRepository = avaliacaoUsuarioRepository;
    }

    @Transactional
    public void salvarDocumento(Documento documento) {
        documentoRepository.save(documento);
    }

    public void salvarDocumento(MultipartFile documento, AvaliacaoUsuario avaliacao) {
        this.salvar(this.raiz, documento, avaliacao);
    }

    public void salvar(String diretorio, MultipartFile arquivo, AvaliacaoUsuario avaliacao) {
        Path diretorioPath = Paths.get(diretorio);
        Documento documento = new Documento();
        // Obtém a data e hora atuais
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define o formato desejado para o timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        // Formata a data e hora atual como uma string
        String timestamp = currentDateTime.format(formatter);



        String fileName = arquivo.getOriginalFilename();
        String[] nameSplit = fileName.split(".pdf");
        fileName = nameSplit[0].concat(timestamp).concat(".pdf");

        documento.setNomeArquivo(fileName);
        documentoRepository.save(documento);
        avaliacao.setDocumento(documento);
        Path arquivoPath = diretorioPath.resolve(fileName);

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }

    public ResponseEntity fazerDownload(Long id) {

        //Recupera o nome do arquivo
        String nomeDoArquivo = avaliacaoUsuarioRepository.getReferenceById(id).getDocumento().getNomeArquivo();

        try {
            //Transforma conteudo do arquivo em bytes
            byte[] conteudoPDF = Files.readAllBytes(Paths.get(this.raiz, nomeDoArquivo));

            //Adequando conteudo para resposta http
            ByteArrayResource resource = new ByteArrayResource(conteudoPDF);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=documento.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    //.contentLength(conteudoPDF.length)
                    .body(resource);

        } catch (IOException e){
            //e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao ler arquivo");

        }



    }
}
