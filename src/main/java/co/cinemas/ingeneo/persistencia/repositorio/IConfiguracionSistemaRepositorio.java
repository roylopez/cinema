package co.cinemas.ingeneo.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cinemas.ingeneo.persistencia.entidad.ConfiguracionSistema;

public interface IConfiguracionSistemaRepositorio extends JpaRepository<ConfiguracionSistema, String> {

}
