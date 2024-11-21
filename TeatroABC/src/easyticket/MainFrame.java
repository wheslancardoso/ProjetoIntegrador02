package easyticket;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TicketManager ticketManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        ticketManager = new TicketManager();

        setTitle("Sistema EasyTicket");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Inicializa o CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Painel do menu principal
        JPanel menuPanel = createMenuPanel();

        // Painéis das funcionalidades
        BuyTicketPanel buyPanel = new BuyTicketPanel(ticketManager, this);
        PrintTicketPanel printPanel = new PrintTicketPanel(ticketManager, this);
        StatisticsPanel statsPanel = new StatisticsPanel(ticketManager, this);

        // Adiciona os painéis ao CardLayout com identificadores
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(buyPanel, "Comprar");
        mainPanel.add(printPanel, "Imprimir");
        mainPanel.add(statsPanel, "Estatísticas");

        // Define o painel inicial
        cardLayout.show(mainPanel, "Menu");

        setContentPane(mainPanel);
    }

    private JPanel createMenuPanel() {
        // Painel do menu principal
        JPanel menuPanel = new JPanel(new BorderLayout());

        // Botões do menu
        JButton buyTicketButton = new JButton("Comprar Ingresso");
        JButton printTicketButton = new JButton("Imprimir Ingresso");
        JButton statisticsButton = new JButton("Estatísticas de Vendas");
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

        exitButton.addActionListener(e -> System.exit(0));

        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(buyTicketButton);
        buttonPanel.add(printTicketButton);
        buttonPanel.add(statisticsButton);
        buttonPanel.add(exitButton);

        // Adicionando ao painel do menu
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        return menuPanel;
    }

    // Método para voltar ao menu principal
    public void showMenu() {
        cardLayout.show(mainPanel, "Menu");
    }
}

