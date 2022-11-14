package tup.lab4.trabajopractico.dtos;

import lombok.Getter;
import lombok.Setter;


public class DtoAltaSueldo {

    public Integer IdEmpleado;
    public int Año;
    public int Mes;
    public float SueldoBruto;
    public float ObraSocial;
    public float Jubilacion;
    public float FondoAltaComplejidad;
    public float MontoAntiguedad;

    public Integer getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(Integer IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public int getMes() {
        return Mes;
    }

    public void setMes(int Mes) {
        this.Mes = Mes;
    }

    public float getSueldoBruto() {
        return SueldoBruto;
    }

    public void setSueldoBruto(float SueldoBruto) {
        this.SueldoBruto = SueldoBruto;
    }

    public float getObraSocial() {
        return ObraSocial;
    }

    public void setObraSocial(float ObraSocial) {
        this.ObraSocial = ObraSocial;
    }

    public float getJubilacion() {
        return Jubilacion;
    }

    public void setJubilacion(float Jubilacion) {
        this.Jubilacion = Jubilacion;
    }

    public float getFondoAltaComplejidad() {
        return FondoAltaComplejidad;
    }

    public void setFondoAltaComplejidad(float FondoAltaComplejidad) {
        this.FondoAltaComplejidad = FondoAltaComplejidad;
    }

    public float getMontoAntiguedad() {
        return MontoAntiguedad;
    }

    public void setMontoAntiguedad(float MontoAntiguedad) {
        this.MontoAntiguedad = MontoAntiguedad;
    }

    public DtoAltaSueldo(Integer IdEmpleado, int Año, int Mes, float SueldoBruto, float ObraSocial, float Jubilacion, float FondoAltaComplejidad, float MontoAntiguedad) {
        this.IdEmpleado = IdEmpleado;
        this.Año = Año;
        this.Mes = Mes;
        this.SueldoBruto = SueldoBruto;
        this.ObraSocial = ObraSocial;
        this.Jubilacion = Jubilacion;
        this.FondoAltaComplejidad = FondoAltaComplejidad;
        this.MontoAntiguedad = MontoAntiguedad;
    }
}
