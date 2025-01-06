import javax.swing.table.DefaultTableModel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface {
    /**
     * Exibe a tela inicial da aplicação.
     * 
     * @param controllerPedido O controlador responsável por gerenciar os pedidos.
     */
    public void telaInicial(ControllerPedido controllerPedido) {
        ArrayList<Pedido> pedidos = controllerPedido.listarPedidos();
        /* ==================== Window ==================== */
        JFrame frame = new JFrame("Pedidos FoodTrack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/assets/logo.png"));

        /* ==================== Body ==================== */
        JPanel panelBody = new JPanel();
        panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
        panelBody.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        /* ==================== Header ==================== */
        JPanel panelHeader = new JPanel();
        JLabel labelTitle = new JLabel("FoodTrack - Sistema de Pedidos", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        panelHeader.add(labelTitle);

        /* ==================== List Pedidos ==================== */
        String[] colunas = { "ID", "Nome", "Valor", "Estado" };
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
        tableModel.setColumnIdentifiers(colunas);
        for (Pedido pedido : pedidos) {
            tableModel.addRow(new Object[] { pedido.getId(), pedido.getNome(), pedido.getValor(), pedido.getEstado() });
        }

        JTable tablePedidos = new JTable();
        tablePedidos.setModel(tableModel);
        JPanel panelPedidosList = criarPanelPedidosList(tablePedidos);

        /* ==================== Estatisticas ==================== */
        JLabel labelTotalPedidos = new JLabel(
                "Total de Pedidos: " + controllerPedido.quantidadePedidos());
        JLabel labelPedidosEntregues = new JLabel(
                "Pedidos Entregues: " + controllerPedido.quantidadePedidosEntregues());
        JLabel labelPedidosEmProcessamento = new JLabel(
                "Pedidos em Processamento: " + controllerPedido.quantidadePedidosEmProcessamento());
        JLabel labelPercentagemPedidos = new JLabel(
                "Percentagem de Pedidos: " + controllerPedido.porcentagemPorEstado());
        JLabel labelTotalValor = new JLabel(
                String.format("Total Valor: €%.2f", controllerPedido.calcularTotalPedidos()));

        JPanel panelEstatisticas = criarPanelEstatisticas(controllerPedido, labelTotalPedidos, labelPedidosEntregues,
                labelPedidosEmProcessamento, labelPercentagemPedidos, labelTotalValor);

        /* ==================== Inserir Pedido ==================== */
        JTextField inputNome = new JTextField();
        JTextField inputValor = new JTextField();
        JButton buttonInserirPedido = new JButton();
        JPanel panelInserirPedidos = criarPanelInserirPedidos(inputNome, inputValor, buttonInserirPedido,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nome = inputNome.getText();
                        double valor = Double.parseDouble(inputValor.getText());
                        Pedido pedido = new Pedido(nome, valor);
                        controllerPedido.adicionarPedido(pedido);
                        JOptionPane.showMessageDialog(null, "Pedido inserido com sucesso!");
                        inputNome.setText("");
                        inputValor.setText("");

                        atualizarTabela(tablePedidos, controllerPedido);

                        atualizarEstatisticas(labelTotalPedidos, labelPedidosEntregues,
                                labelPedidosEmProcessamento, labelPercentagemPedidos,
                                labelTotalValor, controllerPedido);
                    }
                });

        /* ==================== Atualizar Estado ==================== */
        JTextField inputId = new JTextField();
        JComboBox<EstadoPedido> inputEstado = new JComboBox<>();
        JButton buttonAtualizarEstado = new JButton();
        JPanel panelAtualizarEstado = criarPanelAtualizarEstado(inputId, inputEstado, buttonAtualizarEstado,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(inputId.getText());
                        EstadoPedido estado = (EstadoPedido) inputEstado.getSelectedItem();
                        Pedido pedido = controllerPedido.buscarPedido(id);
                        if (pedido != null) {
                            pedido.setEstado(estado);
                            controllerPedido.atualizarPedido(pedido);
                            JOptionPane.showMessageDialog(null, "Estado atualizado com sucesso!");
                            inputId.setText("");

                            atualizarTabela(tablePedidos, controllerPedido);

                            atualizarEstatisticas(labelTotalPedidos, labelPedidosEntregues,
                                    labelPedidosEmProcessamento, labelPercentagemPedidos,
                                    labelTotalValor, controllerPedido);
                        } else {
                            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
                        }
                    }
                });

        /* ==================== Excluir Pedido ==================== */
        JTextField inputIdExcluir = new JTextField();
        JButton buttonExcluirPedido = new JButton();
        JPanel panelExcluirPedido = criarPanelExcluirPedido(inputIdExcluir, buttonExcluirPedido, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(inputIdExcluir.getText());
                controllerPedido.removerPedido(id);
                JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
                inputIdExcluir.setText("");

                atualizarTabela(tablePedidos, controllerPedido);

                atualizarEstatisticas(labelTotalPedidos, labelPedidosEntregues,
                        labelPedidosEmProcessamento, labelPercentagemPedidos,
                        labelTotalValor, controllerPedido);
            }
        });

        /* ==================== Add to Frame ==================== */
        panelBody.add(panelHeader);
        panelBody.add(panelPedidosList);
        panelBody.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBody.add(panelInserirPedidos);
        panelBody.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBody.add(panelAtualizarEstado);
        panelBody.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBody.add(panelExcluirPedido);
        panelBody.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBody.add(panelEstatisticas);
        JScrollPane scrollPaneBody = new JScrollPane(panelBody);
        frame.add(scrollPaneBody);
        frame.setVisible(true);
    }

    /**
     * Cria o painel de listagem de pedidos.
     *
     * @param tablePedidos A tabela de pedidos.
     * @return O painel de listagem de pedidos.
     */
    private JPanel criarPanelPedidosList(JTable tablePedidos) {
        JPanel panelPedidosList = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tablePedidos);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 200));
        panelPedidosList.add(scrollPane);
        return panelPedidosList;
    }

    /**
     * Cria o painel de inserção de pedidos.
     *
     * @return O painel de inserção de pedidos.
     */
    private JPanel criarPanelInserirPedidos(JTextField inputNome, JTextField inputValor, JButton buttonInserirPedido,
            ActionListener actionListener) {
        JPanel panelInserirPedidos = new JPanel(new BorderLayout());

        JLabel labelInserirPedido = new JLabel("Inserir Pedido");
        labelInserirPedido.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel panelInserirPedidosForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelNome = new JLabel("Nome:");
        inputNome.setColumns(20);
        JLabel labelValor = new JLabel("Valor:");
        inputValor.setColumns(10);
        buttonInserirPedido.setText("Inserir Pedido");
        buttonInserirPedido.addActionListener(actionListener);

        panelInserirPedidos.add(labelInserirPedido, BorderLayout.NORTH);
        panelInserirPedidosForm.add(labelNome);
        panelInserirPedidosForm.add(inputNome);
        panelInserirPedidosForm.add(labelValor);
        panelInserirPedidosForm.add(inputValor);
        panelInserirPedidosForm.add(buttonInserirPedido);
        panelInserirPedidos.add(panelInserirPedidosForm, BorderLayout.CENTER);

        return panelInserirPedidos;
    }

    /**
     * Cria o painel de atualização de estado.
     *
     * @return O painel de atualização de estado.
     */
    private JPanel criarPanelAtualizarEstado(JTextField inputId, JComboBox<EstadoPedido> inputEstado,
            JButton buttonAtualizarEstado, ActionListener actionListener) {
        JPanel panelAtualizarEstado = new JPanel(new BorderLayout());

        JLabel labelAtualizarEstado = new JLabel("Atualizar Estado");
        labelAtualizarEstado.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel panelAtualizarEstadoForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelId = new JLabel("ID:");
        inputId.setColumns(5);
        JLabel labelEstado = new JLabel("Estado:");
        inputEstado.setModel(new DefaultComboBoxModel<>(EstadoPedido.values()));
        buttonAtualizarEstado.setText("Atualizar Estado");
        buttonAtualizarEstado.addActionListener(actionListener);

        panelAtualizarEstado.add(labelAtualizarEstado, BorderLayout.NORTH);
        panelAtualizarEstadoForm.add(labelId);
        panelAtualizarEstadoForm.add(inputId);
        panelAtualizarEstadoForm.add(labelEstado);
        panelAtualizarEstadoForm.add(inputEstado);
        panelAtualizarEstadoForm.add(buttonAtualizarEstado);
        panelAtualizarEstado.add(panelAtualizarEstadoForm, BorderLayout.CENTER);

        return panelAtualizarEstado;
    }

    /**
     * Cria o painel de exclusão de pedidos.
     *
     * @return O painel de exclusão de pedidos.
     */
    private JPanel criarPanelExcluirPedido(JTextField inputIdExcluir, JButton buttonExcluirPedido,
            ActionListener actionListener) {
        JPanel panelExcluirPedido = new JPanel(new BorderLayout());

        JLabel labelExcluirPedido = new JLabel("Excluir Pedido");
        labelExcluirPedido.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel panelExcluirPedidoForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelIdExcluir = new JLabel("ID:");
        inputIdExcluir.setColumns(5);
        buttonExcluirPedido.setText("Excluir Pedido");
        buttonExcluirPedido.addActionListener(actionListener);

        panelExcluirPedido.add(labelExcluirPedido, BorderLayout.NORTH);
        panelExcluirPedidoForm.add(labelIdExcluir);
        panelExcluirPedidoForm.add(inputIdExcluir);
        panelExcluirPedidoForm.add(buttonExcluirPedido);
        panelExcluirPedido.add(panelExcluirPedidoForm, BorderLayout.CENTER);

        return panelExcluirPedido;
    }

    /**
     * Cria o painel de estatísticas.
     *
     * @param controllerPedido            O controlador responsável por gerenciar os
     *                                    pedidos.
     * @param labelTotalPedidos           Label para o total de pedidos.
     * @param labelPedidosEntregues       Label para os pedidos entregues.
     * @param labelPedidosEmProcessamento Label para os pedidos em processamento.
     * @param labelPercentagemPedidos     Label para a percentagem de pedidos.
     * @param labelTotalValor             Label para o valor total.
     * @return O painel de estatísticas.
     */
    private JPanel criarPanelEstatisticas(ControllerPedido controllerPedido, JLabel labelTotalPedidos,
            JLabel labelPedidosEntregues, JLabel labelPedidosEmProcessamento, JLabel labelPercentagemPedidos,
            JLabel labelTotalValor) {
        JPanel panelEstatisticas = new JPanel(new BorderLayout());

        JLabel labelEstatisticas = new JLabel("Estatísticas");
        labelEstatisticas.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel panelEstatisticasConteudo = new JPanel(new BorderLayout());

        JPanel panelEstatisticasQuantidade = new JPanel();
        panelEstatisticasQuantidade.setLayout(new BoxLayout(panelEstatisticasQuantidade, BoxLayout.X_AXIS));
        panelEstatisticasQuantidade.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelEstatisticasQuantidade.add(labelTotalPedidos);
        panelEstatisticasQuantidade.add(Box.createRigidArea(new Dimension(10, 0)));
        panelEstatisticasQuantidade.add(labelPedidosEntregues);
        panelEstatisticasQuantidade.add(Box.createRigidArea(new Dimension(10, 0)));
        panelEstatisticasQuantidade.add(labelPedidosEmProcessamento);

        panelEstatisticasConteudo.add(panelEstatisticasQuantidade, BorderLayout.NORTH);
        panelEstatisticasConteudo.add(labelPercentagemPedidos, BorderLayout.CENTER);
        panelEstatisticasConteudo.add(labelTotalValor, BorderLayout.SOUTH);
        panelEstatisticas.add(labelEstatisticas, BorderLayout.NORTH);
        panelEstatisticas.add(panelEstatisticasConteudo, BorderLayout.CENTER);

        return panelEstatisticas;
    }

    /**
     * Atualiza os dados exibidos na tabela de pedidos.
     *
     * @param tablePedidos     A tabela que será atualizada.
     * @param controllerPedido O controlador que fornece a lista atualizada de
     *                         pedidos.
     */
    private void atualizarTabela(JTable tablePedidos, ControllerPedido controllerPedido) {
        DefaultTableModel tableModel = (DefaultTableModel) tablePedidos.getModel();
        tableModel.setRowCount(0);
        ArrayList<Pedido> pedidos = controllerPedido.listarPedidos();
        for (Pedido pedido : pedidos) {
            tableModel.addRow(new Object[] { pedido.getId(), pedido.getNome(), pedido.getValor(), pedido.getEstado() });
        }
    }

    /**
     * Atualiza as estatísticas exibidas na interface.
     *
     * @param labelTotalPedidos           Rótulo para o total de pedidos.
     * @param labelPedidosEntregues       Rótulo para o total de pedidos entregues.
     * @param labelPedidosEmProcessamento Rótulo para o total de pedidos em
     *                                    processamento.
     * @param labelPercentagemPedidos     Rótulo para a porcentagem de pedidos por
     *                                    estado.
     * @param labelTotalValor             Rótulo para o valor total dos pedidos.
     * @param controllerPedido            O controlador que fornece as estatísticas
     *                                    atualizadas.
     */
    private void atualizarEstatisticas(JLabel labelTotalPedidos, JLabel labelPedidosEntregues,
            JLabel labelPedidosEmProcessamento, JLabel labelPercentagemPedidos,
            JLabel labelTotalValor, ControllerPedido controllerPedido) {
        labelTotalPedidos.setText("Total de Pedidos: " + controllerPedido.quantidadePedidos());
        labelPedidosEntregues.setText("Pedidos Entregues: " + controllerPedido.quantidadePedidosEntregues());
        labelPedidosEmProcessamento
                .setText("Pedidos em Processamento: " + controllerPedido.quantidadePedidosEmProcessamento());
        labelPercentagemPedidos.setText("Percentagem de Pedidos: " + controllerPedido.porcentagemPorEstado());
        labelTotalValor.setText(String.format("Total Valor: €%.2f", controllerPedido.calcularTotalPedidos()));
    }

    /**
     * Aplica o tema especificado à interface gráfica.
     *
     * @param tema O nome do tema (Look and Feel) a ser aplicado.
     */
    public static void aplicarLookAndFeel(String tema) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (tema.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
            System.out.println("Tema não encontrado: " + tema);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
