import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public void iniciar() throws IOException{

        Socket cliente = new Socket("127.0.0.1", 8080);

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        TratamentoThreadCliente tratamentoThreadCliente = new TratamentoThreadCliente(cliente);

        Thread thread = new Thread(tratamentoThreadCliente);

        thread.start();

        while(teclado.hasNextLine()){

            saida.println(teclado.nextLine());

        }

        saida.close();
        teclado.close();
        cliente.close();

    }

    public static void main(String[] args)throws UnknownHostException, IOException {

        try {

            Cliente cliente = new Cliente();

            cliente.iniciar();

        }catch(Exception e){

            System.out.println("Exceção: " + e);

        }

    }
}
