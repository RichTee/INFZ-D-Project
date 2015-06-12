/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Ondersteunend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sebastiaan
 */
public class LevelLoader {
    private ArrayList<String> vakjesInhoud = new ArrayList<String>();

    public LevelLoader() {
    }

//public void setLevel(int level)
//    {
//        this.level = level;
//    }
    public ArrayList<String> laadLevel(int level) {
        Scanner read;
        try {
            read = new Scanner(new File("src/Level/level" + level + ".txt"));

            while (read.hasNextLine()) {
                String line = read.nextLine();
                vakjesInhoud.add(line);
            }
            read.close();

        } catch (FileNotFoundException ex) {

            try {
                read = new Scanner(new File("src/Level/level1.txt"));
                while (read.hasNextLine()) {
                    String line = read.nextLine();
                    vakjesInhoud.add(line);
                }
                read.close();

            } catch (FileNotFoundException ex1) {
            }
        }
        return vakjesInhoud;
    }
}
