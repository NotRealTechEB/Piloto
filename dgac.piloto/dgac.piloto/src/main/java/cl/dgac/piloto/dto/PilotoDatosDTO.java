package cl.dgac.piloto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PilotoDatosDTO {
    private int idPiloto;
    private int rutPiloto;
    private String nombreCompleto;
}
