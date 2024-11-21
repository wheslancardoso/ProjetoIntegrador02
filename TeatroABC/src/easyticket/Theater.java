package easyticket;

public class Theater {
    private static Theater instanciaUnica = null;
    private boolean[][][][] poltronasDisponiveis;
    private int[] poltronasPorArea = {25, 100, 15, 30, 50};

    private Theater() {
        poltronasDisponiveis = new boolean[5][3][3][];
        for (int area = 0; area < 5; area++) {
            for (int espetaculo = 0; espetaculo < 3; espetaculo++) {
                for (int sessao = 0; sessao < 3; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new boolean[poltronasPorArea[area]];
                }
            }
        }
    }

    public static Theater getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Theater();
        }
        return instanciaUnica;
    }

    public boolean[] getPoltronasDisponiveis(int area, int espetaculo, int sessao) {
        return poltronasDisponiveis[area][espetaculo][sessao];
    }

    public void reservarPoltrona(Ticket ticket) {
        int areaIndex = Integer.parseInt(ticket.getArea()) - 1;
        int espetaculoIndex = Integer.parseInt(ticket.getEspetaculo()) - 1;
        int sessaoIndex = Integer.parseInt(ticket.getSessao()) - 1;
        int poltrona = ticket.getPoltrona();

        poltronasDisponiveis[areaIndex][espetaculoIndex][sessaoIndex][poltrona] = true;
    }
}

