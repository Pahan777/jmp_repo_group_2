package org.icoval.jmp.kiss;

import org.icoval.jmp.kiss.impl.Ball;
import org.icoval.jmp.kiss.impl.Cube;
import org.icoval.jmp.kiss.impl.Cylinder;

public class Runner
{
    public static void main(String[] args)
    {
        Figure cube = new Cube(3.0);
        System.out.println(cube.toString());

        Figure cyl = new Cylinder(6.0, 2.0);
        System.out.println(cyl.getName() + " Volume = " + cyl.getVolume());

        Figure ball = new Ball(5.0);
        System.out.println(ball.toString());
    }
}
