package clienteEServidor;

import javax.swing.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servidor {


    private ServerSocket servidor;
    private Socket cliente;
    private final short PORT = 8081;
    public static short clientCount = 0;
    public HashMap<String, Integer> jogadas;
    private List<PrintStream> clientes;

    public Servidor(){

        try {
            servidor = new ServerSocket(PORT);
            System.out.println("Porta "+PORT+" aberta!");
            //this.clientes = new ArrayList<ObjectOutputStream>();
            this.clientes = new ArrayList<PrintStream>();
            jogadas = new HashMap<String, Integer>();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void repasseDeMensagem(String mensagem) throws IOException {

        
        for (PrintStream cliente : this.clientes) {
            
            System.out.println("mensagem: " + mensagem);
            
            cliente.println(mensagem);
            
        }
    }
    
    public String getWinner(){
        
        String winner = "";
        int oneWin = 0;
        int twoWin = 0;
                
        for (HashMap.Entry<String, Integer> entry : jogadas.entrySet()) {
            String key = entry.getKey();
            int value = (int)entry.getValue();
            
            if (value == 1){
                
                oneWin ++;
                
                if(oneWin == 1 && twoWin != 1){
                
                    winner = key;
                
                }
            
            }else{
                
                
                twoWin ++;
                
                if(twoWin == 1 && oneWin != 1){
                
                    winner = key;
                
                }
                
            }          
            
            
        }
        
        if(oneWin != 1 && twoWin != 1){
            
            winner = "Empate!";
            
        }
        
        return winner;
        
    }
    
    public void addToMatch(Partida partida){
        
        jogadas.put(partida.nome, partida.escolha);
        System.out.println("fulano de nome : " + partida.nome + " jogou " + jogadas.get(partida.nome));
        
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
                
                PrintStream ps = new PrintStream(cliente.getOutputStream());
                ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
                TratamentoThreadServidor tratamentoThreadServidor = new TratamentoThreadServidor(cliente, this);
                this.clientes.add(ps);
                
                System.out.println("Nova conexao com o cliente "+ clientCount+". Endereco: " + cliente.getInetAddress().getHostAddress());
                os.writeObject("[SERVER]: Bem vindo jogador numero " + clientCount +".");
                os.writeObject("Instruções para jogar:");   
                os.writeObject("Em sua vez, escolha apenas um número \"2\" ou \"1\".");
                os.writeObject("Apenas sua primeira escolha será considerada.");
                os.writeObject("Vence a partida o jogador que escolher o número diferente dos demais.");
                os.writeObject("Se nenhum jogador escolher o número diferente, a partida fica empatada.");


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


	public int getCountClients() {
		return clientCount;
	}
}
