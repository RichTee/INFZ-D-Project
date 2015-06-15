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

import infz.d.project.Enums.Afbeelding;

/**
 *
 * @author Method
 */
public class ImageLoader {
    //          Key,    Value
    private Map<Afbeelding.Statisch, Image> mapStatischAfbeelding = new HashMap<>();
    private Map<Afbeelding.Pacman, Image>   mapPacmanAfbeelding = new HashMap<>();
    private Map<Afbeelding.Spookje, Image>  mapSpookjeAfbeelding = new HashMap<>();;
    private File                            afbeelding;
    
    public ImageLoader(){ 
        laadStatischeSpelElementAfbeelding();
        laadBewegendeSpelElementAfbeelding();
    }
    
    private void laadStatischeSpelElementAfbeelding() {
        // Alle statische spelElementen
        mapStatischAfbeelding.put(Afbeelding.Statisch.MUUR, converteerFileNaarImage(afbeelding = new File("src/afbeelding/muur.png")));
        mapStatischAfbeelding.put(Afbeelding.Statisch.BOLLETJE, converteerFileNaarImage(afbeelding = new File("src/afbeelding/bolletje.png")));
        mapStatischAfbeelding.put(Afbeelding.Statisch.SUPERBOLLETJE, converteerFileNaarImage(afbeelding = new File("src/afbeelding/superbolletje.png")));
        mapStatischAfbeelding.put(Afbeelding.Statisch.KERS, converteerFileNaarImage(afbeelding = new File("src/afbeelding/kers.png")));
    }
    
    private void laadBewegendeSpelElementAfbeelding() {
        //Pacman
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANNOORD, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanNoord.png")));
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANOOST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanOost.png")));
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANZUID, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanZuid.png")));
        mapPacmanAfbeelding.put(Afbeelding.Pacman.PACMANWEST, converteerFileNaarImage(afbeelding = new File("src/afbeelding/PacmanWest.png")));
        
        //Spookjes - Willekeurig
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.INKY, converteerFileNaarImage(afbeelding = new File("src/afbeelding/inky.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.BLINKY, converteerFileNaarImage(afbeelding = new File("src/afbeelding/blinky.png")));
        
        // Spookjes - Achtervolgend
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.PINKY, converteerFileNaarImage(afbeelding = new File("src/afbeelding/pinky.png")));
        mapSpookjeAfbeelding.put(Afbeelding.Spookje.CLYDE, converteerFileNaarImage(afbeelding = new File("src/afbeelding/clyde.png")));
    }
    
    public Image converteerFileNaarImage(File afbeelding) {
        try{
            return ImageIO.read(afbeelding);
        } catch (IOException e){
            System.out.println("ImageLoader - converteerFileNaarImage: afbeelding pad niet gevonden, return null");
            return null;
        }
    }
    
    public Image selectStatischeSpelElementAfbeelding(Afbeelding.Statisch afbeeldingNaam) {
        switch(afbeeldingNaam){
            case MUUR:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.MUUR); // Kan null zijn
            case BOLLETJE:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.BOLLETJE); // Kan null zijn
            case SUPERBOLLETJE:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.SUPERBOLLETJE); // Kan null zijn
            case KERS:
                return mapStatischAfbeelding.get(Afbeelding.Statisch.KERS); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectStatischeSpelElementAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }
    
    public Image selectPacmanAfbeelding(Afbeelding.Pacman afbeeldingNaam) {
        switch(afbeeldingNaam){
            case PACMANNOORD:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANNOORD); // Kan null zijn
            case PACMANOOST:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANOOST); // Kan null zijn
            case PACMANZUID:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANZUID); // Kan null zijn
            case PACMANWEST:
                return mapPacmanAfbeelding.get(Afbeelding.Pacman.PACMANWEST); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectPacmanAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }
    
    public Image selectSpookjeAfbeelding(Afbeelding.Spookje afbeeldingNaam) {
        switch(afbeeldingNaam){
            case INKY:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.INKY); // Kan null zijn
            case BLINKY:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.BLINKY); // Kan null zijn
            case PINKY:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.PINKY); // Kan null zijn
            case CLYDE:
                return mapSpookjeAfbeelding.get(Afbeelding.Spookje.CLYDE); // Kan null zijn
            default:
                System.out.println("ImageLoader - selectSpookjeAfbeelding: Geen afbeelding voor: " + afbeeldingNaam);
                return null;
        }
    }
}
