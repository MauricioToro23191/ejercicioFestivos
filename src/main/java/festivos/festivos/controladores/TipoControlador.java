package festivos.festivos.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import festivos.festivos.interfaces.ITipoServicio;
import festivos.festivos.modelos.Tipo;

@RestController
@RequestMapping("/tipo")
public class TipoControlador {
    @Autowired
    private ITipoServicio servicio;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Tipo> listar() {
        return servicio.listar();
    }

}
