package easyticket;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    private static final String USERS_FILE = "usuarios.txt";

    public GerenciadorUsuarios() {
        usuarios = new ArrayList<>();
        usuarioLogado = null;
        carregarDados();
    }

    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (Usuario usuario : usuarios) {
                String dataNascimento = new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento());
                writer.write(usuario.getNome() + "," + usuario.getCpf() + "," + usuario.getTelefone() + ","
                        + usuario.getEndereco() + "," + dataNascimento + "," + usuario.getSenha());
                writer.newLine();
            }
            System.out.println("Dados dos usuários salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados dos usuários.");
        }
    }

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

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = sdf.parse(parts[4]);
                    String senha = parts[5];

                    usuarios.add(new Usuario(nome, cpf, telefone, endereco, dataNascimento, senha));
                }
            }
            System.out.println("Dados dos usuários carregados com sucesso.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos usuários.");
        }
    }

    public boolean cadastrarUsuario(Usuario usuario, String senha) {
        if (!validarSenha(senha)) {
            JOptionPane.showMessageDialog(null, "A senha não atende aos requisitos.");
            return false;
        }
        if (usuarios.stream().anyMatch(u -> u.getCpf().equals(usuario.getCpf()))) {
            return false;
        }
        usuarios.add(usuario);
        salvarDados();
        return true;
    }

    public boolean validarSenha(String senha) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        return senha.matches(regex);
    }

    public Usuario login(String cpf, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                return usuario;
            }
        }
        return null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void logout() {
        usuarioLogado = null;
    }
}
