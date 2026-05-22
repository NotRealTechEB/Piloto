package cl.dgac.piloto.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "licenciaPiloto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenciaPiloto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idLicencia")
    private int idLicencia;

    @Column(name="fechaRegistro", nullable=false)
    private LocalDateTime fechaRegistro;

    @Column(name = "alturaMaxima", nullable=false)
    private double alturaMaxima;
}
