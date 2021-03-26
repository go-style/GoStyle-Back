package fr.epsi.gostyle.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produit {

  @Id
  private long id;
  private String nom;
  private String description;
  private double prix;
  private String photourl;

  public Produit() {
  }

  public Produit(long id, String nom, String description, double prix, String photourl) {
    this.id = id;
    this.nom = nom;
    this.description = description;
    this.prix = prix;
    this.photourl = photourl;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public double getPrix() {
    return prix;
  }

  public void setPrix(double prix) {
    this.prix = prix;
  }


  public String getPhotourl() {
    return photourl;
  }

  public void setPhotourl(String photourl) {
    this.photourl = photourl;
  }

}
