package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelTicketPanel extends JPanel {
    private TicketManager ticketManager;
    private UserManager userManager;  // Para acessar o usuário logado

    // Componentes da interface
    private JTextField espetaculoField;
    private JTextField sessaoField;
    private JTextField areaField;
    private JTextField poltronaField;
    private JButton cancelarButton;

    public CancelTicketPanel(TicketManager ticketManager, UserManager userManager) {
        this.ticketManager = ticketManager;
        this.userManager = userManager;  // Recebe o UserManager para obter o usuário logado

        // Configurações do painel
        setLayout(new GridLayout(5, 2, 5, 5));

        // Labels e campos de texto
        add(new JLabel("Espetáculo:"));
        espetaculoField = new JTextField();
        add(espetaculoField);

        add(new JLabel("Sessão:"));
        sessaoField = new JTextField();
        add(sessaoField);

        add(new JLabel("Área:"));
        areaField = new JTextField();
        add(areaField);

        add(new JLabel("Poltrona:"));
        poltronaField = new JTextField();
        add(poltronaField);

        // Botão de Cancelar
        cancelarButton = new JButton("Cancelar Ingresso");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarIngresso();
            }
        });
        add(cancelarButton);
    }

    // Função para realizar o cancelamento do ingresso
    private void cancelarIngresso() {
        // Obtendo o CPF do usuário logado
        String cpf = userManager.getLoggedInUser().getCpf();
        String espetaculo = espetaculoField.getText();
        String sessao = sessaoField.getText();
        String area = areaField.getText();
        String poltronaText = poltronaField.getText();

        if (espetaculo.isEmpty() || sessao.isEmpty() || area.isEmpty() || poltronaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        try {
            int poltrona = Integer.parseInt(poltronaText);

            // Debug para verificar se o método de cancelamento está sendo chamado
            System.out.println("Chamando cancelarIngresso com: CPF=" + cpf + ", Poltrona=" + poltrona +
                    ", Espetáculo=" + espetaculo + ", Sessão=" + sessao + ", Área=" + area);

            // Chama o método de cancelamento no TicketManager, usando o CPF do usuário logado
            ticketManager.cancelarIngresso(cpf, poltrona, espetaculo, sessao, area);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Poltrona deve ser um número válido.");
        }
    }

}

