package easyticket;

import javax.swing.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    private Theater theater;
    private Statistics statistics;

    public TicketManager() {
        this.theater = Theater.getInstance();
        this.statistics = new Statistics();
    }

    public boolean isCpfValido(String cpf) {
        return cpf.matches("\\d{11}");
    }

    public int escolherPoltrona(Ticket ticket) {
        int areaIndex = Integer.parseInt(ticket.getArea()) - 1;
        int espetaculoIndex = Integer.parseInt(ticket.getEspetaculo()) - 1;
        int sessaoIndex = Integer.parseInt(ticket.getSessao()) - 1;

        boolean[] poltronasDisponiveis = theater.getPoltronasDisponiveis(areaIndex, espetaculoIndex, sessaoIndex);
        List<Integer> poltronasLivres = new ArrayList<>();

        // Coletar as poltronas disponíveis
        for (int i = 0; i < poltronasDisponiveis.length; i++) {
            if (!poltronasDisponiveis[i]) {
                poltronasLivres.add(i + 1); // +1 para ajustar o índice
            }
        }

        if (poltronasLivres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há poltronas disponíveis nesta área.");
            return -1;
        }

        // Gerar string de intervalos
        StringBuilder intervalos = new StringBuilder();
        int inicio = poltronasLivres.get(0);
        int fim = inicio;

        for (int i = 1; i < poltronasLivres.size(); i++) {
            if (poltronasLivres.get(i) == fim + 1) {
                fim = poltronasLivres.get(i);
            } else {
                if (inicio == fim) {
                    intervalos.append(inicio).append(", ");
                } else {
                    intervalos.append(inicio).append("-").append(fim).append(", ");
                }
                inicio = fim = poltronasLivres.get(i);
            }
        }
        // Adicionar o último intervalo
        if (inicio == fim) {
            intervalos.append(inicio);
        } else {
            intervalos.append(inicio).append("-").append(fim);
        }

        String mensagem = "Poltronas disponíveis: " + intervalos.toString() + "\nEscolha a poltrona (número) ou 'C' para cancelar:";

        while (true) {
            String escolha = JOptionPane.showInputDialog(mensagem);
            if (escolha == null || escolha.equalsIgnoreCase("C")) {
                // Usuário cancelou a operação
                return -1;
            }

            try {
                int poltronaEscolhida = Integer.parseInt(escolha);
                if (poltronasLivres.contains(poltronaEscolhida)) {
                    return poltronaEscolhida - 1; // -1 para ajustar o índice
                } else {
                    JOptionPane.showMessageDialog(null, "Poltrona inválida ou já ocupada! Por favor, escolha uma poltrona disponível.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número de poltrona ou 'C' para cancelar.");
            }
        }
    }

    public void finalizarCompra(Ticket ticket) {
        theater.reservarPoltrona(ticket);
        statistics.addSale(ticket);
    }

    public String imprimirIngressos(String cpf) {
        StringBuilder mensagem = new StringBuilder("Ingressos do CPF " + cpf + ":\n");
        boolean encontrou = statistics.printTicketsForClient(cpf, mensagem);
        if (!encontrou) {
            mensagem.append("Nenhum ingresso encontrado para o CPF informado.");
        }
        return mensagem.toString();
    }

    public String gerarEstatisticas() {
        return statistics.generateStatistics().toString();
    }
}
