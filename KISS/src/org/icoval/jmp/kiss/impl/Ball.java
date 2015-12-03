package org.icoval.jmp.kiss.impl;

import org.icoval.jmp.kiss.Figure;

public class Ball extends Figure
{
    private final static String NAME = "Ball";
    private Double radius;
    private Double volume;

    public Ball(Double radius)
    {
        this.radius = radius;
        this.volume = this.evaluateVolume();
    }

    private Double evaluateVolume()
    {
        return 4 / 3 * Math.PI * Math.pow(radius, 3);
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
