/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjerciciosConGrafosPesados;

import Utileria.RecorridoUtils;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.AdyacentesConPeso;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoPesado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author user
 */
public class Kruskals {
    private GrafoPesado grafoPesado;
    private RecorridoUtils ControlMarcados;
    
public Kruskals(GrafoPesado unGrafoPesado) throws AristaYaExisteExcepcion, ExcepcionNroDeVerticesInvalidos{
    
    this.grafoPesado=unGrafoPesado;
    ControlMarcados= new RecorridoUtils(grafoPesado.cantidadDeVertices());
   agarrarTodosLosPesos();

}

public GrafoPesado ejecutarKurskals(){
    
    
    
    
        return null;
    
    
}

    public List<Double> agarrarTodosLosPesos() throws AristaYaExisteExcepcion, ExcepcionNroDeVerticesInvalidos {
        List<Double> pesos = new ArrayList<>();
        Stack<Integer> pilaDeVertices = new Stack();
        GrafoPesado grafoAux= new GrafoPesado(this.grafoPesado.cantidadDeVertices());
        pilaDeVertices.add(0);
        

        while (!pilaDeVertices.isEmpty()) {
            int verticeActual = pilaDeVertices.pop();
            Iterable<Integer> adyacentesActuales = this.grafoPesado.adyacentesDelVertice(verticeActual);
            for (Integer adyacenteActual : adyacentesActuales) {
                double pesoActual=this.grafoPesado.peso(verticeActual, adyacenteActual);
                if (!ControlMarcados.estaVerticeMarcado(verticeActual)) {
                   
                    grafoAux.insertarArista(verticeActual, adyacenteActual, pesoActual);
                    pilaDeVertices.push(adyacenteActual);
                    pesos.add(pesoActual);
                    ControlMarcados.marcarVertice(adyacenteActual);
                }
                if(!grafoAux.existeAdyacencia(verticeActual, adyacenteActual)){
                    pesos.add(pesoActual);
                    grafoAux.insertarArista(verticeActual, adyacenteActual, pesoActual);
                }
            }

        }

    //Collections.sort(pesos);
        return pesos;
   
}

   
   public GrafoPesado obternerGrafo(){
       return this.grafoPesado;
   }


}
