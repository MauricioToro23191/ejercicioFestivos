package festivos.festivos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.festivos.interfaces.ITipoServicio;
import festivos.festivos.modelos.Tipo;
import festivos.festivos.repositorios.TipoRepositorio;

@Service
public class TipoServicio  implements ITipoServicio{
    
    @Autowired
    TipoRepositorio repositorio;

    @Override
    public List<Tipo> listar() {
        return repositorio.findAll();
    }

}
