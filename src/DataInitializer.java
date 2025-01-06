import java.util.ArrayList;

/**
 * The DataInitializer class provides a method to initialize a list of Pedido
 * objects.
 * This class is used to create sample data for testing or demonstration
 * purposes.
 */
public class DataInitializer {
    public static ArrayList<Pedido> initializePedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(1, "Hamburguer", 10.0, EstadoPedido.ENTREGUE));
        pedidos.add(new Pedido(2, "Batata Frita", 5.0, EstadoPedido.EM_PROCESSAMENTO));
        pedidos.add(new Pedido(3, "Refrigerante", 5.0, EstadoPedido.CANCELADO));
        pedidos.add(new Pedido(4, "Sundae", 7.0, EstadoPedido.NOVO));
        pedidos.add(new Pedido(5, "Taco", 7.5, EstadoPedido.EM_PROCESSAMENTO));
        pedidos.add(new Pedido(6, "Salada", 6.0, EstadoPedido.NOVO));
        pedidos.add(new Pedido(7, "Pizza", 12.0, EstadoPedido.EM_PROCESSAMENTO));
        pedidos.add(new Pedido(8, "Sorvete", 4.0, EstadoPedido.ENTREGUE));
        pedidos.add(new Pedido(9, "Café", 3.0, EstadoPedido.CANCELADO));
        pedidos.add(new Pedido(10, "Sanduíche", 9.0, EstadoPedido.NOVO));
        return pedidos;
    }
}