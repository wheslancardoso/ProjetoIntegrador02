package easyticket;

public class Ticket {
    private String cpf;
    private String espetaculo;
    private String sessao;
    private String area;
    private double preco;
    private int poltrona;

    // Constructor with all fields
    public Ticket(String cpf, String espetaculo, String sessao, String area, int poltrona, double preco) {
        this.cpf = cpf;
        this.espetaculo = espetaculo;
        this.sessao = sessao;
        this.area = area;
        this.poltrona = poltrona;
        this.preco = preco;
    }

    // Constructor without poltrona and preco
    public Ticket(String cpf, String espetaculo, String sessao, String area) {
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

    // Mapeamento para nomes legíveis
    public String getNomeEspetaculo() {
        switch (espetaculo) {
            case "1": return " As tranças da vovó careca ";
            case "2": return " A volta dos que chegaram a partir ";
            case "3": return " Poeira em alto mar ";
            default: return " Desconhecido ";
        }
    }

    public String getNomeSessao() {
        switch (sessao) {
            case "1": return " Manhã ";
            case "2": return " Tarde ";
            case "3": return " Noite ";
            default: return " Desconhecida ";
        }
    }

    public String getNomeArea() {
        switch (area) {
            case "1": return " Plateia A ";
            case "2": return " Plateia B ";
            case "3": return " Frisa ";
            case "4": return " Camarote ";
            case "5": return " Balcão Nobre ";
            default: return " Desconhecida ";
        }
    }

    @Override
    public String toString() {
        return " Espetáculo: " + getNomeEspetaculo() +
                "\n , Sessão: " + getNomeSessao() +
                "\n , Área: " + getNomeArea() +
                "\n , Poltrona: " + (poltrona + 1) +
                "\n , Preço: R$ " + String.format("%.2f", preco).replace(".", ",");
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
