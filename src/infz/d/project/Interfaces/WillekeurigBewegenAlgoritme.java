/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infz.d.project.Interfaces;

import infz.d.project.Enums.Richting;
import infz.d.project.GUI.Vakje;

/**
 *
 * @author Method
 */
public interface WillekeurigBewegenAlgoritme {
    public abstract Richting willekeurigBewegen(Vakje vakje, Vakje laatsteVakje);
}
