package easyticket;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> usuarios;
    private User loggedInUser;
    private static final String USERS_FILE = "usuarios.txt";  // Arquivo para armazenar os dados

    public UserManager() {
        usuarios = new HashMap<>();
        loggedInUser = null;  // Inicialmente, nenhum usuário está logado
        carregarDados();  // Carregar os dados ao inicializar
    }

    // Método para salvar os dados dos usuários no arquivo
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : usuarios.values()) {
                // Salvar os dados do usuário no formato texto
                String dataNascimento = new SimpleDateFormat("dd/MM/yyyy").format(user.getDataNascimento());
                writer.write(user.getNome() + "," + user.getCpf() + "," + user.getTelefone() + ","
                        + user.getEndereco() + "," + dataNascimento + "," + user.getSenha());
                writer.newLine(); // Nova linha para o próximo usuário
            }
            System.out.println("Dados dos usuários salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados dos usuários.");
        }
    }

    // Método para carregar os dados dos usuários a partir do arquivo
    public void carregarDados() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            System.out.println("Arquivo " + USERS_FILE + " não encontrado. Nenhum dado foi carregado.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String nome = parts[0];
                    String cpf = parts[1];
                    String telefone = parts[2];
                    String endereco = parts[3];

                    // Ajuste para a data no formato padrão que o Java entende
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = sdf.parse(parts[4]);
                    String senha = parts[5];

                    // Criar o usuário e adicioná-lo ao mapa
                    User user = new User(nome, cpf, telefone, endereco, dataNascimento, senha);
                    usuarios.put(cpf, user);
                }
            }
            System.out.println("Dados dos usuários carregados com sucesso.");
        } catch (IOException | ParseException e) {
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

