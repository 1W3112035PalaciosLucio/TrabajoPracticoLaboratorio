package tup.lab4.trabajopractico.entidades;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Area {

    public int IdArea;
    public String Descripcion;

    public Area() {
    }

    public Area(int IdArea, String Descripcion) {
        this.IdArea = IdArea;
        this.Descripcion = Descripcion;
    }
}
