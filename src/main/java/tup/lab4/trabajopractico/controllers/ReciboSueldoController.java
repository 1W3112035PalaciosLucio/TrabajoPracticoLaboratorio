package tup.lab4.trabajopractico.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tup.lab4.trabajopractico.dtos.DtoAltaSueldo;
import tup.lab4.trabajopractico.dtos.DtoListaReciboSueldo;
import tup.lab4.trabajopractico.dtos.DtoListaSueldoNeto;
import tup.lab4.trabajopractico.dtos.ResultadoReporte;
import tup.lab4.trabajopractico.entidades.Sueldo;
import tup.lab4.trabajopractico.repositorios.ReciboSueldoRepositorio;

@RestController
@RequestMapping("/sueldo")
public class ReciboSueldoController {

    @Autowired
    private ReciboSueldoRepositorio rsr;

    @PostMapping("/agregarRecibo")
    public ResponseEntity<ResultadoReporte> registrarEmpleado(@RequestBody DtoAltaSueldo s) throws SQLException {

        var resultado = rsr.CrearReciboSueldo(s);

        if (resultado.codigo == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        }
        return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/listaRecibo")
    public ResponseEntity<List<DtoListaReciboSueldo>> getListaReciboSueldo() {
        try {
            return ResponseEntity.ok(rsr.getListaReciboSueldo());
        } catch (SQLException ex) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/listaLegajoPeriodo/{legajo}")
    public ResponseEntity<DtoListaReciboSueldo> getListaLegajoPeriodo(@PathVariable Integer legajo) throws SQLException {
        var resultado = rsr.consultarReciboLegajo(legajo);
        if (resultado.codigo == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else if (resultado.codigo == 400) {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listaListaSueldoNuevo/{año}/{mes}")
    public ResponseEntity<DtoListaSueldoNeto> getListaSueldoNeto(@PathVariable Integer año, @PathVariable Integer mes) throws SQLException {
        var resultado = rsr.consultarDetSNetoArea(año, mes);
        if (resultado.codigo == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else if (resultado.codigo == 400) {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
