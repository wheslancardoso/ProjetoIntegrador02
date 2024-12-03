package easyticket;

import java.io.*;
import java.util.Arrays;

public class Teatro {
    private static Teatro instanciaUnica = null;
    private Boolean[][][][] poltronasDisponiveis;

    // Definindo o número de poltronas por área (ajuste conforme sua lógica de negócio)
    private int[] poltronasPorArea = {25, 100, 5 * 6, 4 * 10, 50}; // Atualizado para frisa e camarote

    // Construtor privado
    private Teatro() {
        inicializarPoltronas();  // Inicializa as poltronas
        carregarDados();  // Carrega os dados ao instanciar
    }

    // Método estático para obter a instância única (Singleton)
    public static Teatro getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Teatro();
        }
        return instanciaUnica;
    }

    // Método para inicializar as poltronas
    private void inicializarPoltronas() {
        poltronasDisponiveis = new Boolean[5][3][3][]; // 5 áreas, 3 espetáculos, 3 sessões
        for (int area = 0; area < poltronasDisponiveis.length; area++) {
            for (int espetaculo = 0; espetaculo < poltronasDisponiveis[area].length; espetaculo++) {
                for (int sessao = 0; sessao < poltronasDisponiveis[area][espetaculo].length; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new Boolean[poltronasPorArea[area]]; // Ajusta a quantidade de poltronas
                    Arrays.fill(poltronasDisponiveis[area][espetaculo][sessao], Boolean.FALSE);  // Inicializa todas as poltronas como disponíveis
                }
            }
        }
    }

    // Método para carregar os dados das poltronas a partir de arquivo
    public void carregarDados() {
        File file = new File("poltronas.txt");

        if (!file.exists()) {
            System.out.println("Arquivo poltronas.txt não encontrado. Criando um novo arquivo.");
            inicializarPoltronas();  // Inicializa as poltronas
            salvarDados();  // Cria o arquivo
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int areaIndex = 0;
            int espetaculoIndex = 0;
            int sessaoIndex = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][i] = line.charAt(i) == '1';
                }
                sessaoIndex++;
                if (sessaoIndex >= poltronasDisponiveis[areaIndex][espetaculoIndex].length) {
                    sessaoIndex = 0;
                    espetaculoIndex++;
                    if (espetaculoIndex >= poltronasDisponiveis[areaIndex].length) {
                        espetaculoIndex = 0;
                        areaIndex++;
                    }
                }
            }
            System.out.println("Dados das poltronas carregados com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados das poltronas.");
        }
    }

    // Método para reservar uma poltrona
    public void reservarPoltrona(Ingresso ingresso) {
        try {
            // Certifique-se de passar a área corretamente, usando apenas números
            String area = ingresso.getArea().split(" -")[0]; // Pegue apenas a parte numérica (se houver texto)
            int areaIndex = Integer.parseInt(area) - 1; // Convertendo a área corretamente
            int espetaculoIndex = Integer.parseInt(ingresso.getEspetaculo()) - 1;
            int sessaoIndex = Integer.parseInt(ingresso.getSessao()) - 1;
            int poltrona = ingresso.getPoltrona();

            poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][poltrona] = true;
        } catch (NumberFormatException e) {
            System.out.println("Erro ao tentar converter a área, espetáculo ou sessão para número.");
            e.printStackTrace();
        }
    }

    // Método para obter as poltronas disponíveis (método que você precisa adicionar)
    public Boolean[] getPoltronasDisponiveis(int area, int espetaculo, int sessao) {
        // Retorna o array de poltronas disponíveis para a área, espetáculo e sessão especificados
        return poltronasDisponiveis[area][espetaculo][sessao];
    }

    // Método para salvar os dados das poltronas
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("poltronas.txt"))) {
            for (int areaIndex = 0; areaIndex < poltronasDisponiveis.length; areaIndex++) {
                for (int espetaculoIndex = 0; espetaculoIndex < poltronasDisponiveis[areaIndex].length; espetaculoIndex++) {
                    for (int sessaoIndex = 0; sessaoIndex < poltronasDisponiveis[areaIndex][espetaculoIndex].length; sessaoIndex++) {
                        StringBuilder line = new StringBuilder();
                        for (boolean poltrona : poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex]) {
                            line.append(poltrona ? "1" : "0");  // Salva 1 para poltrona ocupada, 0 para disponível
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
}