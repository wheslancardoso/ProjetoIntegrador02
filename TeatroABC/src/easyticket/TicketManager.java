package easyticket;

import javax.swing.*;

import javax.swing.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class TicketManager {
    private Theater theater;
    private Statistics statistics;

    public TicketManager() {
        this.theater = Theater.getInstance(); // Obtém a instância única
        this.statistics = new Statistics();
    }
    public void comprarIngresso() {
        try {
            String cpf = JOptionPane.showInputDialog("Informe o CPF (apenas números):");
            if (cpf == null || cpf.isEmpty() || !isCpfValido(cpf)) {
                JOptionPane.showMessageDialog(null, "CPF inválido!");
                return;
            }

            String espetaculo = JOptionPane.showInputDialog(null, "Escolha o espetáculo:\n1) As tranças da vovó careca\n2) A volta dos que chegaram a partir\n3) Poeira em alto mar");
            if (espetaculo == null || !espetaculo.matches("[1-3]")) {
                JOptionPane.showMessageDialog(null, "Espetáculo inválido!");
                return;
            }

            String sessao = JOptionPane.showInputDialog("Escolha a sessão:\n1) Manhã\n2) Tarde\n3) Noite");
            if (sessao == null || !sessao.matches("[1-3]")) {
                JOptionPane.showMessageDialog(null, "Sessão inválida!");
                return;
            }

            String area = JOptionPane.showInputDialog("Escolha a área:\n1) Plateia A - R$40,00\n2) Plateia B - R$60,00\n3) Frisa - R$80,00\n4) Camarote - R$120,00\n5) Balcão Nobre - R$250,00");
            if (area == null || !area.matches("[1-5]")) {
                JOptionPane.showMessageDialog(null, "Área inválida!");
                return;
            }

            Ticket ticket = new Ticket(cpf, espetaculo, sessao, area);

            int poltronaEscolhida = escolherPoltrona(ticket);
            if (poltronaEscolhida == -1) {
                // Usuário cancelou a operação de escolher poltrona
                JOptionPane.showMessageDialog(null, "Compra de ingresso cancelada.");
                return;
            }

            ticket.setPoltrona(poltronaEscolhida);
            theater.reservarPoltrona(ticket);

            statistics.addSale(ticket);
            JOptionPane.showMessageDialog(null, "Ingresso comprado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao comprar ingresso: " + e.getMessage());
        }
    }

    private int escolherPoltrona(Ticket ticket) {
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

    public void imprimirIngresso() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF para imprimir os ingressos:");
        if (cpf == null || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF não pode ser vazio!");
            return;
        }

        StringBuilder mensagem = new StringBuilder("Ingressos do CPF " + cpf + ":\n");
        boolean encontrou = statistics.printTicketsForClient(cpf, mensagem);
        if (!encontrou) {
            mensagem.append("Nenhum ingresso encontrado para o CPF informado.");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public void gerarEstatisticas() {
        StringBuilder estatisticas = statistics.generateStatistics();
        JOptionPane.showMessageDialog(null, estatisticas.toString());
    }

    private boolean isCpfValido(String cpf) {
        return cpf.matches("\\d{11}");
    }
}
