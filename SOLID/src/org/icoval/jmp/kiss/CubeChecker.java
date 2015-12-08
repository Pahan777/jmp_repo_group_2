package org.icoval.jmp.kiss;

/**
 * @author Igor Coval on 08.12.15.
 */
public class CubeChecker implements FigureChecker {
    @Override
    public boolean isValid(Figure f) {
        if (f.getVolume() > 1)
        {
            return true;
        }
        return false;
    }
}
