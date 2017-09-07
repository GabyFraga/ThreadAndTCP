/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import clienteEServidor.Cliente;
import clienteEServidor.Servidor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Gaby
 */
public class TelaPrincipalController implements Initializable {

    
    @FXML
    private Button abrirSala;
    
    @FXML
    private Button conectarNaSala;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void abrirSala(){
        
        Servidor servidor = new Servidor();
        servidor.start();
    
    }
    
    public void conectarSala()throws IOException{
        
        Cliente cliente = new Cliente();
        cliente.connect("127.0.0.1", (short) 8080);
        
    }
    
}
