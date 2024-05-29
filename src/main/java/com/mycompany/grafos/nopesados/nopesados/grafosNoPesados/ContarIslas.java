/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author user
 */
public class ContarIslas {
    private final Grafo grafo;
    private final RecorridoUtils controlMarcados;
    
 
    
public ContarIslas(Grafo unGrafo) throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion  {
    this.grafo=unGrafo;
    controlMarcados=new RecorridoUtils(this.grafo.cantidadDeVertices());
contarCiclosDeUnNoDirigido();

   
}
public int  mostrarIslasDeUnGrafoRecursivo(){
    controlMarcados.desmarcarTodos();
    return mostrarIslasDeUnGrafoRecursivo(this.grafo,0);
}

    public int mostrarIslasDeUnGrafoRecursivo(Grafo grafo, int verticeActual) {

        controlMarcados.marcarVertice(verticeActual);
        Iterable<Integer> listaDeAdyacencias = grafo.adyacentesDelVertice(verticeActual);
        int cantIslas=0;
        for (Integer Adyacente : listaDeAdyacencias) {
            if (!controlMarcados.estaVerticeMarcado(Adyacente)) {
               cantIslas+=mostrarIslasDeUnGrafoRecursivo(grafo, Adyacente);
            }
         }
         if(!controlMarcados.estanTodosMarcados()){
              cantIslas+= mostrarIslasDeUnGrafoRecursivo(grafo, buscarVerticeNoMarcado())+1;
         }
      return cantIslas;
    }

    public String mostrarIslasDeUnGrafo() throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion {

        Stack<Integer> pilaDeVertices = new Stack();
        pilaDeVertices.push(0);
        
        String isla = "|" + 0; 
        controlMarcados.marcarVertice(0);
        while (!pilaDeVertices.isEmpty()) {

            int verticeActual = pilaDeVertices.pop();
          
            Iterable<Integer> adyacenciasDeVerticeActual = this.grafo.adyacentesDelVertice(verticeActual);
            for (Integer VerticeAInsertar : adyacenciasDeVerticeActual) {
                if (!controlMarcados.estaVerticeMarcado(VerticeAInsertar)) {
                    isla = isla + "|" + VerticeAInsertar;
                    controlMarcados.marcarVertice(VerticeAInsertar);
                    pilaDeVertices.push(VerticeAInsertar);
                }
            }
            if (pilaDeVertices.isEmpty()) {

                
                if (buscarVerticeNoMarcado() != -1) {
                    pilaDeVertices.push(buscarVerticeNoMarcado());
                    controlMarcados.marcarVertice(buscarVerticeNoMarcado());
                    isla = isla + "|AGUA|" + buscarVerticeNoMarcado();
                }
            }

        }

        return isla;
    }

    
    public int buscarVerticeNoMarcado(){
        
        for(int i=0; i<this.grafo.cantidadDeVertices(); i++){
            if(!controlMarcados.estaVerticeMarcado(i)){
                return i;
            }
        }
        return -1;
        
    }
    
     public int contarCiclosDeUnNoDirigido() throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion {
       
        Grafo grafoAuxiliar = new Grafo(this.grafo.cantidadDeVertices());
        Stack<Integer> pilaDeVertices = new Stack();
        controlMarcados.desmarcarTodos();
        pilaDeVertices.push(0);
        int contarCiclos = 0;
          controlMarcados.marcarVertice(0);
        while (!pilaDeVertices.isEmpty()&&pilaDeVertices.peek()!=-1) {
            int verticeActual = pilaDeVertices.pop();
            Iterable<Integer> adyacenciasDeVerticeActual = this.grafo.adyacentesDelVertice(verticeActual);
            for (Integer VerticeAInsertar : adyacenciasDeVerticeActual) {
                if (!controlMarcados.estaVerticeMarcado(VerticeAInsertar)) {
                    grafoAuxiliar.insertarArista(verticeActual, VerticeAInsertar);
                    controlMarcados.marcarVertice(VerticeAInsertar);
                    pilaDeVertices.push(VerticeAInsertar);
                }
                if (!grafoAuxiliar.existeAdyacencia(verticeActual, VerticeAInsertar)) {
                    
                    contarCiclos++;
                    grafoAuxiliar.insertarArista(verticeActual, VerticeAInsertar);
                    
                }
            }
            if(pilaDeVertices.isEmpty()){   
                if(saltarSiguienteIsla()!=-1){
                pilaDeVertices.push(saltarSiguienteIsla());
                 controlMarcados.marcarVertice(saltarSiguienteIsla());
                }
            }

        }

        return contarCiclos;
    }
     public int saltarSiguienteIsla(){
        for(int i=0; i<grafo.cantidadDeVertices(); i++){
            if(!controlMarcados.estaVerticeMarcado(i)){
                return i;
            }
        }
        return -1;
     }


}
