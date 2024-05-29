/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author user
 */
public class Grafo {
    
    protected List<List<Integer>>listasDeAdyacencia;
    
    public Grafo(){
        listasDeAdyacencia=new ArrayList<>();
        
    }
    public Grafo(int nroVertices) throws ExcepcionNroDeVerticesInvalidos{
        if(nroVertices<0){
            throw  new ExcepcionNroDeVerticesInvalidos("nro de vertices invalidos");
        }
        this.listasDeAdyacencia=new ArrayList<>();
       
        
            for(int i=0;i<nroVertices;i++){
                this.listasDeAdyacencia.add(new ArrayList<>());
            }
        
        
    }
    public void insertarVertice(){
       
    
        this.listasDeAdyacencia.add(new ArrayList<>());
    }
    public int cantidadDeAristas(){
        
            int cantidadAristas=0;
            int cantidadDeLazos=0;
                for(int i=0;i<this.listasDeAdyacencia.size();i++){   
                    List<Integer>listaAdya=this.listasDeAdyacencia.get(i);
                    for(Integer ad : listaAdya){
                        if(ad==i) {
                            cantidadDeLazos++;
                        }else{
                            cantidadAristas++;
                        }
                    }
                }
               
                return (cantidadAristas/2)+cantidadDeLazos;
       
    }
    
    public int cantidadDeVertices(){
        return this.listasDeAdyacencia.size();
    }
    public void validarVertice(int posicion){
        if(posicion<0 || posicion>=this.cantidadDeVertices()){
            throw new IllegalArgumentException("El vertice: " + posicion + " no pertenece al grafo");
        }
        
    }
    public void insertarArista(int verticeInicial,int verticeFinal) throws AristaYaExisteExcepcion  {
        this.validarVertice(verticeInicial);
        this.validarVertice(verticeFinal);
        if(this.existeAdyacencia(verticeInicial, verticeFinal)){
            throw new AristaYaExisteExcepcion("La arista ya existe en el grafo");
        }
      
        List<Integer>listaAdyacente=this.listasDeAdyacencia.get(verticeInicial);
        listaAdyacente.add(verticeFinal);
        Collections.sort(listaAdyacente);
        
            if(verticeInicial!=verticeFinal){
                listaAdyacente=this.listasDeAdyacencia.get(verticeFinal);
                listaAdyacente.add(verticeInicial);
                Collections.sort(listaAdyacente);
            }
        
        
    
    }
    public boolean existeAdyacencia(int origen,int destino){
        this.validarVertice(origen);
        this.validarVertice(destino);
        List<Integer>listaAdyacente=this.listasDeAdyacencia.get(origen);    
        return listaAdyacente.contains(destino);
    }
    
    public void eliminarVertice(int posVerticeAEliminar){
        this.validarVertice(posVerticeAEliminar);
        this.listasDeAdyacencia.remove(posVerticeAEliminar);
        for(List<Integer>listaAdya:this.listasDeAdyacencia){
            int posicionAEliminarDeAdyacencia=listaAdya.indexOf(posVerticeAEliminar);
            if(posicionAEliminarDeAdyacencia>=0){
                listaAdya.remove(posicionAEliminarDeAdyacencia);
               
            }
            for(int i=0;i<listaAdya.size();i++){    //por que el for este si el remove hace todo el trabajo
                int posicionAdyacente=listaAdya.get(i);
                    if(posicionAdyacente>posVerticeAEliminar){
                        listaAdya.set(i,posicionAdyacente-1);
                    }
            }
        }
    }
    
    
    public void eliminarArista(int posicionInicial,int posicionFinal) throws AristaYaExisteExcepcion{
        this.validarVertice(posicionInicial);
        this.validarVertice(posicionFinal);
        if(!this.existeAdyacencia(posicionInicial, posicionFinal)){
            throw new AristaYaExisteExcepcion("El vertice "+ posicionFinal +" no es adyacente a "+posicionInicial );
        }
        List<Integer>listaAdya=this.listasDeAdyacencia.get(posicionInicial);
        int posicionAEliminar=listaAdya.indexOf(posicionFinal);
        listaAdya.remove(posicionAEliminar);
        
       listaAdya=this.listasDeAdyacencia.get(posicionFinal);
       posicionAEliminar=listaAdya.indexOf(posicionInicial);
       listaAdya.remove(posicionAEliminar);
            
    }
    
    public int gradoDelVertice(int vertice){
        this.validarVertice(vertice);
        List<Integer>adyacentes=this.listasDeAdyacencia.get(vertice);
        return adyacentes.size();
    }
    
    public Iterable<Integer> adyacentesDelVertice(int vertice){
        this.validarVertice(vertice);
        List<Integer>listaAdya=this.listasDeAdyacencia.get(vertice);
        Iterable<Integer>it=listaAdya;
        return it;
    }
    
    public String mostraElGrafo(){
       String s=" |";
        int [][]matriz=new int[this.cantidadDeVertices()][this.cantidadDeVertices()];
        for(int i=0;i<this.cantidadDeVertices();i++){
            s=s+i+"|";
        }
        s=s+"\n";
       for(int i=0;i<this.cantidadDeVertices();i++){
           for(int j=0;j<this.cantidadDeVertices();j++){
               matriz[i][j]=0;
           }
       }
       
        for(int i=0;i<this.listasDeAdyacencia.size();i++){
            List<Integer>adyacentes=listasDeAdyacencia.get(i);
                for(Integer elemento : adyacentes){
                    matriz[i][elemento]=1;
                }
        }
        for(int i=0;i<this.cantidadDeVertices();i++){
            s=s+i+"|";
           for(int j=0;j<this.cantidadDeVertices();j++){
               s=s+matriz[i][j]+" ";
           }
           s=s+"\n";
       }
       return s;
    }
    
    
    
    
}

