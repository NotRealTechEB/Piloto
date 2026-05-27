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
import cl.dgac.piloto.dto.PilotoDatosDTO;
import cl.dgac.piloto.dto.LicenciaResponseDTO;
import cl.dgac.piloto.dto.UpdatePilotoRequest;
import cl.dgac.piloto.mapper.PilotoMapper;
import cl.dgac.piloto.model.Piloto;
import cl.dgac.piloto.service.PilotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/dgac/pilotos")
public class PilotoController {
    
    private final PilotoService pilotoService;
    private final WebClient licenciaApiWebClient;

    public PilotoController(PilotoService pilotoService,WebClient licenciaApiWebClient){
        this.pilotoService = pilotoService;
        this.licenciaApiWebClient = licenciaApiWebClient;
    }

    //Obtener todos los pilotos

    @GetMapping
    public ResponseEntity<List<Piloto>> listarPilotos(){
        List<Piloto> pilotos = pilotoService.obtenerPilotos();
        return ResponseEntity.ok(pilotos);
    }
    
    //Obtener datos del piloto por su ID 

    @GetMapping("{idPiloto}")
    public ResponseEntity<?> datosPilotoID(@RequestParam("idPiloto") int idPiloto){
        PilotoDatosDTO pilotoDatos = pilotoService.datosPilotoId(idPiloto);
        if (pilotoDatos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + idPiloto + " no fue encontrada.");
        }
        return ResponseEntity.ok(pilotoDatos);
    }

    //Obtener piloto y el estado de su licencia
    
    @GetMapping("licencia/validar")
    public ResponseEntity<LicenciaResponseDTO> estadoLicenciaPiloto (@RequestParam("idPiloto") int idPiloto){
        LicenciaResponseDTO respuestaLicencia = pilotoService.consultarLicenciaPiloto(idPiloto);
        return ResponseEntity.ok(respuestaLicencia);
    }

    //Agregar nuevos pilotos

    @PostMapping
    public ResponseEntity<Piloto> agregarPilotos(@Valid @RequestBody CreatePilotoRequest request){
        Piloto piloto = pilotoService.agregarPiloto(PilotoMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(piloto);
    }

    //Actualizar datos de pilotos

    @PutMapping("{id}")
    public ResponseEntity<Piloto> actualizarPilotos(@PathVariable int idPiloto, @Valid @RequestBody UpdatePilotoRequest request){
        Piloto actuPiloto = pilotoService.actualizarPiloto(PilotoMapper.toModel(request));
        return ResponseEntity.ok(actuPiloto);
    }

    //Eliminar pilotos 

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarPiloto(@PathVariable int idPiloto){
        pilotoService.eliminarPiloto(idPiloto);
        return ResponseEntity.noContent().build();

    }

}
