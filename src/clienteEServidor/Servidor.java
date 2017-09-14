package clienteEServidor;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {


    private ServerSocket servidor;
    private Socket cliente;
    private final short PORT = 8080;
    private short clientCount = 0;
    private List<ObjectOutputStream> clientes;
    //private List<PrintStream> clientes;

    public Servidor(){

        try {
            servidor = new ServerSocket(PORT);
            System.out.println("Porta "+PORT+" aberta!");
            this.clientes = new ArrayList<ObjectOutputStream>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void repasseDeMensagem(String mensagem) {

        for (ObjectOutputStream cliente : this.clientes) {
            //cliente.println(mensagem);
        }
    }
    
    public void printName(Partida partida){
        
        System.out.println(partida.nome);
        
    }

    public void start(){

        //joga a excecao caso a porta nao esteja liberada e o socket nao possa ser criado

        try{

            while(true) {

                //cria uma thread com um objeto de tratamento para cada cliente conectado
                //o codigo do servidor interagindo com o cliente e colocado na classe de tratamento

                Socket cliente = servidor.accept();
                clientCount++;

                //PrintStream ps = new PrintStream(cliente.getOutputStream());
                
                //ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
                TratamentoThreadServidor tratamentoThreadServidor = new TratamentoThreadServidor(cliente, this);

                this.clientes.add(os);

                System.out.println("Nova conexao com o cliente "+ clientCount+". Endereco:" + cliente.getInetAddress().getHostAddress());

                Thread thread = new Thread(tratamentoThreadServidor);

                thread.start();
            }

        }catch(Exception e){

            System.out.println("exception: " + e);

        }
        finally {
            try {
                cliente.close();
                servidor.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main (String[] args){

        Servidor server = new Servidor();
        server.start();
    }
}
