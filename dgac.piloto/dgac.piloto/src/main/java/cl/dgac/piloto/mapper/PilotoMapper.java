package cl.dgac.piloto.mapper;

import cl.dgac.piloto.dto.CreatePilotoRequest;
import cl.dgac.piloto.dto.UpdatePilotoRequest;
import cl.dgac.piloto.model.Piloto;

public class PilotoMapper {
    public static Piloto toModel(CreatePilotoRequest request) {
        return new Piloto(
            0, 
            request.rutPiloto(), request.pNombrePiloto(), request.sNombrePiloto(), request.apPaternoPiloto(), request.apMaternoPiloto()
        );
        }

    public static Piloto toModel(UpdatePilotoRequest request) {
        return new Piloto(
            0, 
            request.rutPiloto(), request.pNombrePiloto(), request.sNombrePiloto(), request.apPaternoPiloto(), request.apMaternoPiloto()
        );
        }
}

