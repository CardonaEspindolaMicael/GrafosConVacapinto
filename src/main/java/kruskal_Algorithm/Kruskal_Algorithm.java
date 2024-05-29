/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kruskal_Algorithm;

import ExistenCiclosEnUnGrafoNoDirigido.HayCiclosNoDir;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.AdyacentesConPeso;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoPesado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author user
 */
public class Kruskal_Algorithm{
    
    private final GrafoPesado grafoAux;
    private final GrafoPesado grafoPrincipal;
    private final int tamañoGrafo;
    private final List<EstructuraParaKruskal> listaDePesos;

    
    public Kruskal_Algorithm(GrafoPesado unGrafo) throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion{
        this.grafoPrincipal=unGrafo;
        this.tamañoGrafo=grafoPrincipal.cantidadDeVertices();
        this.grafoAux=new GrafoPesado(tamañoGrafo);
        this.listaDePesos= new ArrayList<>();
        for(int i=0; i<this.tamañoGrafo; i++){
            Iterable<AdyacentesConPeso> listaAd=this.grafoPrincipal.adyacentesDelVerticeConPeso(i);
            for(AdyacentesConPeso adyInd:listaAd){
              this.listaDePesos.add(new EstructuraParaKruskal(i, adyInd.getIndiceVertice(),adyInd.getPeso()));
            }
            
        }
       
        crearEstructura();
        executeKruskal();      
    }
 

    public GrafoPesado executeKruskal() throws AristaYaExisteExcepcion, ExcepcionNroDeVerticesInvalidos {
        for (int i = 0; i < this.listaDePesos.size(); i++) {
            int origen = this.listaDePesos.get(i).getOrigen();
            int destino = this.listaDePesos.get(i).getDestino();
            double peso = this.listaDePesos.get(i).getCosto();
            if (!grafoAux.existeAdyacencia(origen, destino)) {
                grafoAux.insertarArista(origen, destino, peso);
                HayCiclosNoDir existeCiclo = new HayCiclosNoDir(grafoAux);
                if (existeCiclo.existeCicloNoDir()) {
                    grafoAux.elimnarArista(origen, destino);
                }
            }
        }
        return grafoAux;

    }
    public String mostrarKruskal(){
        return grafoAux.toString();
    }

    public void crearEstructura() {
     Collections.sort(this.listaDePesos, (a, b) -> Double.compare(a.getCosto(), b.getCosto())); 
    }
    
    public String mostrarEstructura(){
     String s="";
     s=s+" Origen "+ " Destino "+" Costo "+"\n";
    for(int i=0; i<this.listaDePesos.size(); i++){
        double pesoActual=this.listaDePesos.get(i).getCosto();
        int indiceOrigen=this.listaDePesos.get(i).getOrigen();
        int indiceDestino=this.listaDePesos.get(i).getDestino();
        s=s+ indiceOrigen +"   ->   "+ indiceDestino + "   ->   "+pesoActual+"\n";
    }
     return s; 
    }
  

    
}
