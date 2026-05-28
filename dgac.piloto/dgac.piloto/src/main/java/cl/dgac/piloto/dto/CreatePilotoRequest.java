package cl.dgac.piloto.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record CreatePilotoRequest(

    //Ingreso de nombre(s) y apellidos
    @NotBlank(message = "Debe ingresar el primer nombre del piloto") String pNombrePiloto,
    @Size(max = 30, message = "El segundo nombre no puede ser mayor a 30 caracteres")String sNombrePiloto,
    @NotBlank(message = "Debe ingresar el apellido paterno del piloto") String apPaternoPiloto,
    @NotBlank(message = "Debe ingresar el apellido materno del piloto") String apMaternoPiloto,

    //Ingreso de rut
    @Positive(message = "El rut no puede ser negativo o igual a cero") 
    @Digits(integer = 9, fraction = 0, message = "El rut no puede tener más de 9 dígitos")
    int rutPiloto
    )
    {
}
