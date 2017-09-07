package clienteEServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    private Socket cliente;
    public String nome;
    public boolean online = true;

    public void connect(String address, short port) throws IOException{

            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Digite um nome para começar a jogar.");
            nome = bf.readLine();

            cliente = new Socket(address, port);

            System.out.println("Cliente conectado no servidor na porta " + port);

            Scanner teclado = new Scanner(System.in);
            PrintStream saida = new PrintStream(cliente.getOutputStream());

            TratamentoThreadCliente tratamentoThreadCliente = new TratamentoThreadCliente(cliente);

            Thread thread = new Thread(tratamentoThreadCliente);

            thread.start();

            while (online) {

                saida.println(teclado.nextLine());

            }

            saida.close();
            teclado.close();
            cliente.close();

    }

    public static void main(String[] args)throws UnknownHostException, IOException {

        Cliente client = new Cliente();

        client.connect("127.0.0.1", (short) 8080);

    }
}
