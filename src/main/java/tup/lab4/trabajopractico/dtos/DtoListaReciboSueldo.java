package tup.lab4.trabajopractico.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class DtoListaReciboSueldo {

    public Integer Legajo;
    public String Nombre;
    public int Año;
    public int Mes;
    public float SueldoBruto;
    public float ObraSocial;
    public float Jubilacion;
    public float FondoAltaComplejidad;
    public float MontoAntiguedad;
    public float SueldoNeto;

    public DtoListaReciboSueldo(Integer Legajo,String Nombre, int Año, int Mes, float SueldoBruto,
            float ObraSocial, float Jubilacion, float FondoAltaComplejidad,
            float MontoAntiguedad, float SueldoNeto) {
        
        this.Legajo = Legajo;
        this.Nombre = Nombre;
        this.Año = Año;
        this.Mes = Mes;
        this.SueldoBruto = SueldoBruto;
        this.ObraSocial = ObraSocial;
        this.Jubilacion = Jubilacion;
        this.FondoAltaComplejidad = FondoAltaComplejidad;
        this.MontoAntiguedad = MontoAntiguedad;
        this.SueldoNeto = SueldoNeto;
    }

    public DtoListaReciboSueldo() {
    }
}
