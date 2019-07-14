package co.cinemas.ingeneo.modelo.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Clase encargada de almacenar la información de la entidad Sala
 * 
 * @author Roy López Cardona
 */
public class SalaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idSala;

	@NotNull(message = "Se requiere un tipo de sala")
	private Long idTipoSala;

	@NotNull(message = "El número de filas es requerido")
	@Max(value = 27, message = "El número máximo de filas es 27")
	@Min(value = 3, message = "El número mínimo de filas es 3")
	private Integer numeroFilas;

	@NotNull
	@Min(value = 3, message = "Una fila no puede tener menos de 3 sillas")
	private Integer maxSillasFila;

	@NotNull(message = "Se requiere el identificador de la sucursal")
	private Long idSucursal;

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public Long getIdTipoSala() {
		return idTipoSala;
	}

	public void setIdTipoSala(Long idTipoSala) {
		this.idTipoSala = idTipoSala;
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

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

}
