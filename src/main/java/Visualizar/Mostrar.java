package Visualizar;

import Contar_Islas_De_Un_Grafo_Dirigido.CantIslasDir;
import Dijkstra_Algorithm.Dijsktra;
import EjerciciosConGrafosPesados.Kruskals;
import ExistenCiclosEnUnGrafoNoDirigido.HayCiclosNoDir;
import Floyd_Algorithm.Floyd_Algorithm;
import Ordenamiento_Topologico.Topologico;
import WarshallAlgorithm.AlgoritmoDeWarshall;
import WarshallAlgorithm.IslaDeUnGrafo;
import com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion;
import com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.BFS;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.ContarIslas;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.ContarIslasDigrafo;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.DFS;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Digrafo;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.DigrafoPesado;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.ExisteBucleEnGrafoDir;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Floyd;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Grafo;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoDirigidoInvertido;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoPesado;
import kruskal_Algorithm.Kruskal_Algorithm;

/**
 *
 * @author user
 */
public class Mostrar {

    /**
     *
     * @param arguments
     * @throws
     * com.mycompany.grafos.nopesados.nopesados.excepciones.ExcepcionNroDeVerticesInvalidos
     * @throws
     * com.mycompany.grafos.nopesados.nopesados.excepciones.AristaYaExisteExcepcion
     */
    public static void main(String[] arguments) throws ExcepcionNroDeVerticesInvalidos, AristaYaExisteExcepcion {
        Grafo grafoDePrueba = new Grafo(9);
        Digrafo digrafoDePrueba = new Digrafo(8);
        GrafoPesado grafoPesado = new GrafoPesado(10);
        DigrafoPesado digrafoPesado = new DigrafoPesado(5);

        grafoDePrueba.insertarArista(0, 8);
        grafoDePrueba.insertarArista(1, 3);
        grafoDePrueba.insertarArista(1, 5);
        grafoDePrueba.insertarArista(8, 6);
        grafoDePrueba.insertarArista(1, 7);
        grafoDePrueba.insertarArista(8, 2);
        grafoDePrueba.insertarArista(6, 2);
        grafoDePrueba.insertarArista(0, 7);
        grafoDePrueba.insertarArista(6, 4);

        /**
         * ********************************************************
         */
        /* digrafoDePrueba.insertarArista(0, 1);
        digrafoDePrueba.insertarArista(1, 3);
        digrafoDePrueba.insertarArista(1, 4);
        digrafoDePrueba.insertarArista(3, 1);
        digrafoDePrueba.insertarArista(4, 2);
        digrafoDePrueba.insertarArista(2, 4);
        digrafoDePrueba.insertarArista(2, 2);*/
        /**
         * ********************************************************
         */
        digrafoDePrueba.insertarArista(4, 1);
        digrafoDePrueba.insertarArista(4, 2);
        digrafoDePrueba.insertarArista(4, 3);
        digrafoDePrueba.insertarArista(4, 0);
        digrafoDePrueba.insertarArista(1, 3);
        digrafoDePrueba.insertarArista(2, 1);
        digrafoDePrueba.insertarArista(2, 0);
        digrafoDePrueba.insertarArista(3, 0);
        digrafoDePrueba.insertarArista(5, 6);
        
        
        /**
         * ********************************************************
         */

        grafoPesado.insertarArista(4, 7, 3);
        grafoPesado.insertarArista(4, 6, 4);
        grafoPesado.insertarArista(6, 8, 4);
        grafoPesado.insertarArista(0, 1, 5);
        grafoPesado.insertarArista(1, 5, 5);
        grafoPesado.insertarArista(3, 4, 5);
        grafoPesado.insertarArista(1, 3, 6);
        grafoPesado.insertarArista(6, 9, 6);
        grafoPesado.insertarArista(2, 3, 7);
        grafoPesado.insertarArista(5, 8, 7);
        grafoPesado.insertarArista(8, 9, 7);
        grafoPesado.insertarArista(0, 3, 8);
        grafoPesado.insertarArista(2, 4, 8);
        grafoPesado.insertarArista(5, 6, 9);
        grafoPesado.insertarArista(0, 2, 10);
        grafoPesado.insertarArista(3, 5, 11);
        grafoPesado.insertarArista(6, 7, 12);
        grafoPesado.insertarArista(7, 9, 12);
        grafoPesado.insertarArista(2, 7, 15);

        digrafoPesado.insertarArista(0, 1, 1);
        digrafoPesado.insertarArista(1, 4, 7);
        digrafoPesado.insertarArista(1, 3, 4);
        digrafoPesado.insertarArista(2, 0, 3);
        digrafoPesado.insertarArista(2, 1, 2);
        digrafoPesado.insertarArista(2, 4, 4);
        digrafoPesado.insertarArista(3, 4, 2);
        digrafoPesado.insertarArista(3, 0, 6);
        digrafoPesado.insertarArista(4, 3, 3);


        System.out.println(digrafoDePrueba.mostraElGrafo());
        //------------------------------------------------------------------------------
       

        //Nuevos codigos
        Dijsktra dijkstra_Algo = new Dijsktra(grafoPesado, 5, 4);
        System.out.println("Mostrar dijks: " + dijkstra_Algo.retornarCaminoDeMenorCosto());
        System.out.println("Mostrar Menor costo: " + dijkstra_Algo.retornarMenorCosto());
         // ------------------------------------------------------------------------------------------ 
        HayCiclosNoDir hayCiclo = new HayCiclosNoDir(grafoPesado);
        System.out.println("Existe Ciclo: " + hayCiclo.existeCicloNoDir());
         // ------------------------------------------------------------------------------------------ 
        Kruskal_Algorithm krus = new Kruskal_Algorithm(grafoPesado);
        System.out.println(""+ krus.mostrarEstructura());
        System.out.println(""+krus.mostrarKruskal());
         // ------------------------------------------------------------------------------------------ 
        Topologico topo= new Topologico(digrafoDePrueba);
        System.out.println("El ordenamiento es: "+ topo.mostrarOrdenamiento());
         // ------------------------------------------------------------------------------------------ 
        AlgoritmoDeWarshall warshall = new AlgoritmoDeWarshall(digrafoDePrueba);
        System.out.println(warshall.mostrarWarshall());
        System.out.println("\n" + "¿Existe ciclo?" + "\n" + warshall.cycleExists());
         // ------------------------------------------------------------------------------------------ 
        IslaDeUnGrafo cantidadDeGrafos = new IslaDeUnGrafo(digrafoDePrueba);
        System.out.println("Cantidad de islas de un digrafo: " + cantidadDeGrafos.mostrarElContarIslas());
         // ------------------------------------------------------------------------------------------ 
        Floyd_Algorithm floyd = new Floyd_Algorithm(digrafoPesado);
        System.out.println("Mi Floyd: " + floyd.buscarCamino(0, 4));
         // ------------------------------------------------------------------------------------------ 
        Floyd floydAlgorithm = new Floyd(digrafoPesado);
        System.out.println("Floyd del auxiliar: " + floydAlgorithm.mostrarFloyd());
        System.out.println("camino" + floydAlgorithm.buscarCamino(4, 0));
        System.out.println(floydAlgorithm.mostrarPred());
         // ------------------------------------------------------------------------------------------ 
        Kruskals kruskalsAlgorithm = new Kruskals(grafoPesado);
        System.out.println("Todos los pesos" + kruskalsAlgorithm.agarrarTodosLosPesos());
        System.out.println("Mostrar Arbol Kruskal" + kruskalsAlgorithm.obternerGrafo().toString());
        
       
         // ------------------------------------------------------------------------------------------ 
         //Mostrar Islas por consola
         CantIslasDir islaDigrafo= new CantIslasDir(digrafoDePrueba);
         System.out.println("La cantidad de Islas en un digrafo son: "+ islaDigrafo.contarIslas());
         
        
    }

}
