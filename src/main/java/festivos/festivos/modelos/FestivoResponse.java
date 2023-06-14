package festivos.festivos.modelos;

public class FestivoResponse {
 
    private String fecha;
    private String descripcion;
    private Tipo tipo;

    public FestivoResponse() {
    }
    

    public FestivoResponse(String fecha, String descripcion, Tipo tipo) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

   

}
