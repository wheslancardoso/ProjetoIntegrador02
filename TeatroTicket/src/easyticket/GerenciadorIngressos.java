package easyticket;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorIngressos {
    private Teatro teatro;
    private Estatisticas estatisticas;

    public GerenciadorIngressos() {
        this.teatro = Teatro.getInstance();
        this.estatisticas = Estatisticas.getInstance(); // Usando a instância singleton
        // Carregar dados
        estatisticas.carregarDados();
    }

    public boolean isCpfValido(String cpf) {
        return cpf.matches("\\d{11}");
    }

    public int escolherPoltrona(Ingresso ingresso) {
        int areaIndice = Integer.parseInt(ingresso.getArea()) - 1;
        int espetaculoIndice = Integer.parseInt(ingresso.getEspetaculo()) - 1;
        int sessaoIndice = Integer.parseInt(ingresso.getSessao()) - 1;

        Boolean[] poltronasDisponiveis = teatro.getPoltronasDisponiveis(areaIndice, espetaculoIndice, sessaoIndice);
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

    public void salvarDados() {
        teatro.salvarDados();
        estatisticas.salvarDados();
    }

    public void finalizarCompra(Ingresso ingresso) {
        teatro.reservarPoltrona(ingresso);
        estatisticas.addSale(ingresso); // Usando o método addSale correto
        // Salvar dados após a compra
        teatro.salvarDados();
        estatisticas.salvarDados();
    }

    public String imprimirIngressos(String cpf) {
        StringBuilder mensagem = new StringBuilder("Ingressos do CPF " + cpf + ":\n\n");
        boolean encontrou = estatisticas.imprimirIngressosParaCliente(cpf, mensagem); // Usando o método printTicketsForClient
        if (!encontrou) {
            mensagem.append("Nenhum ingresso encontrado para o CPF informado.");
        }
        return mensagem.toString();
    }

    public String gerarEstatisticas() {
        return estatisticas.criarEstatisticas().toString();
    }
}
