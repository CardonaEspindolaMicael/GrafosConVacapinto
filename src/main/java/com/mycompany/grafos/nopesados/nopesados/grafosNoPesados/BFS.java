/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author user
 */
public class BFS {
    private RecorridoUtils controlMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;
    
    
    public BFS(Grafo unGrafo, int unaPosDeVerticeDePartida) throws ExcepcionNroDeVerticesInvalidos{
        this.grafo = unGrafo;
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido= new ArrayList<>();
        controlMarcados= new RecorridoUtils(this.grafo.cantidadDeVertices());
        ejecutarBFS(unaPosDeVerticeDePartida); 
        
    }
    
    public void ejecutarBFS(int posDeVerticeDePartida) throws ExcepcionNroDeVerticesInvalidos {
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posDeVerticeDePartida);
        controlMarcados.marcarVertice(posDeVerticeDePartida);
        do {
            int posDeVerticeDeEnTurno = cola.poll();
            recorrido.add(posDeVerticeDeEnTurno);
            Iterable<Integer> integerIterable= this.grafo.adyacentesDelVertice(posDeVerticeDeEnTurno);
            for(Integer posDeVerticeAdy: integerIterable){
                if(!this.controlMarcados.estaVerticeMarcado(posDeVerticeAdy)){
                    cola.offer(posDeVerticeAdy);
                    controlMarcados.marcarVertice(posDeVerticeAdy);
                }
            }
        } while (!cola.isEmpty());
    }
    
    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    
    public boolean hayCaminoAVertice(int posDeVerticeDestino) throws ExcepcionNroDeVerticesInvalidos{
        this.grafo.validarVertice(posDeVerticeDestino);
        return this.controlMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }
    
    public boolean hayCaminoAtodosLosVertices(){
        return this.controlMarcados.estanTodosMarcados();
    }
}
