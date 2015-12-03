package org.icoval.jmp.kiss.impl;

import org.icoval.jmp.kiss.Figure;

public class Cylinder extends Figure
{
    private final static String NAME = "Cylinder";
    private Double hight;
    private Double radius;
    private Double volume;

    public Cylinder(Double hight, Double radius)
    {
        this.hight = hight;
        this.radius = radius;
        this.volume = this.evaluateVolume();
    }

    private Double evaluateVolume()
    {
        return hight * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public Double getVolume()
    {
        return volume;
    }
}