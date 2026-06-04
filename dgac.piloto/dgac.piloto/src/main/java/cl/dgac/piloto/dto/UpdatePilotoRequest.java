package cl.dgac.piloto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePilotoRequest(

    //Ingreso de nombre(s) y apellidos
    @NotNull(message = "Debe ingresar el primer nombre del piloto") String pNombrePiloto,
    @Size(max = 30, message = "El segundo nombre no puede ser mayor a 30 caracteres")String sNombrePiloto,
    @NotNull(message = "Debe ingresar el apellido paterno del piloto") String apPaternoPiloto,
    @NotNull(message = "Debe ingresar el apellido materno del piloto") String apMaternoPiloto,

    //Ingreso de rut
    @NotNull(message = "El rut no puede ser negativo o igual a cero") 
    @Size(max=12, message = "El rut no puede tener más de 9 dígitos")
    String rutPiloto,

    //Ingreso de nombre empresa
    @NotNull(message = "Debe ingresar el nombre de la empresa")
    @Size(max=100, message = "El nombre no puede tener más de 100 caracteres") String nombreEmp
    )
    {
}
