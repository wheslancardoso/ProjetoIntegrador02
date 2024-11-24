package easyticket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CancelTicketPanel extends JPanel {
    private TicketManager ticketManager;
    private UserManager userManager;
    private MainFrame mainFrame;

    // Componente para exibir os ingressos comprados
    private JList<String> ticketList;
    private DefaultListModel<String> ticketListModel;

    public CancelTicketPanel(TicketManager ticketManager, UserManager userManager, MainFrame mainFrame) {
        this.ticketManager = ticketManager;
        this.userManager = userManager;
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Painel superior com instruções
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(new JLabel("Selecione o ingresso que deseja cancelar:"));

        // Carregar os ingressos comprados pelo usuário
        loadUserTickets();

        // JList para exibir os ingressos comprados
        ticketListModel = new DefaultListModel<>();
        ticketList = new JList<>(ticketListModel);
        JScrollPane ticketScrollPane = new JScrollPane(ticketList);

        topPanel.add(ticketScrollPane);
        add(topPanel, BorderLayout.NORTH);

        // Painel inferior com botões
        JPanel bottomPanel = new JPanel();
        JButton cancelButton = new JButton("Cancelar Ingresso");
        JButton backButton = new JButton("Voltar");

        cancelButton.addActionListener(e -> cancelSelectedTicket());
        backButton.addActionListener(e -> goBackToMenu());

        bottomPanel.setLayout(new GridLayout(1, 2, 10, 10));
        bottomPanel.add(cancelButton);
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Carregar os ingressos comprados pelo usuário logado
    private void loadUserTickets() {
        User user = userManager.getLoggedInUser();
        if (user != null) {
            String cpf = user.getCpf();
            List<Ticket> tickets = ticketManager.getTicketsByUser(cpf);  // Método que retorna os ingressos do usuário

            for (Ticket ticket : tickets) {
                // Adiciona os ingressos no modelo para exibição
                String ticketDetails = "Espetáculo: " + ticket.getEspetaculo() + ", Sessão: " + ticket.getSessao() +
                        ", Área: " + ticket.getArea() + ", Poltrona: " + (ticket.getPoltrona() + 1) +
                        ", Preço: R$ " + ticket.getPreco();
                ticketListModel.addElement(ticketDetails);
            }
        }
    }

    // Ação do botão "Cancelar Ingresso"
    private void cancelSelectedTicket() {
        int selectedIndex = ticketList.getSelectedIndex();
        if (selectedIndex != -1) {
            // Seleciona o ingresso
            String selectedTicketDetails = ticketList.getSelectedValue();
            String[] details = selectedTicketDetails.split(", ");
            String espetaculo = details[0].split(": ")[1];
            String sessao = details[1].split(": ")[1];
            String area = details[2].split(": ")[1];
            String poltrona = details[3].split(": ")[1];
            String cpf = userManager.getLoggedInUser().getCpf();

            // Obter o preço (supondo que o preço seja extraído de algum lugar do Ticket)
            double preco = getPrecoFromTicket(selectedTicketDetails);  // Implemente este método conforme necessário

            // Criar o ticket
            Ticket ticket = new Ticket(cpf, espetaculo, sessao, area, Integer.parseInt(poltrona) - 1, preco);

            // Cancelar o ingresso
            ticketManager.cancelarIngresso(cpf, ticket.getPoltrona(), espetaculo, sessao, area);

            // Remover da lista
            ticketListModel.remove(selectedIndex);

            // Recarregar ingressos
            reloadUserTickets();

            JOptionPane.showMessageDialog(this, "Ingresso cancelado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um ingresso para cancelar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Ação do botão "Voltar"
    private void goBackToMenu() {
        ((CardLayout) mainFrame.getContentPane().getLayout()).show(mainFrame.getContentPane(), "Menu");
    }

    // Recarregar ingressos ao acessar o painel
    public void reloadUserTickets() {
        // Limpar a lista atual
        ticketListModel.clear();

        // Recarregar os ingressos do usuário logado
        loadUserTickets();
    }

    // Método para extrair o preço do ingresso da string de detalhes
    private double getPrecoFromTicket(String ticketDetails) {
        // Procura a substring "Preço: R$ " na string ticketDetails
        String precoString = ticketDetails.split("Preço: R$ ")[1].trim();

        // Remove qualquer coisa após o símbolo da moeda, como espaços ou vírgulas, e converte para double
        precoString = precoString.replace(",", ".");  // Caso o preço venha com vírgula no lugar do ponto
        return Double.parseDouble(precoString);
    }
}



