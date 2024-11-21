package easyticket;

import java.util.ArrayList;
import java.util.List;

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
        int totalIngressos = ticketsVendidos.size();
        double totalLucro = 0;

        for (Ticket ticket : ticketsVendidos) {
            totalLucro += ticket.getPreco();
        }

        estatisticas.append("Total de Ingressos Vendidos: ").append(totalIngressos).append("\n")
                .append("Total de Lucro: R$").append(totalLucro);

        return estatisticas;
    }
}
