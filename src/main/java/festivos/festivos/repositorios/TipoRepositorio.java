package festivos.festivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import festivos.festivos.modelos.Tipo;

@Repository
public interface TipoRepositorio extends JpaRepository<Tipo, Long>  {
   
}
