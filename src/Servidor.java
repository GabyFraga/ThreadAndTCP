import java.io.IOException;
import java.net.*;

public class Servidor {

	
	private ServerSocket servidor;
	private Socket cliente;
	private final short PORT = 12346;
	private short clientCount = 0;
	
	
	
	
	public Servidor() {
		try {
			servidor = new ServerSocket(PORT);
			System.out.println("Porta "+PORT+" aberta!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void start() {
        try {
            

            while(true) {

                //cria uma thread com um objeto de tratamento para cada cliente conectado
                //o codigo do servidor interagindo com o cliente e colocado na classe de tratamento

                Socket cliente = servidor.accept();
                clientCount++;
                TratamentoThread tratamentoThread = new TratamentoThread(cliente);

                System.out.println("Nova conexao com o cliente "+ clientCount+". Endereco:" + cliente.getInetAddress().getHostAddress());

                Thread thread = new Thread(tratamentoThread);

                thread.start();
            }
        }catch(Exception e){

            System.out.println("exception: " + e);

        }
        finally {
        	try {
				cliente.close();
				servidor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
    public static void main (String[] args){
    	Servidor server = new Servidor();
    	server.start();

    }
}
