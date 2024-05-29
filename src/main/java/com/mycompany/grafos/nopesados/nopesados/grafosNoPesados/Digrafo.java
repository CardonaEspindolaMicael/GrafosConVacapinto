/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author user
 */
public class Digrafo extends Grafo {
    
    public Digrafo(int nroVertices) throws ExcepcionNroDeVerticesInvalidos {
        super(nroVertices);
    }
    public Digrafo(){
        
    }

    @Override
    public void insertarArista(int verticeInicial, int verticeFinal) throws AristaYaExisteExcepcion  {
        super.validarVertice(verticeFinal);
        super.validarVertice(verticeInicial);
        if(super.existeAdyacencia(verticeInicial, verticeFinal)){
            throw new AristaYaExisteExcepcion();
        }
        List<Integer>listaAdya=super.listasDeAdyacencia.get(verticeInicial);
        listaAdya.add(verticeFinal);    
    }

    @Override
    public int gradoDelVertice(int vertice) {
        throw new UnsupportedOperationException("metodo no soportado"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int gradoDeSalida(int vertice){
        return super.gradoDelVertice(vertice);
    }
    public int gradoDeEntrada(int vertice){
        super.validarVertice(vertice);
        int cantidadLlegada=0;
        for(int i=0;i<super.listasDeAdyacencia.size();i++){
            if(super.listasDeAdyacencia.get(i).contains(vertice)){
                cantidadLlegada++;
            }
        }
        return cantidadLlegada;
    }

    @Override
    public int cantidadDeAristas() {
        int cantidad=0;
        for(int i=0;i<super.listasDeAdyacencia.size();i++){
            List<Integer>listaAdya=super.listasDeAdyacencia.get(i);
            cantidad=cantidad+listaAdya.size();
        }
        return cantidad;
    }

    @Override
    public void eliminarArista(int posicionInicial, int posicionFinal) throws AristaYaExisteExcepcion  {
         super.validarVertice(posicionFinal);
         super.validarVertice(posicionInicial);
            if(!super.existeAdyacencia(posicionInicial, posicionFinal)){
                throw new AristaYaExisteExcepcion("El vertice "+ posicionFinal +" no es adyacente a "+posicionInicial );
            }
          List<Integer>listaAdya=super.listasDeAdyacencia.get(posicionInicial);
          int posicionAEliminar=listaAdya.indexOf(posicionFinal);
          listaAdya.remove(posicionAEliminar);
    }
    
 
    
}
