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
import java.util.Stack;

/**
 *
 * @author user
 */
public class DFS {
      private RecorridoUtils controlMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;
    
    
    public DFS(Grafo unGrafo, int unaPosDeVerticeDePartida) throws ExcepcionNroDeVerticesInvalidos{
        this.grafo = unGrafo;
        grafo.validarVertice(unaPosDeVerticeDePartida);
        recorrido= new ArrayList<>();
        controlMarcados= new RecorridoUtils(this.grafo.cantidadDeVertices());
        ejecutarDFS(unaPosDeVerticeDePartida); 
        
    }
    
    public void ejecutarDFS(int posDeVerticeDePartida) throws ExcepcionNroDeVerticesInvalidos {
       recorrido.add(posDeVerticeDePartida);
        controlMarcados.marcarVertice(posDeVerticeDePartida);
       
            Iterable<Integer> integerIterable= this.grafo.adyacentesDelVertice(posDeVerticeDePartida);
            for(Integer posDeVerticeAdy: integerIterable){
                if(!this.controlMarcados.estaVerticeMarcado(posDeVerticeAdy)){
                    ejecutarDFS(posDeVerticeAdy);
                }
            }
        
    }
    
    public boolean esConexo() {
        Stack<Integer> pilaDeVertices = new Stack<>();
        pilaDeVertices.push(0);
        controlMarcados.marcarVertice(0);
        while (!pilaDeVertices.isEmpty()) {
            int verticeActual = pilaDeVertices.pop();
            Iterable<Integer> listaDeAdyacentes = grafo.adyacentesDelVertice(verticeActual);
            for (Integer adyacenteEnTurno : listaDeAdyacentes) {
                if (!controlMarcados.estaVerticeMarcado(adyacenteEnTurno)) {
                    pilaDeVertices.push(adyacenteEnTurno);
                    controlMarcados.marcarVertice(adyacenteEnTurno);
                }
            }
        }
        if (!controlMarcados.estanTodosMarcados()) {
            return false;
        }

        return true;
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
