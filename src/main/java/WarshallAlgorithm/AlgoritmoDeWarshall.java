/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WarshallAlgorithm;

import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Grafo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class AlgoritmoDeWarshall {
    private final Grafo grafo;
    private boolean [][] matrizDeAdyacencias;
    
    public AlgoritmoDeWarshall(Grafo unGrafo) throws ExcepcionNroDeVerticesInvalidos{
        this.grafo=unGrafo;
         int dimensionMatricial= grafo.cantidadDeVertices();
        matrizDeAdyacencias= new boolean [dimensionMatricial][dimensionMatricial];
         /* for(int i=0; i<dimensionMatricial; i++){
            for(int j=0; j<dimensionMatricial; j++ ){
                Iterable<Integer> listaDeAdyacencias=grafo.adyacentesDelVertice(i);
                for(Integer Adyacencia:listaDeAdyacencias ){
                    matrizDeAdyacencias[i][Adyacencia]=true;
                } 
            }
        }*/
       
    matrizDeCaminos();

    }
    
    public boolean[][] matrizDeCamino() {
        int tamaño = grafo.cantidadDeVertices();
        boolean[][] matrizDeAdyancencia = new boolean[tamaño][tamaño];
        for (int i = 0; i < matrizDeAdyancencia.length; i++) {
            for (int j = 0; j < matrizDeAdyancencia.length; j++) {
                Iterable<Integer> listaAdj = grafo.adyacentesDelVertice(i);
                for (Integer adj : listaAdj) {
                    matrizDeAdyancencia[i][adj] = true;
                }
            }
        }
        return matrizDeAdyancencia;
    }
    
    public boolean [][] matrizDeCaminos(){
        boolean [][] warshall= matrizDeCamino();
        for(int k=0; k<warshall.length; k++){
            for(int i=0; i<warshall.length; i++){
                for(int j=0; j<warshall.length; j++){
                    warshall[i][j]=warshall[i][j]|| (warshall[i][k]&& warshall[k][j]) ;
                }
            }
        }
        matrizDeAdyacencias=warshall;
        return warshall;
    }
    
    public void ejecutarWharshall() {

        for (int k = 0; k < matrizDeAdyacencias.length; k++) {
            for (int i = 0; i < matrizDeAdyacencias.length; i++) {
                for (int j = 0; j < matrizDeAdyacencias.length; j++) {
                    matrizDeAdyacencias[i][j] = matrizDeAdyacencias[i][j] || (matrizDeAdyacencias[i][k] && matrizDeAdyacencias[k][j]);
                }
            }

        }

    }
    
    public boolean cycleExists(){
        
        for(int i=0; i<matrizDeAdyacencias.length; i++){
            if(matrizDeAdyacencias[i][i]==true){
                return true; 
            }
        }
        return false;
        
    }
    
    public boolean sonTodosUno(){
        
        for(int i=0; i<matrizDeAdyacencias.length; i++){
            for(int j=0; j<matrizDeAdyacencias.length; j++){
                if (matrizDeAdyacencias[i][j]==false){
                    return false;
                }
            }
        }
        return true;
    }
    
    public String mostrarWarshall(){
           String s="MATRIZ DE CAMINOS";
        
       
        
       for(int i=0;i<this.grafo.cantidadDeVertices();i++){
           s=s+"\n";
           for(int j=0;j<this.grafo.cantidadDeVertices();j++){
              if(matrizDeAdyacencias[i][j]==true){
                  s=s+1+"|";
               } else{
                   s=s+0+"|";
              }
             
          } 
    }
        return s;
        
    }
    

    

    
}

