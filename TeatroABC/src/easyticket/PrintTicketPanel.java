package easyticket;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class PrintTicketPanel extends JPanel {
    private TicketManager ticketManager;
    private MainFrame mainFrame;

    public PrintTicketPanel(TicketManager ticketManager, MainFrame mainFrame) {
        this.ticketManager = ticketManager;
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();

        JButton printButton = new JButton("Imprimir Ingressos");
        JButton backButton = new JButton("Voltar");

        JTextArea ticketsArea = new JTextArea();
        ticketsArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(cpfLabel);
        inputPanel.add(cpfField);
        inputPanel.add(printButton);
        inputPanel.add(backButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(ticketsArea), BorderLayout.CENTER);

        // Ação do botão imprimir
        printButton.addActionListener(e -> {
            String cpf = cpfField.getText();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "CPF não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String ingressos = ticketManager.imprimirIngressos(cpf);
            ticketsArea.setText(ingressos);
        });

        // Ação do botão voltar
        backButton.addActionListener(e -> {
            mainFrame.showMenu();
        });
    }
}
