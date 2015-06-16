/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Interfaces;

import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Method
 */
public class WillekeurigBewegen implements WillekeurigBewegenAlgoritme{

    @Override
    public Richting willekeurigBewegen(Vakje vakje, Vakje laatsteVakje) {
        Random random = new Random();
        ArrayList<Richting> openRichting = new ArrayList<>();
        Iterator iter = vakje.getBuurLijst().entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry pair = (Map.Entry) iter.next();
            if (pair.getValue() != laatsteVakje) {
                switch (pair.getKey().toString()) {
                    case "NOORD":
                        openRichting.add(Richting.NOORD);
                        break;
                    case "OOST":
                        openRichting.add(Richting.OOST);
                        break;
                    case "ZUID":
                        openRichting.add(Richting.ZUID);
                        break;
                    case "WEST":
                        openRichting.add(Richting.WEST);
                        break;
                }
            }
        }

        int tempNummer = random.nextInt(openRichting.size());

        return openRichting.get(tempNummer);
    }
    
}
