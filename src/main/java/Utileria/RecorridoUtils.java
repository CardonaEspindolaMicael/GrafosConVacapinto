/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utileria;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.Digrafo;
import com.mycompany.grafos.nopesados.nopesados.grafosNoPesados.GrafoPesado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class RecorridoUtils {

    private List<Boolean> marcados;
    
    public RecorridoUtils(int nroVertices){
        this.marcados= new ArrayList<>(); 
        for(int i=0; i<nroVertices; i++){
            this.marcados.add(Boolean.FALSE);
        }
    }

    public RecorridoUtils(GrafoPesado digrafo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void  desmarcarTodos(){
        for(int i=0; i< this.marcados.size(); i++){
            this.marcados.set(i, Boolean.FALSE);
            
        }
    }
    
    public void  marcarVertice(int posVertice){
        //Pre: posVertice es una posicion valida
         this.marcados.set(posVertice, Boolean.TRUE);
    }
    
    public boolean estaVerticeMarcado(int posVertice){
        
        return this.marcados.get(posVertice);
    }
    
    public boolean estanTodosMarcados(){
        for(Boolean marcado: this.marcados){
            if(!marcado){
                return false;
            }
        }
        return true;
    }
    
}

