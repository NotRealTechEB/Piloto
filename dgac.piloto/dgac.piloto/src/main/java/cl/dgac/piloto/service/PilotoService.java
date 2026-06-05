package cl.dgac.piloto.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.dgac.piloto.dto.PilotoDTO;
import cl.dgac.piloto.dto.UpdatePilotoRequest;
import cl.dgac.piloto.exception.ResourceNotFoundException;
import cl.dgac.piloto.mapper.PilotoMapper;
import cl.dgac.piloto.model.Piloto;
import cl.dgac.piloto.repository.PilotoRepository;

@Service
public class PilotoService {

    private PilotoRepository pilotoRepository;
    private WebClient licenciaApiWebClient;

    public PilotoService(PilotoRepository pilotoRepository, WebClient licenciaApiWebClient) {
        this.pilotoRepository = pilotoRepository;
        this.licenciaApiWebClient = licenciaApiWebClient;
    }

    //-------------------------------Metodos de administracion-------------------------------//

    //Opciones de obtener pilotos registrados

        //Todos los pilotos registrados
    public List<Piloto> obtenerPilotos(){
        return pilotoRepository.findAll();
    }
        //Hallar por ID
    public Piloto obtenerPilotoId(int idPiloto){
        return pilotoRepository.findById(idPiloto).orElse(null);
    }

    //Agregar pilotos

    public Piloto agregarPiloto(Piloto piloto){
        return pilotoRepository.save(piloto);
    }

    //Actualizar datos de los pilotos

    public Piloto actualizarPiloto(int idPiloto, UpdatePilotoRequest update){
        Piloto pilotoExistente = pilotoRepository.findById(idPiloto).orElseThrow(() -> new ResourceNotFoundException("Piloto no encontrado"));
        pilotoExistente.setPNombrePiloto(update.pNombrePiloto());
        pilotoExistente.setSNombrePiloto(update.sNombrePiloto());
        pilotoExistente.setApPaternoPiloto(update.apPaternoPiloto());
        pilotoExistente.setApMaternoPiloto(update.apMaternoPiloto());
        return pilotoRepository.save(pilotoExistente);
    }

    //Eliminar pilotos de la lista
    
    public String eliminarPiloto(int idPiloto){
        pilotoRepository.deleteById(idPiloto);
        return "Piloto eliminado de la lista";
    }


    //-------------------------------Metodos HU - Piloto-------------------------------//

    public PilotoDTO consultarResumen(String rutPiloto) {
    try {
        List<Piloto> pilotoLocal = pilotoRepository.findByRutPiloto(rutPiloto);

        if (pilotoLocal != null && !pilotoLocal.isEmpty()) { 
            Piloto piloto = pilotoLocal.get(0); 
            return PilotoMapper.toModel(piloto); 
        }
    } catch (Exception ex) {
        System.out.println("Error al encontrar piloto con ese rut: " + ex.getMessage());
    }

    return new PilotoDTO();
}
}
