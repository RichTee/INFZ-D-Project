/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Method
 */
public class Vakje extends javax.swing.JPanel {

    /**
     * Creates new form Vakje
     */
    private int                     xPositie;
    private int                     yPositie;
    private String                  element;
    private ArrayList<Vakje>        buurVakje = new ArrayList<>();
    private ArrayList<SpelElement>  spelElementList = new ArrayList<>();
    private final Spelbord          spelbord;
    private SpelElement             spelElement;
    private Muur                    muur;
    private Spookje                 spookje;
    private Pacman                  pacman;
    private Bolletje                bolletje;
    private Superbolletje           superbolletje;
    public  Kers                    kers;
    
    public Vakje(int xPositie, int yPositie, String element, Spelbord spelBord) {
        initComponents();
        
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.element = element;
        this.spelbord = spelBord;
        
        setPreferredSize(new Dimension(50, 50));

        vulSpelElementList();
    }

       
    // Positie van vakje(X)
    public int getXPositie() {
        return xPositie;
    }

    // Positie van vakje(Y)
    public int getYPositie() {
        return yPositie;
    }

    // Return SpelElement (muur of poppetje..)
    public String getElement() {
        return element;
    }

    public void setElement(String element, SpelElement spelElement) {
        if (spelElement == null) {
            this.element = element;
        } else {
            switch(element){
                case "pacman":
                    this.element = element;
                    this.pacman = (Pacman) spelElement;
                    spelElementList.add(spelElement);
                    break;
                case "spookje":
                case "bolletje":
                case "superbolletje":
                case "kers":
                    this.element = element;
                    spelElementList.add(spelElement);
                    break;
            }
            // Andere SpelElementen later.
        }
    }

    public SpelElement getSpelElement(String spelElement){
        for(SpelElement element : spelElementList){
            if(element.toString().equals(spelElement)){
                return element;
            }
        }
        
        return null;
    }
    
    public ArrayList getSpelElementList(){
        return spelElementList;
    }

    public void voegtBuurToe(Vakje vakje) {
        buurVakje.add(vakje);
    }
    
    public void voegSpelElementToe(SpelElement spelElement){
        spelElementList.add(spelElement);
    }
    
    public void verwijderSpelElement(SpelElement spelElement){
        for(SpelElement element : spelElementList){
            if(element instanceof Bolletje)
                spelElementList.remove(element);
            else if (element instanceof Superbolletje) 
                spelElementList.remove(element);
            else if (element instanceof Kers) 
                spelElementList.remove(element);
            else if (element instanceof Spookje) 
                spelElementList.remove(element);
            else if (element instanceof Pacman) 
                spelElementList.remove(element);
        }
    }

    private int puntenOptellenVanBuurVakje(Vakje vakje) {
        ArrayList<SpelElement> vakjeSpelElementList = vakje.getSpelElementList();
        int punten = 0;

        for (SpelElement element : vakjeSpelElementList) {
            punten = punten + element.getPunten();
        }
        
        return punten;
    }
    
    public void pacmanRichting(String richting) {
        /*
        * NOORD = 0
        * OOST  = 1
        * ZUID  = 2
        * WEST  = 3
        */
        switch(richting) {
            case "NOORD":
                checkPacmanCollisie(buurVakje.get(0));
                break;
            case "OOST":
                checkPacmanCollisie(buurVakje.get(1));
                break;
            case "ZUID":
                checkPacmanCollisie(buurVakje.get(2));
                break;
            case "WEST":
                checkPacmanCollisie(buurVakje.get(3));
                break;
        }
    }
    
    public void spookjeRichting(String richting) {
        /*
        * NOORD = 0
        * OOST  = 1
        * ZUID  = 2
        * WEST  = 3
        */
        switch(richting) {
            case "NOORD":
                checkSpookjeCollisie(buurVakje.get(0));
                break;
            case "OOST":
                checkSpookjeCollisie(buurVakje.get(1));
                break;
            case "ZUID":
                checkSpookjeCollisie(buurVakje.get(2));
                break;
            case "WEST":
                checkSpookjeCollisie(buurVakje.get(3));
                break;
        }
    }

    private void checkPacmanCollisie(Vakje vakje){
        int punten = 0;

        // ToDo: Prioriteit: Spookje > Bolletje > Superbolletje > Kers
        switch(vakje.getElement()){
            case "muur":
                break;
            case "pad":
                vakje.setElement(this.getElement(), null);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "bolletje":
                punten = puntenOptellenVanBuurVakje(vakje);
                spelbord.maakKers();
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "bolletje");
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "superbolletje":
                punten = puntenOptellenVanBuurVakje(vakje);
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.geefPacmanSuperkracht(pacman);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "superbolletje");
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "kers":
                punten = puntenOptellenVanBuurVakje(vakje);
                vakje.setElement(this.getElement(), null);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "kers");
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            case "spookje":
                if (pacman.getKracht()) {
                    punten = puntenOptellenVanBuurVakje(vakje);
                    vakje.setElement(this.getElement(), pacman);
                    this.setElement("pad", null);
                    pacman.setVakje(vakje);
                    spelbord.tekenOpnieuw();
                    spelbord.setSpelInformatie(punten, 0, "");

                } else if (!pacman.getKracht()) {
                    System.out.println("VERLOREN");
                    spelbord.setSpelInformatie(0, -1, "");
                    spelbord.resetPoppetje();
                    spelbord.tekenOpnieuw();

                }
                //spelbord.tekenOpnieuw(this.getXPositie(), this.getYPositie());
                //tekenOpnieuw();
                //vakje.tekenOpnieuw();
                break;
            default:
                break;
        }
    }

    private void checkSpookjeCollisie(Vakje vakje){ }
    
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        switch(element){
            case "muur":
                muur = new Muur(this);
                spelElementList.add(muur);
                break;
            case "bolletje":
                bolletje = new Bolletje(this);
                spelElementList.add(bolletje);
                break;
            case "superbolletje":
                superbolletje = new Superbolletje(this);
                spelElementList.add(superbolletje);
                break;
            case "spookje":
                spookje = new Spookje(this);
                spelElementList.add(spookje);
                break;
            case "pacman":
                pacman = new Pacman(this);
                spelElementList.add(pacman);
                break;
            case "kers":
                kers = new Kers(this);
                spelElementList.add(kers);
                break;
            default:
                // Pad, niks toevoegen. Indien vakje leeg is, dan moet die niks bevatten
                break;
        }
    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    public void draw(Graphics g) {
        switch (element) {
            case "pad":
                g.setColor(Color.decode("#000000"));
                g.fillRect(yPositie * 50/*yPositie*/, xPositie * 50/*xPositie*/, 50, 50);
                break;
            case "muur":
                if (spelElementList.contains(muur)) {
                    muur.draw(g);
                }
                break;
            case "bolletje":
                if (spelElementList.contains(bolletje)){
                    bolletje.draw(g);
                }
                break;
            case "superbolletje":
                if (spelElementList.contains(superbolletje)){
                    superbolletje.draw(g);
                }
                break;
            case "spookje":
                if (spelElementList.contains(spookje)) {
                    spookje.draw(g);
                }
                break;
            case "pacman":
                if (spelElementList.contains(pacman)) {
                    pacman.draw(g);
                }
                break;
            case "kers":
                if (spelElementList.contains(kers)){
                    kers.draw(g);
                }
            default:
                break;
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
