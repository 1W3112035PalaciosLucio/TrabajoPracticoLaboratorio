package tup.lab4.trabajopractico.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import tup.lab4.trabajopractico.dtos.DtoListaEmpleado;
import tup.lab4.trabajopractico.dtos.ResultadoReporte;
import tup.lab4.trabajopractico.entidades.Empleado;

@Repository
public class EmpleadoRepositorio {

    private Connection con;

    //LISTADO DE EMPLEADOS
    public List<DtoListaEmpleado> getListaEmpleados() throws SQLException {
        List<DtoListaEmpleado> lista = new ArrayList<>();

        String consult = "SELECT e.Legajo 'LEGAJO', e.Nombre 'NOMBRE' , e.Apellido 'APELLIDO' , e.FechaNacimiento 'FECHANACIMIENTO'"
                + ", e.FechaIngreso 'FECHAINGRESO' , e.SueldoBruto 'SUELDOBRUTO', a.Descripcion 'DESCRIPCION',"
                + "TIMESTAMPDIFF(YEAR,e.FechaIngreso,CURDATE()) 'ANTIGUEDAD' "
                + " FROM empleados e JOIN areas a ON a.IdArea = e.IdArea";

        DtoListaEmpleado em = null;
        try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new DtoListaEmpleado(
                        rs.getInt("LEGAJO"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getDate("FECHANACIMIENTO"),
                        rs.getDate("FECHAINGRESO"),
                        rs.getString("DESCRIPCION"),
                        rs.getFloat(6),
                        rs.getInt("ANTIGUEDAD")
                ));
            }

            rs.close();
            ps.close();
            cnn.close();
            return lista;

        } catch (SQLException ex) {
            throw ex;
        }

    }

    //ALTA DE EMPLEADO
    public ResultadoReporte CrearEmpleado(Empleado e) throws SQLException {
        ResultadoReporte result = new ResultadoReporte();
        String consult = "INSERT INTO `recibosueldoslucio`.`empleados` (`Legajo`, `Nombre`, `Apellido`, `FechaNacimiento`, `FechaIngreso`, `IdArea`, `SueldoBruto`) "
                + "VALUES (?, ?, ?, ?, ?, ?,?); ";

        try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {

            ps.setInt(1, e.getLegajo());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setDate(4, (Date) e.getFechaNacimiento());
            ps.setDate(5, (Date) e.getFechaIngreso());
            ps.setInt(6, e.getIdArea());
            ps.setFloat(7, e.getSueldoBruto());
            ps.execute();

            result.codigo = 200;
            result.mensaje = "Empleado creado con exito";

            ps.close();
            cnn.close();

        } catch (SQLException ex) {

            result.codigo = 400;
            result.mensaje = "Hubo un error al cargar el empleado";
        }
        return result;
    }
}
