import java.net.*;

public class Servidor {

    public static void main (String[] args){

        try {

            //joga a excecao caso a porta nao esteja liberada e o socket nao possa ser criado

            ServerSocket servidor = new ServerSocket(8080);
            System.out.println("Porta 8080 aberta!");

            while(true) {

                //cria uma thread com um objeto de tratamento para cada cliente conectado
                //o codigo do servidor interagindo com o cliente e colocado na classe de tratamento

                Socket cliente = servidor.accept();
                TratamentoThread tratamentoThread = new TratamentoThread(cliente);

                System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

                Thread thread = new Thread(tratamentoThread);

                thread.start();
            }
        }catch(Exception e){

            System.out.println("Exceção: " + e);

        }
    }
}
