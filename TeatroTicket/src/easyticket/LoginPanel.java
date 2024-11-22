package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private UserManager userManager;
    private MainFrame mainFrame;
    private JTextField cpfField;
    private JPasswordField senhaField;

    public LoginPanel(UserManager userManager, MainFrame mainFrame) {
        this.userManager = userManager;
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Arial", Font.PLAIN, 16); // Fonte ajustada

        // Definir configurações dos campos
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 5); // Margem em volta do label
        add(cpfLabel, gbc);

        cpfField = new JTextField(25);  // Aumentando a largura
        cpfField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 5, 10); // Margem em volta do campo
        add(cpfField, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(senhaLabel, gbc);

        senhaField = new JPasswordField(25); // Aumentando a largura
        senhaField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 5, 20, 10); // Aumentando o espaçamento inferior
        add(senhaField, gbc);

        // Ajustar os botões para ficarem proporcionalmente maiores
        JButton loginButton = new JButton("Login");
        loginButton.setFont(font);
        loginButton.setPreferredSize(new Dimension(180, 40)); // Botão um pouco maior
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // O botão ocupa 2 colunas
        gbc.insets = new Insets(30, 50, 15, 10); // Aumentando o espaçamento superior
        add(loginButton, gbc);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(font);
        cadastrarButton.setPreferredSize(new Dimension(180, 40)); // Botão um pouco maior
        cadastrarButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 50, 10, 10); // Aumentando o espaçamento inferior
        add(cadastrarButton, gbc);

        // Ação de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Ação de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showCadastro();
            }
        });
    }

    private void login() {
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());

        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o CPF e a senha.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userManager.login(cpf, senha);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.showMenu(); // Redireciona para o menu principal após o login bem-sucedido
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
