/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Floyd_Algorithm;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.DigrafoPesado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author user
 */
public class Floyd_Algorithm {
    
    private double [][] pivote;
    private int [][] pred;
    private DigrafoPesado digrafo;
    private final int tamanoMatriz;
    private RecorridoUtils controlMarcados;
    private static final double INFINITO=Double.POSITIVE_INFINITY;

    
    public Floyd_Algorithm(DigrafoPesado unDigrafo) throws AristaYaExisteExcepcion{
        this.digrafo=unDigrafo;
        this.tamanoMatriz=this.digrafo.cantidadDeVertices();
        this.pivote= new double [tamanoMatriz][tamanoMatriz];
        this.pred= new int [tamanoMatriz][tamanoMatriz];
        this.controlMarcados= new RecorridoUtils(tamanoMatriz);
        createMatrices();
        executeFloyd();
        
    }

    private void createMatrices() throws AristaYaExisteExcepcion {
        
        for(int i=0; i<this.tamanoMatriz; i++){
            for(int j=0; j<this.tamanoMatriz; j++){
                if(this.digrafo.existeAdyacencia(i, j)&& i!=j){
                   this.pivote[i][j]=this.digrafo.peso(i, j);
                   this.pred[i][j]=j;
                }else {
                    if(i==j){
                        this.pivote[i][j]=0;
                        this.pred[i][j]=-1;
                        
                    }else{
                        this.pivote[i][j]=INFINITO;
                        this.pred[i][j]=-1;
                    }
                }
     
            }
        }
    }

    private void executeFloyd() {

        for (int pivote = 0; pivote < this.tamanoMatriz; pivote++) {
            for (int i = 0; i < this.tamanoMatriz; i++) {
                for (int j = 0; j < this.tamanoMatriz; j++) {
                    if (i != j && i != pivote && j != pivote) {
                        if (this.pivote[i][j] > (this.pivote[i][pivote] + this.pivote[pivote][j])) {
                            this.pivote[i][j]=this.pivote[i][pivote]+this.pivote[pivote][j];
                            this.pred[i][j]=pivote;
                        }
                    }

                }
            }
        }

    }
  public List<Integer> buscarCamino(int verticeInicio, int verticeFin) { //OJO
         List<Integer> camino= new ArrayList<>();
         
         int valorActual=this.pred[verticeInicio][verticeFin];
         if(valorActual!=-1){
             this.controlMarcados.marcarVertice(verticeInicio);
             this.controlMarcados.marcarVertice(verticeFin);
             camino.add(verticeInicio);
             camino.add(valorActual);
             camino.add(verticeFin);
           
             Collections.sort(camino);
             return buscarCamino(verticeInicio, verticeFin,camino,valorActual);
         }else{
             return camino;
         }
    }
   private List<Integer> buscarCamino(int verticeInicio, int verticeFin, List<Integer> camino,int valorActual) { //OJO
        if(this.controlMarcados.estaVerticeMarcado(valorActual)){
            return camino;
        }
    

        if(this.pred[verticeInicio][valorActual]!=-1 && !this.controlMarcados.estaVerticeMarcado(valorActual)){
           camino.add(this.pred[verticeInicio][valorActual]);
            this.controlMarcados.marcarVertice(this.pred[verticeInicio][valorActual]);
           buscarCamino(verticeInicio, verticeFin, camino,this.pred[verticeInicio][valorActual]);
           Collections.sort(camino);
        }
        
        
        if( this.pred[valorActual][verticeFin]!=-1&& !this.controlMarcados.estaVerticeMarcado(valorActual)){
           camino.add(this.pred[valorActual][verticeFin]);
           this.controlMarcados.marcarVertice(this.pred[valorActual][verticeFin]);
           buscarCamino(verticeInicio, verticeFin, camino,this.pred[valorActual][verticeFin]);
           Collections.sort(camino);
        }
        return camino;
   }   
    
    public String mostrarFloyd(){
           String s="MATRIZ DE PRED";
       for(int i=0;i<this.digrafo.cantidadDeVertices();i++){
           s=s+"\n";
           for(int j=0;j<this.digrafo.cantidadDeVertices();j++){
              if(this.pivote[i][j]!=INFINITO){
                s=s+this.pivote[i][j]+"|";   
              } else{
                  s=s+"inf."+"!";
              }
          } 
    }
        return s;
        
    }

}
