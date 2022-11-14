package tup.lab4.trabajopractico.controllers;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tup.lab4.trabajopractico.dtos.DtoListaArea;
import tup.lab4.trabajopractico.repositorios.AreasRepositorio;

@RestController
@RequestMapping("/area")
public class AreasController {

    @Autowired
    private AreasRepositorio ar;

    @GetMapping("/listaArea/{Descripcion}")
    public ResponseEntity<DtoListaArea> getListaArea(@PathVariable String Descripcion) throws SQLException {
        var resultado = ar.listaAreaPorDescripcion(Descripcion);
        if (resultado.codigo == 200) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else if (resultado.codigo == 400) {
            return new ResponseEntity(resultado, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
