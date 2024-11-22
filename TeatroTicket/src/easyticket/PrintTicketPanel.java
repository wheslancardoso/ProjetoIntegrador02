package easyticket;

import javax.swing.*;
import java.awt.*;

public class PrintTicketPanel extends JPanel {
    private TicketManager ticketManager;
    private MainFrame mainFrame;
    private UserManager userManager;

    public PrintTicketPanel(TicketManager ticketManager, MainFrame mainFrame, UserManager userManager) {
        this.ticketManager = ticketManager;
        this.mainFrame = mainFrame;
        this.userManager = userManager;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Removido o campo de CPF
        JTextArea ticketsArea = new JTextArea();
        ticketsArea.setEditable(false);
        ticketsArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fonte monoespaçada para melhor alinhamento

        JButton printButton = new JButton("Imprimir Ingressos");
        JButton backButton = new JButton("Voltar");

        // Painel para os campos de entrada e botões
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
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
            User user = userManager.getLoggedInUser(); // Obtém o usuário logado
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Você precisa estar logado para imprimir ingressos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cpf = user.getCpf();  // Usa o CPF do usuário logado
            String ingressos = ticketManager.imprimirIngressos(cpf);
            ticketsArea.setText(ingressos);
        });

        // Ação do botão voltar
        backButton.addActionListener(e -> {
            mainFrame.showMenu();
        });
    }
}

