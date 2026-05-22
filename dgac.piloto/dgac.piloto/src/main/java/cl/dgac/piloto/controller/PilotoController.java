package cl.dgac.piloto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.dgac.piloto.dto.CreatePilotoRequest;
import cl.dgac.piloto.dto.UpdatePilotoRequest;
import cl.dgac.piloto.mapper.PilotoMapper;
import cl.dgac.piloto.model.Piloto;
import cl.dgac.piloto.service.PilotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/dgac/pilotos")
public class PilotoController {
    
    private final PilotoService pilotoService;

    public PilotoController(PilotoService pilotoService){
        this.pilotoService = pilotoService;
    }

    @GetMapping
    public ResponseEntity<List<Piloto>> listarPilotos(){
        List<Piloto> pilotos = pilotoService.obtenerPilotos();
        return ResponseEntity.ok(pilotos);
    }

    @PostMapping
    public ResponseEntity<Piloto> agregarPilotos(@Valid @RequestBody CreatePilotoRequest request){
        Piloto piloto = pilotoService.agregarPiloto(PilotoMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(piloto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Piloto> actualizarPilotos(@PathVariable int idPiloto, @Valid @RequestBody UpdatePilotoRequest request){
        Piloto actuPiloto = pilotoService.actualizarPiloto(PilotoMapper.toModel(request));
        return ResponseEntity.ok(actuPiloto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarPiloto(@PathVariable int idPiloto){
        pilotoService.eliminarPiloto(idPiloto);
        return ResponseEntity.noContent().build();

    }
}
