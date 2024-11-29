package easyticket;

import java.io.*;
import java.util.Arrays;

public class Teatro {
    private static Teatro instanciaUnica = null;
    private Boolean[][][][] poltronasDisponiveis; // Variável de instância

    // Definir as quantidades de poltronas por área
    private int[] poltronasPorArea = {25, 100, 5 * 6, 4 * 10, 50}; // Atualizado para frisa (6 frisas com 5 poltronas cada) e camarote (4 camarotes com 10 poltronas cada)

    // Construtor privado
    private Teatro() {
        inicializarPoltronas();  // Garante que as poltronas sejam inicializadas sempre
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
        poltronasDisponiveis = new Boolean[5][3][3][]; // Inicializa o array de poltronas (ajuste conforme seu modelo)
        for (int area = 0; area < poltronasDisponiveis.length; area++) {
            for (int espetaculo = 0; espetaculo < poltronasDisponiveis[area].length; espetaculo++) {
                for (int sessao = 0; sessao < poltronasDisponiveis[area][espetaculo].length; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new Boolean[poltronasPorArea[area]]; // Usando a quantidade de poltronas por área
                    Arrays.fill(poltronasDisponiveis[area][espetaculo][sessao], Boolean.FALSE);  // Inicializa todas as poltronas como disponíveis
                }
            }
        }
    }

    // Método para carregar os dados das poltronas a partir do arquivo "poltronas.txt"
    public void carregarDados() {
        File file = new File("poltronas.txt");

        // Verifica se o arquivo existe, caso contrário, cria um novo
        if (!file.exists()) {
            System.out.println("Arquivo poltronas.txt não encontrado. Criando um novo arquivo.");
            inicializarPoltronas();  // Inicializa as poltronas se o arquivo não existir
            salvarDados();  // Cria o arquivo com dados iniciais
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

    // Método para obter as poltronas disponíveis
    public Boolean[] getPoltronasDisponiveis(int area, int espetaculo, int sessao) {
        return poltronasDisponiveis[area][espetaculo][sessao];
    }

    // Método para reservar uma poltrona
    public void reservarPoltrona(Ingresso ingresso) {
        int areaIndex = Integer.parseInt(ingresso.getArea()) - 1;
        int espetaculoIndex = Integer.parseInt(ingresso.getEspetaculo()) - 1;
        int sessaoIndex = Integer.parseInt(ingresso.getSessao()) - 1;
        int poltrona = ingresso.getPoltrona();

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
                            line.append(poltrona ? "1" : "0");  // Salva '1' para poltrona ocupada, '0' para disponível
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
