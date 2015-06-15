/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.SpelElementen;

import infz.d.project.Enums.Afbeelding;
import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;
import java.awt.Graphics;
import java.io.File;

import infz.d.project.Interfaces.AchtervolgenBewegenAlgoritme;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Method
 */
public class AchtervolgendSpookje extends Spookje implements AchtervolgenBewegenAlgoritme{
    
    public AchtervolgendSpookje(Vakje vakje, String naam) {
        this.vakje = vakje;
        this.startPositie = vakje;
        this.naam = naam;
        this.punten = 200;
        this.elementNaam = "spookje";

        if (naam.equals("pinky")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.PINKY);
        } else if (naam.equals("clyde")) {
            this.image = this.vakje.getImageLoader().selectSpookjeAfbeelding(Afbeelding.Spookje.CLYDE);
        }
    }
    
    @Override
    public void bewegen() {
        switch(randomRichting()){
            case 1:
                vakje.spookjeRichting(Richting.NOORD, this);
                break;
            case 2:
                vakje.spookjeRichting(Richting.OOST, this);
                break;
            case 3:
                vakje.spookjeRichting(Richting.ZUID, this);
                break;
            case 4:
                vakje.spookjeRichting(Richting.WEST, this);
                break;
            default:
                System.out.println("Tempnummer is wrong");
                break;
        }
    }
    
    @Override
    public void draw(Graphics g) {
        this.row = vakje.getXPositie();
        this.column = vakje.getYPositie();

        g.drawImage(this.image, column * CELL, row * CELL, 35, 35, null);
    }

    @Override
    public boolean bfs() {
        int r = 0, c = 0;
        int startXPos = this.vakje.getXPositie(), startYPos = this.vakje.getYPositie();
        int finXPos = 0, finYPos = 0;
        int[] dx = {1, -1, 0, 0};//right, left, NA, NA
        int[] dy = {0, 0, 1, -1};//NA, NA, bottom, top
        char[][] grid = new char[r][c];//Main grid
        if (startXPos == finXPos && startYPos == finYPos) {
            return true;//He's already there
        } else {
            grid[finXPos][finYPos] = 'G';//finish
            Queue<int[]> q = new LinkedList<int[]>();
            int[] start = {startXPos, startYPos};// start coordinaten
            q.add(start);//Adding start to the queue since we're already visiting it
            grid[startXPos][startYPos] = 'B';
            while (q.peek() != null) {
                int[] curr = q.poll();//poll or remove. Same thing
                for (int i = 0; i < 4; i++)//for each direction
                {
                    if ((curr[0] + dx[i] >= 0 && curr[0] + dx[i] < r) && (curr[1] + dy[i] >= 0 && curr[1] + dy[i] < c)) {
                        //Checked if x and y are correct. ALL IN 1 GO
                        int xc = curr[0] + dx[i];//Setting current x coordinate
                        int yc = curr[1] + dy[i];//Setting current y coordinate
                        if (grid[xc][yc] == 'G')//Destination found
                        {
                            //System.out.println(xc+" "+yc);
                            return true;
                        } else if (grid[xc][yc] == 'E')//Movable. Can't return here again so setting it to 'B' now
                        {
                            //System.out.println(xc+" "+yc);
                            grid[xc][yc] = 'B';//now BLOCKED
                            int[] temp = {xc, yc};
                            q.add(temp);//Adding current coordinates to the queue
                        }
                    }
                }
            }
            return false;//Will return false if no route possible
        }
    }
}
