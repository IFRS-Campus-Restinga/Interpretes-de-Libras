package br.com.interpreto.model.feedback;

import java.util.Optional;

import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.model.usuario.UsuarioRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Transient
	private UsuarioRepository usuarioRepository;

	public Feedback(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String resenha;
	private Double nota;
	@JoinColumn(name = "avaliador_id")
	private Long avaliador;

	@ManyToOne
	@JoinColumn(name = "avaliado_id")
	private Usuario usuario;

	public Feedback() {
		// Construtor padrão sem argumentos necessário por JPA
	}

	public Feedback(Usuario usuario) {
		this.usuario = usuario;
		this.nota = usuario.getNota();
	}

	public Long getId() {
		return id;
	}

	public Long getIdAvaliador() {
		return avaliador;
	}

	public void setIdAvaliador(Long avaliador) {
		this.avaliador = avaliador;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResenha() {
		return resenha;
	}

	public void setResenha(String resenha) {
		this.resenha = resenha;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeAvaliado() {
		return (usuario != null) ? usuario.getNome() : null;
	}

	public String getNomeAvaliador() {
		if (avaliador != null) {
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(avaliador);
			return usuarioOptional.map(Usuario::getNome).orElse(null);
		} else {
			return null;
		}
	}
}
