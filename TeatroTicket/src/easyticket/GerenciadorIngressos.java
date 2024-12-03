package easyticket;

import java.util.List;
import java.util.ArrayList;

public class GerenciadorIngressos {

    private String areaSelecionada;
    private String sessaoSelecionada;
    private String espetaculoSelecionado;
    private List<Ingresso> ingressosReservados;

    // Construtor
    public GerenciadorIngressos() {
        ingressosReservados = new ArrayList<>();
    }

    // Métodos de configuração (Setters)
    public void setAreaSelecionada(String area) {
        this.areaSelecionada = area;
    }

    public void setSessaoSelecionada(String sessao) {
        this.sessaoSelecionada = sessao;
    }

    public void setEspetaculoSelecionado(String espetaculo) {
        this.espetaculoSelecionado = espetaculo;
    }

    // Métodos de obtenção (Getters)
    public String getAreaSelecionada() {
        return areaSelecionada;
    }

    public String getSessaoSelecionada() {
        return sessaoSelecionada;
    }

    public String getEspetaculoSelecionado() {
        return espetaculoSelecionado;
    }

    // Método para registrar um ingresso reservado
    public void finalizarCompra(Ingresso ingresso) {
        ingressosReservados.add(ingresso);  // Adiciona o ingresso à lista de ingressos reservados
    }

    // Método para obter os ingressos reservados
    public List<Ingresso> getIngressosReservados() {
        return ingressosReservados;
    }

    // Método para registrar a escolha da poltrona
    public void escolherPoltrona(Ingresso ingresso) {
        // Neste caso, podemos simplesmente adicionar o ingresso à lista de ingressos reservados
        // ou você pode adicionar lógica adicional para controle de poltronas, etc.
        if (!ingressosReservados.contains(ingresso)) {
            ingressosReservados.add(ingresso);
        }
    }
}
