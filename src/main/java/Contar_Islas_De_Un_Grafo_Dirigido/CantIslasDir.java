/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contar_Islas_De_Un_Grafo_Dirigido;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.DFS;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Digrafo;

/**
 *
 * @author user
 */
public class CantIslasDir {
    
    private Digrafo digrafo;
    private RecorridoUtils controlMarcados;
 
    public CantIslasDir(Digrafo unDigrafo){
        this.digrafo=unDigrafo;
        this.controlMarcados= new RecorridoUtils(this.digrafo.cantidadDeVertices());
    }
    
  public int contarIslas(){
      int numIslas=0;
      for(int vertice=0; vertice<this.digrafo.cantidadDeVertices(); vertice++){
          if(!controlMarcados.estaVerticeMarcado(vertice)){
              numIslas++;
              dfs(vertice);
          }
      }
      return numIslas;
  }

    private void dfs(int verticeActual) {
     controlMarcados.marcarVertice(verticeActual);
         for (int vecino : this.digrafo.adyacentesDelVertice(verticeActual)) {
            if (!controlMarcados.estaVerticeMarcado(vecino)) {
                dfs(vecino);
            }
    }
 }
  }