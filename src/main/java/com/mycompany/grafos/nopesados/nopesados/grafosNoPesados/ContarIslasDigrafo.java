/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
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
public class ContarIslasDigrafo {
    
   private Digrafo digrafo;
   public ContarIslasDigrafo(Digrafo unDigrafo) throws ExcepcionNroDeVerticesInvalidos{
    digrafo=unDigrafo;

   
}
    public List<Integer> llegarDesdeUnPunto(int pos) throws ExcepcionNroDeVerticesInvalidos {
        List<Integer> listaDeInicios = new ArrayList<>();
        Stack<Integer> pilaDeVertices = new Stack<>();
        RecorridoUtils controlMarcados = new RecorridoUtils(digrafo.cantidadDeVertices());

        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            pilaDeVertices.push(i);
            controlMarcados.marcarVertice(i);
            while (!pilaDeVertices.isEmpty()) {
                int verticeActual = pilaDeVertices.pop();
                Iterable<Integer> listaDeAdyacencias = digrafo.adyacentesDelVertice(verticeActual);
                for (Integer adyacente : listaDeAdyacencias) {
                    if (!controlMarcados.estaVerticeMarcado(adyacente)) {
                        pilaDeVertices.push(adyacente);
                        controlMarcados.marcarVertice(adyacente);
                    }
                }
            }
            if (controlMarcados.estaVerticeMarcado(pos)&& i!=pos  ) {
                listaDeInicios.add(i);
                
            }
            controlMarcados.desmarcarTodos();

        }

        return listaDeInicios;
    }

   
   


}
