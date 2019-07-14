package co.cinemas.ingeneo.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la entidad de tipo Configuracion_sistema
 * 
 * @author Roy López Cardona
 */
@Entity
@Table(name = "CONFIGURACION_SISTEMA")
public class ConfiguracionSistema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String valor;

	private String descripcion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
