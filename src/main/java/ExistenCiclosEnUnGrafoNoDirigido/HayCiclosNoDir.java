/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExistenCiclosEnUnGrafoNoDirigido;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Grafo;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoPesado;
import java.util.Stack;

/**
 *
 * @author user
 */
public class HayCiclosNoDir {
    
    private GrafoPesado grafo;
    private RecorridoUtils controlMarcados;
    private GrafoPesado grafoAuxiliar;

    
    
public HayCiclosNoDir(GrafoPesado unGrafo) throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion{
    this.grafo=unGrafo;
    int tamañoGrafo=this.grafo.cantidadDeVertices();
    this.grafoAuxiliar=new GrafoPesado(tamañoGrafo);
    this.controlMarcados= new RecorridoUtils(tamañoGrafo);
}

    public boolean existeCicloNoDir() throws AristaYaExisteExcepcion {
    
     Stack<Integer> pilaDeVertiecs= new Stack<>();
     pilaDeVertiecs.push(0);
     controlMarcados.marcarVertice(0);
     while (!pilaDeVertiecs.isEmpty()){
         int verticeActual=pilaDeVertiecs.pop();
         Iterable<Integer> listaDeAdyacencias=this.grafo.adyacentesDelVertice(verticeActual);
         for(Integer adyacenteIndividual:listaDeAdyacencias){
             if(!controlMarcados.estaVerticeMarcado(adyacenteIndividual)){
                 grafoAuxiliar.insertarArista(verticeActual, adyacenteIndividual,1);
                 controlMarcados.marcarVertice(adyacenteIndividual);
                 pilaDeVertiecs.push(adyacenteIndividual);
             }
             if(!grafoAuxiliar.existeAdyacencia(verticeActual, adyacenteIndividual)){
                 return true;
             }       
         }
          if(pilaDeVertiecs.isEmpty()&& !controlMarcados.estanTodosMarcados()){
              pilaDeVertiecs.push(saltarAOtraIsla());
          }    
     }
        return false;
    }
    
    private int saltarAOtraIsla(){
        for (int i=0; i<this.grafo.cantidadDeVertices(); i++){
            
            if(!controlMarcados.estaVerticeMarcado(i)){
                controlMarcados.marcarVertice(i);
                return i;
            }
            
        }
        
        return -1; 
    }
    
}
