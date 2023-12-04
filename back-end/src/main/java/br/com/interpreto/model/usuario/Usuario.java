package br.com.interpreto.model.usuario;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.enums.TipoUsuario;
import br.com.interpreto.model.feedback.Feedback;
import br.com.interpreto.model.solicitacao.Solicitacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private TipoUsuario role;
    @JsonFormat(pattern="yyyy-MM-dd")//Deve ser verificado
    private LocalDate dataNascimento;
    private Boolean ativo;
    private Double nota;
    private Long quantidadeEncontros;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)//Para tornar Bidirecional...
    @JsonManagedReference
    private List<AvaliacaoUsuario> avaliacaoUsuario;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Feedback> feedback;
    
    public Double calcularMediaNotas() {
        if (feedback == null || feedback.isEmpty()) {
            return nota;
        } else {
            double totalNotas = feedback.stream().mapToDouble(Feedback::getNota).sum();
            return (totalNotas + nota) / (feedback.size() + 1);
        }
    }

    public Usuario() {
    	
    	this.nota = 5.0;

    }
    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

	public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getQuantidadeEncontros() {
        return quantidadeEncontros;
    }

    public void setQuantidadeEncontros(Long quantidadeEncontros) {
        this.quantidadeEncontros = quantidadeEncontros;
    }

    public List<AvaliacaoUsuario> getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(List<AvaliacaoUsuario> avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }

    public TipoUsuario getRole() {
        return role;
    }

    public void setRole(TipoUsuario role) {
        this.role = role;
    }

    //Spring Secutiry
    @Override//Roless
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == TipoUsuario.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_SURDO"),
                    new SimpleGrantedAuthority("ROLE_INTERPRETE"));
        }else if(this.role == TipoUsuario.SURDO) {
            return List.of(new SimpleGrantedAuthority("ROLE_SURDO"));
        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_INTERPRETE"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}