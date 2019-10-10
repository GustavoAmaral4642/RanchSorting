package com.ranchsorting.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_boiada")
public class Boiada implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int numero;
	private List<Passada> passadas;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bo_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "bo_numero")
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@OneToMany(mappedBy = "ordemEntrada", cascade = CascadeType.ALL)
	public List<Passada> getPassadas() {
		return passadas;
	}

	public void setPassadas(List<Passada> passadas) {
		this.passadas = passadas;
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
		Boiada other = (Boiada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
