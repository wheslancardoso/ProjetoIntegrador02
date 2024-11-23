package easyticket;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> usuarios;
    private User loggedInUser; // Variável para armazenar o usuário logado

    public UserManager() {
        usuarios = new HashMap<>();
        loggedInUser = null; // Inicialmente, nenhum usuário está logado
    }

    // Método para validar a senha
    public boolean validarSenha(String senha) {
        // Expressão regular para verificar os requisitos da senha
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";

        // Retorna verdadeiro se a senha atender aos requisitos
        return senha.matches(regex);
    }

    // Método para cadastrar o usuário
    public boolean cadastrarUsuario(User user, String senha) {
        // Primeiramente, valida a senha
        if (!validarSenha(senha)) {
            JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 8 caracteres, " +
                    "uma letra maiúscula, uma minúscula, um número e um caractere especial.");
            return false;
        }

        // Verifica se o CPF já está cadastrado
        if (usuarios.containsKey(user.getCpf())) {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // CPF já cadastrado
        }

        // Se a senha for válida e o CPF não estiver cadastrado, adiciona o usuário
        usuarios.put(user.getCpf(), user);
        return true;
    }

    // Método para login
    public User login(String cpf, String senha) {
        User user = usuarios.get(cpf);
        if (user != null && user.getSenha().equals(senha)) {
            loggedInUser = user; // Armazena o usuário logado
            return user; // Login bem-sucedido
        }
        return null; // CPF ou senha inválidos
    }

    // Método para obter o usuário logado
    public User getLoggedInUser() {
        return loggedInUser;
    }

    // Método para deslogar o usuário
    public void logout() {
        loggedInUser = null; // Limpa o usuário logado
    }
}
