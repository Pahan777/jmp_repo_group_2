package org.icoval.jmp.kiss.impl;

import org.icoval.jmp.kiss.Figure;

public class Cube extends Figure
{
    private final static String NAME = "Cube";
    private Double edge;
    private Double volume;

    public Cube(Double edge)
    {
        this.edge = edge;
        this.volume = this.evaluateVolume();
    }

    private Double evaluateVolume()
    {
        return Math.pow(edge, 3);
    }

    public String getName()
    {
        return NAME;
    }

    public Double getVolume()
    {
        return volume;
    }
}
