package tup.lab4.trabajopractico.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tup.lab4.trabajopractico.dtos.DtoAltaEmpleado;
import tup.lab4.trabajopractico.dtos.DtoListaEmpleado;
import tup.lab4.trabajopractico.dtos.ResultadoReporte;
import tup.lab4.trabajopractico.entidades.Empleado;
import tup.lab4.trabajopractico.repositorios.EmpleadoRepositorio;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepositorio emp;

    @GetMapping("/lista")
    public ResponseEntity<List<DtoListaEmpleado>> getLista() {
        try {
            return ResponseEntity.ok(emp.getListaEmpleados());
        } catch (SQLException ex) {
            return ResponseEntity.internalServerError().body(null);
        }

    }

    @PostMapping("/agregar")
    public ResponseEntity<ResultadoReporte> registrarEmpleado(@RequestBody Empleado e) throws SQLException {

        var resultado = emp.CrearEmpleado(e);
        if (resultado.codigo == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        }
        return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
    }
}
