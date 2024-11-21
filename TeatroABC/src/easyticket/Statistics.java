package easyticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Statistics {
    private List<Ticket> ticketsVendidos;

    public Statistics() {
        this.ticketsVendidos = new ArrayList<>();
    }

    public void addSale(Ticket ticket) {
        ticketsVendidos.add(ticket);
    }

    public boolean printTicketsForClient(String cpf, StringBuilder mensagem) {
        boolean encontrou = false;
        for (Ticket ticket : ticketsVendidos) {
            if (ticket.getCpf().equals(cpf)) {
                encontrou = true;
                mensagem.append("Espetáculo: ").append(ticket.getEspetaculo())
                        .append("\nSessão: ").append(ticket.getSessao())
                        .append("\nÁrea: ").append(ticket.getArea())
                        .append("\nPoltrona: ").append(ticket.getPoltrona() + 1)
                        .append("\nPreço: R$").append(ticket.getPreco())
                        .append("\n\n");
            }
        }
        return encontrou;
    }

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
}
