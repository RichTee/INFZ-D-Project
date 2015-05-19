/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.MenuListener;

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
        this.setSize(610, 460); // Hardcoded, beter te veranderen in dynamische variable met JPanel/Menubar in mind.
        getMenu();
        //getPanel();
        KeyListener listener = new MenuListener();
        this.addKeyListener(listener);
        
        Doolhof doolhof = new Doolhof();
        add(doolhof.board);
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
                System.out.println("start");
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
    // Test Methode(legacy)
    private void getPanel() {
        /*JPanel board = new JPanel();
        board.setBackground(Color.BLACK);
        board.setSize(600, 400);
        add(board);
        */
    }
    
    // Aparte klasse maken ipv nested.
    public class Doolhof extends JPanel {
            JPanel board = new JPanel();
        public Doolhof() {
            speelveld();
        }
        
        // Test Methode
        private void speelveld() {
            String[][] celWaarden = new String[5][5];
            final String muur = "m";
            final String veld = "v";

            for(int i=0; i < 5; i++){
                for(int j=0; j < 5; j++){
                    if(i == 0 || j == 0){
                        celWaarden[i][j] = muur;
                    } else if (i == celWaarden.length - 1 || j == celWaarden[0].length - 1) {
                        celWaarden[i][j] = muur;
                    } else {
                        System.out.println("Row:" + celWaarden.length + "\nColumn:" + celWaarden[0].length);
                        celWaarden[i][j] = veld;
                    }
                }
            }
            // Laatste edit voor celWaardes
            celWaarden[2][2] = muur;
            celWaarden[0][2] = veld;
            celWaarden[4][2] = veld;
            board.setLayout(new GridLayout(5, 5));
            board.setSize(600, 400);

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(celWaarden[i][j].toString().equals(muur)){
                        board.add(blackCell());
                    } else {
                        board.add(whiteCell());
                    }
                }
            }
        }
        
        // Andere benaming
        private void generate(){
            board.setLayout(new GridLayout(5, 5));
            board.setSize(600, 400);
            boolean flag = false;
            for(int i = 0; i < (5 * 5); i++){
                if(flag){
                    board.add(whiteCell());
                    flag = false;
                }else{
                    board.add(blackCell());
                    flag = true;
                }
                    
            }
        }
        
        // Moet 2d Array(?), tijdelijk voor weergave
        private JPanel whiteCell(){
            JPanel whiteCell = new JPanel();
            whiteCell.setBackground(Color.WHITE);
            
            return whiteCell;
        }
        
        private JPanel blackCell(){
            JPanel blackCell = new JPanel();
            blackCell.setBackground(Color.BLACK);
            
            return blackCell;
        }
        public void paint(Graphics g){

        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
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
                    herstarten.setEnabled(true);
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

            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
