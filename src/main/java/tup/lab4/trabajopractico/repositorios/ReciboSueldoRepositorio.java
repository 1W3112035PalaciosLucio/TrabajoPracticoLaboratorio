package tup.lab4.trabajopractico.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import tup.lab4.trabajopractico.dtos.DtoAltaSueldo;
import tup.lab4.trabajopractico.dtos.DtoListaReciboSueldo;
import tup.lab4.trabajopractico.dtos.DtoListaSueldoNeto;
import tup.lab4.trabajopractico.dtos.DtoReciboLegajo;
import tup.lab4.trabajopractico.dtos.ResultadoReporte;
import tup.lab4.trabajopractico.entidades.Sueldo;

@Repository
public class ReciboSueldoRepositorio {

    private Connection con;

    //ALTA DE SUELDOS
    public ResultadoReporte CrearReciboSueldo(DtoAltaSueldo s) throws SQLException {
        ResultadoReporte result = new ResultadoReporte();

        if (s.getIdEmpleado() == null) {
            result.codigo = 400;
            result.mensaje = "El empleado no existe, el recibo no pudo cargarse";
            result.resultado = null;
            return result;
        }

        String consult = "INSERT INTO `recibosueldoslucio`.`sueldos`"
                + " (`IdEmpleado`, `Año`, `Mes`, `SueldoBruto`, `ObraSocial`, `Jubilacion`, `FondoAltaComplejidad`, `MontoAntiguedad`)"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");
            PreparedStatement ps = cnn.prepareStatement(consult);

            ps.setInt(1, s.getIdEmpleado());
            ps.setInt(2, s.getAño());
            ps.setInt(3, s.getMes());
            ps.setFloat(4, s.getSueldoBruto());
            ps.setFloat(5, s.getObraSocial());
            ps.setFloat(6, s.getJubilacion());
            ps.setFloat(7, s.getFondoAltaComplejidad());
            ps.setFloat(8, s.getMontoAntiguedad());
            result.codigo = 200;
            result.mensaje = "Recibo de sueldo creado con exito";

            ps.execute();
            ps.close();
            cnn.close();

        } catch (Exception ex) {

            result.codigo = 500;
            result.mensaje = ex.getMessage();
        }
        return result;
    }

    //LISTA DE SUELDOS
    public List<DtoListaReciboSueldo> getListaReciboSueldo() throws SQLException {
        List<DtoListaReciboSueldo> lista = new ArrayList<>();

        String consult = "SELECT e.Legajo 'LEGAJO', e.Nombre 'NOMBREEMPLEADO', s.Año 'AÑO', s.Mes 'MES',"
                + "s.SueldoBruto 'SUELDOBRUTO', s.ObraSocial 'OBRASOCIAL', s.Jubilacion 'JUBILACION',"
                + "s.FondoAltaComplejidad 'FONDOALTACOMPLEJIDAD', s.MontoAntiguedad 'MONTOANTIGUEDAD',"
                + "((s.SueldoBruto+s.MontoAntiguedad)-(s.ObraSocial+s.Jubilacion+s.FondoAltaComplejidad )) 'SUELDONETO'"
                + "FROM sueldos s join empleados e on s.IdEmpleado = e.IdEmpleado";

        DtoListaReciboSueldo recSueldo = null;
        try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new DtoListaReciboSueldo(
                        rs.getInt("LEGAJO"),
                        rs.getString("NOMBREEMPLEADO"),
                        rs.getInt("AÑO"),
                        rs.getInt("MES"),
                        rs.getFloat("SUELDOBRUTO"),
                        rs.getFloat("OBRASOCIAL"),
                        rs.getFloat("JUBILACION"),
                        rs.getFloat("FONDOALTACOMPLEJIDAD"),
                        rs.getFloat("MONTOANTIGUEDAD"),
                        rs.getFloat("SUELDONETO")
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

    //MOSTRAR RECIBOS POR LEGAJO EN UN PERIODO
    public ResultadoReporte consultarReciboLegajo(Integer legajo) throws SQLException {

        List<DtoReciboLegajo> lista = new ArrayList<>();

        {
            String consult = "SELECT e.Legajo 'Legajo', e.Nombre 'Nombre', s.Año 'Año', s.Mes 'Mes',"
                    + "                s.SueldoBruto 'SueldoBruto', s.ObraSocial 'ObraSocial', s.Jubilacion 'Jubilacion',"
                    + "                s.FondoAltaComplejidad 'FondoAltaComplejidad', s.MontoAntiguedad 'MontoAntiguedad',"
                    + "                ((s.SueldoBruto+s.MontoAntiguedad)-(s.ObraSocial+s.Jubilacion+s.FondoAltaComplejidad )) 'SUELDONETO'"
                    + "                FROM sueldos s JOIN empleados e ON s.IdEmpleado = e.IdEmpleado"
                    + "                WHERE e.Legajo = ? ";

            ResultadoReporte resultado = new ResultadoReporte();
            try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {
                ps.setInt(1, legajo);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lista.add(new DtoReciboLegajo(
                            rs.getInt("Legajo"),
                            rs.getString("Nombre"),
                            rs.getInt("Año"),
                            rs.getInt("Mes"),
                            rs.getFloat("SueldoBruto"),
                            rs.getFloat("ObraSocial"),
                            rs.getFloat("Jubilacion"),
                            rs.getFloat("FondoAltaComplejidad"),
                            rs.getFloat("MontoAntiguedad"),
                            rs.getFloat("SUELDONETO")
                    ));
                }

                resultado.codigo = 200;
                resultado.mensaje = lista.isEmpty() ? "No hay recibos de sueldo para este legajo!!" : "Recibo encontrado con exito!!";
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

    //MOSTRAR REPORTE CON DETALLE DE SUELDO NETO AGRUPADO POR AREA
    public ResultadoReporte consultarDetSNetoArea(Integer año, Integer mes) {
        List<DtoListaSueldoNeto> lista = new ArrayList<>();

        {
            String consult = "SELECT e.Nombre 'NOMBRE', a.Descripcion 'AREA', "
                    + "((s.SueldoBruto+s.MontoAntiguedad)-(s.ObraSocial+s.Jubilacion+s.FondoAltaComplejidad )) 'SUELDONETO', "
                    + "s.Año 'AÑO', s.Mes 'MES' "
                    + "FROM sueldos s JOIN empleados e ON s.IdEmpleado = e.IdEmpleado "
                    + "JOIN areas a ON a.IdArea = e.IdArea "
                    + "WHERE (AÑO = ?) AND (MES = ?) "
                    + "GROUP BY NOMBRE, AREA, SUELDONETO, AÑO, MES "
                    + "ORDER BY SUELDONETO";

            ResultadoReporte resultado = new ResultadoReporte();
            try ( Connection cnn = DriverManager.getConnection("jdbc:mysql://2022-api-tpi-iv-dev.mysql.database.azure.com/recibosueldoslucio", "springboot123", "UtnFrc2022");  PreparedStatement ps = cnn.prepareStatement(consult);) {
                ps.setInt(1, año);
                ps.setInt(2, mes);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lista.add(new DtoListaSueldoNeto(
                            rs.getString("NOMBRE"),
                            rs.getString("AREA"),
                            rs.getFloat("SUELDONETO"),
                            rs.getInt("AÑO"),
                            rs.getInt("MES")
                    ));
                }

                resultado.codigo = 200;
                resultado.mensaje = lista.isEmpty() ? "No hay recibos de sueldo para periodo!!" : "Recibo encontrado con exito!!";
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
