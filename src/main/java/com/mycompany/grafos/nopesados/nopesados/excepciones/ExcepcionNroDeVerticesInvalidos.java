/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.excepciones;

/**
 *
 * @author user
 */
public class ExcepcionNroDeVerticesInvalidos extends Exception{
    public ExcepcionNroDeVerticesInvalidos(){
        super("Nro de vertices inválido");
    }
    
    public ExcepcionNroDeVerticesInvalidos(String message){
        super(message);
    }
}
