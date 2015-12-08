package org.icoval.jmp.kiss;

import org.icoval.jmp.kiss.impl.Cube;
import org.icoval.jmp.kiss.impl.Cylinder;

/**
 * @author Igor Coval on 08.12.15.
 */
public class FigureValidator {

    public static boolean isValid(Figure obj)
    {
        if (obj instanceof Cube)
        {
            Cube cube = (Cube) obj;
            if (cube.getVolume() > 1)
            {
                return true;
            }
        }
        if (obj instanceof Cylinder)
        {
            Cylinder cyl = (Cylinder) obj;
            if (cyl.getVolume() > 2)
            {
                return true;
            }
        }
        return false;
    }
}
