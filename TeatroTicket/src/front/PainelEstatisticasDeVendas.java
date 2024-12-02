/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package front;

/**
 *
 * @author WC
 */
public class PainelEstatisticasDeVendas extends javax.swing.JPanel {

    /**
     * Creates new form PainelEstatisticasDeVendas
     */
    public PainelEstatisticasDeVendas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelEstatisticas = new javax.swing.JPanel();
        etiquetaTitulo = new javax.swing.JLabel();
        painelLucro = new javax.swing.JPanel();
        campoLucro = new javax.swing.JFormattedTextField();
        etiquetaLucro = new javax.swing.JLabel();
        painelRankIngressosVendidos = new javax.swing.JPanel();
        campoMaisVendido = new javax.swing.JFormattedTextField();
        etiquetaMaisVendido = new javax.swing.JLabel();
        etiquetaMenosVendido = new javax.swing.JLabel();
        campoMenosVendido = new javax.swing.JFormattedTextField();
        painelRankOcupacao = new javax.swing.JPanel();
        etiquetaMaiorOcupacao = new javax.swing.JLabel();
        etiquetaMenorOcupacao = new javax.swing.JLabel();
        campoMenorOcupacao = new javax.swing.JFormattedTextField();
        campoMaiorOcupacao = new javax.swing.JFormattedTextField();
        painelRankLucro = new javax.swing.JPanel();
        campoMaisLucrativo = new javax.swing.JFormattedTextField();
        etiquetaMaisLucrativo = new javax.swing.JLabel();
        etiquetaMenosLucrativo = new javax.swing.JLabel();
        campoMenosLucrativo = new javax.swing.JFormattedTextField();
        PainelLucroMedio = new javax.swing.JPanel();
        campoLucroMedioEsp1 = new javax.swing.JFormattedTextField();
        etiquetaEspetaculo1 = new javax.swing.JLabel();
        etiquetaEspetaculo2 = new javax.swing.JLabel();
        campoLucroMedioEsp2 = new javax.swing.JFormattedTextField();
        etiquetaSubtitulo = new javax.swing.JLabel();
        etiquetaEspetaculo3 = new javax.swing.JLabel();
        campoLucroMedioEsp3 = new javax.swing.JFormattedTextField();
        VoltarMenu = new javax.swing.JButton();
        botaoGerarEstatisticas = new javax.swing.JButton();

        painelEstatisticas.setBackground(new java.awt.Color(255, 255, 255));
        painelEstatisticas.setPreferredSize(new java.awt.Dimension(950, 680));

        etiquetaTitulo.setBackground(new java.awt.Color(21, 63, 69));
        etiquetaTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etiquetaTitulo.setText("Estatísticas de Vendas");

        painelLucro.setBackground(new java.awt.Color(21, 63, 69));
        painelLucro.setPreferredSize(new java.awt.Dimension(696, 49));

        etiquetaLucro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaLucro.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaLucro.setText("Lucro Total");

