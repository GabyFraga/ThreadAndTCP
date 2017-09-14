package clienteEServidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TratamentoThreadServidor implements Runnable {

    private Socket socket;
    private Servidor servidor;
    private ObjectInputStream is;
    private ObjectOutputStream os;

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
                       
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
            int x = 0;
            
            while(x < 10){
                
                Partida partida = (Partida) is.readObject();
                servidor.printName(partida);
                x++;
                
            }
            
        }catch(Exception e){

            System.out.println("Exceção na classe TratamentoThreadServidor: " + e);
        }
    }
}
