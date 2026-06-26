package cl.dgac.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dgac.piloto.model.Piloto;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Integer>{
    List<Piloto> findByRutPiloto(String rutPiloto);
}
