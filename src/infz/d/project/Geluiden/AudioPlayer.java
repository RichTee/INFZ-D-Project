/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Geluiden;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

 /*
 * @author Sebastiaan
 */
public class AudioPlayer {

    Clip clip;
    
    public void playMusic(String path, boolean loop) {
        AudioInputStream inputStream;
        try {
            clip = null;
            File file = new File(path);
            clip = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(file);

            clip.open(inputStream);
            if (loop) {
                clip.loop(-1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void stopMusic() {
        clip.stop();      
    }
}
