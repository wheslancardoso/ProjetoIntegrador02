package easyticket;

import com.google.gson.Gson;
import java.io.*;
import java.util.Arrays;

public class Theater {
    private static Theater instanciaUnica = null;
    private Boolean[][][][] poltronasDisponiveis; // Variável de instância
    private int[] poltronasPorArea = {25, 100, 15, 30, 50};

    // Construtor privado
    private Theater() {
        carregarDados(); // Carrega os dados ao instanciar
    }

    // Método estático para obter a instância única (Singleton)
    public static Theater getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Theater();
        }
        return instanciaUnica;
    }

    // Método para inicializar as poltronas
    private void inicializarPoltronas() {
        poltronasDisponiveis = new Boolean[5][3][3][];
        for (int area = 0; area < 5; area++) {
            for (int espetaculo = 0; espetaculo < 3; espetaculo++) {
                for (int sessao = 0; sessao < 3; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new Boolean[poltronasPorArea[area]];
                    // Inicializa todas as poltronas como disponíveis (false)
                    Arrays.fill(poltronasDisponiveis[area][espetaculo][sessao], Boolean.FALSE);
                }
            }
        }
    }

    // Método para obter as poltronas disponíveis
    public Boolean[] getPoltronasDisponiveis(int area, int espetaculo, int sessao) {
        return poltronasDisponiveis[area][espetaculo][sessao];
    }

    // Método para reservar uma poltrona
    public void reservarPoltrona(Ticket ticket) {
        int areaIndex = Integer.parseInt(ticket.getArea()) - 1;
        int espetaculoIndex = Integer.parseInt(ticket.getEspetaculo()) - 1;
        int sessaoIndex = Integer.parseInt(ticket.getSessao()) - 1;
        int poltrona = ticket.getPoltrona();

        poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][poltrona] = true;
    }

    // Método para salvar os dados das poltronas
    public void salvarDados() {
        try (Writer writer = new FileWriter("poltronas.json")) {
            Gson gson = new Gson();
            gson.toJson(poltronasDisponiveis, writer);
            System.out.println("Dados das poltronas salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try (Reader reader = new FileReader("poltronas.json")) {
            Gson gson = new Gson();
            poltronasDisponiveis = gson.fromJson(reader, Boolean[][][][].class);
            if (poltronasDisponiveis == null) {
                System.out.println("Dados das poltronas estão vazios. Inicializando poltronas.");
                inicializarPoltronas();
            } else {
                System.out.println("Dados das poltronas carregados com sucesso.");
            }
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, inicializa poltronas
            System.out.println("Arquivo poltronas.json não encontrado. Inicializando poltronas.");
            inicializarPoltronas();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados das poltronas. Inicializando poltronas.");
            inicializarPoltronas();
        }
    }

}


