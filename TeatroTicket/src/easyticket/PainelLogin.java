package easyticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Arial", Font.PLAIN, 16); // Fonte ajustada

        // Definir configurações dos campos
        JLabel etiquetaCPF = new JLabel("CPF:");
        etiquetaCPF.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 5); // Margem em volta do label
        add(etiquetaCPF, gbc);

        campoCpf = new JTextField(25);  // Aumentando a largura
        campoCpf.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 5, 10); // Margem em volta do campo
        add(campoCpf, gbc);

        JLabel etiquetaSenha = new JLabel("Senha:");
        etiquetaSenha.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(etiquetaSenha, gbc);

        campoSenha = new JPasswordField(25); // Aumentando a largura
        campoSenha.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 5, 20, 10); // Aumentando o espaçamento inferior
        add(campoSenha, gbc);

        // Ajustar os botões para ficarem proporcionalmente maiores
        JButton botaoLogin = new JButton("Login");
        botaoLogin.setFont(font);
        botaoLogin.setPreferredSize(new Dimension(180, 40)); // Botão um pouco maior
        botaoLogin.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // O botão ocupa 2 colunas
        gbc.insets = new Insets(30, 50, 15, 10); // Aumentando o espaçamento superior
        add(botaoLogin, gbc);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setFont(font);
        botaoCadastrar.setPreferredSize(new Dimension(180, 40)); // Botão um pouco maior
        botaoCadastrar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 50, 10, 10); // Aumentando o espaçamento inferior
        add(botaoCadastrar, gbc);

        // Ação de login
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Ação de cadastro
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.mostrarCadastro();
            }
        });
    }

    private void login() {
        String cpf = campoCpf.getText();
        String senha = new String(campoSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o CPF e a senha.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar CPF
        if (!ValidadorCPF.validaCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = gerenciadorUsuarios.login(cpf, senha);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            janelaPrincipal.mostrarMenu(); // Redireciona para o menu principal após o login bem-sucedido
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
