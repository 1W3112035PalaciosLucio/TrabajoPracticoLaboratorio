package tup.lab4.trabajopractico.dtos;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class DtoAltaEmpleado {

    public int Legajo;
    public String Nombre;
    public String Apellido;
    public Date FechaNacimiento;
    public Date FechaIngreso;
    public int IdArea;
    public float SueldoBruto;

    public DtoAltaEmpleado(@RequestParam int Legajo,
            @RequestParam String Nombre,
            @RequestParam String Apellido,
            @RequestParam Date FechaNacimiento,
            @RequestParam Date FechaIngreso,
            @RequestParam int IdArea,
            @RequestParam float SueldoBruto) {
        this.Legajo = Legajo;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.FechaIngreso = FechaIngreso;
        this.IdArea = IdArea;
        this.SueldoBruto = SueldoBruto;
    }

}
