/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.gui;

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
    private String                  element   = "pad";
    private ArrayList<Vakje>        buurVakje = new ArrayList<>();
    private ArrayList<SpelElement>  spelElementList = new ArrayList<>();
    private final Spelbord          spelbord;
    private SpelElement             spelElement;
    private Muur                    muur;
    public WillekeurigSpookje       willekeurigSpookje;
    private AchtervolgendSpookje    achtervolgendSpookje;
    private WillekeurigSpookje      inky;
    private WillekeurigSpookje      blinky;
    private AchtervolgendSpookje    pinky;
    private AchtervolgendSpookje    clyde;
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
                    break;
                case "inky":
                    this.inky = (WillekeurigSpookje) spelElement;
                    break;
                case "blinky":
                    this.blinky = (WillekeurigSpookje) spelElement;
                    break;
                case "pinky":
                    this.pinky = (AchtervolgendSpookje) spelElement;
                    break;
                case "clyde":
                    this.clyde = (AchtervolgendSpookje) spelElement;
                    break;
                case "bolletje":
                case "superbolletje":
                    break;
                case "kers":
                    this.element = element;
                    this.spelElement = spelElement;
                    System.out.println("Element: " + element);
                    System.out.println("spelElement: " + spelElement);
                    break;
            }
            // Andere SpelElementen later.
        }
    }
    
    public Pacman getPacman() {
        return pacman;
    }
    
    public Kers getKers(){
        return (Kers) this.spelElement;
    }

    public SpelElement getSpelElement() {
        return spelElement;
    }
    
    public Spookje getSpookje(String spookje) {
        switch(spookje){
            case "inky":
                return inky;
            case "blinky":
                return blinky;
            case "pinky":
                return pinky;
            case "clyde":
                return clyde;
            default:
                return null;
        }
    }
    
    public void setSpookje(Spookje spookje) {
        switch(spookje.naam /*getNaam*/) {
            case "inky":
                this.inky = (WillekeurigSpookje) spookje;
                break;
            case "blinky":
                this.blinky = (WillekeurigSpookje) spookje;
                break;
            case "pinky":
                this.pinky = (AchtervolgendSpookje) spookje;
                break;
            case "clyde":
                this.clyde = (AchtervolgendSpookje) spookje;
                break;
            default:
                break;
        }
    }
    
    public void voegtBuurToe(Vakje vakje) {
        buurVakje.add(vakje);
    }

    private int puntenOptellenVanBuurVakje(Vakje vakje) {
        int punten = 0;
        
        Spookje inky = vakje.getSpookje("inky");
        Spookje blinky = vakje.getSpookje("blinky");
        Spookje pinky = vakje.getSpookje("pinky");
        Spookje clyde = vakje.getSpookje("clyde");
        
        if(inky != null)
            punten = punten + inky.getPunten();
        if(blinky != null)
            punten = punten + blinky.getPunten();
        if(pinky != null)
            punten = punten + pinky.getPunten();
        if(clyde != null)
            punten = punten + clyde.getPunten();
        
        switch(vakje.getElement()){
            case "bolletje":
            case "superbolletje":
            case "kers":
                if(vakje.getSpelElement() != null) {
                    punten = punten + vakje.getSpelElement().getPunten();
                }
                break;
            default:
                break;
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
    
    public void spookjeRichting(String richting, Spookje spookje) {
        /*
        * NOORD = 0
        * OOST  = 1
        * ZUID  = 2
        * WEST  = 3
        */
        switch(richting) {
            case "NOORD":
                checkSpookjeCollisie(buurVakje.get(0), spookje);
                break;
            case "OOST":
                checkSpookjeCollisie(buurVakje.get(1), spookje);
                break;
            case "ZUID":
                checkSpookjeCollisie(buurVakje.get(2), spookje);
                break;
            case "WEST":
                checkSpookjeCollisie(buurVakje.get(3), spookje);
                break;
        }
    }

    private void checkPacmanCollisie(Vakje vakje){
        int punten = 0;
        boolean verlorenSwitchConditie = false;

        // ToDo: Prioriteit: Spookje > Bolletje > Superbolletje > Kers
        if (vakje.getSpookje("inky") != null || vakje.getSpookje("blinky") != null
                || vakje.getSpookje("pinky") != null || vakje.getSpookje("clyde") != null) {
            
            if (pacman.getKracht() && !vakje.getElement().equals("pad")) {
                punten = puntenOptellenVanBuurVakje(vakje);
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "");

            } else if (pacman.getKracht()){
                punten = puntenOptellenVanBuurVakje(vakje);
                vakje.setElement(this.getElement(), pacman);
                this.setElement("pad", null);
                pacman.setVakje(vakje);
                spelbord.tekenOpnieuw();
                spelbord.setSpelInformatie(punten, 0, "");
            } else if (!pacman.getKracht()) {
                System.out.println("VERLOREN");
                verlorenSwitchConditie = true;
                spelbord.setSpelInformatie(0, -1, "");
                spelbord.resetPoppetje();
                spelbord.tekenOpnieuw();
            }
        }
        if(!verlorenSwitchConditie){
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
                    pacman.setVakje(vakje); // ToDo: Spookje error
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
                default:
                    break;
            }
        }
    }

    private void checkSpookjeCollisie(Vakje vakje, Spookje spookje){ 
        switch(vakje.getElement()){
            case "muur":
                break;
            case "pad":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                spelbord.tekenOpnieuw();
                break;
            case "bolletje":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                spelbord.tekenOpnieuw();
                break;
            case "superbolletje":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                spelbord.tekenOpnieuw();
                break;
            case "kers":
                spookje.setVakje(vakje);
                vakje.setSpookje(spookje);
                if(spookje.naam.equals("inky"))
                    this.inky = null;
                spelbord.tekenOpnieuw();
                break;
            case "pacman":
                if (pacman.getKracht()) {
                    spookje.setVakje(vakje);
                    vakje.setSpookje(spookje);
                    if(spookje.naam.equals("inky"))
                        this.inky = null;
                    spelbord.tekenOpnieuw();
                    
                } else if (!pacman.getKracht()) {
                    System.out.println("VERLOREN");
                    spelbord.setSpelInformatie(0, -1, "");
                    spelbord.resetPoppetje();
                    spelbord.tekenOpnieuw();
                    
                }
                break;
            default:
                break;
        }
    }
    
    // Logischer in Spelbord voor minder Memory intake en makkelijkere toegang.
    private void vulSpelElementList() {
        switch(element){
            case "muur":
                muur = new Muur(this);
                spelElement = muur;
                break;
            case "bolletje":
                spelElement = new Bolletje(this);
                break;
            case "superbolletje":
                spelElement = new Superbolletje(this);
                break;
            case "inky":
                inky = new WillekeurigSpookje(this, "inky");
                break;
            case "blinky":
                blinky = new WillekeurigSpookje(this, "blinky");
                break;
            case "pinky":
                pinky = new AchtervolgendSpookje(this, "pinky");
                break;
            case "clyde":
                clyde = new AchtervolgendSpookje(this, "clyde");
                break;
            case "pacman":
                pacman = new Pacman(this);
                break;
            case "kers":
                spelElement = new Kers(this);
                break;
            default:
                // Pad, niks toevoegen. Indien vakje leeg is, dan moet die niks bevatten
                break;
        }
        kers = new Kers(this);
       // sortSpelElementList();
    }

    // Draw Logic | Vakje moet zich kleuren op basis van inhoud.
    // Met als prioriteit muur > pacman > spookje > bolletje > superbolletje > kers
    public void draw(Graphics g) {
        switch(element){
            case "muur":
                muur.draw(g);
                break;
            case "bolletje":
            case "superbolletje":
                spelElement.draw(g);
                break;
            case "kers": // todo: kers
                kers.draw(g);
                break;
            default:
                break;
        }

        if (pacman != null) {
            pacman.draw(g);
        } else if (inky != null) {
            inky.draw(g);
        } else if (blinky != null) {
            blinky.draw(g);
        } else if (pinky != null) {
            pinky.draw(g);
        } else if (clyde != null) {
            clyde.draw(g);
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
