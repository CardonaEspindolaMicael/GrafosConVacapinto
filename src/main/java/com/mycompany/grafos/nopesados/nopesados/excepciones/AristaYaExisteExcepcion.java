/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos.nopesados.nopesados.excepciones;

/**
 *
 * @author user
 */
public class AristaYaExisteExcepcion extends Exception{

    public AristaYaExisteExcepcion() {
        super("Arista ya existe");
    }
    public AristaYaExisteExcepcion(String message ){
        super(message);
    }
    
    
}
