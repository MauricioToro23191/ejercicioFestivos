package festivos.festivos.servicios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import festivos.festivos.interfaces.IFestivoServicio;
import festivos.festivos.modelos.Festivo;
import festivos.festivos.modelos.Tipo;
import festivos.festivos.modelos.FestivoResponse;
import festivos.festivos.repositorios.FestivoRepositorio;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    FestivoRepositorio repositorio;

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll();
    }

    @Override
    public String validadFechaDestivo(int anio,int mes,int dia){
        List<FestivoResponse> listafestivos=listaFestivosxaño(anio);
        String fecha=anio+"/"+mes+"/"+dia;
        for (FestivoResponse festivo:listafestivos){
            if(fecha.equals(festivo.getFecha())){
                return "Si es un festivo";
            }
        }
        return "No es un festivo";
    }

    @Override
    public List<FestivoResponse> listaFestivosxaño(int anio) {
        //primero calculamos el dia de pascua segun el año para usarlo en el resto de la funcion
        LocalDate pascua = diapascua(anio);
        //inicializacion de arreglos
        List<Festivo> listafestivos = this.listar();
        List<FestivoResponse> listaResponse = new ArrayList<FestivoResponse>();
        //fecha devuelta como String se procesa en las condiciones y se agrega a el objeto de la clase FestivoResponse
        String fecha = new String();
        for (Festivo festivo : listafestivos) {
            //definicion de objeto que tendra los datos que se enviaran terminar la peticion de la api
            FestivoResponse res = new FestivoResponse();
            Tipo tipo = festivo.getTipo();
            //si el festico es fijo le dara formato como String y seguira con la siguiente fecha
            if (((Long) tipo.getId()).intValue() == 1) {
                fecha = (anio + "/" + festivo.getMes() + "/" + festivo.getDia());
            } else if (((Long) tipo.getId()).intValue() == 2) {
                //si el festivo es de puente festivo, calcule la fecha del lunes y le damos el mismo formato al string 
                LocalDate festivoactual = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                LocalDate siguiente = obtenerSiguienteLunes(festivoactual);
                fecha = siguiente.getYear() + "/" + siguiente.getMonthValue()
                        + "/" + siguiente.getDayOfMonth();
            } else if (((Long) tipo.getId()).intValue() == 3) {
                // si el festivo es calculado con el lines de pascual, restele o sumele los dias
                LocalDate calculadopascua=pascua.plusDays(festivo.getDiaspascua());
                //dar formato 
                fecha=calculadopascua.getYear() + "/" + calculadopascua.getMonthValue()
                        + "/" + calculadopascua.getDayOfMonth();
            } else if (((Long) tipo.getId()).intValue() == 4) {
                //si el festivo se calcula y cumple la ley de los puentes festivos debe hacer las 3 operaciones en un orden logico
                LocalDate calculadopascua=pascua.plusDays(festivo.getDiaspascua());
                LocalDate siguiente = obtenerSiguienteLunes(calculadopascua);
                fecha = siguiente.getYear() + "/" + siguiente.getMonthValue()
                        + "/" + siguiente.getDayOfMonth();
            }
            //se asignan los valores obtenidos en la iteracion 
            res.setFecha(fecha);
            res.setDescripcion(festivo.getNombre());
            res.setTipo(tipo);
            //se agregann los valores a la lista para enviarlos como respuesta a ala peticion de la api
            listaResponse.add(res);
        }
        return listaResponse;
    }

    private LocalDate obtenerSiguienteLunes(LocalDate fecha) {
        LocalDate siguienteLunes = fecha;
        // Obtener el día de la semana de la fecha actual
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        // Calcular la cantidad de días hasta el siguiente lunes (día 1 de la semana)
        int diasHastaLunes = (DayOfWeek.MONDAY.getValue()) - (diaSemana.getValue());
        if (diasHastaLunes <= 0) {

            diasHastaLunes += 7;
        }
        // Agregar la cantidad de días hasta el siguiente lunes
        siguienteLunes = siguienteLunes.plusDays(diasHastaLunes);
        return siguienteLunes;
    }

    private LocalDate diapascua(int anio) {
        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + ((2 *b) + (4 * c) + (6 * d) + 5) % 7;
        //se le suman 15 dias para obtener el domingo de ramos
        dias+=15;
        //Sele suman 7 días para obtener el domingo de pascua
        dias+=7;
        if (dias > 31) {
            //si los dias es mayor a 31 pascua cae en el mes de abril
            return LocalDate.of(anio, 4, (dias - 31));
        } else if(dias<=31) {
            //si es menos a 31 cae en el mes de mayo
            return LocalDate.of(anio, 3, dias);
        }
        return null;
    }

}
