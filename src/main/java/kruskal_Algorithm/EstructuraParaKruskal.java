/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kruskal_Algorithm;

import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.AdyacentesConPeso;

/**
 *
 * @author user
 */
public class EstructuraParaKruskal {
    private int origen;
    private int destino;
    private double costo;

 public EstructuraParaKruskal(int unOrigen,int unDestino,double unCosto){
     this.origen=unOrigen;
     this.destino=unDestino;
     this.costo=unCosto;
 }

 public int getOrigen(){
     return this.origen;
 }
 public int getDestino(){
     return this.destino;
 }
 public double getCosto(){
     return this.costo;
 }
 
}
