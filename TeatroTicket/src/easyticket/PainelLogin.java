package easyticket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PainelLogin extends JPanel {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private JanelaPrincipal janelaPrincipal;
    private JTextField campoCpf;
    private JPasswordField campoSenha;

    public PainelLogin(GerenciadorUsuarios gerenciadorUsuarios, JanelaPrincipal janelaPrincipal) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.janelaPrincipal = janelaPrincipal;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 255, 255)); // Light gray background

        GridBagConstraints gbc = new GridBagConstraints();
        Font labelFont = new Font("SansSerif", Font.BOLD, 16); // Bold font for labels
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14); // Regular font for fields

        // CPF Label
        JLabel etiquetaCPF = new JLabel("CPF");
        etiquetaCPF.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 5, 0); // Margin below the label
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        add(etiquetaCPF, gbc);

        // CPF Field
        campoCpf = createRoundedTextField(25, fieldFont);
        addPlaceholder(campoCpf, "Digite seu CPF");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 50, 10, 50); // Margin around the field
        gbc.ipadx = 150; // Width of the field
        add(campoCpf, gbc);

        // Password Label
        JLabel etiquetaSenha = new JLabel("Senha");
        etiquetaSenha.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 5, 0); // Margin below the label
        gbc.ipadx = 0; // Reset width adjustments for labels
        add(etiquetaSenha, gbc);

        // Password Field
        campoSenha = createRoundedPasswordField(25, fieldFont);
        addPlaceholder(campoSenha, "Digite sua senha");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 50, 20, 50);
        gbc.ipadx = 150;
        add(campoSenha, gbc);

        // Login Button
        RoundedButton botaoLogin = new RoundedButton("Login", 20);
        botaoLogin.setBackground(new Color(76, 175, 80)); // Green for login
        botaoLogin.setPreferredSize(new Dimension(180, 40));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(30, 50, 15, 50);
        gbc.ipadx = 0; // Reset width adjustments for buttons
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoLogin, gbc);

        // Register Button
        RoundedButton botaoCadastrar = new RoundedButton("Cadastrar", 20);
        botaoCadastrar.setBackground(new Color(244, 67, 54)); // Red for register
        botaoCadastrar.setPreferredSize(new Dimension(180, 40));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 50, 10, 50);
        add(botaoCadastrar, gbc);

        // Button Actions
        botaoLogin.addActionListener(e -> login());
        botaoCadastrar.addActionListener(e -> janelaPrincipal.mostrarCadastro());
    }

    private JTextField createRoundedTextField(int columns, Font font) {
        JTextField textField = new JTextField(columns);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 10, 5, 10))); // Rounded border with padding
        return textField;
    }

    private JPasswordField createRoundedPasswordField(int columns, Font font) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setFont(font);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 10, 5, 10))); // Rounded border with padding
        return passwordField;
    }

    private void addPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        // Add FocusListener for placeholder effect
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void login() {
        String cpf = campoCpf.getText();
        String senha = new String(campoSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty() || cpf.equals("Digite seu CPF") || senha.equals("Digite sua senha")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o CPF e a senha.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate CPF
        if (!ValidadorCPF.validaCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = gerenciadorUsuarios.login(cpf, senha);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            janelaPrincipal.mostrarMenu(); // Redirect to main menu after successful login
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
