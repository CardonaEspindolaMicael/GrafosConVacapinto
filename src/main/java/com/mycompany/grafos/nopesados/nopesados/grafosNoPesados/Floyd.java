/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.grafosNoPesados;

import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static javax.swing.text.html.HTML.Tag.P;

/**
 *
 * @author user
 */
public class Floyd {
    private final DigrafoPesado digrafo;
    private final int [][] matrizPred;
    private final double[][] matrizP;

    public Floyd(DigrafoPesado unDigrafo) throws AristaYaExisteExcepcion {
        this.digrafo = unDigrafo;
        int longitud = digrafo.cantidadDeVertices();
        matrizP = new double[longitud][longitud];
        matrizPred = new int[longitud][longitud];
        for (int i = 0; i < longitud; i++) {
            for (int j = 0; j < longitud; j++) {
            matrizP[i][j]=0;
            matrizPred[i][j]=-1;
            }
        }
        crearMatrizP();
        ejecutarFloyd();
        buscarCamino(4,3);
    }
    
    public void ejecutarFloyd() {
        int longitud = digrafo.cantidadDeVertices();
        for (int k = 0; k < longitud; k++) {
            for (int i = 0; i < longitud; i++) {
                for (int j = 0; j < longitud; j++) {
                   if(k!=j && i!=k && i!=j){         
                        matrizP[i][j] = Math.min(matrizP[i][j], matrizP[i][k] + matrizP[k][j]);
                       if(matrizP[i][j]==matrizP[i][k]+matrizP[k][j] && matrizP[i][j]!=Double.MAX_VALUE){
                          matrizPred[i][j] =k;  
                      }    
                  }
               }
            }
        }   
    }
    
    public List<Integer> buscarCamino(int verticeInicio, int verticeFin) { //OJO
        if (matrizPred[verticeInicio][verticeFin] != -1) {
            List<Integer> camino = new ArrayList<>();
            camino.add(verticeInicio);
            camino.add(verticeFin);
            for (int i = 0; i < camino.size()-1; i++) {
                if (matrizPred[camino.get(i)][camino.get(i + 1)] != -1) {
                    camino.add(matrizPred[camino.get(i)][camino.get(i + 1)]);
                    i = -1;
                    Collections.sort(camino);
                }
            }
            return camino;
        }
        return null;
    }

    

    
    public void crearMatrizP() throws AristaYaExisteExcepcion{
        
         for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            for (int j = 0; j < digrafo.cantidadDeVertices(); j++) {
                if (digrafo.existeAdyacencia(i, j) && i!=j) {
                    matrizP[i][j]=digrafo.peso(i, j);
                } else{
                if(i!=j){
                    matrizP[i][j]=Double.MAX_VALUE;
                }
                }
            }
        }
    }
    
      public String mostrarFloyd(){
           String s="MATRIZ DE PRED";
       for(int i=0;i<this.digrafo.cantidadDeVertices();i++){
           s=s+"\n";
           for(int j=0;j<this.digrafo.cantidadDeVertices();j++){
              if(matrizP[i][j]!=Double.MAX_VALUE){
                s=s+matrizP[i][j]+"|";   
              } else{
                  s=s+"Inf."+"!";
              }
              
          
             
          } 
    }
        return s;
        
    }
      
    public String mostrarPred() {
        String s = "MATRIZ DE PRED";

        for (int i = 0; i < this.digrafo.cantidadDeVertices(); i++) {
            s = s + "\n";
            for (int j = 0; j < this.digrafo.cantidadDeVertices(); j++) {
                if (matrizPred[i][j] != Double.MAX_VALUE) {
                    s = s + matrizPred[i][j] + "|";
                } else {
                    s = s + "Inf." + "!";
                }

            }
        }
        return s;

    }

  
}
