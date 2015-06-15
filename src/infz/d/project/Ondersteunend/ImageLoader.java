/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.Ondersteunend;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author Method
 */
public class ImageLoader {
    //          Key,    Value
    private Map<String, Image>          mapStatischAfbeelding = new HashMap<>();
    private Map<String, Image>          mapBewegendeAfbeelding = new HashMap<>();;
    private File                        afbeelding;
    
    public ImageLoader(){ 
        laadStatischeSpelElementAfbeelding();
        laadBewegendeSpelElementAfbeelding();
    }
    
    private void laadStatischeSpelElementAfbeelding() {
        // Alle statische spelElementen
        mapStatischAfbeelding.put("muur", converteerFileNaarImage(afbeelding = new File("src/afbeelding/muur.png")));
        mapStatischAfbeelding.put("bolletje", converteerFileNaarImage(afbeelding = new File("src/afbeelding/bolletje.png")));
        mapStatischAfbeelding.put("superbolletje", converteerFileNaarImage(afbeelding = new File("src/afbeelding/superbolletje.png")));
        mapStatischAfbeelding.put("kers", converteerFileNaarImage(afbeelding = new File("src/afbeelding/kers.png")));
    }
    
    private void laadBewegendeSpelElementAfbeelding() {
        //Pacman
        mapBewegendeAfbeelding.put("pacmanNoord", converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanNoord.png")));
        mapBewegendeAfbeelding.put("pacmanOost", converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanOost.png")));
        mapBewegendeAfbeelding.put("pacmanZuid", converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanZuid.png")));
        mapBewegendeAfbeelding.put("pacmanWest", converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanWest.png")));
        
        //Spookjes - Willekeurig
        mapBewegendeAfbeelding.put("inky", converteerFileNaarImage(afbeelding = new File("src/afbeelding/inky.png")));
        mapBewegendeAfbeelding.put("blinky", converteerFileNaarImage(afbeelding = new File("src/afbeelding/blinky.png")));
        
        // Spookjes - Achtervolgend
        mapBewegendeAfbeelding.put("pinky", converteerFileNaarImage(afbeelding = new File("src/afbeelding/pinky.png")));
        mapBewegendeAfbeelding.put("clyde", converteerFileNaarImage(afbeelding = new File("src/afbeelding/clyde.png")));
    }
    
    public Image converteerFileNaarImage(File afbeelding) {
        try{
            return ImageIO.read(afbeelding);
        } catch (IOException e){
            System.out.println("ImageLoader - converteerFileNaarImage: afbeelding pad niet gevonden, return null");
            return null;
        }
    }
    
    public Image selectStatischeSpelElementAfbeelding(String afbeeldingNaam) {
        switch(afbeeldingNaam){
            case "muur":
                return mapStatischAfbeelding.get("muur"); // Kan null zijn
            case "bolletje":
                return mapStatischAfbeelding.get("bolletje"); // Kan null zijn
            case "superbolletje":
                return mapStatischAfbeelding.get("superbolletje"); // Kan null zijn
            case "kers":
                return mapStatischAfbeelding.get("kers"); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectStatischeSpelElementAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }
    
    public Image selectBewegendeSpelElementAfbeelding(String afbeeldingNaam) {
        switch(afbeeldingNaam){
            case "pacmanNoord":
                return mapBewegendeAfbeelding.get("pacmanNoord"); // Kan null zijn
            case "pacmanOost":
                return mapBewegendeAfbeelding.get("pacmanOost"); // Kan null zijn
            case "pacmanZuid":
                return mapBewegendeAfbeelding.get("pacmanZuid"); // Kan null zijn
            case "pacmanWest":
                return mapBewegendeAfbeelding.get("pacmanWest"); // Kan null zijn
            case "inky":
                return mapBewegendeAfbeelding.get("inky"); // Kan null zijn
            case "blinky":
                return mapBewegendeAfbeelding.get("blinky"); // Kan null zijn
            case "pinky":
                return mapBewegendeAfbeelding.get("pinky"); // Kan null zijn
            case "clyde":
                return mapBewegendeAfbeelding.get("clyde"); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectBewegendeSpelElementAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }
}
