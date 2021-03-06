/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.GUI;

import static infz.d.project.Enums.Geluid.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Method
 */
public class Spel extends javax.swing.JFrame {
    private int punten = 0;
    private int levens = 3;

    /**
     * Creates new form Spel
     */
    public Spel() {
        initComponents();
        this.spelbord2.startMuziek(START_GELUID, true);
        this.spelbord2.setSpel(this);
        
        this.setTitle("Pacman");
        this.setSize(1150, 950); // Hardcoded
        this.setResizable(false);
        
        btnPauzeer.setEnabled(false);
        btnHerstarten.setEnabled(false);
        lblScore.setText("Score: " + punten);
        lblLevens.setText("Levens: " + levens);
        lblLevel.setText("Level: " + spelbord2.level);
    }
    
    public int getAantalLevens(){
        return this.levens;
    }
    
    public void setAantalLevens(int levens){
        this.levens = levens;
        this.lblLevens.setText("Levens: " + this.levens);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        spelbord2 = new infz.d.project.GUI.Spelbord();
        btnHerstarten = new javax.swing.JButton();
        lblScore = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblLevens = new javax.swing.JLabel();
        btnPauzeer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout spelbord2Layout = new javax.swing.GroupLayout(spelbord2);
        spelbord2.setLayout(spelbord2Layout);
        spelbord2Layout.setHorizontalGroup(
            spelbord2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        spelbord2Layout.setVerticalGroup(
            spelbord2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnHerstarten.setText("Herstarten");
        btnHerstarten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHerstartenActionPerformed(evt);
            }
        });

        lblScore.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblLevel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblLevens.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        btnPauzeer.setText("Pauzeer");
        btnPauzeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauzeerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnStart)
                        .addGap(27, 27, 27)
                        .addComponent(btnHerstarten)
                        .addGap(18, 18, 18)
                        .addComponent(btnPauzeer)
                        .addGap(48, 48, 48)
                        .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblLevel, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addGap(31, 31, 31)
                        .addComponent(lblLevens, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spelbord2, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevens, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHerstarten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPauzeer))
                    .addComponent(lblScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spelbord2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        System.out.println("Start");
        this.spelbord2.stopMuziek();

        this.spelbord2.start();
        
        btnStart.setEnabled(false);
        btnHerstarten.setEnabled(true);
        btnPauzeer.setEnabled(true);
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnHerstartenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHerstartenActionPerformed
        System.out.println("Herstarten");
        if(!btnStart.isEnabled()) {
           this.spelbord2.stopMuziek();
           this.spelbord2.startMuziek(BACKGROUND_GELUID, true);
           spelbord2.setLevel(0);
           spelbord2.levelIncrement(1);
           this.spelbord2.herstart();
           btnStart.setEnabled(false);
           lblLevel.setText("Level: " + spelbord2.level);
           this.setAantalLevens(3);
        }
    }//GEN-LAST:event_btnHerstartenActionPerformed

    private void btnPauzeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauzeerActionPerformed
        this.btnPauzeer.setText("Speel verder");
        spelbord2.pauzeer();

    }//GEN-LAST:event_btnPauzeerActionPerformed

    public void setTekstPauze() {
        this.btnPauzeer.setText("Pauze");
    }
    
    public void setScore(int punten) {
        this.punten += punten;
        
        lblScore.setText("Score: " + this.punten);
    }
    
    public void resetScore() {
        this.punten = 0;
        
        System.out.println("ResetScore");
        lblScore.setText("Score: " + punten);

    }
    
    public void heeftGewonnenOfVerloren(String invoer) {
        if (invoer.equals("verloren")) {
            System.out.println("GameOver");
            String[] options = new String[]{"Herstarten", "Stoppen"};
            spelbord2.pauzeer();
            int response = JOptionPane.showOptionDialog(null, "Wilt u herstarten of stoppen?", "GAME OVER",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            resetVerloren(response);
        } else if (invoer.equals("gewonnen")) {
            JOptionPane.showMessageDialog(null, "U heeft gewonnen!");
            spelbord2.pauzeer();
            spelbord2.levelIncrement(1);
            this.spelbord2.nextLevel();
            lblLevel.setText("Level: " + spelbord2.level);
        }
    }
    
    private void resetVerloren(int response) {
        if (response == 0) {
            spelbord2.setLevel(0);
            spelbord2.levelIncrement(1);
            spelbord2.herstart();
            lblLevel.setText("Level: " + spelbord2.level);
        } else {
            System.exit(1);
        }
    }
    
    public void setLevens(int levens) {
        if (this.levens == 1) {
            heeftGewonnenOfVerloren("verloren");
        } else {
            this.levens += levens;

            lblLevens.setText("Levens: " + this.levens);
        }
    }

    public void resetLevens() {
        this.levens = 3;
        
        lblLevens.setText("Levens: " + levens);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Spel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Spel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Spel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Spel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Spel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHerstarten;
    private javax.swing.JButton btnPauzeer;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblLevens;
    private javax.swing.JLabel lblScore;
    private infz.d.project.GUI.Spelbord spelbord2;
    // End of variables declaration//GEN-END:variables
}
