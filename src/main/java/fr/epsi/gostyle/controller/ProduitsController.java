package fr.epsi.gostyle.controller;

import fr.epsi.gostyle.models.Produit;
import fr.epsi.gostyle.repository.ProduitsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/go")
public class ProduitsController {

    private final ProduitsRepository produitsRepository;

    public ProduitsController(ProduitsRepository produitsRepository) {
        this.produitsRepository = produitsRepository;
    }

    @GetMapping("/produits")
    public Iterable<Produit> getProduits(){
        return produitsRepository.findAll();
    }

    @GetMapping("/produits/{id}")
    public Produit getProduitById(@PathVariable (value = "id") int id){
        return findById(id);
    }

    private Produit findById(int id) {
        return produitsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format(" '%s' non trouvé", id))
                );
    }



    @GetMapping("/produits/nom/{nom}")
    public Optional<Produit> getProduitByNom(@PathVariable (value = "nom") String nom){
        return produitsRepository.findByNom(nom);

    }

}
