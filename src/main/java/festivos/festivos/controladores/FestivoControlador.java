package festivos.festivos.controladores;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import festivos.festivos.interfaces.IFestivoServicio;
import festivos.festivos.modelos.Festivo;
import festivos.festivos.modelos.FestivoResponse;

@RestController
@RequestMapping("/festivo")
public class FestivoControlador {
    @Autowired
    private IFestivoServicio servicio;
    //codigo tomado de https://www.techiedelight.com/es/validate-date-java/
    private static final String Date_REGEX =
                    "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|" +
                    "(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))" +
                    "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3" +
                    "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|" +
                    "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|" +
                    "^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|" +
                    "2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
 
    private static final Pattern Date_PATTERN = Pattern.compile(Date_REGEX);
 
    public static boolean dateValidator(String date)
    {
        Matcher matcher = Date_PATTERN.matcher(date);
        return matcher.matches();
    }
    //Fin de codigo copiado
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    @ResponseBody
    public List<Festivo> listar() {
        return servicio.listar();
    }

    @RequestMapping(value = "/listar/{anio}", method = RequestMethod.GET)
    public List<FestivoResponse> listaFestivosxaño(@PathVariable int anio) {
        return servicio.listaFestivosxaño(anio);
    }
    @RequestMapping(value = "/validar/{anio}/{mes}/{dia}", method = RequestMethod.GET)
    public String validadFechaDestivo(@PathVariable int anio,@PathVariable int mes,@PathVariable int dia) {
        //validamos si la fecha es valida antes de hacer peticiones o llamadas a los metodos 
        if(dateValidator(mes+"/"+dia+"/"+anio)){
            return servicio.validadFechaDestivo(anio,mes,dia);
        }

        return "La fecha es invalida el formato debe ser año/mes/dia";
        
        
    }
}


