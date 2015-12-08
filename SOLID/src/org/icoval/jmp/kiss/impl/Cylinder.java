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

    public Double getRadius()
    {
        return radius;
    }

    @Override
    public Double getVolume()
    {
        if (null == volume)
        {
            volume = this.evaluateVolume();
        }
        return volume;
    }
}