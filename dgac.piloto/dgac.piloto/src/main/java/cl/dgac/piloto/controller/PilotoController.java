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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.piloto.dto.CreatePilotoRequest;
import cl.dgac.piloto.dto.PilotoDTO;
import cl.dgac.piloto.dto.UpdatePilotoRequest;
import cl.dgac.piloto.mapper.PilotoMapper;
import cl.dgac.piloto.model.Piloto;
import cl.dgac.piloto.service.PilotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pilotos")
public class PilotoController {
    
    private final PilotoService pilotoService;
    private final WebClient licenciaApiWebClient;

    public PilotoController(PilotoService pilotoService,WebClient licenciaApiWebClient){
        this.pilotoService = pilotoService;
        this.licenciaApiWebClient = licenciaApiWebClient;
    }

    //-------------------------------Metodos de administracion-------------------------------//

    //Obtener todos los pilotos

    @GetMapping
    public ResponseEntity<List<Piloto>> listarPilotos(){
        List<Piloto> pilotos = pilotoService.obtenerPilotos();
        return ResponseEntity.ok(pilotos);
    }

    //Agregar nuevos pilotos

    @PostMapping
    public ResponseEntity<Piloto> agregarPilotos(@Valid @RequestBody CreatePilotoRequest request){
        Piloto piloto = pilotoService.agregarPiloto(PilotoMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(piloto);
    }

    //Actualizar datos de pilotos (Parcial)

    @PutMapping("/{idPiloto}")
    public ResponseEntity<Piloto> actualizarPilotos(@PathVariable("idPiloto") int idPiloto, @Valid @RequestBody UpdatePilotoRequest request){
        Piloto actuPiloto = pilotoService.actualizarPiloto(idPiloto, request);
        return ResponseEntity.ok(actuPiloto);
    }

    //Eliminar pilotos 

    @DeleteMapping("/{idPiloto}")
    public ResponseEntity<Void> eliminarPiloto(@PathVariable("idPiloto") int idPiloto){
        pilotoService.eliminarPiloto(idPiloto);
        return ResponseEntity.noContent().build();

    }

    //-------------------------------Metodos HU - Piloto-------------------------------//

    //Obtener piloto y el estado de su licencia

    @GetMapping("/datos-piloto")
    public ResponseEntity<PilotoDTO> datosPiloto(@RequestParam("rut") String rutPiloto){
        PilotoDTO datosPiloto = pilotoService.consultarResumen(rutPiloto);
        return ResponseEntity.ok(datosPiloto);
    }
}
