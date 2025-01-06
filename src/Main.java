import java.util.ArrayList;

/**
 * Classe principal do programa. Responsável por inicializar e executar a
 * aplicação.
 * Cria e adiciona pedidos ao controlador e inicializa a interface gráfica.
 * 
 * @author KalebeCCS
 * @version 1.0
 */
public class Main {

    /**
     * Método principal do programa. Cria e adiciona pedidos ao controlador
     * e inicializa a interface gráfica.
     * 
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        ArrayList<Pedido> pedidos = DataInitializer.initializePedidos();

        ControllerPedido controllerPedido = new ControllerPedido();
        for (Pedido pedido : pedidos) {
            controllerPedido.adicionarPedido(pedido);
        }

        Interface.aplicarLookAndFeel("Windows");
        Interface myInterface = new Interface();
        myInterface.telaInicial(controllerPedido);
    }
}
