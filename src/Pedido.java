/**
 * A classe Pedido representa um pedido com um identificador, nome, valor e
 * estado.
 */
public class Pedido {

    /**
     * O identificador único do pedido.
     */
    private int id;

    /**
     * O nome do pedido.
     */
    private String nome;

    /**
     * O valor do pedido.
     */
    private double valor;

    /**
     * O estado atual do pedido.
     */
    private EstadoPedido estado;

    /**
     * Construtor que cria um novo pedido com o nome e valor especificados.
     * O estado do pedido é definido como NOVO.
     *
     * @param nome  o nome do pedido
     * @param valor o valor do pedido
     */
    public Pedido(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
        this.estado = EstadoPedido.NOVO;
    }

    /**
     * Construtor que cria um pedido com o id, nome, valor e estado especificados.
     *
     * @param id     o identificador do pedido
     * @param nome   o nome do pedido
     * @param valor  o valor do pedido
     * @param estado o estado do pedido
     */
    public Pedido(int id, String nome, double valor, EstadoPedido estado) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.estado = estado;
    }

    /**
     * Obtém o identificador do pedido.
     *
     * @return o identificador do pedido
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do pedido.
     *
     * @param id o novo identificador do pedido
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do pedido.
     *
     * @return o nome do pedido
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do pedido.
     *
     * @param nome o novo nome do pedido
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o valor do pedido.
     *
     * @return o valor do pedido
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor do pedido.
     *
     * @param valor o novo valor do pedido
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtém o estado do pedido.
     *
     * @return o estado do pedido
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Define o estado do pedido.
     *
     * @param estado o novo estado do pedido
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}
