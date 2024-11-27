package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaPrincipal extends JFrame {
    private GerenciadorIngressos gerenciadorIngressos;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public JanelaPrincipal() {
        gerenciadorIngressos = new GerenciadorIngressos();
        gerenciadorUsuarios = new GerenciadorUsuarios();

        setTitle("Sistema EasyTicket");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        // Save data on window close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gerenciadorIngressos.salvarDados();
            }
        });
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        // Initialize panels
        PainelLogin painelLogin = new PainelLogin(gerenciadorUsuarios, this);
        PainelCadastro painelCadastro = new PainelCadastro(gerenciadorUsuarios, this);

        JPanel painelMenu = createMenuPanel();
        PainelCompraIngresso painelComprar = new PainelCompraIngresso(gerenciadorIngressos, this, gerenciadorUsuarios);
        PainelImpressaoIngresso painelImprimir = new PainelImpressaoIngresso(gerenciadorIngressos, this, gerenciadorUsuarios);
        PainelEstatisticas painelEstats = new PainelEstatisticas(gerenciadorIngressos, this);

        // Add panels
        painelPrincipal.add(painelLogin, "Login");
        painelPrincipal.add(painelCadastro, "Cadastro");
        painelPrincipal.add(painelMenu, "Menu");
        painelPrincipal.add(painelComprar, "Comprar");
        painelPrincipal.add(painelImprimir, "Imprimir");
        painelPrincipal.add(painelEstats, "Estatísticas");

        cardLayout.show(painelPrincipal, "Login");
        setContentPane(painelPrincipal);
    }

    private JPanel createMenuPanel() {
        JPanel painelMenu = new JPanel(new BorderLayout());
        painelMenu.setBackground(new Color(245, 245, 245));

        // Menu buttons
        JButton botaoComprarIngresso = createModernButton("Comprar Ingresso");
        JButton botaoImprimirIngresso = createModernButton("Imprimir Ingresso");
        JButton botaoEstatisticas = createModernButton("Estatísticas de Vendas");
        JButton botaoSair = createModernButton("Sair");

        // Button actions
        botaoComprarIngresso.addActionListener(e -> cardLayout.show(painelPrincipal, "Comprar"));
        botaoImprimirIngresso.addActionListener(e -> cardLayout.show(painelPrincipal, "Imprimir"));
        botaoEstatisticas.addActionListener(e -> cardLayout.show(painelPrincipal, "Estatísticas"));
        botaoSair.addActionListener(e -> System.exit(0));

        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 15, 15));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelBotoes.setBackground(new Color(245, 245, 245));

        painelBotoes.add(botaoComprarIngresso);
        painelBotoes.add(botaoImprimirIngresso);
        painelBotoes.add(botaoEstatisticas);
        painelBotoes.add(botaoSair);

        painelMenu.add(painelBotoes, BorderLayout.CENTER);
        return painelMenu;
    }

    private JButton createModernButton(String text) {
        return new RoundedButton(text, 20); // Corner radius of 20 for rounded edges
    }


    public void mostrarMenu() {
        cardLayout.show(painelPrincipal, "Menu");
    }

    public void mostrarCadastro() {
        cardLayout.show(painelPrincipal, "Cadastro");
    }

    public void mostrarLogin() {
        cardLayout.show(painelPrincipal, "Login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true));
    }
}
