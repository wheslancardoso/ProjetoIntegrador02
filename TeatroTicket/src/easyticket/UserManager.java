package easyticket;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> usuarios;

    public UserManager() {
        usuarios = new HashMap<>();
    }

    public boolean cadastrarUsuario(User user) {
        if (usuarios.containsKey(user.getCpf())) {
            return false; // CPF já cadastrado
        }
        usuarios.put(user.getCpf(), user);
        return true;
    }

    public User login(String cpf, String senha) {
        User user = usuarios.get(cpf);
        if (user != null && user.getSenha().equals(senha)) {
            return user; // Login bem-sucedido
        }
        return null; // CPF ou senha inválidos
    }
}
