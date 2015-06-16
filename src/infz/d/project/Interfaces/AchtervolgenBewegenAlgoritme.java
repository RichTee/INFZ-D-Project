/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infz.d.project.Interfaces;

import infz.d.project.GUI.Vakje;

/**
 *
 * @author Method
 */
public interface AchtervolgenBewegenAlgoritme {
    public abstract Vakje geefCell(Vakje huidigVakje);
    public abstract Vakje interceptPacman(Vakje root);
    public abstract Vakje mainBfs(Vakje root);
    public abstract Vakje retrievePad(Vakje node);
}
