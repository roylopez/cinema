package co.cinemas.ingeneo.servicios;

import java.util.List;

import co.cinemas.ingeneo.persistencia.entidad.ConfiguracionSistema;
import co.cinemas.ingeneo.persistencia.entidad.Sala;
import co.cinemas.ingeneo.persistencia.entidad.Sucursal;
import co.cinemas.ingeneo.persistencia.entidad.TipoSala;

/**
 * Interfaz que define las operaciones a ser realizadas por el servicio
 * SalaService
 * 
 * @author Roy López Cardona
 */
public interface ISalaService {

	/**
	 * Método que se encarga de la creación de salas
	 * 
	 * @param sala Entidad de tipo sala
	 * @return Entidad de tipo sala creada
	 */
	public Sala create(Sala sala);

	/**
	 * Métod que permite obtener todas las salas
	 * 
	 * @return Lista con entidades de tipo sala
	 */
	public List<Sala> findAll();

	/**
	 * Método que permite obtener una sala mediante su identificador
	 * 
	 * @param id Identificador de la sala
	 * @return Entidad sala
	 */
	public Sala findById(Long id);

	/**
	 * Método que permite consultar una sucursal por medio de su identificador
	 * 
	 * @param id Identificador de la sucursal
	 * @return Entidad sucursal
	 */
	public Sucursal findSucursalById(Long id);

	/**
	 * Método que permite consultar un tipo de sala por medio de su identificador
	 * 
	 * @param id Identificador del tipo sala
	 * @return Entidad TipoSala
	 */
	public TipoSala findTipoSalaById(Long id);

	/**
	 * Método que permite obtener una configuración del sistema por medio de su
	 * identificador
	 * 
	 * @param id Identificador de la configuración del sistema
	 * @return Etidad ConfiguracionSistema
	 */
	public ConfiguracionSistema findConfiguracionSistemaById(String id);

	/**
	 * Método que permite crear la disposición de una sala mediante una cadena de
	 * caracteres dado el número de filas y el número de sillas por fila
	 * 
	 * @param filas      Cantidad de filas
	 * @param sillasFila Cantidad de sillas por fila
	 * @return String que simula la disposición de la sala
	 */
	public String crearDisposicionSala(Integer filas, Integer sillasFila);

}
