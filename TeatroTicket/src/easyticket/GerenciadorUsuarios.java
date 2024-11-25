package easyticket;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorUsuarios {
    private Map<String, Usuario> usuarios;
    private Usuario usuarioLogado;
    private static final String USERS_FILE = "usuarios.txt";  // Arquivo para armazenar os dados

    public GerenciadorUsuarios() {
        usuarios = new HashMap<>();
        usuarioLogado = null;  // Inicialmente, nenhum usuário está logado
        carregarDados();  // Carregar os dados ao inicializar
    }

    // Método para salvar os dados dos usuários no arquivo
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (Usuario usuario : usuarios.values()) {
                // Salvar os dados do usuário no formato texto
                String dataNascimento = new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento());
                writer.write(usuario.getNome() + "," + usuario.getCpf() + "," + usuario.getTelefone() + ","
                        + usuario.getEndereco() + "," + dataNascimento + "," + usuario.getSenha());
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
                    Usuario usuario = new Usuario(nome, cpf, telefone, endereco, dataNascimento, senha);
                    usuarios.put(cpf, usuario);
                }
            }
            System.out.println("Dados dos usuários carregados com sucesso.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos usuários.");
        }
    }


    // Método para cadastrar o usuário
    public boolean cadastrarUsuario(Usuario usuario, String senha) {
        if (!validarSenha(senha)) {
            JOptionPane.showMessageDialog(null, "A senha não atende aos requisitos.");
            return false;
        }
        if (usuarios.containsKey(usuario.getCpf())) {
            return false; // CPF já cadastrado
        }
        usuarios.put(usuario.getCpf(), usuario);
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
    public Usuario login(String cpf, String senha) {
        Usuario usuario = usuarios.get(cpf);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;  // Armazena o usuário logado
            return usuario;  // Login bem-sucedido
        }
        return null;  // CPF ou senha inválidos
    }

    // Método para obter o usuário logado
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    // Método para deslogar o usuário
    public void logout() {
        usuarioLogado = null;  // Limpa o usuário logado
    }
}

