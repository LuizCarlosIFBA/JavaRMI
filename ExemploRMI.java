import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Interface remota que define os métodos disponíveis no servidor
public interface MiddlewareInterface extends java.rmi.Remote {
    public void sendMessage(String message) throws RemoteException;
}

// Implementação do servidor que estende a classe UnicastRemoteObject e implementa a interface remota
public class MiddlewareServer extends UnicastRemoteObject implements MiddlewareInterface {
    public MiddlewareServer() throws RemoteException {
        // Construtor padrão da classe UnicastRemoteObject
    }
    
    // Implementação do método remoto definido na interface
    public void sendMessage(String message) throws RemoteException {
        System.out.println("Mensagem recebida: " + message);
    }
}

// Classe cliente que se conecta ao servidor remoto
public class MiddlewareClient {
    public static void main(String[] args) {
        try {
            // Cria uma instância do servidor remoto
            MiddlewareInterface server = new MiddlewareServer();
            
            // Registra o servidor para que os clientes possam encontrá-lo
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            
            // Associa o objeto remoto ao nome "middleware"
            java.rmi.Naming.rebind("rmi://localhost/middleware", server);
            
            System.out.println("Servidor registrado e aguardando conexões...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
