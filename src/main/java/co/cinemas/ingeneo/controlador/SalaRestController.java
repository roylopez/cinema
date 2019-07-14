package co.cinemas.ingeneo.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.cinemas.ingeneo.modelo.dto.SalaDto;
import co.cinemas.ingeneo.persistencia.entidad.Sala;
import co.cinemas.ingeneo.persistencia.entidad.Sucursal;
import co.cinemas.ingeneo.persistencia.entidad.TipoSala;
import co.cinemas.ingeneo.servicios.ISalaService;
import co.cinemas.ingeneo.utilidad.Constantes;
import co.cinemas.ingeneo.utilidad.SalaMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * Controlador de tipo Rest para exponer las operaciones asociadas a la sala
 * 
 * @author Roy López Cardona
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(value = "SalaRestController")
public class SalaRestController {

	@Autowired
	private ISalaService salaService;

	/**
	 * Método que permite obtener todas las salas registradas en la base de datos
	 * 
	 * @return Lista de salas registradas en base de datos
	 */
	@GetMapping("/salas")
	@ApiOperation(value = "index", notes = "Operación que permite obtener todas las salas registradas en base de datos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponse(response = List.class, code = 200, message = "Lista de salas")
	public List<SalaDto> index() {
		return salaService.findAll().stream().map(SalaMapper::convertToDto).collect(Collectors.toList());
	}

	/**
	 * Método que permite obtener la información de una sala a partir de su
	 * identificador
	 * 
	 * @param id El identificador de la sala
	 * @return Información tras realizar la operación
	 */
	@GetMapping("/salas/{id}")
	@ApiOperation(value = "show", notes = "Operación que permite obtener una sala registrada en base de datos a partir de su identificador", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiParam(name = "id", required = true)
	@ApiResponse(response = ResponseEntity.class, code = 200, message = "Información de la sala solicitada")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Sala sala = null;
		Map<String, Object> mensajes = new HashMap<>();

		try {
			sala = salaService.findById(id);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar la consulta en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (sala == null) {
			mensajes.put("mensaje", "La sala con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<SalaDto>(SalaMapper.convertToDto(sala), HttpStatus.OK);
	}

	/**
	 * Método que permite realizar la creación de un objeto tipo sala en la base de
	 * datos
	 * 
	 * @param salaDto Dto con la información de la sala
	 * @param result  Resultados de validaciones javax
	 * @return Información de la sala registrada
	 */
	@PostMapping("/salas")
	@ApiOperation(value = "create", notes = "Operación que permite registrar una sala en base de datos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiResponse(response = ResponseEntity.class, code = 200, message = "Información de la sala registrada")
	public ResponseEntity<?> create(@Valid @RequestBody SalaDto salaDto, BindingResult result) {
		Sala sala = SalaMapper.convertToSalaEntity(salaDto);
		Map<String, Object> mensajes = new HashMap<>();

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		Integer maxSillasFila = Integer
				.parseInt(salaService.findConfiguracionSistemaById(Constantes.MAX_SILLAS_FILA).getValor());
		if (salaDto.getMaxSillasFila().compareTo(maxSillasFila) > 0) {
			mensajes.put("mensaje", "El número máximo de sillas es invalido, no debe superar " + maxSillasFila);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		Sucursal sucursal = salaService.findSucursalById(salaDto.getIdSucursal());
		if (sucursal == null) {
			mensajes.put("mensaje", "La sucursal no existe");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		sala.setSucursal(sucursal);

		TipoSala tipoSala = salaService.findTipoSalaById(salaDto.getIdTipoSala());
		if (tipoSala == null) {
			mensajes.put("mensaje", "El tipo de sala no existe");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		sala.setTipoSala(tipoSala);

		try {
			sala = salaService.create(sala);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		salaDto = SalaMapper.convertToDto(sala);
		return new ResponseEntity<SalaDto>(salaDto, HttpStatus.OK);
	}

	/**
	 * Método que permite simular la disposición de sillas en una sala mediante la
	 * creación de una secuencia de caracteres
	 * 
	 * @param filas      Número de filas para la sala
	 * @param sillasFila Número de sillas para cada fila
	 * @return Cadena de caracteres
	 */
	@GetMapping("/salas/disposicion/{filas}/{sillas-fila}")
	@ApiOperation(value = "disposicion", notes = "Operación que permite simular la disposición de la sala", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> disposicion(@PathVariable(name = "filas") Integer filas,
			@PathVariable(name = "sillas-fila") Integer sillasFila) {
		Map<String, Object> mensajes = new HashMap<>();

		Integer maxSillasFila = Integer
				.parseInt(salaService.findConfiguracionSistemaById(Constantes.MAX_SILLAS_FILA).getValor());
		if (sillasFila.compareTo(maxSillasFila) > 0 || sillasFila.intValue() < 3) {
			mensajes.put("mensaje",
					"Número de sillas por fila es invalido, debe ser un valor entre 3 y " + maxSillasFila);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		if (filas.intValue() < 3 || filas.intValue() > 27) {
			mensajes.put("mensaje", "Número de filas inválido, debe ser un valor entre 3 y 27");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(salaService.crearDisposicionSala(filas, sillasFila), HttpStatus.OK);
	}

}
