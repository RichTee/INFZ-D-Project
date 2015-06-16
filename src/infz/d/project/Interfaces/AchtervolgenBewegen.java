/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Interfaces;

import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;
import infz.d.project.SpelElementen.Pacman;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Method
 */
public class AchtervolgenBewegen implements AchtervolgenBewegenAlgoritme{
    private Vakje pacmanRichting;
    private Vakje huidigVakje;
    
    @Override
    public Vakje geefCell(Vakje huidigVakje) {
        if(huidigVakje == null)
            return null;
        
        this.huidigVakje = huidigVakje;
        
        pacmanRichting = interceptPacman(huidigVakje);
        return mainBfs(huidigVakje);
    }

    @Override
    public Vakje interceptPacman(Vakje root) {
        Queue<Vakje> queue = new LinkedList();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            Vakje node = queue.poll();
            
            if(node.getPacman() instanceof Pacman)
                return node;
            
            Map<Richting, Vakje> map = node.getBuurLijst();
            for(Map.Entry<Richting, Vakje> entry : map.entrySet()){
                if(!(queue.contains(entry.getValue()))){
                    queue.offer(entry.getValue());
                }
            }
        }
        
        return null;
    }

    @Override
    public Vakje mainBfs(Vakje root) {
        ArrayList seen = new ArrayList();
        Queue<Vakje> queue = new LinkedList();

        queue.offer(root);
        root.setParent(null);
        
        while(!queue.isEmpty()){
            Vakje node = queue.poll();
            
            if(node == huidigVakje.getSpelbord().getPacman().getVakje())
                return retrievePad(node);
                
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
        
        return parents.pop(); // NullPtr
    }
    
}
