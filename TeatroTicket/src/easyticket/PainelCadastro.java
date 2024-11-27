package easyticket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PainelCadastro extends JPanel {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextArea areaEndereco;
    private JTextField campoDataNascimento;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmaSenha;

    public PainelCadastro(GerenciadorUsuarios gerenciadorUsuarios, JanelaPrincipal janelaPrincipal) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        initComponents(janelaPrincipal);
    }

    private void initComponents(JanelaPrincipal janelaPrincipal) {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20)); // 20px margin on all sides

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components stretch horizontally
        gbc.weightx = 1.0; // Allow horizontal resizing
        gbc.weighty = 0; // Components won't stretch vertically by default

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);

        // Helper method to create labeled fields
        JLabel etiquetaNome = createLabel("Nome:", labelFont);
        campoNome = createRoundedField(20, fieldFont);

        JLabel etiquetaCpf = createLabel("CPF:", labelFont);
        campoCpf = createRoundedField(20, fieldFont);

        JLabel etiquetaTelefone = createLabel("Telefone:", labelFont);
        campoTelefone = createRoundedField(20, fieldFont);

        JLabel etiquetaEndereco = createLabel("Endereço:", labelFont);
        areaEndereco = new JTextArea(4, 20);
        areaEndereco.setFont(fieldFont);
        areaEndereco.setLineWrap(true);
        areaEndereco.setWrapStyleWord(true);
        areaEndereco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JScrollPane scrollEndereco = new JScrollPane(areaEndereco);

        JLabel etiquetaDataNascimento = createLabel("Data de Nascimento (dd/MM/yyyy):", labelFont);
        campoDataNascimento = createRoundedField(20, fieldFont);

        JLabel etiquetaSenha = createLabel("Senha:", labelFont);
        campoSenha = createPasswordField(20, fieldFont);

        JLabel etiquetaConfirmaSenha = createLabel("Confirme a Senha:", labelFont);
        campoConfirmaSenha = createPasswordField(20, fieldFont);

        // Buttons
        JButton botaoCadastrar;
        JButton botaoVoltar;

        // Add components to layout
        int row = 0;

        // Row: Nome
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.2;
        add(etiquetaNome, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        add(campoNome, gbc);

        // Row: CPF
        gbc.gridy = ++row; gbc.gridx = 0; gbc.weightx = 0.2;
        add(etiquetaCpf, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        add(campoCpf, gbc);

        // Row: Telefone
        gbc.gridy = ++row; gbc.gridx = 0; gbc.weightx = 0.2;
        add(etiquetaTelefone, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        add(campoTelefone, gbc);

        // Row: Endereço
        gbc.gridy = ++row; gbc.gridx = 0; gbc.weightx = 0.2;
        add(etiquetaEndereco, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.1;
        add(scrollEndereco, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;

        // Row: Data de Nascimento
        gbc.gridy = ++row; gbc.gridx = 0; gbc.weightx = 0.2;
        add(etiquetaDataNascimento, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        add(campoDataNascimento, gbc);

        // Row: Senha
        gbc.gridy = ++row; gbc.gridx = 0; gbc.weightx = 0.2;
        add(etiquetaSenha, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        add(campoSenha, gbc);

        // Row: Confirma Senha
        gbc.gridy = ++row; gbc.gridx = 0; gbc.weightx = 0.2;
        add(etiquetaConfirmaSenha, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        add(campoConfirmaSenha, gbc);

        // Row: Buttons
        gbc.gridy = ++row; gbc.gridx = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setOpaque(false);

        // Initialize buttons using RoundedButton
        botaoCadastrar = new RoundedButton("Cadastrar", 20); // Green for success
        botaoCadastrar.setBackground(new Color(76, 175, 80));

        botaoVoltar = new RoundedButton("Voltar", 20);       // Red for cancel
        botaoVoltar.setBackground(new Color(244, 67, 54));

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoVoltar);

        add(painelBotoes, gbc);

        // Button actions
        botaoCadastrar.addActionListener(e -> cadastrarUsuario(janelaPrincipal));
        botaoVoltar.addActionListener(e -> janelaPrincipal.mostrarLogin());
    }


    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createRoundedField(int columns, Font font) {
        JTextField field = new JTextField(columns);
        field.setFont(font);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return field;
    }

    private JPasswordField createPasswordField(int columns, Font font) {
        JPasswordField field = new JPasswordField(columns);
        field.setFont(font);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return field;
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(51, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void cadastrarUsuario(JanelaPrincipal janelaPrincipal) {
        // Existing logic from the original code

        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String endereco = areaEndereco.getText();
        String dataNascimento = campoDataNascimento.getText();
        String senha = new String(campoSenha.getPassword());
        String confirmaSenha = new String(campoConfirmaSenha.getPassword());

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty() ||
                dataNascimento.isEmpty() || senha.isEmpty() || !senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios e as senhas devem coincidir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataNascimento);

            Usuario usuario = new Usuario(nome, cpf, telefone, endereco, data, senha);
            if (gerenciadorUsuarios.cadastrarUsuario(usuario, senha)) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                janelaPrincipal.mostrarLogin();
            } else {
                JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


