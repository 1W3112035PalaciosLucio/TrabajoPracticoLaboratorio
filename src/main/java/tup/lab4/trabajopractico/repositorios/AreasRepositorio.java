package tup.lab4.trabajopractico.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import tup.lab4.trabajopractico.dtos.DtoListaArea;
import tup.lab4.trabajopractico.dtos.ResultadoReporte;
import tup.lab4.trabajopractico.entidades.Area;

@Repository
public class AreasRepositorio {

    private Connection con;

    /*public ResultadoReporte consultarAreaPorDescripcion(String descripcion)  {
        String consult = "SELECT a.IdArea, a.Descripcion FROM areas a WHERE a.Descripcion = ?";
        Area a = null;
        ResultadoReporte resultado = new ResultadoReporte();
        try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Area();
                a.setDescripcion(rs.getString("Descripcion"));
                a.setIdArea(rs.getInt("IdArea"));
            }

            if (a == null) {
                resultado.codigo = 400;
                resultado.mensaje = "Area no encontrada";
                resultado.resultado = null;
            } else {
                resultado.codigo = 200;
                resultado.mensaje = "Area encontrada con exito";
                resultado.resultado = a;
            }

            ps.close();
            cnn.close();

        } catch (Exception e) {
            resultado.codigo = 500;
            resultado.mensaje = e.getMessage();
            resultado.resultado = null;
        }
        return resultado;
    }
     */
    public ResultadoReporte listaAreaPorDescripcion(String Descripcion) throws SQLException {

        List<DtoListaArea> lista = new ArrayList<>();

        {
            String consult = "SELECT a.IdArea, a.Descripcion 'Descripcion' FROM areas a WHERE a.Descripcion = ?";

            ResultadoReporte resultado = new ResultadoReporte();
            try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {
                ps.setString(1, Descripcion);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lista.add(new DtoListaArea(
                            rs.getString("Descripcion")
                    ));
                }

                resultado.codigo = 200;
                resultado.mensaje = lista.isEmpty() ? "No hay areas para esta descripcion!!" : "Area encontrada con exito!!";
                resultado.resultado = lista;

                ps.close();
                cnn.close();

            } catch (SQLException e) {
                resultado.codigo = 500;
                resultado.mensaje = e.getMessage();
                resultado.resultado = null;
            }
            return resultado;
        }
    }

}
