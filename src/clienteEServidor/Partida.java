/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteEServidor;

import java.io.Serializable;

/**
 *
 * @author Gaby
 */
public class Partida implements Serializable{
    
    public String nome;
    
    public Partida(String nome){
        
        this.nome = nome;
        
    }
    
    public void printName(){
        
        System.out.println(this.nome);
        
    }
    
}
