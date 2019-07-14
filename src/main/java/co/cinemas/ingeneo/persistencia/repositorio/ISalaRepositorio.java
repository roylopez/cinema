package co.cinemas.ingeneo.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cinemas.ingeneo.persistencia.entidad.Sala;

public interface ISalaRepositorio extends JpaRepository<Sala, Long> {

}
