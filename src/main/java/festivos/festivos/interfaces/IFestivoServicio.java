package festivos.festivos.interfaces;

import java.util.List;


import festivos.festivos.modelos.Festivo;
import festivos.festivos.modelos.FestivoResponse;

public interface IFestivoServicio {
    
    public List<Festivo> listar();

    public List<FestivoResponse> listaFestivosxaño(int anio);
    
    public String validadFechaDestivo(int anio,int mes,int dia);
    
}
