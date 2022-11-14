package tup.lab4.trabajopractico.dtos;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;


public class DtoListaEmpleado {

    public int Legajo;
    public String Nombre;
    public String Apellido;
    public Date FechaNacimiento;
    public Date FechaIngreso;
    public String Area;
    public float SueldoBruto;
    public int Antiguedad;

    public DtoListaEmpleado(int Legajo, String Nombre, String Apellido, Date FechaNacimiento, Date FechaIngreso, String Area, float SueldoBruto, int Antiguedad) {
        this.Legajo = Legajo;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.FechaIngreso = FechaIngreso;
        this.Area = Area;
        this.SueldoBruto = SueldoBruto;
        this.Antiguedad = Antiguedad;
    }
}
