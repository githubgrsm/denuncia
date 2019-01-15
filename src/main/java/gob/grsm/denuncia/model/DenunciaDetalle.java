package gob.grsm.denuncia.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the denuncia_detalle database table.
 * 
 */
@Entity
@Table(name="denuncia_detalle")
@NamedQuery(name="DenunciaDetalle.findAll", query="SELECT d FROM DenunciaDetalle d")
public class DenunciaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_denuncia_detalle")
	private String idDenunciaDetalle;

	@Column(name="id_tipo_archivo")
	private Integer idTipoArchivo;

	private String ruta;

	//bi-directional many-to-one association to Denuncia
	@ManyToOne
	@JoinColumn(name="id_denuncia")
	private Denuncia denuncia;

	public DenunciaDetalle() {
	}

	public String getIdDenunciaDetalle() {
		return this.idDenunciaDetalle;
	}

	public void setIdDenunciaDetalle(String idDenunciaDetalle) {
		this.idDenunciaDetalle = idDenunciaDetalle;
	}

	public Integer getIdTipoArchivo() {
		return this.idTipoArchivo;
	}

	public void setIdTipoArchivo(Integer idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Denuncia getDenuncia() {
		return this.denuncia;
	}

	public void setDenuncia(Denuncia denuncia) {
		this.denuncia = denuncia;
	}

}