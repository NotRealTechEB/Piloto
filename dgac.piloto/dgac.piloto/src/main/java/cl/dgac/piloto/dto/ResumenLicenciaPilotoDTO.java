package cl.dgac.piloto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResumenLicenciaPilotoDTO {

    private String rutPiloto;
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    private String nombreEmpresa;
    private String estVigencia;
    
    
}
