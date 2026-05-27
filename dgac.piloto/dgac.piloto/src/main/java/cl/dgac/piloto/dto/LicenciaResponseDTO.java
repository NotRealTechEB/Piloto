package cl.dgac.piloto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenciaResponseDTO {
    private int idPiloto;
    private boolean estValidacion;
    private String anotacion;
}
