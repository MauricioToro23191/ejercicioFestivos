package festivos.festivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import festivos.festivos.modelos.Festivo;

@Repository
public interface FestivoRepositorio extends JpaRepository<Festivo, Long>{

}
