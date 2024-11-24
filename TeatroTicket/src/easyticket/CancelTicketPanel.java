package easyticket;

import javax.swing.*;
import java.awt.*;

public class CancelTicketPanel extends JPanel {
    private TicketManager ticketManager;
    private UserManager userManager;
    private MainFrame mainFrame;  // Referência para o MainFrame

    public CancelTicketPanel(TicketManager ticketManager, UserManager userManager, MainFrame mainFrame) {
        this.ticketManager = ticketManager;
        this.userManager = userManager;
        this.mainFrame = mainFrame;  // Certifique-se de que a referência é atribuída corretamente
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel espetaculoLabel = new JLabel("Espetáculo:");
        String[] espetaculos = {" As tranças da vovó careca", " A volta dos que chegaram a partir", " Poeira em alto mar"};
        JComboBox<String> espetaculoBox = new JComboBox<>(espetaculos);

        JLabel sessaoLabel = new JLabel("Sessão:");
        String[] sessoes = {" Manhã", " Tarde", " Noite"};
        JComboBox<String> sessaoBox = new JComboBox<>(sessoes);

        JLabel areaLabel = new JLabel("Área:");
        String[] areas = {" Plateia A - R$40,00", " Plateia B - R$60,00", " Frisa - R$80,00", " Camarote - R$120,00", " Balcão Nobre - R$250,00"};
        JComboBox<String> areaBox = new JComboBox<>(areas);

        JLabel poltronaLabel = new JLabel("Poltrona:");
        JTextField poltronaField = new JTextField();

        JButton cancelButton = new JButton("Cancelar Ingresso");
        JButton backButton = new JButton("Voltar");

        // Ação do botão cancelar ingresso
        cancelButton.addActionListener(e -> {
            User user = userManager.getLoggedInUser();  // Obtém o usuário logado
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Você precisa estar logado para cancelar ingressos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cpf = user.getCpf();  // Usa o CPF do usuário logado
            int espetaculo = espetaculoBox.getSelectedIndex() + 1;
            int sessao = sessaoBox.getSelectedIndex() + 1;
            int area = areaBox.getSelectedIndex() + 1;
            int poltrona;

            try {
                poltrona = Integer.parseInt(poltronaField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número da poltrona inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ticketManager.cancelarIngresso(cpf, poltrona - 1, String.valueOf(espetaculo), String.valueOf(sessao), String.valueOf(area));
            JOptionPane.showMessageDialog(this, "Ingresso cancelado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        // Ação do botão voltar
        backButton.addActionListener(e -> {
            // Voltar ao menu principal usando a referência de mainFrame
            ((CardLayout) mainFrame.getContentPane().getLayout()).show(mainFrame.getContentPane(), "Menu");
        });

        add(espetaculoLabel);
        add(espetaculoBox);
        add(sessaoLabel);
        add(sessaoBox);
        add(areaLabel);
        add(areaBox);
        add(poltronaLabel);
        add(poltronaField);
        add(cancelButton);
        add(backButton);
    }
}


