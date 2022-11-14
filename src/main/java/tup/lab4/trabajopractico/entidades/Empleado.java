package tup.lab4.trabajopractico.entidades;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Empleado {

    public int IdEmpleado;
    public int Legajo;
    public String Nombre;
    public String Apellido;
    public Date FechaNacimiento;
    public Date FechaIngreso;
    public int IdArea;
    public float SueldoBruto;

    public Empleado() {
    }

    public Empleado(int IdEmpleado, int Legajo, String Nombre, String Apellido,
            Date FechaNacimiento, Date FechaIngreso, int IdArea, float SueldoBruto) {
        this.IdEmpleado = IdEmpleado;
        this.Legajo = Legajo;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.FechaIngreso = FechaIngreso;
        this.IdArea = IdArea;
        this.SueldoBruto = SueldoBruto;
    }
}
