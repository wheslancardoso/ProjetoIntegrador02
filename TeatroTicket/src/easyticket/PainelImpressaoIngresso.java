    package easyticket;

    import javax.swing.*;
    import java.awt.*;

    public class PainelImpressaoIngresso extends JPanel {
        private GerenciadorIngressos gerenciadorIngressos;
        private JanelaPrincipal janelaPrincipal;
        private GerenciadorUsuarios gerenciadorUsuarios;

        public PainelImpressaoIngresso(GerenciadorIngressos gerenciadorIngressos, JanelaPrincipal janelaPrincipal, GerenciadorUsuarios gerenciadorUsuarios) {
            this.gerenciadorIngressos = gerenciadorIngressos;
            this.janelaPrincipal = janelaPrincipal;
            this.gerenciadorUsuarios = gerenciadorUsuarios;
            inicializarComponentes();
        }

        private void inicializarComponentes() {
            setLayout(new BorderLayout());

            // Removido o campo de CPF
            JTextArea areaIngressos = new JTextArea();
            areaIngressos.setEditable(false);
            areaIngressos.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fonte monoespaçada para melhor alinhamento

            JButton botaoImprimir = new JButton("Imprimir Ingressos");
            JButton botaoVoltar = new JButton("Voltar");

            // Painel para os campos de entrada e botões
            JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
            painelBotoes.add(botaoImprimir);
            painelBotoes.add(botaoVoltar);

            // Adicionar a borda vazia ao painelBotoes
            painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            // Painel intermediário para conter o painelBotoes e a areaIngressos
            JPanel painelConteudo = new JPanel(new BorderLayout());

            // Adicionar a borda vazia ao painelConteudo
            painelConteudo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Adicionar o painelBotoes ao norte do painelConteudo
            painelConteudo.add(painelBotoes, BorderLayout.NORTH);

            // Adicionar o areaIngressos (dentro de um JScrollPane) ao centro do painelConteudo
            painelConteudo.add(new JScrollPane(areaIngressos), BorderLayout.CENTER);

            // Adicionar o painelConteudo ao painel principal
            add(painelConteudo, BorderLayout.CENTER);

            areaIngressos.setMargin(new Insets(10, 10, 10, 10));

            // Ação do botão imprimir
            botaoImprimir.addActionListener(e -> {
                Usuario usuario = gerenciadorUsuarios.getUsuarioLogado(); // Obtém o usuário logado
                if (usuario == null) {
                    JOptionPane.showMessageDialog(this, "Você precisa estar logado para imprimir ingressos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String cpf = usuario.getCpf();  // Usa o CPF do usuário logado
                String ingressos = gerenciadorIngressos.imprimirIngressos(cpf);
                areaIngressos.setText(ingressos);
            });

            // Ação do botão voltar
            botaoVoltar.addActionListener(e -> {
                janelaPrincipal.mostrarMenu();
            });
        }
    }

