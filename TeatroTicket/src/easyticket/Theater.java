package easyticket;

import java.io.*;
import java.util.Arrays;

public class Theater {
    private static Theater instanciaUnica = null;
    private Boolean[][][][] poltronasDisponiveis; // Variável de instância
    private int[] poltronasPorArea = {25, 100, 15, 30, 50};

    // Construtor privado
    private Theater() {
        inicializarPoltronas();  // Garante que as poltronas sejam inicializadas sempre
        carregarDados();  // Carrega os dados ao instanciar
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
        poltronasDisponiveis = new Boolean[5][3][3][]; // Inicializa o array de poltronas (ajuste conforme seu modelo)
        for (int area = 0; area < poltronasDisponiveis.length; area++) {
            for (int espetaculo = 0; espetaculo < poltronasDisponiveis[area].length; espetaculo++) {
                for (int sessao = 0; sessao < poltronasDisponiveis[area][espetaculo].length; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new Boolean[poltronasPorArea[area]];
                    Arrays.fill(poltronasDisponiveis[area][espetaculo][sessao], Boolean.FALSE);  // Inicializa todas as poltronas como disponíveis
                }
            }
        }
    }

    // Método para carregar os dados das poltronas a partir do arquivo "poltronas.txt"
    public void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("poltronas.txt"))) {
            String line;
            int areaIndex = 0, espetaculoIndex = 0, sessaoIndex = 0, poltronaIndex = 0;

            while ((line = reader.readLine()) != null) {
                for (char estado : line.toCharArray()) {
                    if (poltronasDisponiveis == null) {
                        inicializarPoltronas();  // Garante que as poltronas estão inicializadas
                    }
                    poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][poltronaIndex] = (estado == '1');
                    poltronaIndex++;
                    if (poltronaIndex == poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex].length) {
                        poltronaIndex = 0;
                        sessaoIndex++;
                        if (sessaoIndex == poltronasDisponiveis[areaIndex][espetaculoIndex].length) {
                            sessaoIndex = 0;
                            espetaculoIndex++;
                            if (espetaculoIndex == poltronasDisponiveis[areaIndex].length) {
                                espetaculoIndex = 0;
                                areaIndex++;
                            }
                        }
                    }
                }
            }
            System.out.println("Dados das poltronas carregados com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados das poltronas.");
            inicializarPoltronas();  // Se houver erro, inicializa as poltronas
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

    // Método para salvar os dados das poltronas em um arquivo
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("poltronas.txt"))) {
            for (int areaIndex = 0; areaIndex < poltronasDisponiveis.length; areaIndex++) {
                for (int espetaculoIndex = 0; espetaculoIndex < poltronasDisponiveis[areaIndex].length; espetaculoIndex++) {
                    for (int sessaoIndex = 0; sessaoIndex < poltronasDisponiveis[areaIndex][espetaculoIndex].length; sessaoIndex++) {
                        StringBuilder line = new StringBuilder();
                        for (boolean poltrona : poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex]) {
                            line.append(poltrona ? "1" : "0");
                        }
                        writer.write(line.toString());
                        writer.newLine();
                    }
                }
            }
            System.out.println("Dados das poltronas salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar os dados das poltronas.");
        }
    }

    // Método para limpar os dados das poltronas
    public void limparDados() {
        inicializarPoltronas();  // Reinicializa as poltronas
        File file = new File("poltronas.txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Arquivo poltronas.txt excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir o arquivo poltronas.txt.");
            }
        } else {
            System.out.println("Arquivo poltronas.txt não existe.");
        }
    }
}

