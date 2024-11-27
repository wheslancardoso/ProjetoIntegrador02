package easyticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Estatisticas {
    private static Estatisticas instance = null;
    private List<Ingresso> ticketsVendidos;
    private static final String FILE_PATH = "tickets.txt";

    public Estatisticas() {
        this.ticketsVendidos = new ArrayList<>();
    }

    public static Estatisticas getInstance() {
        if (instance == null) {
            instance = new Estatisticas();
            instance.carregarDados();
        }
        return instance;
    }

    public void addSale(Ingresso ingresso) {
        ticketsVendidos.add(ingresso);
    }

    public boolean printTicketsForClient(String cpf, StringBuilder mensagem) {
        boolean encontrou = false;
        int count = 1;

        for (Ingresso ingresso : ticketsVendidos) {
            if (ingresso.getCpf().equals(cpf)) {
                encontrou = true;
                mensagem.append("Ingresso ").append(count).append(":\n");
                mensagem.append("Espetáculo: ").append(getNomeEspetaculo(ingresso.getEspetaculo())).append("\n");
                mensagem.append("Sessão: ").append(getNomeSessao(ingresso.getSessao())).append("\n");
                mensagem.append("Área: ").append(getNomeArea(ingresso.getArea())).append("\n");
                mensagem.append("Poltrona: ").append(ingresso.getPoltrona() + 1).append("\n");
                mensagem.append("Preço: R$ ").append(String.format("%.2f", ingresso.getPreco())).append("\n\n");
                count++;
            }
        }
        return encontrou;
    }

    public StringBuilder generateStatistics() {
        StringBuilder estatisticas = new StringBuilder("Estatísticas de Vendas:\n");

        String[] espetaculos = {"1", "2", "3"};
        String[] sessoes = {"1", "2", "3"};

        // Arrays para armazenar dados por espetáculo e sessão
        int[] ingressosPorEspetaculo = new int[espetaculos.length];
        double[] lucroPorEspetaculo = new double[espetaculos.length];

        int[] ocupacaoPorSessao = new int[sessoes.length];
        double[] lucroPorSessao = new double[sessoes.length];

        double lucroTotal = 0.0;

        // Processando os ingressos vendidos
        for (Ingresso ingresso : ticketsVendidos) {
            int espIndex = Integer.parseInt(ingresso.getEspetaculo()) - 1;
            int sesIndex = Integer.parseInt(ingresso.getSessao()) - 1;

            ingressosPorEspetaculo[espIndex]++;
            lucroPorEspetaculo[espIndex] += ingresso.getPreco();

            ocupacaoPorSessao[sesIndex]++;
            lucroPorSessao[sesIndex] += ingresso.getPreco();

            lucroTotal += ingresso.getPreco();
        }

        estatisticas.append("Lucro total: R$ ").append(String.format("%.2f", lucroTotal)).append("\n\n");

        // Determinando a peça com mais e menos ingressos vendidos
        int maxIngressos = -1, minIngressos = Integer.MAX_VALUE;
        int espMaisVendida = -1, espMenosVendida = -1;

        for (int i = 0; i < espetaculos.length; i++) {
            if (ingressosPorEspetaculo[i] > maxIngressos) {
                maxIngressos = ingressosPorEspetaculo[i];
                espMaisVendida = i;
            }
            if (ingressosPorEspetaculo[i] < minIngressos) {
                minIngressos = ingressosPorEspetaculo[i];
                espMenosVendida = i;
            }
        }

        estatisticas.append("Peça com mais ingressos vendidos: ").append(getNomeEspetaculo(espetaculos[espMaisVendida]))
                .append(" (").append(maxIngressos).append(" ingressos)\n");
        estatisticas.append("Peça com menos ingressos vendidos: ").append(getNomeEspetaculo(espetaculos[espMenosVendida]))
                .append(" (").append(minIngressos).append(" ingressos)\n\n");

        // Determinando qual sessão teve maior e menor ocupação
        int maxOcupacao = -1, minOcupacao = Integer.MAX_VALUE;
        int sessaoMaiorOcupacao = -1, sessaoMenorOcupacao = -1;

        for (int i = 0; i < sessoes.length; i++) {
            if (ocupacaoPorSessao[i] > maxOcupacao) {
                maxOcupacao = ocupacaoPorSessao[i];
                sessaoMaiorOcupacao = i;
            }
            if (ocupacaoPorSessao[i] < minOcupacao) {
                minOcupacao = ocupacaoPorSessao[i];
                sessaoMenorOcupacao = i;
            }
        }

        estatisticas.append("Sessão com maior ocupação: ").append(getNomeSessao(sessoes[sessaoMaiorOcupacao]))
                .append(" (").append(maxOcupacao).append(" ingressos)\n");
        estatisticas.append("Sessão com menor ocupação: ").append(getNomeSessao(sessoes[sessaoMenorOcupacao]))
                .append(" (").append(minOcupacao).append(" ingressos)\n\n");

        // Peça mais e menos lucrativa
        double maxLucroEsp = -1, minLucroEsp = Double.MAX_VALUE;
        int espMaisLucrativa = -1, espMenosLucrativa = -1;

        for (int i = 0; i < espetaculos.length; i++) {
            if (lucroPorEspetaculo[i] > maxLucroEsp) {
                maxLucroEsp = lucroPorEspetaculo[i];
                espMaisLucrativa = i;
            }
            if (lucroPorEspetaculo[i] < minLucroEsp) {
                minLucroEsp = lucroPorEspetaculo[i];
                espMenosLucrativa = i;
            }
        }

        estatisticas.append("Peça mais lucrativa: ").append(getNomeEspetaculo(espetaculos[espMaisLucrativa]))
                .append(" (R$ ").append(String.format("%.2f", maxLucroEsp)).append(")\n");
        estatisticas.append("Peça menos lucrativa: ").append(getNomeEspetaculo(espetaculos[espMenosLucrativa]))
                .append(" (R$ ").append(String.format("%.2f", minLucroEsp)).append(")\n\n");

        // Lucro médio por peça
        estatisticas.append("Lucro médio do teatro com todas as áreas por peça:\n");
        for (int i = 0; i < espetaculos.length; i++) {
            double lucroMedio = (ingressosPorEspetaculo[i] > 0)
                    ? lucroPorEspetaculo[i] / ingressosPorEspetaculo[i]
                    : 0;
            estatisticas.append(getNomeEspetaculo(espetaculos[i]))
                    .append(": R$ ").append(String.format("%.2f", lucroMedio)).append("\n");
        }

        return estatisticas;
    }

    private String getNomeEspetaculo(String esp) {
        switch (esp) {
            case "1":
                return "As tranças da vovó careca";
            case "2":
                return "A volta dos que chegaram a partir";
            case "3":
                return "Poeira em alto mar";
            default:
                return "Desconhecido";
        }
    }

    private String getNomeSessao(String ses) {
        switch (ses) {
            case "1":
                return "Manhã";
            case "2":
                return "Tarde";
            case "3":
                return "Noite";
            default:
                return "Desconhecida";
        }
    }

    private String getNomeArea(String area) {
        switch (area) {
            case "1":
                return "Plateia A";
            case "2":
                return "Plateia B";
            case "3":
                return "Frisa";
            case "4":
                return "Camarote";
            case "5":
                return "Balcão Nobre";
            default:
                return "Desconhecida";
        }
    }

    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Ingresso ingresso : ticketsVendidos) {
                writer.write(ingresso.getCpf() + "," + ingresso.getEspetaculo() + "," + ingresso.getSessao() + "," +
                        ingresso.getArea() + "," + ingresso.getPoltrona() + "," + ingresso.getPreco());
                writer.newLine();
            }
            System.out.println("Dados dos ingressos salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar os dados dos ingressos.");
        }
    }

    public void carregarDados() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Arquivo " + FILE_PATH + " não encontrado. Nenhum dado foi carregado.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String cpf = parts[0];
                    String espetaculo = parts[1];
                    String sessao = parts[2];
                    String area = parts[3];

                    int poltrona = Integer.parseInt(parts[4].trim());
                    double preco = Double.parseDouble(parts[5].trim());

                    Ingresso ingresso = new Ingresso(cpf, espetaculo, sessao, area, poltrona, preco);
                    ticketsVendidos.add(ingresso);
                }
            }
            System.out.println("Dados dos ingressos carregados com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados dos ingressos.");
        }
    }
}
