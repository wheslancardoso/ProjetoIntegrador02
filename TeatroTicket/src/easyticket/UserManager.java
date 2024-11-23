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
        return senha.matches(regex);  // Retorna verdadeiro se a senha atender aos requisitos
    }

    // Método para cadastrar o usuário
    public boolean cadastrarUsuario(User user, String senha) {
        // Verificar se o CPF já está cadastrado antes de validar a senha
        if (usuarios.containsKey(user.getCpf())) {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Não faz o cadastro se o CPF já existe
        }

        // Validar a senha só depois de garantir que o CPF não está cadastrado
        if (!validarSenha(senha)) {
            JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 8 caracteres, " +
                    "uma letra maiúscula, uma minúscula, um número e um caractere especial.");
            return false;
        }

        // Se o CPF não estiver cadastrado e a senha for válida, cadastra o usuário
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
