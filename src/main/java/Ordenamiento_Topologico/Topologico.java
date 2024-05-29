/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento_Topologico;

import Utileria.RecorridoUtils;
import WarshallAlgorithm.AlgoritmoDeWarshall;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Digrafo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author user
 */
public class Topologico {
    private Queue<Integer> colaDeNodos;
    private Digrafo digrafo;
    private List<Integer> listaDeRespuesta;
    private RecorridoUtils controlMarcados;
    private List<Integer> entradas;
    
    
    
public Topologico(Digrafo unDigrafo) throws ExcepcionNroDeVerticesInvalidos{
    this.digrafo=unDigrafo;
    this.colaDeNodos=new LinkedList<>();
    this.listaDeRespuesta= new ArrayList<>();
    this.entradas= new ArrayList<>();
    this.controlMarcados=new RecorridoUtils(this.digrafo.cantidadDeVertices());
    for(int i=0; i<digrafo.cantidadDeVertices(); i++){
        this.entradas.add(i, this.digrafo.gradoDeEntrada(i));
    }
    ejecutarTopologico();
}

    private void ejecutarTopologico() throws ExcepcionNroDeVerticesInvalidos {
        AlgoritmoDeWarshall warshall = new AlgoritmoDeWarshall(this.digrafo);
        if (!warshall.cycleExists()) {
            agregarGradoZero();
            while (!this.colaDeNodos.isEmpty()) {
                int nodoActual = colaDeNodos.poll();
                this.listaDeRespuesta.add(nodoActual);
                Iterable<Integer> listaAdy = this.digrafo.adyacentesDelVertice(nodoActual);
                if (!controlMarcados.estaVerticeMarcado(nodoActual)) {
                    for (Integer adyInd : listaAdy) {
                        entradas.set(adyInd, this.entradas.get(adyInd) - 1);
                    }
                    controlMarcados.marcarVertice(nodoActual);
                }
                agregarGradoZero();
            }
        }

    }

private void agregarGradoZero(){
    for(int i=0; i<this.digrafo.cantidadDeVertices(); i++){
       int grado=entradas.get(i);
       if(grado==0 && !controlMarcados.estaVerticeMarcado(i)){
           this.colaDeNodos.offer(i);
       }
    }
}

public List<Integer> mostrarOrdenamiento(){
    return this.listaDeRespuesta;
}

}
