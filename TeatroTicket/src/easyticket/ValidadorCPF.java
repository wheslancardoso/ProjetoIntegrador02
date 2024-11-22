package easyticket;

public class ValidadorCPF {

    public static boolean validaCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Valida CPFs conhecidos (todos iguais)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calculando o primeiro dígito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
        }
        int resto1 = (soma1 * 10) % 11;
        if (resto1 == 10) {
            resto1 = 0;
        }

        // Calculando o segundo dígito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
        }
        int resto2 = (soma2 * 10) % 11;
        if (resto2 == 10) {
            resto2 = 0;
        }

        // Verifica se os dois dígitos verificadores estão corretos
        return (resto1 == Integer.parseInt(String.valueOf(cpf.charAt(9)))) &&
                (resto2 == Integer.parseInt(String.valueOf(cpf.charAt(10))));
    }
}
