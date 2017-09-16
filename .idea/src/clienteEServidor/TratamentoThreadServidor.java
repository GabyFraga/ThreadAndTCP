package clienteEServidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TratamentoThreadServidor implements Runnable {

    private Socket socket;
    private Servidor servidor;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private int clientCount;

    public TratamentoThreadServidor(Socket socket, Servidor servidor){

        this.socket = socket;
        this.servidor = servidor;

    }

    public void setServidor(Servidor servidor){

        this.servidor = servidor;

    }

    public Servidor getServidor(Servidor servidor){

        return this.servidor;
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

                servidor.repasseDeMensagem(scanner.nextLine());

            }*/
                       
            
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            
            //String msg = (String) is.readObject();
            //System.out.println(msg);
            
            //servidor.repasseDeMensagem("Selecione \"1\" ou \"2\":");
            
            //int x = servidor.getCountClients();
            
            //while(x > 0) {
            //	int a = (Integer) is.readInt();
            //}
            
            while(true){
                Partida partida = (Partida) is.readObject();
                servidor.addToMatch(partida);
                servidor.repasseDeMensagem("O jogador " + servidor.getWinner() + "venceu.");
            }
                
            
            
        }catch(Exception e){

            System.out.println("Excecao na classe TratamentoThreadServidor: " + e);
        }
    }
}
