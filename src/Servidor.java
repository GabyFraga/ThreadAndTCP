import java.net.*;
import java.util.Scanner;

public class Servidor {

    public static void main (String[] args){

        try {

            //joga a excecao caso a porta nao esteja liberada e o socket nao possa ser criado

            ServerSocket servidor = new ServerSocket(8080);

            System.out.println("Porta 8080 aberta!");

            Socket cliente = servidor.accept();
            Scanner scanner = new Scanner(cliente.getInputStream());

            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            while (scanner.hasNextLine()){

                System.out.println(scanner.nextLine());
            }

            scanner.close();
            cliente.close();
            servidor.close();

        }catch(Exception e){

            System.out.println("Exceção: " + e);

        }
    }
}
