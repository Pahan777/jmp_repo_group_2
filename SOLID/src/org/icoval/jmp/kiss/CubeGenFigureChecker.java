package org.icoval.jmp.kiss;

import org.icoval.jmp.kiss.impl.Cube;

/**
 * @author Igor Coval on 08.12.15.
 */
public class CubeGenFigureChecker implements GenFigureChecker<Cube> {
    @Override
    public boolean isValid(Cube figure) {
        if (figure.getVolume() > 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public void printResult(Cube figure) {
        System.out.println(figure.getName() + " Check result : " + isValid(figure));
    }
}
