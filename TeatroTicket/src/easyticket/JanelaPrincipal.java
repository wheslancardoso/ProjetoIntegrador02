package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaPrincipal extends JFrame {
    private GerenciadorIngressos gerenciadorIngressos;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public JanelaPrincipal() {
        gerenciadorIngressos = new GerenciadorIngressos();
        gerenciadorUsuarios = new GerenciadorUsuarios(); // Instancia o UserManager para gerenciar os usuários

        setTitle("Sistema EasyTicket");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        // Adicionar listener para salvar dados ao fechar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gerenciadorIngressos.salvarDados();
            }
        });
    }

    private void initComponents() {
        // Inicializa o CardLayout
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        // Painel do login e cadastro
        PainelLogin painelLogin = new PainelLogin(gerenciadorUsuarios, this);
        PainelCadastro painelCadastro = new PainelCadastro(gerenciadorUsuarios, this);  // Passando o MainFrame para CadastroPanel

        // Painel do menu principal
        JPanel painelMenu = createMenuPanel();

        // Painéis das funcionalidades
        PainelCompraIngresso painelComprar = new PainelCompraIngresso(gerenciadorIngressos, this, gerenciadorUsuarios);
        PainelImpressaoIngresso painelImprimir = new PainelImpressaoIngresso(gerenciadorIngressos, this, gerenciadorUsuarios);
        PainelEstatisticas painelEstats = new PainelEstatisticas(gerenciadorIngressos, this);

        // Adiciona os painéis ao CardLayout com identificadores
        painelPrincipal.add(painelLogin, "Login");
        painelPrincipal.add(painelCadastro, "Cadastro");
        painelPrincipal.add(painelMenu, "Menu");
        painelPrincipal.add(painelComprar, "Comprar");
        painelPrincipal.add(painelImprimir, "Imprimir");
        painelPrincipal.add(painelEstats, "Estatísticas");

        // Define o painel inicial
        cardLayout.show(painelPrincipal, "Login");

        setContentPane(painelPrincipal);
    }

    private JPanel createMenuPanel() {
        // Painel do menu principal
        JPanel painelMenu = new JPanel(new BorderLayout());

        // Botões do menu
        JButton botaoComprarIngresso = new JButton("Comprar Ingresso");
        JButton botaoImprimirIngresso = new JButton("Imprimir Ingresso");
        JButton botaoEstatisticas = new JButton("Estatísticas de Vendas");
        JButton botaoSair = new JButton("Sair");

        // Adicionando ações aos botões
        botaoComprarIngresso.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "Comprar");
        });

        botaoImprimirIngresso.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "Imprimir");
        });

        botaoEstatisticas.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "Estatísticas");
        });

        botaoSair.addActionListener(e -> System.exit(0));

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 10, 10));
        painelBotoes.add(botaoComprarIngresso);
        painelBotoes.add(botaoImprimirIngresso);
        painelBotoes.add(botaoEstatisticas);
        painelBotoes.add(botaoSair);

        // Adiciona uma margem lateral ao painelBotoes
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionando ao painel do menu
        painelMenu.add(painelBotoes, BorderLayout.CENTER);

        return painelMenu;
    }

    // Método para voltar ao menu principal
    public void mostrarMenu() {
        cardLayout.show(painelPrincipal, "Menu");
    }

    // Método para ir ao painel de Cadastro
    public void mostrarCadastro() {
        cardLayout.show(painelPrincipal, "Cadastro");
    }

    // Método para ir ao painel de Login
    public void mostrarLogin() {
        cardLayout.show(painelPrincipal, "Login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JanelaPrincipal().setVisible(true);
        });
    }
}