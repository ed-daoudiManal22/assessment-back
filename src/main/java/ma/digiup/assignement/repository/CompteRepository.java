package ma.digiup.assignement.repository;

import ma.digiup.assignement.domain.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
  Compte findByNrCompte(String nrCompte);


  Optional <Compte>  findByRib(String rib);
}
