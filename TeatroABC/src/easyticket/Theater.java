package easyticket;

public class Theater {
    // Instância única da classe Theater
    private static Theater instanciaUnica = null;

    // Estrutura: [Área][Espetáculo][Sessão][Poltrona]
    private boolean[][][][] poltronasDisponiveis;

    // Quantidade de poltronas por área
    private int[] poltronasPorArea = {25, 100, 15, 30, 50};

    // Construtor privado para evitar múltiplas instâncias
    private Theater() {
        // Inicializando as poltronas
        poltronasDisponiveis = new boolean[5][3][3][];
        for (int area = 0; area < 5; area++) {
            for (int espetaculo = 0; espetaculo < 3; espetaculo++) {
                for (int sessao = 0; sessao < 3; sessao++) {
                    poltronasDisponiveis[area][espetaculo][sessao] = new boolean[poltronasPorArea[area]];
                }
            }
        }
    }

    // Método estático para obter a instância única
    public static Theater getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Theater();
        }
        return instanciaUnica;
    }

    // Métodos existentes permanecem os mesmos
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
