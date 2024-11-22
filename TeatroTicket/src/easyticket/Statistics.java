package easyticket;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Statistics {
    private static Statistics instance = null;
    private List<Ticket> ticketsVendidos;
    private static final String FILE_PATH = "tickets.json";

    public Statistics() {
        this.ticketsVendidos = new ArrayList<>();
    }

    // Método estático para obter a instância única
    public static Statistics getInstance() {
        if (instance == null) {
            instance = new Statistics();
            instance.carregarDados();
        }
        return instance;
    }

    // Adiciona um ingresso vendido
    public void addSale(Ticket ticket) {
        ticketsVendidos.add(ticket);
        // Não chame carregarDados() aqui para evitar sobrescrita de dados
    }

    // Imprime os ingressos associados a um CPF com numeração
    public boolean printTicketsForClient(String cpf, StringBuilder mensagem) {
        boolean encontrou = false;
        int count = 1; // Contador para numerar os ingressos

        for (Ticket ticket : ticketsVendidos) {
            if (ticket.getCpf().equals(cpf)) {
                encontrou = true;
                mensagem.append("Ingresso ").append(count).append(":\n");
                mensagem.append("Espetáculo: ").append(getNomeEspetaculo(ticket.getEspetaculo())).append("\n");
                mensagem.append("Sessão: ").append(getNomeSessao(ticket.getSessao())).append("\n");
                mensagem.append("Área: ").append(getNomeArea(ticket.getArea())).append("\n");
                mensagem.append("Poltrona: ").append(ticket.getPoltrona() + 1).append("\n");
                mensagem.append("Preço: R$ ").append(String.format("%.2f", ticket.getPreco())).append("\n\n");
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
        for (Ticket ticket : ticketsVendidos) {
            // Contagem por espetáculo
            String esp = ticket.getEspetaculo();
            ingressosPorEspetaculo.put(esp, ingressosPorEspetaculo.get(esp) + 1);
            lucroPorEspetaculo.put(esp, lucroPorEspetaculo.get(esp) + ticket.getPreco());

            // Contagem por sessão
            String ses = ticket.getSessao();
            ocupacaoPorSessao.put(ses, ocupacaoPorSessao.get(ses) + 1);
            lucroPorSessao.put(ses, lucroPorSessao.get(ses) + ticket.getPreco());
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
        switch(area) {
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
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(ticketsVendidos, writer);
            System.out.println("Dados dos ingressos salvos com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar os dados dos ingressos.");
        }
    }

    public void carregarDados() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Ticket>>(){}.getType();
            List<Ticket> loadedTickets = gson.fromJson(reader, listType);
            if (loadedTickets != null) {
                ticketsVendidos = loadedTickets;
                System.out.println("Dados dos ingressos carregados com sucesso.");
            } else {
                ticketsVendidos = new ArrayList<>();
                System.out.println("Nenhum ingresso encontrado no arquivo. Lista iniciada vazia.");
            }
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, inicia lista vazia
            System.out.println("Arquivo " + FILE_PATH + " não encontrado. Iniciando lista de ingressos vazia.");
            ticketsVendidos = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados dos ingressos. Lista iniciada vazia.");
            ticketsVendidos = new ArrayList<>();
        }
    }

    public void limparDados() {
        // Limpa a lista de ingressos vendidos
        ticketsVendidos.clear();

        // Exclui o arquivo de dados
        File file = new File(FILE_PATH);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Arquivo " + FILE_PATH + " excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir o arquivo " + FILE_PATH + ".");
            }
        } else {
            System.out.println("Arquivo " + FILE_PATH + " não existe.");
        }
    }
}