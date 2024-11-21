package easyticket;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {
    private TicketManager ticketManager;
    private MainFrame mainFrame;

    public StatisticsPanel(TicketManager ticketManager, MainFrame mainFrame) {
        this.ticketManager = ticketManager;
        this.mainFrame = mainFrame;
        initComponents();

    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);

        JButton generateButton = new JButton("Gerar Estatísticas");
        JButton backButton = new JButton("Voltar");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 linha, 2 colunas, espaçamento horizontal de 10 pixels);
        buttonPanel.add(generateButton);
        buttonPanel.add(backButton);

        // Adicionar a borda vazia ao buttonPanel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Painel intermediário para conter os componentes
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Adicionar a borda vazia ao contentPanel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionar os componentes ao contentPanel
        contentPanel.add(buttonPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(statsArea), BorderLayout.CENTER);

        // Adicionar o contentPanel ao painel principal
        add(contentPanel, BorderLayout.CENTER);

        statsArea.setMargin(new Insets(10, 10, 10, 10));

        // Ação do botão gerar
        generateButton.addActionListener(e -> {
            String estatisticas = ticketManager.gerarEstatisticas();
            statsArea.setText(estatisticas);
        });

        // Ação do botão voltar
        backButton.addActionListener(e -> {
            mainFrame.showMenu();
        });
    }

}
