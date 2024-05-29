/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author user
 */
public class GrafoDirigidoInvertido {
    private Digrafo digrafo;
    private RecorridoUtils controlMarcados;
public GrafoDirigidoInvertido(Digrafo unDigrafo){
    this.digrafo=unDigrafo;
    controlMarcados= new RecorridoUtils(this.digrafo.cantidadDeVertices());
}
 
public Digrafo digrafoInvertido() throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion{
    Digrafo digrafoInv= new Digrafo(this.digrafo.cantidadDeVertices());
    Queue<Integer> colaDeVertices= new LinkedList<>();
    colaDeVertices.offer(0);
    
    while(!controlMarcados.estanTodosMarcados()){
      
        int verticeActual= colaDeVertices.poll();
        controlMarcados.marcarVertice(verticeActual);
        Iterable<Integer> listaDeAdyacencias= this.digrafo.adyacentesDelVertice(verticeActual);
        for(Integer verticeActualAdyacente:listaDeAdyacencias){
            if(!controlMarcados.estaVerticeMarcado(verticeActualAdyacente)){
                colaDeVertices.offer(verticeActualAdyacente);
               Iterable<Integer> adyacenciaParaVerificar= this.digrafo.adyacentesDelVertice(verticeActualAdyacente);
               boolean esBucle=verificarQueNoEsBucle(adyacenciaParaVerificar,verticeActual);
               if(!esBucle){
                   digrafoInv.insertarArista(verticeActualAdyacente, verticeActual);
                   esBucle=true;
               } 
                  digrafoInv.insertarArista(verticeActual, verticeActualAdyacente);
            }
        }
        if(colaDeVertices.isEmpty() && controlMarcados.estaVerticeMarcado(verticeActual) && !controlMarcados.estanTodosMarcados()){
            colaDeVertices.offer(saltarAOtroVertice());
        }
    }
    return digrafoInv;
}
    
    private boolean verificarQueNoEsBucle(Iterable<Integer> AdyacenciaAux,int verticeActual) {
      for(Integer esBucle:AdyacenciaAux){
          if(esBucle==verticeActual){
              return true;
          }
      }
      return false;
    }

    private Integer saltarAOtroVertice() {
      for(int i=0; i<this.digrafo.cantidadDeVertices(); i++){
         if(!controlMarcados.estaVerticeMarcado(i))
             return i;
  
      }
      return -1;
    }

}
