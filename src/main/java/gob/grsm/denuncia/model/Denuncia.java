package gob.grsm.denuncia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the denuncia database table.
 * 
 */
@Entity
@NamedQuery(name="Denuncia.findAll", query="SELECT d FROM Denuncia d")
public class Denuncia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_denuncia")
	private String idDenuncia;

	private String email;

	private Timestamp fecha;

	private String glosa;

	private String imei;

	private double latitud;

	private double longitud;

	private String telefono;

	//bi-directional many-to-one association to DenunciaCalificacion
	@ManyToOne
	@JoinColumn(name="id_denuncia_calificacion")
	private DenunciaCalificacion denunciaCalificacion;

	//bi-directional many-to-one association to DenunciaEstado
	@ManyToOne
	@JoinColumn(name="id_denuncia_estado")
	private DenunciaEstado denunciaEstado;

	//bi-directional many-to-one association to Denunciante
	@ManyToOne
	@JoinColumn(name="id_denunciante")
	private Denunciante denunciante;

	//bi-directional many-to-one association to DenunciaDetalle
	@OneToMany(mappedBy="denuncia")
	private List<DenunciaDetalle> denunciaDetalles;

	public Denuncia() {
	}

	public String getIdDenuncia() {
		return this.idDenuncia;
	}

	public void setIdDenuncia(String idDenuncia) {
		this.idDenuncia = idDenuncia;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getGlosa() {
		return this.glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DenunciaCalificacion getDenunciaCalificacion() {
		return this.denunciaCalificacion;
	}

	public void setDenunciaCalificacion(DenunciaCalificacion denunciaCalificacion) {
		this.denunciaCalificacion = denunciaCalificacion;
	}

	public DenunciaEstado getDenunciaEstado() {
		return this.denunciaEstado;
	}

	public void setDenunciaEstado(DenunciaEstado denunciaEstado) {
		this.denunciaEstado = denunciaEstado;
	}

	public Denunciante getDenunciante() {
		return this.denunciante;
	}

	public void setDenunciante(Denunciante denunciante) {
		this.denunciante = denunciante;
	}

	public List<DenunciaDetalle> getDenunciaDetalles() {
		return this.denunciaDetalles;
	}

	public void setDenunciaDetalles(List<DenunciaDetalle> denunciaDetalles) {
		this.denunciaDetalles = denunciaDetalles;
	}

	public DenunciaDetalle addDenunciaDetalle(DenunciaDetalle denunciaDetalle) {
		getDenunciaDetalles().add(denunciaDetalle);
		denunciaDetalle.setDenuncia(this);

		return denunciaDetalle;
	}

	public DenunciaDetalle removeDenunciaDetalle(DenunciaDetalle denunciaDetalle) {
		getDenunciaDetalles().remove(denunciaDetalle);
		denunciaDetalle.setDenuncia(null);

		return denunciaDetalle;
	}

}