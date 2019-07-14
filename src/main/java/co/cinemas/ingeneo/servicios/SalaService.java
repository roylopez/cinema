package co.cinemas.ingeneo.servicios;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.cinemas.ingeneo.persistencia.entidad.ConfiguracionSistema;
import co.cinemas.ingeneo.persistencia.entidad.Sala;
import co.cinemas.ingeneo.persistencia.entidad.Sucursal;
import co.cinemas.ingeneo.persistencia.entidad.TipoSala;
import co.cinemas.ingeneo.persistencia.repositorio.IConfiguracionSistemaRepositorio;
import co.cinemas.ingeneo.persistencia.repositorio.ISalaRepositorio;
import co.cinemas.ingeneo.persistencia.repositorio.ISucursalRepositorio;
import co.cinemas.ingeneo.persistencia.repositorio.ITipoSalaRepositorio;

/**
 * Servicio que se encarga de realizar las operaciones CRUD para el objeto de
 * tipo sala
 * 
 * @author Roy López Cardona
 */
@Service
public class SalaService implements ISalaService {

	@Autowired
	private ISalaRepositorio repositorioSala;

	@Autowired
	private ISucursalRepositorio repositorioSucursal;

	@Autowired
	private ITipoSalaRepositorio repositorioTipoSala;

	@Autowired
	private IConfiguracionSistemaRepositorio repositorioConfiguracion;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Sala create(Sala sala) {
		return repositorioSala.save(sala);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Sala> findAll() {
		return repositorioSala.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Sala findById(Long id) {
		return repositorioSala.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Sucursal findSucursalById(Long id) {
		return repositorioSucursal.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public TipoSala findTipoSalaById(Long id) {
		return repositorioTipoSala.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ConfiguracionSistema findConfiguracionSistemaById(String id) {
		return repositorioConfiguracion.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String crearDisposicionSala(Integer filas, Integer sillasFila) {
		StringBuilder disposicion = new StringBuilder();

		IntStream.rangeClosed(1, filas).forEach(fila -> {
			IntStream.rangeClosed(1, sillasFila).forEach(sillaFila -> {
				disposicion.append("|");
				disposicion.append((char) (64 + fila));
				disposicion.append(sillaFila);
			});
			disposicion.append("\n");
		});

		return disposicion.toString();
	}

}
