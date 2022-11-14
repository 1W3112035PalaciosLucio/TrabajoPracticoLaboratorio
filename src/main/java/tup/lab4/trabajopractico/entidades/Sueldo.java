package tup.lab4.trabajopractico.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sueldo {

    public int IdSueldo;
    public Integer IdEmpleado;
    public int Año;
    public int Mes;
    public float SueldoBruto;
    public float ObraSocial;
    public float Jubilacion;
    public float FondoAltaComplejidad;
    public float MontoAntiguedad;

    public Sueldo(int IdSueldo, Integer IdEmpleado, int Año, int Mes, float SueldoBruto, float ObraSocial, float Jubilacion, float FondoAltaComplejidad, float MontoAntiguedad) {
        this.IdSueldo = IdSueldo;
        this.IdEmpleado = IdEmpleado;
        this.Año = Año;
        this.Mes = Mes;
        this.SueldoBruto = SueldoBruto;
        this.ObraSocial = ObraSocial;
        this.Jubilacion = Jubilacion;
        this.FondoAltaComplejidad = FondoAltaComplejidad;
        this.MontoAntiguedad = MontoAntiguedad;
    }

    public Sueldo() {
    }
}
