package com.ranchsorting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome; // OK 
	private String sobreNome; // OK
	private String email; // OK
	private String senha; // OK
	private Ocorrencia ocorrencia; // Falta implementar
	private List<PermissoesDeUsuario> permissoes = new ArrayList<>(); //OK

	@Id
	@GeneratedValue
	@Column(name = "us_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max=120)
	@Column(name = "us_nome", nullable = false, length = 120)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Size(max=120)
	@Column(name = "us_sobrenome", length = 120)
	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	@NotBlank
	@Size(max=120)
	@Column(name = "us_email", nullable=false, length = 120)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Size(max=35)
	@Column(name = "us_senha", nullable = false, length = 35)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// CascadeType.All Se eu estiver salvando, ele salva primeiro a ocorrencia,
	// se eu quiser
	// posso usar o CascadeType.PERSIST para quando eu estiver persistindo, ou
	// CascadeType.Type.MERGE, se eu for deletar, não é para fazer nada.
	// CascadeType.ALL faz tudo!
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "us_ocorrencia")
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	public List<PermissoesDeUsuario> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissoesDeUsuario> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
