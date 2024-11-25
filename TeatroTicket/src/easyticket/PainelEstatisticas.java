package easyticket;

import javax.swing.*;
import java.awt.*;

public class PainelEstatisticas extends JPanel {
    private GerenciadorIngressos gerenciadorIngressos;
    private JanelaPrincipal janelaPrincipal;

    public PainelEstatisticas(GerenciadorIngressos gerenciadorIngressos, JanelaPrincipal janelaPrincipal) {
        this.gerenciadorIngressos = gerenciadorIngressos;
        this.janelaPrincipal = janelaPrincipal;
        inicializarComponentes();

    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        JTextArea areaEstatisticas = new JTextArea();
        areaEstatisticas.setEditable(false);

        JButton botaoGerar = new JButton("Gerar Estatísticas");
        JButton botaoVoltar = new JButton("Voltar");

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 linha, 2 colunas, espaçamento horizontal de 10 pixels);
        painelBotoes.add(botaoGerar);
        painelBotoes.add(botaoVoltar);

        // Adicionar a borda vazia ao painelBotao
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Painel intermediário para conter os componentes
        JPanel painelConteudo = new JPanel(new BorderLayout());

        // Adicionar a borda vazia ao painelConteudo
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionar os componentes ao painelConteudo
        painelConteudo.add(painelBotoes, BorderLayout.NORTH);
        painelConteudo.add(new JScrollPane(areaEstatisticas), BorderLayout.CENTER);

        // Adicionar o painelConteudo ao painel principal
        add(painelConteudo, BorderLayout.CENTER);

        areaEstatisticas.setMargin(new Insets(10, 10, 10, 10));

        // Ação do botão gerar
        botaoGerar.addActionListener(e -> {
            String estatisticas = gerenciadorIngressos.gerarEstatisticas();
            areaEstatisticas.setText(estatisticas);
        });

        // Ação do botão voltar
        botaoVoltar.addActionListener(e -> {
            janelaPrincipal.mostrarMenu();
        });
    }

}
