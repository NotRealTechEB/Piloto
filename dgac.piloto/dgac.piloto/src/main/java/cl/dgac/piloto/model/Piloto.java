package cl.dgac.piloto.model;

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
@Table(name = "piloto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPiloto")
    private int idPiloto;

    @Column(name = "rutPiloto", nullable=false)
    private int rutPiloto;

    @Column(name = "rutEmpresa", nullable = false, length=12)
    private String rutEmpresa;

    @Column(name = "pNombre", nullable = false, length = 30)
    private String pNombrePiloto;

    @Column(name = "sNombre", length = 30)
    private String sNombrePiloto;

    @Column(name = "apPaterno", nullable = false, length = 30)
    private String apPaternoPiloto;

    @Column(name = "apMaterno", nullable = false, length = 30)
    private String apMaternoPiloto;

    

}
