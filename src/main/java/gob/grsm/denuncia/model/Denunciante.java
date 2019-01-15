package gob.grsm.denuncia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the denunciante database table.
 * 
 */
@Entity
@NamedQuery(name="Denunciante.findAll", query="SELECT d FROM Denunciante d")
public class Denunciante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_denunciante")
	private Long idDenunciante;

	private String dni;

	private String email;

	private String nombres;

	//bi-directional many-to-one association to Denuncia
	@OneToMany(mappedBy="denunciante")
	private List<Denuncia> denuncias;

	public Denunciante() {
	}

	public Long getIdDenunciante() {
		return this.idDenunciante;
	}

	public void setIdDenunciante(Long idDenunciante) {
		this.idDenunciante = idDenunciante;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<Denuncia> getDenuncias() {
		return this.denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	public Denuncia addDenuncia(Denuncia denuncia) {
		getDenuncias().add(denuncia);
		denuncia.setDenunciante(this);

		return denuncia;
	}

	public Denuncia removeDenuncia(Denuncia denuncia) {
		getDenuncias().remove(denuncia);
		denuncia.setDenunciante(null);

		return denuncia;
	}

}