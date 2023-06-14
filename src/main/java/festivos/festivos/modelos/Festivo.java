package festivos.festivos.modelos;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "festivo")
public class Festivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivo")
    @GenericGenerator(name = "secuencia_festivo", strategy = "increment")
    @Column(name = "Id")
    private long id;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "Dia")
    private Integer dia;

    @Column(name = "Mes")
    private Integer mes;

    @Column(name = "diaspascua")
    private Integer diaspascua;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    @JsonManagedReference
    private Tipo tipo;

    public Festivo() {
    }

    public Festivo(long id, String nombre, Integer dia, Integer mes, Integer diaspascua, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diaspascua = diaspascua;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDiaspascua() {
        return diaspascua;
    }

    public void setDiaspascua(Integer diaspascua) {
        this.diaspascua = diaspascua;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
