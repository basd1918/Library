package com.example.parkingapp;

public class Book {
    private String titlu,autor,editura,anPublicatie,cotaCarte,categorie;
    private  int nrExemplare, nrImprumutate;

    public  Book(String titlu,String autor,String editura,String anPublicatie,String categorie,String cotaCarte,int nrExemplare,int nrImprumutate)
    {
        this.setAutor(autor);
        this.setTitlu(titlu);
        this.setEditura(editura);
        this.setAnPublicatie(anPublicatie);
        this.setCategorie(categorie);
        this.setCotaCarte(cotaCarte);
        this.setNrExemplare(nrExemplare);
        this.setNrImprumutate(nrImprumutate);
    }
    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public String getAnPublicatie() {
        return anPublicatie;
    }

    public void setAnPublicatie(String anPublicatie) {
        this.anPublicatie = anPublicatie;
    }

    public String getCotaCarte() {
        return cotaCarte;
    }

    public void setCotaCarte(String cotaCarte) {
        this.cotaCarte = cotaCarte;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNrExemplare() {
        return nrExemplare;
    }
    public int getNrImprumutate() {return nrImprumutate;}

    public void setNrExemplare(int nrExemplare) {
        this.nrExemplare = nrExemplare;
    }
    public void setNrImprumutate(int nrImprumutate) {
        this.nrImprumutate = nrImprumutate;}
}
