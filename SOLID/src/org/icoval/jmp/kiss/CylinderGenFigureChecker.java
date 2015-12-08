package org.icoval.jmp.kiss;

import org.icoval.jmp.kiss.impl.Cube;
import org.icoval.jmp.kiss.impl.Cylinder;

/**
 * @author Igor Coval on 08.12.15.
 */
public class CylinderGenFigureChecker implements GenFigureChecker<Cylinder> {
    @Override
    public boolean isValid(Cylinder figure) {
        if (figure.getVolume() > 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public void printResult(Cylinder figure) {
        System.out.println(figure.getName() + " Check result : " + isValid(figure));
    }
}
