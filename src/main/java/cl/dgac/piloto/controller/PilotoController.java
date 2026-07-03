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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
        summary = "Presenta pilotos inscritos",
        description= "Muestra todos los pilotos registrados, no se usan filtros"
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Lista de pilotos",
                value = "[{\"rutPiloto\": \"12345678-9\", \"nombreEmpresa\": \"Aerolinea Altos Cielos\", \"pNombrePiloto\": \"Juan\", \"sNombrePiloto\": \"Carlos\", \"apPaternoPiloto\": \"Pérez\", \"apMaternoPiloto\": \"Gómez\"}]"
            )
        ) 
    )
    @GetMapping
    public ResponseEntity<List<Piloto>> listarPilotos(){
        List<Piloto> pilotos = pilotoService.obtenerPilotos();
        return ResponseEntity.ok(pilotos);
    }

    //Agregar nuevos pilotos

    @Operation(
        summary = "Agregar nuevos pilotos",
        description= "Permite guardar datos de nuevos pilotos al sistema"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos necesarios para añadir un nuevo piloto al sistema",
        required = true,
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Ejemplo de agregar piloto",
                value = "{\"idPiloto\": 1, \"rutPiloto\": \"12345678-9\", \"nombreEmpresa\": \"Aerolinea Altos Cielos\", \"pNombrePiloto\": \"Juan\", \"sNombrePiloto\": \"Carlos\", \"apPaternoPiloto\": \"Pérez\", \"apMaternoPiloto\": \"Gómez\"}"
            )
        ))
    @ApiResponse(
        responseCode = "201",
        description = "CREATED",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Piloto agregado",
                value = "{\"idPiloto\": 1, \"rutPiloto\": \"12345678-9\", \"nombreEmpresa\": \"Aerolinea Altos Cielos\", \"pNombrePiloto\": \"Juan\", \"sNombrePiloto\": \"Carlos\", \"apPaternoPiloto\": \"Pérez\", \"apMaternoPiloto\": \"Gómez\"}"
            )
        ) 
    )
    @PostMapping
    public ResponseEntity<Piloto> agregarPilotos(@Valid @RequestBody CreatePilotoRequest request){
        Piloto piloto = pilotoService.agregarPiloto(PilotoMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(piloto);
    }

    //Actualizar datos de pilotos (Parcial)

    @Operation(
        summary = "Actualizar pilotos",
        description= "Permite actualizar datos de los pilotos registrados en el sistema"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos necesarios para actualizar datos del piloto ",
        required = true,
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Ejemplo de actualizar piloto",
                value = "{\"idPiloto\": 1, \"rutPiloto\": \"12345678-9\", \"nombreEmpresa\": \"Aerolinea Altos Cielos\", \"pNombrePiloto\": \"Juan\", \"sNombrePiloto\": \"Carlos\", \"apPaternoPiloto\": \"Pérez\", \"apMaternoPiloto\": \"Gómez\"}"
            )
        ))
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Piloto actualizado",
                value = "{\"idPiloto\": 1, \"rutPiloto\": \"12345678-9\", \"nombreEmpresa\": \"Aerolinea Altos Cielos\", \"pNombrePiloto\": \"Juan\", \"sNombrePiloto\": \"Carlos\", \"apPaternoPiloto\": \"Pérez\", \"apMaternoPiloto\": \"Gómez\"}"
            )
        ) 
    )
    @PutMapping("/{idPiloto}")
    public ResponseEntity<Piloto> actualizarPilotos(@PathVariable("idPiloto") int idPiloto, @Valid @RequestBody UpdatePilotoRequest request){
        Piloto actuPiloto = pilotoService.actualizarPiloto(idPiloto, request);
        return ResponseEntity.ok(actuPiloto);
    }

    //Eliminar pilotos 

    @Operation(
        summary = "Eliminar piloto",
        description= "Permite eliminar datos de piloto"
    )
    @ApiResponse(
        responseCode = "204",
        description = "No Content - Piloto eliminado con éxito"
        ) 
    @DeleteMapping("/{idPiloto}")
    public ResponseEntity<Void> eliminarPiloto(@PathVariable("idPiloto") int idPiloto){
        pilotoService.eliminarPiloto(idPiloto);
        return ResponseEntity.noContent().build();

    }

    //-------------------------------Metodos HU - Piloto-------------------------------//

    //Obtener piloto y el estado de su licencia

    @Operation(
        summary = "Mostrar datos piloto",
        description= "Permite ver los datos de los pilotos al introducir su rut"
    )
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Lista de pilotos",
                value = "[{\"idPiloto\": 1, \"rutPiloto\": \"12345678-9\", \"nombreEmpresa\": \"Aerolinea Altos Cielos\", \"pNombrePiloto\": \"Juan\", \"sNombrePiloto\": \"Carlos\", \"apPaternoPiloto\": \"Pérez\", \"apMaternoPiloto\": \"Gómez\"}]"
            )
        ) 
    )
    @GetMapping("/datos-piloto")
    public ResponseEntity<PilotoDTO> datosPiloto(@RequestParam("rut") String rutPiloto){
        PilotoDTO datosPiloto = pilotoService.consultarResumen(rutPiloto);
        return ResponseEntity.ok(datosPiloto);
    }
}
