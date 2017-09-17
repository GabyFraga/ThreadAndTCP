package clienteEServidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TratamentoThreadCliente implements Runnable {

    private Socket socket;
    private String nome;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    String escolha;

    public TratamentoThreadCliente(Socket socket, String nome){

        this.socket = socket;
        this.nome = nome;
        
        System.out.println("Seu nome de jogador e: " + this.nome);
                

    }

    public void setSocket(Socket socket){

        this.socket = socket;

    }

    public Socket getSocket(Socket socket){

        return this.socket;
    }

    /**
     * executa o codigo do servidor para lidar com o cliente.
     * no caso, receber a mensagem do cliente conectado
     */

    public void run(){

        try {

            /*Scanner scanner = new Scanner(this.socket.getInputStream());

            while (scanner.hasNextLine()) {

                System.out.println(scanner.nextLine());

            }*/

            Partida partida = new Partida(nome);
            
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
                        
            Scanner scanner = new Scanner(System.in);
            
            String msg = (String) is.readObject();
            System.out.println(msg);
            msg = (String) is.readObject();
            System.out.println(msg);
            msg = (String) is.readObject();
            System.out.println(msg);
            msg = (String) is.readObject();
            System.out.println(msg);
            msg = (String) is.readObject();
            System.out.println(msg);
            msg = (String) is.readObject();
            System.out.println(msg);
            
            
            //os.writeObject("SALVE TRUTA"); / isso aqui tá printando no server

            
            while(true){
                
                //if(Servidor.clientCount == 3){
                
                System.out.println(partida.comecaRodada);
                escolha = scanner.nextLine();
                
                while(!(escolha.equals("1")) && !(escolha.equals("2"))){
                    
                    System.out.println("Digite apenas \"1\" ou \"2\"");
                    escolha = scanner.nextLine();
                
                }
                
                partida.escolha = Integer.parseInt(escolha); 
                os.writeObject(partida);                
                //partidaEnvio = (Partida)is.readObject();                
                
                //}
            }
                    
        }catch(Exception e){

            System.out.println("Excecao na classe TratamentoThreadCliente: " + e);
        }
    }
}
