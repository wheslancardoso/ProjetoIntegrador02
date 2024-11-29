package easyticket;

import javax.swing.*;
import java.awt.*;

public class PainelCompraIngresso extends JPanel {
    private GerenciadorIngressos gerenciadorIngressos;
    private JanelaPrincipal janelaPrincipal;
    private GerenciadorUsuarios gerenciadorUsuarios;

    public PainelCompraIngresso(GerenciadorIngressos gerenciadorIngressos, JanelaPrincipal janelaPrincipal, GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorIngressos = gerenciadorIngressos;
        this.janelaPrincipal = janelaPrincipal;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel etiquetaEspetaculo = new JLabel("Espetáculo:");
        String[] espetaculos = {" As tranças da vovó careca", " A volta dos que chegaram a partir", " Poeira em alto mar"};
        JComboBox<String> caixaEspetaculo = new JComboBox<>(espetaculos);

        JLabel etiquetaSessao = new JLabel("Sessão:");
        String[] sessoes = {" Manhã", " Tarde", " Noite"};
        JComboBox<String> caixaSessao = new JComboBox<>(sessoes);

        JLabel etiquetaArea = new JLabel("Área:");
        String[] areas = {" Plateia A - R$40,00", " Plateia B - R$60,00", " Frisa - R$120,00", " Camarote - R$80,00", " Balcão Nobre - R$250,00"};
        JComboBox<String> caixaArea = new JComboBox<>(areas);

        JButton botaoEscolherPoltrona = new JButton("Escolher Poltrona");
        JButton botaoVoltar = new JButton("Voltar");

        // Ação do botão escolher poltrona
        botaoEscolherPoltrona.addActionListener(e -> {
            Usuario usuario = gerenciadorUsuarios.getUsuarioLogado();  // Obtém o usuário logado
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Você precisa estar logado para comprar ingressos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cpf = usuario.getCpf();  // Usa o CPF do usuário logado
            int espetaculo = caixaEspetaculo.getSelectedIndex() + 1;
            int sessao = caixaSessao.getSelectedIndex() + 1;
            int area = caixaArea.getSelectedIndex() + 1;

            Ingresso ingresso = new Ingresso(cpf, String.valueOf(espetaculo), String.valueOf(sessao), String.valueOf(area));
            int poltronaEscolhida = gerenciadorIngressos.escolherPoltrona(ingresso);

            if (poltronaEscolhida == -1) {
                JOptionPane.showMessageDialog(this, "Compra de ingresso cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ingresso.setPoltrona(poltronaEscolhida);
            gerenciadorIngressos.finalizarCompra(ingresso);
            JOptionPane.showMessageDialog(this, "Ingresso comprado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        // Ação do botão voltar
        botaoVoltar.addActionListener(e -> {
            janelaPrincipal.mostrarMenu();
        });

        add(etiquetaEspetaculo);
        add(caixaEspetaculo);
        add(etiquetaSessao);
        add(caixaSessao);
        add(etiquetaArea);
        add(caixaArea);
        add(botaoEscolherPoltrona);
        add(botaoVoltar);
    }
}