package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private TicketManager ticketManager;
    private UserManager userManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        ticketManager = new TicketManager();
        userManager = new UserManager(); // Instancia o UserManager para gerenciar os usuários

        setTitle("Sistema EasyTicket");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        // Adicionar listener para salvar dados ao fechar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ticketManager.salvarDados();
            }
        });
    }

    private void initComponents() {
        // Inicializa o CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Painel do login e cadastro
        LoginPanel loginPanel = new LoginPanel(userManager, this);
        CadastroPanel cadastroPanel = new CadastroPanel(userManager, this);  // Passando o MainFrame para CadastroPanel

        // Painel do menu principal
        JPanel menuPanel = createMenuPanel();

        // Painéis das funcionalidades
        BuyTicketPanel buyPanel = new BuyTicketPanel(ticketManager, this, userManager);
        PrintTicketPanel printPanel = new PrintTicketPanel(ticketManager, this, userManager);
        StatisticsPanel statsPanel = new StatisticsPanel(ticketManager, this);

        // Adiciona os painéis ao CardLayout com identificadores
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(cadastroPanel, "Cadastro");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(buyPanel, "Comprar");
        mainPanel.add(printPanel, "Imprimir");
        mainPanel.add(statsPanel, "Estatísticas");

        // Define o painel inicial
        cardLayout.show(mainPanel, "Login");

        setContentPane(mainPanel);
    }

    private JPanel createMenuPanel() {
        // Painel do menu principal
        JPanel menuPanel = new JPanel(new BorderLayout());

        // Botões do menu
        JButton buyTicketButton = new JButton("Comprar Ingresso");
        JButton printTicketButton = new JButton("Imprimir Ingresso");
        JButton statisticsButton = new JButton("Estatísticas de Vendas");
        JButton cancelTicketButton = new JButton("Cancelar Ingresso");
        JButton exitButton = new JButton("Sair");

        // Adicionando ações aos botões
        buyTicketButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Comprar");
        });

        printTicketButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Imprimir");
        });

        statisticsButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Estatísticas");
        });

        cancelTicketButton.addActionListener(e -> {
            // Exibe a tela de cancelamento de ingresso
            SwingUtilities.invokeLater(() -> {
                CancelTicketPanel cancelPanel = new CancelTicketPanel(ticketManager, userManager);
                JFrame frame = new JFrame("Cancelar Ingresso");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 300); // Ajuste o tamanho da janela
                frame.add(cancelPanel);  // Adiciona o painel ao frame
                frame.setLocationRelativeTo(null);  // Centraliza a janela
                frame.setVisible(true);  // Exibe a janela
            });
        });



        exitButton.addActionListener(e -> System.exit(0));

        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(buyTicketButton);
        buttonPanel.add(printTicketButton);
        buttonPanel.add(statisticsButton);
        buttonPanel.add(cancelTicketButton);
        buttonPanel.add(exitButton);

        // Adiciona uma margem lateral ao buttonPanel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionando ao painel do menu
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        return menuPanel;
    }

    // Método para voltar ao menu principal
    public void showMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    // Método para ir ao painel de Cadastro
    public void showCadastro() {
        cardLayout.show(mainPanel, "Cadastro");
    }

    // Método para ir ao painel de Login
    public void showLogin() {
        cardLayout.show(mainPanel, "Login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}