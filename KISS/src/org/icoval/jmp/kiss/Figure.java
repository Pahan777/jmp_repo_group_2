package org.icoval.jmp.kiss;

public abstract class Figure
{
    public abstract String getName();

    public abstract Double getVolume();

    public String toString()
    {
        return getName() + " Volume = " + getVolume();
    }
}
