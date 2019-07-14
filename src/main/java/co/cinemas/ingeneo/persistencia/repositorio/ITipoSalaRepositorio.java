package co.cinemas.ingeneo.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cinemas.ingeneo.persistencia.entidad.TipoSala;

public interface ITipoSalaRepositorio extends JpaRepository<TipoSala, Long> {

}
