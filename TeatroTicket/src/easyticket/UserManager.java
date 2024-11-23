package easyticket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> usuarios;
    private User loggedInUser;
    private static final String USERS_FILE = "usuarios.json";  // Arquivo para armazenar os dados

    public UserManager() {
        usuarios = new HashMap<>();
        loggedInUser = null;  // Inicialmente, nenhum usuário está logado
        carregarDados();  // Carregar os dados ao inicializar
    }

    // Método para salvar os dados dos usuários no arquivo
    public void salvarDados() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            Gson gson = new Gson();
            gson.toJson(usuarios.values(), writer);  // Salva todos os usuários
            System.out.println("Dados dos usuários salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados dos usuários.");
        }
    }

    // Método para carregar os dados dos usuários a partir do arquivo
    public void carregarDados() {
        try (Reader reader = new FileReader(USERS_FILE)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, User>>(){}.getType();
            Map<String, User> loadedUsers = gson.fromJson(reader, type);
            if (loadedUsers != null) {
                usuarios = loadedUsers;
                System.out.println("Dados dos usuários carregados com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado. Iniciando lista vazia.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado. Iniciando lista vazia.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos usuários.");
        }
    }

    // Método para cadastrar o usuário
    public boolean cadastrarUsuario(User user, String senha) {
        if (!validarSenha(senha)) {
            JOptionPane.showMessageDialog(null, "A senha não atende aos requisitos.");
            return false;
        }
        if (usuarios.containsKey(user.getCpf())) {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado.");
            return false; // CPF já cadastrado
        }
        usuarios.put(user.getCpf(), user);
        salvarDados();  // Salvar dados após cadastro
        return true;
    }

    // Método para validar a senha
    public boolean validarSenha(String senha) {
        // Expressão regular para verificar os requisitos da senha
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        return senha.matches(regex);  // Retorna verdadeiro se a senha atender aos requisitos
    }

    // Método para login
    public User login(String cpf, String senha) {
        User user = usuarios.get(cpf);
        if (user != null && user.getSenha().equals(senha)) {
            loggedInUser = user;  // Armazena o usuário logado
            return user;  // Login bem-sucedido
        }
        return null;  // CPF ou senha inválidos
    }

    // Método para obter o usuário logado
    public User getLoggedInUser() {
        return loggedInUser;
    }

    // Método para deslogar o usuário
    public void logout() {
        loggedInUser = null;  // Limpa o usuário logado
    }
}
