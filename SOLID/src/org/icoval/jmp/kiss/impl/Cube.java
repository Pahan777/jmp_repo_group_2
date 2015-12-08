package org.icoval.jmp.kiss.impl;

import org.icoval.jmp.kiss.Figure;

public class Cube extends Figure
{
    private final static String NAME = "Cube";
    private Double edge;
    private Double volume;

    public Cube(Double edge)
    {
        if (edge > 0 )
        {
            this.edge = edge;
            this.volume = null;
        }
        else
        {
            throw new Error("This value should be greater than 1");
        }
    }

    private Double evaluateVolume()
    {
        return Math.pow(edge, 3);
    }

    public String getName()
    {
        return NAME;
    }

    public Double getEdge()
    {
        return edge;
    }

    public Double getVolume()
    {
       if (null == volume)
       {
           volume = this.evaluateVolume();
       }
       return volume;
    }
}
