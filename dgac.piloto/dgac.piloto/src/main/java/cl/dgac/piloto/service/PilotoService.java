package cl.dgac.piloto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dgac.piloto.dto.PilotoDatosDTO;
import cl.dgac.piloto.model.Piloto;
import cl.dgac.piloto.repository.PilotoRepository;

@Service
public class PilotoService {
    @Autowired

    private PilotoRepository pilotoRepository;


    //Opciones de obtener pilotos registrados//

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

    public Piloto actualizarPiloto(Piloto piloto){
        return pilotoRepository.save(piloto);
    }

    //Eliminar pilotos de la lista
    
    public String eliminarPiloto(int idPiloto){
        pilotoRepository.deleteById(idPiloto);
        return "Piloto eliminado de la lista";
    }

    //Mostrar datos del piloto DTO

    public PilotoDatosDTO datosPilotoId (int idPiloto){
        Piloto piloto = pilotoRepository.findById(idPiloto).orElse(null);
        if (piloto == null) {
            return null; 
        }

        PilotoDatosDTO dataPiloto = new PilotoDatosDTO();
        dataPiloto.setIdPiloto(piloto.getIdPiloto());
        dataPiloto.setRutPiloto(piloto.getRutPiloto());

        String pNombre = (piloto.getPNombrePiloto() != null) ? piloto.getPNombrePiloto() : "";
        String sNombre = (piloto.getSNombrePiloto() != null) ? piloto.getSNombrePiloto() : "";
        String apPaterno = (piloto.getApPaternoPiloto() != null) ? piloto.getApPaternoPiloto() : "";
        String apMaterno = (piloto.getApMaternoPiloto() != null) ? piloto.getApMaternoPiloto() : "";
        String nombreCompleto = (pNombre + " " + sNombre + " " + apPaterno + " " + apMaterno).replaceAll("\\s+", " ").trim();                

        dataPiloto.setNombreCompleto(nombreCompleto);
        
        return dataPiloto;
    }

}
