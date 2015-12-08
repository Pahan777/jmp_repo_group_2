package org.icoval.jmp.kiss;

/**
 * @author Igor Coval on 08.12.15.
 */
public interface GenFigureChecker<T> {

    public boolean isValid(T figure);

    public void printResult(T figure);
}
