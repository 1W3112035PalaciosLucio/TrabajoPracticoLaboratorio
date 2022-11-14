package tup.lab4.trabajopractico.dtos;

import lombok.Getter;
import lombok.Setter;


public class DtoListaSueldoNeto {

    public String Nombre;
    public String Area;
    public float SueldoNeto;
    public Integer Año;
    public Integer Mes;

    public DtoListaSueldoNeto(String Nombre, String Area, float SueldoNeto, Integer Año, Integer Mes) {
        this.Nombre = Nombre;
        this.Area = Area;
        this.SueldoNeto = SueldoNeto;
        this.Año = Año;
        this.Mes = Mes;
    }

}
