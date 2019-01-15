package gob.grsm.denuncia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the denuncia_calificacion database table.
 * 
 */
@Entity
@Table(name="denuncia_calificacion")
@NamedQuery(name="DenunciaCalificacion.findAll", query="SELECT d FROM DenunciaCalificacion d")
public class DenunciaCalificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_denuncia_calificacion")
	private Integer idDenunciaCalificacion;

	@Column(name="dsc_calificacion_denuncia")
	private String dscCalificacionDenuncia;

	private Integer peso;

	//bi-directional many-to-one association to Denuncia
	@OneToMany(mappedBy="denunciaCalificacion")
	private List<Denuncia> denuncias;

	public DenunciaCalificacion() {
	}

	public Integer getIdDenunciaCalificacion() {
		return this.idDenunciaCalificacion;
	}

	public void setIdDenunciaCalificacion(Integer idDenunciaCalificacion) {
		this.idDenunciaCalificacion = idDenunciaCalificacion;
	}

	public String getDscCalificacionDenuncia() {
		return this.dscCalificacionDenuncia;
	}

	public void setDscCalificacionDenuncia(String dscCalificacionDenuncia) {
		this.dscCalificacionDenuncia = dscCalificacionDenuncia;
	}

	public Integer getPeso() {
		return this.peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public List<Denuncia> getDenuncias() {
		return this.denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	public Denuncia addDenuncia(Denuncia denuncia) {
		getDenuncias().add(denuncia);
		denuncia.setDenunciaCalificacion(this);

		return denuncia;
	}

	public Denuncia removeDenuncia(Denuncia denuncia) {
		getDenuncias().remove(denuncia);
		denuncia.setDenunciaCalificacion(null);

		return denuncia;
	}

}