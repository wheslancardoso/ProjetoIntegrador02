package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroPanel extends JPanel {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextArea areaEndereco;
    private JTextField campoDataNascimento;
    private JPasswordField campoSenha;
    private JTextField campoConfirmaSenha;

    public CadastroPanel(GerenciadorUsuarios gerenciadorUsuarios, JanelaPrincipal janelaPrincipal) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        initComponents(janelaPrincipal);
    }

    private void initComponents(JanelaPrincipal janelaPrincipal) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Margem entre os componentes

        Font largeFont = new Font("Arial", Font.PLAIN, 16);

        // Adicionando os labels e os campos de texto
        JLabel etiquetaNome = new JLabel("Nome:");
        etiquetaNome.setFont(largeFont);
        campoNome = new JTextField(20);
        campoNome.setFont(largeFont);

        JLabel etiquetaCpf = new JLabel("CPF:");
        etiquetaCpf.setFont(largeFont);
        campoCpf = new JTextField(20);
        campoCpf.setFont(largeFont);

        JLabel etiquetaTelefone = new JLabel("Telefone:");
        etiquetaTelefone.setFont(largeFont);
        campoTelefone = new JTextField(20);
        campoTelefone.setFont(largeFont);

        JLabel etiquetaEndereco = new JLabel("Endereço:");
        etiquetaEndereco.setFont(largeFont);
        areaEndereco = new JTextArea(4, 20); // Aumenta a altura do JTextArea para o endereço
        areaEndereco.setFont(largeFont);
        areaEndereco.setLineWrap(true);
        areaEndereco.setWrapStyleWord(true);

        JLabel etiquetaDataNascimento = new JLabel("Data de Nascimento (dd/MM/yyyy):");
        etiquetaDataNascimento.setFont(largeFont);
        campoDataNascimento = new JTextField(20);
        campoDataNascimento.setFont(largeFont);

        JLabel etiquetaSenha = new JLabel("Senha:");
        etiquetaSenha.setFont(largeFont);
        campoSenha = new JPasswordField(20);
        campoSenha.setFont(largeFont);

        JLabel etiquetaConfirmaSenha = new JLabel("Confirme a Senha:");
        etiquetaConfirmaSenha.setFont(largeFont);
        campoConfirmaSenha = new JTextField(20);
        campoConfirmaSenha.setFont(largeFont);

        // Botões
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setFont(largeFont);
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(largeFont);

        // Definir layout e adicionar os componentes

        // Linha 1 - Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(etiquetaNome, gbc);

        gbc.gridx = 1;
        add(campoNome, gbc);

        // Linha 2 - CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etiquetaCpf, gbc);

        gbc.gridx = 1;
        add(campoCpf, gbc);

        // Linha 3 - Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(etiquetaTelefone, gbc);

        gbc.gridx = 1;
        add(campoTelefone, gbc);

        // Linha 4 - Endereço
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(etiquetaEndereco, gbc);

        gbc.gridx = 1;
        add(new JScrollPane(areaEndereco), gbc);

        // Linha 5 - Data de Nascimento
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(etiquetaDataNascimento, gbc);

        gbc.gridx = 1;
        add(campoDataNascimento, gbc);

        // Linha 6 - Senha
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(etiquetaSenha, gbc);

        gbc.gridx = 1;
        add(campoSenha, gbc);

        // Linha 7 - Confirma Senha
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(etiquetaConfirmaSenha, gbc);

        gbc.gridx = 1;
        add(campoConfirmaSenha, gbc);

        // Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoVoltar);

        // Definir espaçamento do painel de botões
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(painelBotoes, gbc);

        // Ação de cadastro
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario(janelaPrincipal);
            }
        });

        // Ação de voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.mostrarLogin();
            }
        });
    }

    private void cadastrarUsuario(JanelaPrincipal janelaPrincipal) {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String endereco = areaEndereco.getText();
        String dataNascimento = campoDataNascimento.getText();
        String senha = new String(campoSenha.getPassword());
        String confirmaSenha = campoConfirmaSenha.getText();

        // Validar dados
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty() ||
                dataNascimento.isEmpty() || senha.isEmpty() || !senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios e as senhas devem coincidir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataNascimento);

            // Criar um novo usuário
            Usuario usuario = new Usuario(nome, cpf, telefone, endereco, data, senha);

            // Cadastrar o usuário
            boolean sucesso = gerenciadorUsuarios.cadastrarUsuario(usuario, senha);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                janelaPrincipal.mostrarLogin(); // Após cadastro, retorna ao login
            } else {
                JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


