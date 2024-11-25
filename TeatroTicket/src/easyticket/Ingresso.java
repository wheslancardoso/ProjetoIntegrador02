package easyticket;

public class Ingresso {
    private String cpf;
    private String espetaculo;
    private String sessao;
    private String area;
    private double preco;
    private int poltrona;

    // Constructor with all fields
    public Ingresso(String cpf, String espetaculo, String sessao, String area, int poltrona, double preco) {
        this.cpf = cpf;
        this.espetaculo = espetaculo;
        this.sessao = sessao;
        this.area = area;
        this.poltrona = poltrona;
        this.preco = preco;
    }

    // Constructor without poltrona and preco
    public Ingresso(String cpf, String espetaculo, String sessao, String area) {
        this.cpf = cpf;
        this.espetaculo = espetaculo;
        this.sessao = sessao;
        this.area = area;
        this.preco = calculatePrice(area);
    }

    private double calculatePrice(String area) {
        switch (area) {
            case "1": return 40.00;     // Plateia A
            case "2": return 60.00;     // Plateia B
            case "3": return 80.00;     // Frisa
            case "4": return 120.00;    // Camarote
            case "5": return 250.00;    // Balcao Nobre
            default: return 0.00;
        }
    }

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
