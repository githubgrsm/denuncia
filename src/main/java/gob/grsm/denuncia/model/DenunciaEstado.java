package gob.grsm.denuncia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the denuncia_estado database table.
 * 
 */
@Entity
@Table(name="denuncia_estado")
@NamedQuery(name="DenunciaEstado.findAll", query="SELECT d FROM DenunciaEstado d")
public class DenunciaEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_denuncia_estado")
	private Integer idDenunciaEstado;

	@Column(name="dsc_denuncia")
	private String dscDenuncia;

	//bi-directional many-to-one association to Denuncia
	@OneToMany(mappedBy="denunciaEstado")
	private List<Denuncia> denuncias;

	public DenunciaEstado() {
	}

	public Integer getIdDenunciaEstado() {
		return this.idDenunciaEstado;
	}

	public void setIdDenunciaEstado(Integer idDenunciaEstado) {
		this.idDenunciaEstado = idDenunciaEstado;
	}

	public String getDscDenuncia() {
		return this.dscDenuncia;
	}

	public void setDscDenuncia(String dscDenuncia) {
		this.dscDenuncia = dscDenuncia;
	}

	public List<Denuncia> getDenuncias() {
		return this.denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	public Denuncia addDenuncia(Denuncia denuncia) {
		getDenuncias().add(denuncia);
		denuncia.setDenunciaEstado(this);

		return denuncia;
	}

	public Denuncia removeDenuncia(Denuncia denuncia) {
		getDenuncias().remove(denuncia);
		denuncia.setDenunciaEstado(null);

		return denuncia;
	}

}