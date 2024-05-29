/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dijkstra_Algorithm;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoPesado;
import java.util.Stack;

/**
 *
 * @author user
 */
public class Dijsktra {
    private final GrafoPesado grafo;
    private final RecorridoUtils controlMarcados;
    private final int [] predecesor;
    private final double [] costo;
    private final int posIni;
    private final int posFin;
    
    
public Dijsktra(GrafoPesado unGrafo, int posIni, int posFin) throws AristaYaExisteExcepcion{
    this.grafo=unGrafo;
    this.posFin=posFin;
    this.posIni=posIni;
    int tamañoGrafo=unGrafo.cantidadDeVertices();
    this.controlMarcados= new RecorridoUtils(tamañoGrafo);
    this.predecesor= new int [tamañoGrafo];
    this.costo= new double [tamañoGrafo];
    
    for( int i=0; i<tamañoGrafo; i++){
        this.costo[i]=Double.POSITIVE_INFINITY;
        this.predecesor[i]=-1;
    }
      solucionarDijkstra(this.posIni, this.posFin);
}

    private void solucionarDijkstra(int posInicio, int posFin) throws AristaYaExisteExcepcion {
        this.controlMarcados.desmarcarTodos();
        int verticeActual = posInicio;
        this.costo[verticeActual] = 0;
        controlMarcados.marcarVertice(posInicio);

        while (!this.controlMarcados.estaVerticeMarcado(posFin)&& verticeActual!=-1) {
            Iterable<Integer> listaDeAdyacentes = this.grafo.adyacentesDelVertice(verticeActual);
            for (Integer verticeIndividual : listaDeAdyacentes) {
                if (!this.controlMarcados.estaVerticeMarcado(verticeIndividual)) {
                    double total = costo[verticeActual] + this.grafo.peso(verticeActual, verticeIndividual);
                    if (this.costo[verticeIndividual] > total) {
                        this.costo[verticeIndividual] = total;
                        this.predecesor[verticeIndividual] = verticeActual;
                    }
                }

            }
            verticeActual=buscarNoMarcadoDeMenorCosto();
            this.controlMarcados.marcarVertice(verticeActual);

        }

    }

    private int buscarNoMarcadoDeMenorCosto() {
    double valorMenor=Double.POSITIVE_INFINITY;
    int verticeNoMarcadoMenor=0;
     for(int i=0; i<this.grafo.cantidadDeVertices(); i++){
         
       if(valorMenor>this.costo[i] && !this.controlMarcados.estaVerticeMarcado(i)){
           valorMenor=this.costo[i];
           verticeNoMarcadoMenor=i;
       }
     }
     if(valorMenor==Double.POSITIVE_INFINITY){
         return -1;
     }
     return verticeNoMarcadoMenor;
    }
    
    public Stack<Integer> retornarCaminoDeMenorCosto(){
     Stack<Integer> camino= new Stack<>();

     int posicion=this.predecesor[posFin];
     int anterior=this.posFin;
     while(posicion!=-1){   
         camino.push(anterior);
         anterior=posicion;
         posicion=this.predecesor[posicion];   
     }
       camino.push(anterior);
        return camino;
    }
    
    public double retornarMenorCosto(){
        return this.costo[posFin];
    }

}
