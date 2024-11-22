package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroPanel extends JPanel {
    private UserManager userManager;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextArea enderecoArea;
    private JTextField dataNascimentoField;
    private JPasswordField senhaField;
    private JTextField confirmaSenhaField;

    public CadastroPanel(UserManager userManager, MainFrame mainFrame) {
        this.userManager = userManager;
        initComponents(mainFrame);
    }

    private void initComponents(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Margem entre os componentes

        Font largeFont = new Font("Arial", Font.PLAIN, 16);

        // Adicionando os labels e os campos de texto
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(largeFont);
        nomeField = new JTextField(20);
        nomeField.setFont(largeFont);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(largeFont);
        cpfField = new JTextField(20);
        cpfField.setFont(largeFont);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(largeFont);
        telefoneField = new JTextField(20);
        telefoneField.setFont(largeFont);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(largeFont);
        enderecoArea = new JTextArea(4, 20); // Aumenta a altura do JTextArea para o endereço
        enderecoArea.setFont(largeFont);
        enderecoArea.setLineWrap(true);
        enderecoArea.setWrapStyleWord(true);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento (dd/MM/yyyy):");
        dataNascimentoLabel.setFont(largeFont);
        dataNascimentoField = new JTextField(20);
        dataNascimentoField.setFont(largeFont);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(largeFont);
        senhaField = new JPasswordField(20);
        senhaField.setFont(largeFont);

        JLabel confirmaSenhaLabel = new JLabel("Confirme a Senha:");
        confirmaSenhaLabel.setFont(largeFont);
        confirmaSenhaField = new JTextField(20);
        confirmaSenhaField.setFont(largeFont);

        // Botões
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(largeFont);
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(largeFont);

        // Definir layout e adicionar os componentes

        // Linha 1 - Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(nomeLabel, gbc);

        gbc.gridx = 1;
        add(nomeField, gbc);

        // Linha 2 - CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cpfLabel, gbc);

        gbc.gridx = 1;
        add(cpfField, gbc);

        // Linha 3 - Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(telefoneLabel, gbc);

        gbc.gridx = 1;
        add(telefoneField, gbc);

        // Linha 4 - Endereço
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(enderecoLabel, gbc);

        gbc.gridx = 1;
        add(new JScrollPane(enderecoArea), gbc);

        // Linha 5 - Data de Nascimento
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(dataNascimentoLabel, gbc);

        gbc.gridx = 1;
        add(dataNascimentoField, gbc);

        // Linha 6 - Senha
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(senhaLabel, gbc);

        gbc.gridx = 1;
        add(senhaField, gbc);

        // Linha 7 - Confirma Senha
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(confirmaSenhaLabel, gbc);

        gbc.gridx = 1;
        add(confirmaSenhaField, gbc);

        // Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(voltarButton);

        // Definir espaçamento do painel de botões
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Ação de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario(mainFrame);
            }
        });

        // Ação de voltar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLogin();
            }
        });
    }

    private void cadastrarUsuario(MainFrame mainFrame) {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String telefone = telefoneField.getText();
        String endereco = enderecoArea.getText();
        String dataNascimento = dataNascimentoField.getText();
        String senha = new String(senhaField.getPassword());
        String confirmaSenha = confirmaSenhaField.getText();

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
            User user = new User(nome, cpf, telefone, endereco, data, senha);

            // Cadastrar o usuário
            boolean sucesso = userManager.cadastrarUsuario(user);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                mainFrame.showLogin(); // Após cadastro, retorna ao login
            } else {
                JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
