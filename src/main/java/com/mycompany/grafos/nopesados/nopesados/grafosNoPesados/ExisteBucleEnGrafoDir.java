/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;

/**
 *
 * @author user
 */
public class ExisteBucleEnGrafoDir extends Grafo{
    RecorridoUtils controlDeMarcados;
    Grafo grafo;
    Grafo grafoAux;
    
    
public ExisteBucleEnGrafoDir(Grafo unGrafo) throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion{
    this.grafo=unGrafo;
    this.controlDeMarcados=new RecorridoUtils(this.grafo.cantidadDeVertices());
    this.grafoAux= new Grafo(this.grafo.cantidadDeVertices());
   
}

    public int encontrarCiclo(int verticeActual) throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion {
    controlDeMarcados.marcarVertice(verticeActual);
    Iterable<Integer> adyacentesDelVerticeActual=this.grafo.adyacentesDelVertice(verticeActual);
    int cantidadDeCiclos=0;
    for(Integer adyacenceIndividual: adyacentesDelVerticeActual){
        if(!controlDeMarcados.estaVerticeMarcado(adyacenceIndividual)&& this.grafo.existeAdyacencia(verticeActual, adyacenceIndividual)){
            this.grafoAux.insertarArista(verticeActual, adyacenceIndividual);
            cantidadDeCiclos+=encontrarCiclo(adyacenceIndividual);
        }
        if(!this.grafoAux.existeAdyacencia(verticeActual, adyacenceIndividual)){
            grafoAux.insertarArista(verticeActual, adyacenceIndividual);
            return cantidadDeCiclos+1;
        }
    }
    if(!controlDeMarcados.estanTodosMarcados()){
        cantidadDeCiclos+= encontrarCiclo(saltarAOtraIsla());
    }
    
    return cantidadDeCiclos;
    }

    private int saltarAOtraIsla() {
     for(int i=0; i<this.grafo.cantidadDeVertices(); i++){
         if(!controlDeMarcados.estaVerticeMarcado(i)){
             return i;
         }
     }   
     return -1;
    }


}
