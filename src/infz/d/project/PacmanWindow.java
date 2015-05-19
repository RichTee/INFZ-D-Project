/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Method
 */
public class PacmanWindow extends javax.swing.JFrame {
    JMenuItem start, pauze, herstarten, stop;
    boolean isBezig = false;
    
    /**
     * Creates new form Main
     */
    public PacmanWindow() {
        initComponents();
        this.setTitle("Pacman");
        this.setSize(500, 400); // Hardcoded, beter te veranderen in dynamische variable met JPanel/Menubar in mind.
        getMenu();
        //getPanel();
        KeyListener listener = new MenuListener();
        this.addKeyListener(listener);
        
        setDefaultCloseOperation(PacmanWindow.EXIT_ON_CLOSE);
    }
    
    private void getMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu spelMenu = new JMenu("Spel...");

        JMenu configuratieMenu = new JMenu("Configuratie...");

        start = new JMenuItem("Start");

        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("start"); // vervangen door methode
                doolhof1.start();
                isBezig = true;
                herstarten.setEnabled(true);

            }
        });
        pauze = new JMenuItem("Pauze");
        pauze.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pauze");
                String naam = pauze.getText();
                if (naam.equals("Pauze")) {
                    isBezig = false;
                    pauze.setText("Onpauzeren");
                } else {
                    isBezig = true;
                    pauze.setText("Pauze");
                }

            }
        });
        herstarten = new JMenuItem("Herstarten");
        herstarten.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("herstart");
                
                doolhof1.reset();
                herstarten.setEnabled(false);
                if (pauze.getText().equals("Onpauzeren")) {
                    isBezig = true;
                    pauze.setText("Pauze");
                }

            }
        });
        stop = new JMenuItem("Stoppen");
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stop");
                herstarten.setEnabled(false);
                isBezig = false;
                if (pauze.getText().equals("Onpauzeren")) {
                    pauze.setText("Pauze");
                }

            }
        });
        setJMenuBar(menuBar);
        spelMenu.add(start);
        spelMenu.add(pauze);
        spelMenu.add(herstarten);
        spelMenu.add(stop);
        menuBar.add(spelMenu);
        menuBar.add(configuratieMenu);
        herstarten.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doolhof1 = new infz.d.project.Doolhof();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(doolhof1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(doolhof1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PacmanWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PacmanWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PacmanWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PacmanWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PacmanWindow().setVisible(true);
            }
        });
    }
    
    // Begin KeyListener voor het menu.
    class MenuListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent ke) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_N:
                    System.out.println("Start");
                    doolhof1.start();
                    herstarten.setEnabled(true);
                    start.setEnabled(false);
                    isBezig = true;
                    break;
                case KeyEvent.VK_P:
                    System.out.println("Pauze");
                    if (isBezig) {
                        pauze.setText("Onpauzeren");
                        isBezig = false;

                    } else {
                        pauze.setText("Pauze");
                        isBezig = true;
                    }
                    break;
                case KeyEvent.VK_H:
                    if (isBezig) {
                        // Spel creeërt de eerste level. Speler begint weer bij level 1.
                        // Pop up prompt om level te starten indien er op herstart is gedrukt?
                        // Pop up prompt voordat het spel herstart indien er op herstart is gedrukt?
                        System.out.println("Herstarten");
                        herstarten.setEnabled(false);
                        doolhof1.reset();
                        isBezig = false;
                    } else {
                        System.out.println("Niet herstarten"); // Onnodige statement, maar ter test.
                    }
                default:
                    break;
            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private infz.d.project.Doolhof doolhof1;
    // End of variables declaration//GEN-END:variables
}
