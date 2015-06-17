/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Interfaces;

import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;
import infz.d.project.SpelElementen.Muur;
import infz.d.project.SpelElementen.Pacman;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Method
 */
public class VluchtenBewegen implements VluchtenBewegenAlgoritme{
    private Vakje               huidigVakje;
    private int                 xPos;
    private int                 yPos;
    private boolean             geenMuur = true;
    
    @Override
    public Vakje geefCell(Vakje huidigVakje) {
        if(huidigVakje == null)
            return null;
        
        this.huidigVakje = huidigVakje;
        randomPos();
        return mainBfs(this.huidigVakje);
    }

    private void randomPos() {
        geenMuur = true;
        Vakje randomPos;
        while(geenMuur){
            Random rand = new Random();
            int tempXPos = rand.nextInt(15) + 1;
            int tempYPos = rand.nextInt(15) + 1;
            randomPos = huidigVakje.getSpelbord().getSpecifiekVakje(tempYPos, tempXPos);
            xPos = tempXPos;
            yPos = tempYPos;
            
            if(!(randomPos.getSpelElement() instanceof Muur))
                geenMuur = false;
        }
    }
    
    @Override
    public Vakje mainBfs(Vakje root) {
        ArrayList seen = new ArrayList();
        Queue<Vakje> queue = new LinkedList();

        queue.offer(root);
        root.setParent(null);
        
        while(!queue.isEmpty()){
            Vakje node = queue.poll();
            
            if(node == huidigVakje.getSpelbord().getSpecifiekVakje(yPos, xPos)) {
                
                if(retrievePad(node) == null) 
                    geefCell(huidigVakje);
                else 
                    return retrievePad(node);
            }
                
            seen.add(node);
            
            Map<Richting, Vakje> map = node.getBuurLijst();
            for(Map.Entry<Richting, Vakje> entry : map.entrySet()){
                if(!(seen.contains(entry.getValue())) && !(queue.contains(entry.getValue()))){
                    entry.getValue().setParent(node);
                    queue.offer(entry.getValue());
                }
            }
            
        }
        
        return null;
    }

    @Override
    public Vakje retrievePad(Vakje node) {
        Stack<Vakje> parents = new Stack();
        
        while(node.getParent() != null){
            parents.add(node);
            node = node.getParent();
        }
        try {
            return parents.pop();
        } catch (EmptyStackException e){
            return null;
        }
    }
}
