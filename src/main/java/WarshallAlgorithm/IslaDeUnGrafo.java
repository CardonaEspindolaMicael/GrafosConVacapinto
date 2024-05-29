/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WarshallAlgorithm;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Grafo;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class IslaDeUnGrafo {
    private final  Grafo grafo;
    private final  RecorridoUtils marcados;
    private  int contarIslas;
    
    public IslaDeUnGrafo(Grafo unGrafo) throws ExcepcionNroDeVerticesInvalidos {
        this.grafo = unGrafo;
        contarIslas=0;
        marcados= new RecorridoUtils(this.grafo.cantidadDeVertices());
        cantidadDeIslasEnUnGrafoDirigido();
    }
    
    public int cantidadDeIslasEnUnGrafoDirigido() {
    return cantidadDeIslasEnUnGrafoDirigido(this.grafo,0);
    }

      private int cantidadDeIslasEnUnGrafoDirigido(Grafo grafo, int verticeActual) { 
          if(this.marcados.estanTodosMarcados()){
              return 0;
          }
           this.marcados.marcarVertice(verticeActual);
          List<Integer> listaDeAdyacencias= (List<Integer>) grafo.adyacentesDelVertice(verticeActual);
          if(!listaDeAdyacencias.isEmpty()){
           for (Integer posibleVertice : listaDeAdyacencias) {
                 if(!this.marcados.estaVerticeMarcado(posibleVertice)){
                      return cantidadDeIslasEnUnGrafoDirigido(grafo, posibleVertice);
                 }  
              }
          }
   
              List<Integer> posibleListaDeAdyacencias = esVerticeSolitario(verticeActual, listaDeAdyacencias);
              if(!posibleListaDeAdyacencias.isEmpty()){
                   for (Integer posibleVerticeSolitario : posibleListaDeAdyacencias) {
                  return cantidadDeIslasEnUnGrafoDirigido(grafo, posibleVerticeSolitario);
              }
              }
                 int otraIsla=otroVerticeNoMarcado();
                contarIslas++;
                return  cantidadDeIslasEnUnGrafoDirigido(grafo, otraIsla) ; 
                 
                
      }
      
     public int mostrarElContarIslas(){
         return contarIslas;
     }
   
          
      
    private List<Integer> esVerticeSolitario(int verticeActual, Iterable<Integer> listaDeAdyacencias) {
        List<Integer> lista= new LinkedList<>();
        for(int i=0; i<this.grafo.cantidadDeVertices(); i++){
        List<Integer> listaDeAdy=(List<Integer>) this.grafo.adyacentesDelVertice(i);
            for(Integer verticeNoMarcado:listaDeAdy){
            if(verticeNoMarcado==verticeActual && !this.marcados.estaVerticeMarcado(i)){
              lista.add(i);
            }
        }
        }
        return lista;

     }

    private int otroVerticeNoMarcado() {
        for(int i=0; i<grafo.cantidadDeVertices(); i++){
            if(!marcados.estaVerticeMarcado(i)){
                return i;
            }
        }
        return -999;
    }

 
}
    


