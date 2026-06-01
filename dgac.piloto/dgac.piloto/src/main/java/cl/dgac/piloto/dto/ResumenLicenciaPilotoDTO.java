package cl.dgac.piloto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResumenLicenciaPilotoDTO {

    private String rutPiloto;
    private String nombreCompleto;

    private String estVigencia;
}
