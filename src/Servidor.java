import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {


    private List<PrintStream> clientes;

    public Servidor(){


        this.clientes = new ArrayList<PrintStream>();

    }


    public void repasseDeMensagem(String mensagem) {

        for (PrintStream cliente : this.clientes) {
            cliente.println(mensagem);
        }
    }

    public void iniciar() throws IOException{

        //joga a excecao caso a porta nao esteja liberada e o socket nao possa ser criado

        ServerSocket servidor = new ServerSocket(8080);

        System.out.println("Porta 8080 aberta!");

        while(true) {

            //cria uma thread com um objeto de tratamento para cada cliente conectado
            //o codigo do servidor interagindo com o cliente e colocado na classe de tratamento

            Socket cliente = servidor.accept();
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            TratamentoThreadServidor tratamentoThreadServidor = new TratamentoThreadServidor(cliente, this);

            this.clientes.add(ps);

            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            Thread thread = new Thread(tratamentoThreadServidor);

            thread.start();
        }

    }

    public static void main (String[] args){

        try {

            Servidor servidor = new Servidor();

            servidor.iniciar();

        }catch(Exception e){

            System.out.println("Exceção: " + e);

        }
    }
}
