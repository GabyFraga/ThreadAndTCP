import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    private Socket cliente;

    public void connect(String address, short port) throws IOException{

        try {

            cliente = new Socket(address, port);

            System.out.println("Cliente conectado no servidor na porta " + port);

            Scanner teclado = new Scanner(System.in);
            PrintStream saida = new PrintStream(cliente.getOutputStream());

            TratamentoThreadCliente tratamentoThreadCliente = new TratamentoThreadCliente(cliente);

            Thread thread = new Thread(tratamentoThreadCliente);

            thread.start();

            while (teclado.hasNextLine()) {

                saida.println(teclado.nextLine());

            }

            saida.close();
            teclado.close();
            cliente.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws UnknownHostException, IOException {

        Cliente client = new Cliente();

        client.connect("127.0.0.1", (short) 8080); 

    }
}
