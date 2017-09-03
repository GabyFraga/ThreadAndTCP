import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TratamentoThreadServidor implements Runnable {

    private Socket socket;
    private Servidor servidor;

    public TratamentoThreadServidor(Socket socket, Servidor servidor){

        this.socket = socket;
        this.servidor = servidor;

    }

    public void setSoservidor(Servidor servidor){

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

            Scanner scanner = new Scanner(this.socket.getInputStream());

            while (scanner.hasNextLine()) {

                servidor.repasseDeMensagem(scanner.nextLine());

            }
        }catch(Exception e){

            System.out.println("Exceção na classe TratamentoThreadServidor: " + e);
        }
    }
}
