package co.cinemas.ingeneo.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Clase que representa la entidad de tipo Sala
 * 
 * @author Roy López Cardona
 */
@Entity
@Table(name = "SALA")
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "tipo_sala_id")
	private TipoSala tipoSala;

	@Column(name = "numero_filas")
	private Integer numeroFilas;

	@Column(name = "max_sillas_fila")
	private Integer maxSillasFila;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "sucursal_id")
	private Sucursal sucursal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoSala getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(TipoSala tipoSala) {
		this.tipoSala = tipoSala;
	}

	public Integer getNumeroFilas() {
		return numeroFilas;
	}

	public void setNumeroFilas(Integer numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	public Integer getMaxSillasFila() {
		return maxSillasFila;
	}

	public void setMaxSillasFila(Integer maxSillasFila) {
		this.maxSillasFila = maxSillasFila;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
