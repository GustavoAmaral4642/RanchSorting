package com.ranchsorting.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_tarefas")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomePrograma; // OK
	private String codigoPrograma; // OK
	private PermissoesDeUsuario permissao; //OK
	
	@Id
	@GeneratedValue
	@Column(name = "tf_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="tf_nome_programa", nullable=false)
	public String getNomePrograma() {
		return nomePrograma;
	}

	public void setNomePrograma(String nomePrograma) {
		this.nomePrograma = nomePrograma;
	}

	@Column(name="tf_codigo_programa", nullable=false)
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	@ManyToOne
	@JoinColumn(name = "tf_permissao_id")
	public PermissoesDeUsuario getPermissao() {
		return permissao;
	}

	public void setPermissao(PermissoesDeUsuario permissao) {
		this.permissao = permissao;
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
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
