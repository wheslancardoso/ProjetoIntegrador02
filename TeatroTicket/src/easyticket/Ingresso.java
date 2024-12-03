package easyticket;

public class Ingresso {
    private String cpf;
    private String espetaculo;
    private String sessao;
    private String area;
    private double preco;
    private int poltrona;

    // Construtor com todos os campos
    public Ingresso(String cpf, String espetaculo, String sessao, String area, int poltrona, double preco) {
        this.cpf = cpf;
        this.espetaculo = espetaculo;
        this.sessao = sessao;
        this.area = area;
        this.poltrona = poltrona;
        this.preco = preco;
    }

    // Construtor sem poltrona e preco (automaticamente calcula o preço baseado na área)
    public Ingresso(String cpf, String espetaculo, String sessao, String area) {
        this.cpf = cpf;
        this.espetaculo = espetaculo;
        this.sessao = sessao;
        this.area = area; // A área é passada como uma string numérica
        this.preco = calcularPreco(area);  // Calcula o preço baseado na área
    }

    private double calcularPreco(String area) {
        switch (area) {
            case "1": return 40.00;     // Plateia A
            case "2": return 60.00;     // Plateia B
            case "3": return 120.00;    // Frisa
            case "4": return 80.00;     // Camarote
            case "5": return 250.00;    // Balcao Nobre
            default: return 0.00; // Caso inválido
        }
    }

    // Método que retorna o número de poltronas por área
    public int getQuantidadePoltronas() {
        switch (area) {
            case "1": return 25;        // Plateia A
            case "2": return 100;       // Plateia B
            case "3": return 30;        // Frisa (6 frisas com 5 poltronas por frisa)
            case "4": return 40;        // Camarote (4 camarotes com 10 poltronas por camarote)
            case "5": return 50;        // Balcao Nobre
            default: return 0;
        }
    }

    // Getters e setters
    public String getCpf() { return cpf; }
    public String getEspetaculo() { return espetaculo; }
    public String getSessao() { return sessao; }
    public String getArea() { return area; }
    public double getPreco() { return preco; }
    public int getPoltrona() { return poltrona; }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }
}
