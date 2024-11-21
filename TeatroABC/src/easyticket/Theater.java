package easyticket;

public class Theater {
    // Estrutura: [Área][Espetáculo][Sessão][Poltrona]
    private boolean[][][][] poltronasDisponiveis;

    // Quantidade de poltronas por área
    private int[] poltronasPorArea = {25, 100, 15, 30, 50};

    public Theater() {
        // 5 áreas, 3 espetáculos, 3 sessões, número variável de poltronas
        poltronasDisponiveis = new boolean[5][3][3][];
        for (int area = 0; area < 5; area++) {
            for (int espetaculo = 0; espetaculo < 3; espetaculo++) {
                for (int sessao = 0; sessao < 3; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new boolean[poltronasPorArea[area]];
                }
            }
        }
    }

    public boolean[] getPoltronasDisponiveis(int area, int espetaculo, int sessao) {
        return poltronasDisponiveis[area][espetaculo][sessao];
    }

    public boolean isPoltronaDisponivel(Ticket ticket) {
        int areaIndex = Integer.parseInt(ticket.getArea()) - 1;
        int espetaculoIndex = Integer.parseInt(ticket.getEspetaculo()) - 1;
        int sessaoIndex = Integer.parseInt(ticket.getSessao()) - 1;
        int poltrona = ticket.getPoltrona();

        return !poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][poltrona];
    }

    public void reservarPoltrona(Ticket ticket) {
        int areaIndex = Integer.parseInt(ticket.getArea()) - 1;
        int espetaculoIndex = Integer.parseInt(ticket.getEspetaculo()) - 1;
        int sessaoIndex = Integer.parseInt(ticket.getSessao()) - 1;
        int poltrona = ticket.getPoltrona();

        poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][poltrona] = true;
    }
}
