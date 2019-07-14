package co.cinemas.ingeneo.utilidad;

import co.cinemas.ingeneo.modelo.dto.SalaDto;
import co.cinemas.ingeneo.persistencia.entidad.Sala;
import co.cinemas.ingeneo.persistencia.entidad.Sucursal;
import co.cinemas.ingeneo.persistencia.entidad.TipoSala;

/**
 * Clase que expone los métodos necesarios para convertir una entidad Sala a su
 * correspondiente Dto y viceversa
 * 
 * @author Roy López Cardona
 */
public class SalaMapper {

	/**
	 * Método utilitario que se encarga de convertir un dto de sala a su entidad
	 * correspondiente
	 * 
	 * @param salaDto El dto con la información de la sala
	 * @return Entidad de tipo Sala
	 */
	public static Sala convertToSalaEntity(SalaDto salaDto) {
		Sala sala = new Sala();
		sala.setId(salaDto.getIdSala());
		sala.setMaxSillasFila(salaDto.getMaxSillasFila());
		sala.setNumeroFilas(salaDto.getNumeroFilas());

		Sucursal sucursal = new Sucursal();
		sucursal.setId(salaDto.getIdSucursal());
		sala.setSucursal(sucursal);

		TipoSala tipoSala = new TipoSala();
		tipoSala.setId(salaDto.getIdTipoSala());
		sala.setTipoSala(tipoSala);

		return sala;
	}

	/**
	 * Método utilitario que se encarga de convertir una entidad de sala a su dto
	 * correspondiente
	 * 
	 * @param sala La entidad con la información de la sala
	 * @return Dto de tipo Sala
	 */
	public static SalaDto convertToDto(Sala sala) {
		SalaDto dto = new SalaDto();
		dto.setIdSala(sala.getId());
		dto.setIdSucursal(sala.getSucursal().getId());
		dto.setIdTipoSala(sala.getTipoSala().getId());
		dto.setMaxSillasFila(sala.getMaxSillasFila());
		dto.setNumeroFilas(sala.getNumeroFilas());

		return dto;
	}

}
