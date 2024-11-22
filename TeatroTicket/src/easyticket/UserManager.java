package easyticket;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> usuarios;
    private User loggedInUser; // Variável para armazenar o usuário logado

    public UserManager() {
        usuarios = new HashMap<>();
        loggedInUser = null; // Inicialmente, nenhum usuário está logado
    }

    // Método para cadastrar o usuário
    public boolean cadastrarUsuario(User user) {
        if (usuarios.containsKey(user.getCpf())) {
            return false; // CPF já cadastrado
        }
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