        javax.swing.GroupLayout painelLucroLayout = new javax.swing.GroupLayout(painelLucro);
        painelLucro.setLayout(painelLucroLayout);
        painelLucroLayout.setHorizontalGroup(
            painelLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLucroLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(etiquetaLucro)
                .addGap(374, 374, 374)
                .addComponent(campoLucro, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        painelLucroLayout.setVerticalGroup(
            painelLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLucroLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(painelLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaLucro)
                    .addComponent(campoLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        painelRankIngressosVendidos.setBackground(new java.awt.Color(21, 63, 69));
        painelRankIngressosVendidos.setPreferredSize(new java.awt.Dimension(696, 93));

        campoMaisVendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoMaisVendidoActionPerformed(evt);
            }
        });

        etiquetaMaisVendido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaMaisVendido.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMaisVendido.setText("Espetáculo com mais ingresso vendidos");

        etiquetaMenosVendido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaMenosVendido.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMenosVendido.setText("Espetáculo com menos ingresso vendidos");

        javax.swing.GroupLayout painelRankIngressosVendidosLayout = new javax.swing.GroupLayout(painelRankIngressosVendidos);
        painelRankIngressosVendidos.setLayout(painelRankIngressosVendidosLayout);
        painelRankIngressosVendidosLayout.setHorizontalGroup(
            painelRankIngressosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRankIngressosVendidosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(painelRankIngressosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaMaisVendido)
                    .addComponent(etiquetaMenosVendido))
                .addGap(172, 172, 172)
                .addGroup(painelRankIngressosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoMenosVendido)
                    .addComponent(campoMaisVendido, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        painelRankIngressosVendidosLayout.setVerticalGroup(
            painelRankIngressosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRankIngressosVendidosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(painelRankIngressosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMaisVendido)
                    .addComponent(campoMaisVendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(painelRankIngressosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMenosVendido)
                    .addComponent(campoMenosVendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        painelRankOcupacao.setBackground(new java.awt.Color(21, 63, 69));
        painelRankOcupacao.setPreferredSize(new java.awt.Dimension(696, 93));

        etiquetaMaiorOcupacao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaMaiorOcupacao.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMaiorOcupacao.setText("Sessão com maior ocupação");

        etiquetaMenorOcupacao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaMenorOcupacao.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMenorOcupacao.setText("Sessão com menor ocupação");

        javax.swing.GroupLayout painelRankOcupacaoLayout = new javax.swing.GroupLayout(painelRankOcupacao);
        painelRankOcupacao.setLayout(painelRankOcupacaoLayout);
        painelRankOcupacaoLayout.setHorizontalGroup(
            painelRankOcupacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRankOcupacaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(painelRankOcupacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelRankOcupacaoLayout.createSequentialGroup()
                        .addComponent(etiquetaMenorOcupacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                        .addComponent(campoMenorOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelRankOcupacaoLayout.createSequentialGroup()
                        .addComponent(etiquetaMaiorOcupacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoMaiorOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        painelRankOcupacaoLayout.setVerticalGroup(
            painelRankOcupacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRankOcupacaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(painelRankOcupacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMaiorOcupacao)
                    .addComponent(campoMaiorOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(painelRankOcupacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMenorOcupacao)
                    .addComponent(campoMenorOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        painelRankLucro.setBackground(new java.awt.Color(21, 63, 69));
        painelRankLucro.setPreferredSize(new java.awt.Dimension(696, 93));

        campoMaisLucrativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoMaisLucrativoActionPerformed(evt);
            }
        });

        etiquetaMaisLucrativo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaMaisLucrativo.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMaisLucrativo.setText("Espetáculo mais lucrativo");

        etiquetaMenosLucrativo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaMenosLucrativo.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMenosLucrativo.setText("Espetáculo menos lucrativo");

        javax.swing.GroupLayout painelRankLucroLayout = new javax.swing.GroupLayout(painelRankLucro);
        painelRankLucro.setLayout(painelRankLucroLayout);
        painelRankLucroLayout.setHorizontalGroup(
            painelRankLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRankLucroLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(painelRankLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelRankLucroLayout.createSequentialGroup()
                        .addComponent(etiquetaMenosLucrativo)
                        .addGap(267, 267, 267)
                        .addComponent(campoMenosLucrativo, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                    .addGroup(painelRankLucroLayout.createSequentialGroup()
                        .addComponent(etiquetaMaisLucrativo)
                        .addGap(280, 280, 280)
                        .addComponent(campoMaisLucrativo, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        painelRankLucroLayout.setVerticalGroup(
            painelRankLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRankLucroLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(painelRankLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMaisLucrativo)
                    .addComponent(campoMaisLucrativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(painelRankLucroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMenosLucrativo)
                    .addComponent(campoMenosLucrativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        PainelLucroMedio.setBackground(new java.awt.Color(21, 63, 69));
        PainelLucroMedio.setPreferredSize(new java.awt.Dimension(696, 93));

        etiquetaEspetaculo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaEspetaculo1.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaEspetaculo1.setText("The Batman");

        etiquetaEspetaculo2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaEspetaculo2.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaEspetaculo2.setText("Kill Bill: Volume 1 ");

        etiquetaSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaSubtitulo.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaSubtitulo.setText("Lucro médio do teatro com todas as áreas por espetáculo");

        etiquetaEspetaculo3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        etiquetaEspetaculo3.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaEspetaculo3.setText("Django Livre");

        javax.swing.GroupLayout PainelLucroMedioLayout = new javax.swing.GroupLayout(PainelLucroMedio);
        PainelLucroMedio.setLayout(PainelLucroMedioLayout);
        PainelLucroMedioLayout.setHorizontalGroup(
            PainelLucroMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLucroMedioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PainelLucroMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLucroMedioLayout.createSequentialGroup()
                        .addComponent(etiquetaEspetaculo1)
                        .addGap(363, 363, 363)
                        .addComponent(campoLucroMedioEsp1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                    .addGroup(PainelLucroMedioLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(etiquetaSubtitulo))
                    .addGroup(PainelLucroMedioLayout.createSequentialGroup()
                        .addComponent(etiquetaEspetaculo3)
                        .addGap(357, 357, 357)
                        .addComponent(campoLucroMedioEsp3, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                    .addGroup(PainelLucroMedioLayout.createSequentialGroup()
                        .addComponent(etiquetaEspetaculo2)
                        .addGap(321, 321, 321)
                        .addComponent(campoLucroMedioEsp2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        PainelLucroMedioLayout.setVerticalGroup(
            PainelLucroMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLucroMedioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaSubtitulo)
                .addGap(16, 16, 16)
                .addGroup(PainelLucroMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaEspetaculo1)
                    .addComponent(campoLucroMedioEsp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelLucroMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaEspetaculo2)
                    .addComponent(campoLucroMedioEsp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(PainelLucroMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaEspetaculo3)
                    .addComponent(campoLucroMedioEsp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        VoltarMenu.setBackground(new java.awt.Color(21, 63, 69));
        VoltarMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VoltarMenu.setForeground(new java.awt.Color(255, 255, 255));
        VoltarMenu.setText("Voltar ao Menu");
        VoltarMenu.setPreferredSize(new java.awt.Dimension(150, 40));

        botaoGerarEstatisticas.setBackground(new java.awt.Color(39, 141, 98));
        botaoGerarEstatisticas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoGerarEstatisticas.setForeground(new java.awt.Color(255, 255, 255));
        botaoGerarEstatisticas.setText("Gerar Estatísticas");
        botaoGerarEstatisticas.setPreferredSize(new java.awt.Dimension(150, 40));

        javax.swing.GroupLayout painelEstatisticasLayout = new javax.swing.GroupLayout(painelEstatisticas);
        painelEstatisticas.setLayout(painelEstatisticasLayout);
        painelEstatisticasLayout.setHorizontalGroup(
            painelEstatisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstatisticasLayout.createSequentialGroup()
                .addGroup(painelEstatisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelEstatisticasLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(VoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoGerarEstatisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEstatisticasLayout.createSequentialGroup()
                        .addContainerGap(113, Short.MAX_VALUE)
                        .addGroup(painelEstatisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(painelLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelLucroMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelRankLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelRankOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelRankIngressosVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelEstatisticasLayout.createSequentialGroup()
                                .addComponent(etiquetaTitulo)
                                .addGap(259, 259, 259)))))
                .addGap(129, 129, 129))
        );
        painelEstatisticasLayout.setVerticalGroup(
            painelEstatisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEstatisticasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addComponent(painelLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(painelRankIngressosVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(painelRankOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(painelRankLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(PainelLucroMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEstatisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoGerarEstatisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelEstatisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelEstatisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void campoMaisVendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoMaisVendidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoMaisVendidoActionPerformed

    private void campoMaisLucrativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoMaisLucrativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoMaisLucrativoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelLucroMedio;
    private javax.swing.JButton VoltarMenu;
    private javax.swing.JButton botaoGerarEstatisticas;
    private javax.swing.JFormattedTextField campoLucro;
    private javax.swing.JFormattedTextField campoLucroMedioEsp1;
    private javax.swing.JFormattedTextField campoLucroMedioEsp2;
    private javax.swing.JFormattedTextField campoLucroMedioEsp3;
    private javax.swing.JFormattedTextField campoMaiorOcupacao;
    private javax.swing.JFormattedTextField campoMaisLucrativo;
    private javax.swing.JFormattedTextField campoMaisVendido;
    private javax.swing.JFormattedTextField campoMenorOcupacao;
    private javax.swing.JFormattedTextField campoMenosLucrativo;
    private javax.swing.JFormattedTextField campoMenosVendido;
    private javax.swing.JLabel etiquetaEspetaculo1;
    private javax.swing.JLabel etiquetaEspetaculo2;
    private javax.swing.JLabel etiquetaEspetaculo3;
    private javax.swing.JLabel etiquetaLucro;
    private javax.swing.JLabel etiquetaMaiorOcupacao;
    private javax.swing.JLabel etiquetaMaisLucrativo;
    private javax.swing.JLabel etiquetaMaisVendido;
    private javax.swing.JLabel etiquetaMenorOcupacao;
    private javax.swing.JLabel etiquetaMenosLucrativo;
    private javax.swing.JLabel etiquetaMenosVendido;
    private javax.swing.JLabel etiquetaSubtitulo;
    private javax.swing.JLabel etiquetaTitulo;
    private javax.swing.JPanel painelEstatisticas;
    private javax.swing.JPanel painelLucro;
    private javax.swing.JPanel painelRankIngressosVendidos;
    private javax.swing.JPanel painelRankLucro;
    private javax.swing.JPanel painelRankOcupacao;
    // End of variables declaration//GEN-END:variables
}
