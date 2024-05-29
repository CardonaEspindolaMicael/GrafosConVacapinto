/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

/**
 *
 * @author user
 */
public class AdyacentesConPeso implements Comparable<AdyacentesConPeso> {
    
    private int indiceVertice;
    private double peso;
    
    public AdyacentesConPeso(int vertice){
        this.indiceVertice=vertice;
    }
    public AdyacentesConPeso(int vertice,double peso){
        this.indiceVertice=vertice;
        this.peso=peso;
    }
    
    public int getIndiceVertice(){
        return this.indiceVertice;
    }
    public double getPeso(){
        return this.peso;
    }
    
    public void setIndiceVertice(int vertice){
        this.indiceVertice=vertice;
    }
    
    public void setPeso(double peso){
        this.peso=peso;
    }
    
    
    
    

    @Override
    public int compareTo(AdyacentesConPeso otroAdyacenteConPeso) {
     if(otroAdyacenteConPeso==null){
         return -1;
     }
     if(this.indiceVertice>otroAdyacenteConPeso.indiceVertice){
         return 1;
     }
     if(this.indiceVertice<otroAdyacenteConPeso.indiceVertice){
         return -1;
     }
     return 0;
     }

    @Override
    public boolean equals(Object otro) {
        if(otro==null){
            return false;
        }
        if(getClass()!=otro.getClass()){
            return false;
        }
        AdyacentesConPeso other=(AdyacentesConPeso)otro;
        return this.indiceVertice==other.indiceVertice;
        
    }

    @Override
    public int hashCode() {
        int hash=3;
        hash=67*hash+this.indiceVertice;
        return hash;
    }
     public String toString() {
         String adyConPeso="";
         adyConPeso="{ "+this.indiceVertice+", "+this.peso+" }";
        return adyConPeso;
    }
    
    
    
}
