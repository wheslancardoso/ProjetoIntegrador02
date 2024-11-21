package easyticket;

import javax.swing.*;
import java.awt.*;


public class BuyTicketPanel extends JPanel {
    private TicketManager ticketManager;
    private MainFrame mainFrame;

    public BuyTicketPanel(TicketManager ticketManager, MainFrame mainFrame) {
        this.ticketManager = ticketManager;
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();

        JLabel espetaculoLabel = new JLabel("Espetáculo:");
        String[] espetaculos = {"As tranças da vovó careca", "A volta dos que chegaram a partir", "Poeira em alto mar"};
        JComboBox<String> espetaculoBox = new JComboBox<>(espetaculos);

        JLabel sessaoLabel = new JLabel("Sessão:");
        String[] sessoes = {"Manhã", "Tarde", "Noite"};
        JComboBox<String> sessaoBox = new JComboBox<>(sessoes);

        JLabel areaLabel = new JLabel("Área:");
        String[] areas = {"Plateia A - R$40,00", "Plateia B - R$60,00", "Frisa - R$80,00", "Camarote - R$120,00", "Balcão Nobre - R$250,00"};
        JComboBox<String> areaBox = new JComboBox<>(areas);

        JButton chooseSeatButton = new JButton("Escolher Poltrona");
        JButton backButton = new JButton("Voltar");

        // Ação do botão escolher poltrona
        chooseSeatButton.addActionListener(e -> {
            String cpf = cpfField.getText();
            int espetaculo = espetaculoBox.getSelectedIndex() + 1;
            int sessao = sessaoBox.getSelectedIndex() + 1;
            int area = areaBox.getSelectedIndex() + 1;

            if (cpf.isEmpty() || !ticketManager.isCpfValido(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Ticket ticket = new Ticket(cpf, String.valueOf(espetaculo), String.valueOf(sessao), String.valueOf(area));
            int poltronaEscolhida = ticketManager.escolherPoltrona(ticket);

            if (poltronaEscolhida == -1) {
                JOptionPane.showMessageDialog(this, "Compra de ingresso cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ticket.setPoltrona(poltronaEscolhida);
            ticketManager.finalizarCompra(ticket);
            JOptionPane.showMessageDialog(this, "Ingresso comprado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        // Ação do botão voltar
        backButton.addActionListener(e -> {
            mainFrame.showMenu();
        });

        add(cpfLabel);
        add(cpfField);
        add(espetaculoLabel);
        add(espetaculoBox);
        add(sessaoLabel);
        add(sessaoBox);
        add(areaLabel);
        add(areaBox);
        add(chooseSeatButton);
        add(backButton);
    }
}
