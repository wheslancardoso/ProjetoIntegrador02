package easyticket;

import javax.swing.*;

public class TicketSystem {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Ingressos");
        TicketManager manager = new TicketManager();

        while (true) {
            String opcao = JOptionPane.showInputDialog(frame, "Menu:\n" +
                    "1) Comprar Ingresso\n" +
                    "2) Imprimir Ingresso\n" +
                    "3) Estatísticas de Vendas\n" +
                    "4) Sair\n" +
                    "Escolha uma opção:");

            if (opcao == null || opcao.equals("4")) {
                System.exit(0);
            }

            switch (opcao) {
                case "1":
                    manager.comprarIngresso();
                    break;
                case "2":
                    manager.imprimirIngresso();
                    break;
                case "3":
                    manager.gerarEstatisticas();
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Opção inválida!");
            }
        }
    }
}
