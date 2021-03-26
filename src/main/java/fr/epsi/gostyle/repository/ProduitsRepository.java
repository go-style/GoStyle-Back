package fr.epsi.gostyle.repository;

import fr.epsi.gostyle.models.Produit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProduitsRepository extends CrudRepository<Produit, Integer> {

    Optional<Produit> findByNom(String nom);
}
