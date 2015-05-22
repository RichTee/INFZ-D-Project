/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Method
 */
public class Spelbord extends javax.swing.JPanel {
    private int xPos, yPos; // Positie
    private int cellBreedte, cellHoogte; // Cell dimensies
    private final static int CELL = 50;
    private Vakje[][] vakje;
    private ArrayList<String> vakjesInhoud = new ArrayList<String>();
    private Vakje[][] vakjeBuur = new Vakje[5][5];
    private Border lineBorder = BorderFactory.createLineBorder(Color.black);
    
    /**
     * Creates new form Spelbord
     */
    public Spelbord() {
        initComponents();
        genereerSpelbordPanelGegevens();
    }
    
    // Panel gegevens
    private void genereerSpelbordPanelGegevens() {
        getSpelbordTxtFile();
        genereerGuiMap();
        //genereerVakjes();
        setPreferredSize(new Dimension(CELL * cellBreedte, CELL * cellHoogte));
        // this.setBackground(Color.BLACK);
        this.setBorder(lineBorder);
    }
    
    // Snelle start voor Spel om de veld te activeren met het spel.
    public void start() {
        genereerSpelbordPanelGegevens();
    }
    
    // Get posities van entiteiten die een spel vormen uit de txt file.
    private void getSpelbordTxtFile() {
        try {
            Scanner read = new Scanner(new File("txt/maze.txt"));
            int lineCount = 0;
            while (read.hasNextLine()) {
                String line = read.nextLine();
                vakjesInhoud.add(line);
                System.out.println(line);
                if (line.contains("5")) {
                    yPos = lineCount;
                    xPos = line.indexOf('5');
                }
                lineCount++;
            }
            read.close();

            cellHoogte = vakjesInhoud.size();
            cellBreedte = vakjesInhoud.get(0).length();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Genereer de map voor de GUI nadat de TXT is gelezen
    private void genereerGuiMap() {
        vakje = new Vakje[cellHoogte][cellBreedte];

        for (int row = 0; row < cellHoogte; row++) {
            for (int column = 0; column < cellBreedte; column++) {
                switch (String.valueOf(charAt(row, column))) {
                    case "0":
                        vakje[row][column] = new Vakje(row, column, "pad");
                        System.out.println("Pad");
                        break;
                    case "1":
                        // muur
                        vakje[row][column] = new Vakje(row, column, "muur");
                        System.out.println("Muur");
                        break;
                    case "2":
                        // Bolletje
                        break;
                    case "3":
                        // Superbolletje
                        break;
                    case "4":
                        // Spookje
                        break;
                    case "5":
                        // Pacman
                        vakje[row][column] = new Vakje(row, column, "pacman");
                        System.out.println("Pacman");
                        break;
                    default:
                        break;
                }
            }
        }
    }
    /*
    private void genereerVakjes() {
        Vakje vakje;
        int muurAantal = 0;
        
        for(int row = 0; row < cellHoogte; row++){
            for (int column = 0; column < cellBreedte; column++){
                String output = String.valueOf(charAt(row, column));
                System.out.println("Output: " + output);
                if(output.equals("1"))
                    muurAantal++;
            }
        }
        System.out.println("Totaal muur: " + muurAantal);
        
        for(int i = 0; i < 25; i++){
            vakje = new Vakje(rows, columns, (output == 1 ? true : false));
            System.out.println("Aantal row:" + rows + ""
                    + "\nAantal Column: " + columns + "Totaal muur: " + muurAantal);
        }
    }*/
    
    // Override paintComponent om spelElementen te laten zien in de map.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLACK);
        g.fillRect(0, 0, cellBreedte * CELL, cellHoogte * CELL);
    }
    
    // Makkelijk info halen uit een rij en kolom van vakjesInhoud.
    private char charAt(int row, int column) {
        return vakjesInhoud.get(row).charAt(column);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
