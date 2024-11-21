package easyticket;

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

        // Campo para inserir o CPF
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();

        // Botões
        JButton printButton = new JButton("Imprimir Ingressos");
        JButton backButton = new JButton("Voltar");

        // Área de texto para exibir os ingressos
        JTextArea ticketsArea = new JTextArea();
        ticketsArea.setEditable(false);
        ticketsArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fonte monoespaçada para melhor alinhamento

        // Painel para os campos de entrada e botões
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(cpfLabel);
        inputPanel.add(cpfField);
        inputPanel.add(printButton);
        inputPanel.add(backButton);

        // Adicionar a borda vazia ao inputPanel
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Painel intermediário para conter o inputPanel e a ticketsArea
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Adicionar a borda vazia ao contentPanel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionar o inputPanel ao norte do contentPanel
        contentPanel.add(inputPanel, BorderLayout.NORTH);

        // Adicionar o ticketsArea (dentro de um JScrollPane) ao centro do contentPanel
        contentPanel.add(new JScrollPane(ticketsArea), BorderLayout.CENTER);

        // Adicionar o contentPanel ao painel principal
        add(contentPanel, BorderLayout.CENTER);

        ticketsArea.setMargin(new Insets(10, 10, 10, 10));

        // Ação do botão imprimir
        printButton.addActionListener(e -> {
            String cpf = cpfField.getText().trim();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "CPF não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ticketManager.isCpfValido(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF inválido! Deve conter 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
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
