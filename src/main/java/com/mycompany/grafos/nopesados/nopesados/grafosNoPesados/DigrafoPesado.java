/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author user
 */
public class DigrafoPesado extends GrafoPesado {
    
    public DigrafoPesado(){
        super();
    }

    @Override
    public int gradoDelVertice(int posicionDelVertice) {
         throw new UnsupportedOperationException("El metodo no esta disponible para un digrafo pesado");
    }

    @Override
    public void elimnarArista(int posicionOrigen, int posicionDestino) {
        validarVertice(posicionOrigen);
        validarVertice(posicionDestino);
            if(!existeAdyacencia(posicionOrigen, posicionDestino)){
            }
        List<AdyacentesConPeso>lista=listaDeAdyacencia.get(posicionOrigen);
        AdyacentesConPeso nodo=new AdyacentesConPeso(posicionDestino);
        int posicionAEliminar=lista.indexOf(nodo);
        lista.remove(posicionAEliminar);
    }

    @Override
    public int cantidadDeAristas() {
        int size=0;
        for(int i=0;i<listaDeAdyacencia.size();i++){
            List<AdyacentesConPeso>lista=listaDeAdyacencia.get(i);
            size+=lista.size();
        }
       return size;
    }

    @Override
    public void insertarArista(int posicionOrigen, int posicionDestino, double peso) throws AristaYaExisteExcepcion{
          validarVertice(posicionOrigen);
          validarVertice(posicionDestino);
            if(existeAdyacencia(posicionOrigen, posicionDestino)){
                throw new AristaYaExisteExcepcion();
            }
        AdyacentesConPeso nodo=new AdyacentesConPeso(posicionDestino,peso);
        List<AdyacentesConPeso>list=listaDeAdyacencia.get(posicionOrigen);
        list.add(nodo);
        Collections.sort(list);
    }
    
    public DigrafoPesado(int nroVertices) throws ExcepcionNroDeVerticesInvalidos{
        super(nroVertices);
    }
    
    public int gradoDeSalidaDelVertice(int vertice){
        List<AdyacentesConPeso>lista=listaDeAdyacencia.get(vertice);
        return lista.size();
    }
    
    public int gradoDeEntradaDelVertice(int vertice){
        int cantidad=0;
        for(int i=0;i<listaDeAdyacencia.size();i++){
            List<AdyacentesConPeso>listaAuxiliar=listaDeAdyacencia.get(i);
             for(AdyacentesConPeso elemento:listaAuxiliar){
                 if(elemento.getIndiceVertice()==vertice){
                     cantidad++;
                 }
             }
        }
       return cantidad;
    }
        
}

