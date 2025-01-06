import java.util.ArrayList;

/**
 * Classe responsável por gerenciar pedidos no sistema.
 * Contém métodos para adicionar, atualizar, remover, buscar e listar pedidos,
 * além de cálculos e estatísticas relacionadas aos pedidos.
 * 
 * @author KalebeCCS
 * @since 2025
 */
public class ControllerPedido {
    private ArrayList<Pedido> pedidos;
    private int nextId;

    /**
     * Construtor da classe ControllerPedido.
     * Inicializa a lista de pedidos e o próximo ID disponível.
     */
    public ControllerPedido() {
        pedidos = new ArrayList<Pedido>();
        nextId = 1;
    }

    /**
     * Adiciona um novo pedido à lista de pedidos.
     * O pedido recebe um ID único automaticamente.
     * 
     * @param pedido O pedido a ser adicionado.
     */
    public void adicionarPedido(Pedido pedido) {
        pedido.setId(nextId);
        nextId++;
        pedidos.add(pedido);
    }

    /**
     * Atualiza as informações de um pedido existente.
     * Procura pelo ID do pedido e atualiza seus dados.
     * 
     * @param pedido O pedido com as informações atualizadas.
     */
    public void atualizarPedido(Pedido pedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == pedido.getId()) {
                p.setNome(pedido.getNome());
                p.setValor(pedido.getValor());
                p.setEstado(pedido.getEstado());
            }
        }
    }

    /**
     * Remove um pedido da lista com base no ID fornecido.
     * 
     * @param id O ID do pedido a ser removido.
     */
    public void removerPedido(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                pedidos.remove(p);
                break;
            }
        }
    }

    /**
     * Busca um pedido na lista pelo seu ID.
     * 
     * @param id O ID do pedido a ser buscado.
     * @return O pedido correspondente, ou null se não for encontrado.
     */
    public Pedido buscarPedido(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retorna a lista de todos os pedidos.
     * 
     * @return Uma lista contendo todos os pedidos.
     */
    public ArrayList<Pedido> listarPedidos() {
        return pedidos;
    }

    /**
     * Obtém a quantidade total de pedidos cadastrados.
     * 
     * @return O número total de pedidos.
     */
    public int quantidadePedidos() {
        return pedidos.size();
    }

    /**
     * Obtém a quantidade de pedidos com estado "NOVO".
     * 
     * @return O número de pedidos novos.
     */
    public int quantidadePedidosNovos() {
        int quantidade = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.NOVO) {
                quantidade++;
            }
        }
        return quantidade;
    }

    /**
     * Obtém a quantidade de pedidos com estado "ENTREGUE".
     * 
     * @return O número de pedidos entregues.
     */
    public int quantidadePedidosEntregues() {
        int quantidade = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.ENTREGUE) {
                quantidade++;
            }
        }
        return quantidade;
    }

    /**
     * Obtém a quantidade de pedidos com estado "EM_PROCESSAMENTO".
     * 
     * @return O número de pedidos em processamento.
     */
    public int quantidadePedidosEmProcessamento() {
        int quantidade = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.EM_PROCESSAMENTO) {
                quantidade++;
            }
        }
        return quantidade;
    }

    /**
     * Obtém a quantidade de pedidos com estado "CANCELADO".
     * 
     * @return O número de pedidos cancelados.
     */
    public int quantidadePedidosCancelados() {
        int quantidade = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado() == EstadoPedido.CANCELADO) {
                quantidade++;
            }
        }
        return quantidade;
    }

    /**
     * Calcula a porcentagem de pedidos em cada estado (NOVO, ENTREGUE,
     * EM_PROCESSAMENTO, CANCELADO) em relação ao total.
     * 
     * @return Uma string contendo as porcentagens de cada estado.
     */
    public String porcentagemPorEstado() {
        int total = pedidos.size();
        int novos = quantidadePedidosNovos();
        int entregues = quantidadePedidosEntregues();
        int emProcessamento = quantidadePedidosEmProcessamento();
        int cancelados = quantidadePedidosCancelados();
        return "Novos: " + (novos * 100 / total) + "%\n Entregues: " + (entregues * 100 / total)
                + "%\n Em Processamento: "
                + (emProcessamento * 100 / total) + "%\n Cancelados: " + (cancelados * 100 / total) + "%";
    }

    /**
     * Calcula o valor total de todos os pedidos cadastrados.
     * 
     * @return O valor total dos pedidos.
     */
    public double calcularTotalPedidos() {
        double total = 0;
        for (Pedido p : pedidos) {
            total += p.getValor();
        }
        return total;
    }
}
