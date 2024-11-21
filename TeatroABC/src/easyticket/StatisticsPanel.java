package easyticket;

import javax.swing.*;
import java.awt.*;

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(statsArea), BorderLayout.CENTER);

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
