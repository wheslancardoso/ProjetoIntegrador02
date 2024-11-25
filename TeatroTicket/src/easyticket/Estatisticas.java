package easyticket;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Estatisticas {
    private static Estatisticas instance = null;
    private List<Ingresso> ticketsVendidos;
    private static final String FILE_PATH = "tickets.txt";  // Alterado para .txt

    public Estatisticas() {
        this.ticketsVendidos = new ArrayList<>();
    }

    // Método estático para obter a instância única
    public static Estatisticas getInstance() {
        if (instance == null) {
            instance = new Estatisticas();
            instance.carregarDados();
        }
        return instance;
    }

    // Adiciona um ingresso vendido
    public void addSale(Ingresso ingresso) {
        ticketsVendidos.add(ingresso);
    }

    // Imprime os ingressos associados a um CPF com numeração
    public boolean printTicketsForClient(String cpf, StringBuilder mensagem) {
        boolean encontrou = false;
        int count = 1; // Contador para numerar os ingressos

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

    // Gera estatísticas de vendas
    public StringBuilder generateStatistics() {
        StringBuilder estatisticas = new StringBuilder("Estatísticas de Vendas:\n");

        // Mapas para armazenar dados por espetáculo e sessão
        Map<String, Integer> ingressosPorEspetaculo = new HashMap<>();
        Map<String, Double> lucroPorEspetaculo = new HashMap<>();

        Map<String, Integer> ocupacaoPorSessao = new HashMap<>();
        Map<String, Double> lucroPorSessao = new HashMap<>();

        // Inicializando os mapas com valores zero
        String[] espetaculos = {"1", "2", "3"};
        for (String espetaculo : espetaculos) {
            ingressosPorEspetaculo.put(espetaculo, 0);
            lucroPorEspetaculo.put(espetaculo, 0.0);
        }

        String[] sessoes = {"1", "2", "3"};
        for (String sessao : sessoes) {
            ocupacaoPorSessao.put(sessao, 0);
            lucroPorSessao.put(sessao, 0.0);
        }

        // Processando os ingressos vendidos
        for (Ingresso ingresso : ticketsVendidos) {
            // Contagem por espetáculo
            String esp = ingresso.getEspetaculo();
            ingressosPorEspetaculo.put(esp, ingressosPorEspetaculo.get(esp) + 1);
            lucroPorEspetaculo.put(esp, lucroPorEspetaculo.get(esp) + ingresso.getPreco());

            // Contagem por sessão
            String ses = ingresso.getSessao();
            ocupacaoPorSessao.put(ses, ocupacaoPorSessao.get(ses) + 1);
            lucroPorSessao.put(ses, lucroPorSessao.get(ses) + ingresso.getPreco());
        }

        // Determinando qual peça teve mais e menos ingressos vendidos
        String espMaisVendida = null;
        String espMenosVendida = null;
        int maxIngressos = -1;
        int minIngressos = Integer.MAX_VALUE;

        for (String esp : espetaculos) {
            int ingressos = ingressosPorEspetaculo.get(esp);
            if (ingressos > maxIngressos) {
                maxIngressos = ingressos;
                espMaisVendida = esp;
            }
            if (ingressos < minIngressos) {
                minIngressos = ingressos;
                espMenosVendida = esp;
            }
        }

        // Mapeando números para nomes dos espetáculos
        String espMaisVendidaNome = getNomeEspetaculo(espMaisVendida);
        String espMenosVendidaNome = getNomeEspetaculo(espMenosVendida);

        estatisticas.append("Peça com mais ingressos vendidos: ").append(espMaisVendidaNome)
                .append(" (").append(maxIngressos).append(" ingressos)\n");
        estatisticas.append("Peça com menos ingressos vendidos: ").append(espMenosVendidaNome)
                .append(" (").append(minIngressos).append(" ingressos)\n\n");

        // Determinando qual sessão teve maior e menor ocupação
        String sessaoMaiorOcupacao = null;
        String sessaoMenorOcupacao = null;
        int maxOcupacao = -1;
        int minOcupacao = Integer.MAX_VALUE;

        for (String ses : sessoes) {
            int ocupacao = ocupacaoPorSessao.get(ses);
            if (ocupacao > maxOcupacao) {
                maxOcupacao = ocupacao;
                sessaoMaiorOcupacao = ses;
            }
            if (ocupacao < minOcupacao) {
                minOcupacao = ocupacao;
                sessaoMenorOcupacao = ses;
            }
        }

        String sessaoMaiorOcupacaoNome = getNomeSessao(sessaoMaiorOcupacao);
        String sessaoMenorOcupacaoNome = getNomeSessao(sessaoMenorOcupacao);

        estatisticas.append("Sessão com maior ocupação: ").append(sessaoMaiorOcupacaoNome)
                .append(" (").append(maxOcupacao).append(" ingressos)\n");
        estatisticas.append("Sessão com menor ocupação: ").append(sessaoMenorOcupacaoNome)
                .append(" (").append(minOcupacao).append(" ingressos)\n\n");

        // Peça/sessão mais e menos lucrativa
        String espMaisLucrativa = null;
        String espMenosLucrativa = null;
        double maxLucroEsp = -1;
        double minLucroEsp = Double.MAX_VALUE;

        for (String esp : espetaculos) {
            double lucro = lucroPorEspetaculo.get(esp);
            if (lucro > maxLucroEsp) {
                maxLucroEsp = lucro;
                espMaisLucrativa = esp;
            }
            if (lucro < minLucroEsp) {
                minLucroEsp = lucro;
                espMenosLucrativa = esp;
            }
        }

        String espMaisLucrativaNome = getNomeEspetaculo(espMaisLucrativa);
        String espMenosLucrativaNome = getNomeEspetaculo(espMenosLucrativa);

        estatisticas.append("Peça mais lucrativa: ").append(espMaisLucrativaNome)
                .append(" (R$ ").append(String.format("%.2f", maxLucroEsp)).append(")\n");
        estatisticas.append("Peça menos lucrativa: ").append(espMenosLucrativaNome)
                .append(" (R$ ").append(String.format("%.2f", minLucroEsp)).append(")\n\n");

        // Lucro médio do teatro com todas as áreas por peça
        estatisticas.append("Lucro médio do teatro com todas as áreas por peça:\n");
        for (String esp : espetaculos) {
            int ingressosVendidos = ingressosPorEspetaculo.get(esp);
            double lucroTotal = lucroPorEspetaculo.get(esp);
            double lucroMedio = ingressosVendidos > 0 ? lucroTotal / ingressosVendidos : 0;
            String espNome = getNomeEspetaculo(esp);
            estatisticas.append(espNome).append(": R$ ").append(String.format("%.2f", lucroMedio)).append("\n");
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

    // Métodos para salvar e carregar dados dos ingressos vendidos
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

                    // Tenta converter o valor de poltrona para inteiro e preco para double
                    int poltrona = Integer.parseInt(parts[4].trim());
                    double preco = Double.parseDouble(parts[5].trim());

                    // Criar o ticket e adicionar à lista
                    Ingresso ingresso = new Ingresso(cpf, espetaculo, sessao, area, poltrona, preco);
                    ticketsVendidos.add(ingresso);
                }
            }
            System.out.println("Dados dos ingressos carregados com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados dos ingressos.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Erro de formato nos dados dos ingressos.");
        }
    }

}



