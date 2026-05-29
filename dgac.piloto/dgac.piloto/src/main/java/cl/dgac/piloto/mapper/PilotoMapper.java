package cl.dgac.piloto.mapper;

import cl.dgac.piloto.dto.CreatePilotoRequest;
import cl.dgac.piloto.dto.ResumenLicenciaPilotoDTO;
import cl.dgac.piloto.dto.UpdatePilotoRequest;
import cl.dgac.piloto.model.Piloto;

public class PilotoMapper {
    public static Piloto toModel(CreatePilotoRequest request) {
        return new Piloto(
            0, 
            request.rutPiloto(), request.rutEmp(), request.pNombrePiloto(), request.sNombrePiloto(), request.apPaternoPiloto(), request.apMaternoPiloto()
        );
        }

    public static Piloto toModel(UpdatePilotoRequest request) {
        return new Piloto(
            0, 
            request.rutPiloto(), request.rutEmp(), request.pNombrePiloto(), request.sNombrePiloto(), request.apPaternoPiloto(), request.apMaternoPiloto()
        );
        }

    public static ResumenLicenciaPilotoDTO toModel(Piloto piloto, ResumenLicenciaPilotoDTO licencia) {
        ResumenLicenciaPilotoDTO dto = new ResumenLicenciaPilotoDTO();
        dto.setIdPiloto(piloto.getIdPiloto());
        dto.setRutPiloto(piloto.getRutPiloto());

        String nombreCompleto = String.join(" ", 
            piloto.getPNombrePiloto(), piloto.getSNombrePiloto(), piloto.getApPaternoPiloto(), piloto.getApMaternoPiloto());
        dto.setNombreCompleto(nombreCompleto.trim().replaceAll("\\s+", " ")); 

        dto.setIdLicencia(licencia.getIdLicencia());
        dto.setEstVigencia(licencia.getEstVigencia());
        
        return dto;
    }
}    


