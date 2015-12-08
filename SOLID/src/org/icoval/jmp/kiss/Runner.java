package org.icoval.jmp.kiss;

import org.icoval.jmp.kiss.impl.Ball;
import org.icoval.jmp.kiss.impl.Cube;
import org.icoval.jmp.kiss.impl.Cylinder;

public class Runner
{
    public static GenFigureChecker checker;
    public static boolean configurable = false;

    public static void init()
    {
        if (!configurable)
        {
            checker = new GenFigureChecker() {
                @Override
                public boolean isValid(Object figure) {
                    return false;
                }

                @Override
                public void printResult(Object figure) {
                    System.out.println("Simply example");
                }
            };

        }
    }


    public static void main(String[] args)
    {
        init();
        //предположим что мы хотим ввести ограничение: любое из измерений фигуры должно быть больше чем 0
        double cubeEdge = 0.5;
        if (cubeEdge > 0)
        {
            Figure cube = new Cube(cubeEdge);
            //получим Error т.к. валидация уже есть в конструкторе. Для того чтобы соблюдать принцип единственности
            //введем класс FigureValidator который умеет проверять фигуры ЛЮБОГО типа и тем самым нарушает принцип
            //открытости (каждый раз при добавлении новой фигуры мы будем изменять класс валидатора.)
            if (FigureValidator.isValid(cube))
            {
                System.out.println(cube.toString());
            }
            //Поэтому реализуем
            //интерфейс FigureChecker и кастомные реализации для любой фигуры т.е. при необходимости будем расширять
            // а не изменять объект.
            CubeChecker cubeChecker = new CubeChecker();
            if (cubeChecker.isValid(cube))
            {
                System.out.println(cube.toString());
            }
            //Но хотелось бы чтобы наш интерфейс был параметризован, поэтому создадим GenGigureChecker и добавим
            // в него просто так метод printResult.
            //это позволит нам передвать объект типа Figure и не выполнять приведение типов т.о.
            //наш чекер сможет получать на влоде как объект базового класса так и его наследник.
            GenFigureChecker genFigureChecker = new CubeGenFigureChecker();
            genFigureChecker.isValid(cube);
            //добавив в него метод printResult получили жирный интерфейс, теперь каждая реализация зависит еще и от этого
            //метода, теперь, если мы захотим для цилиндра добавить например форматтер нам нужно будет изменить сигнатуру
            //метода т.о. затронуть все реализации, поэтому следует разделять функционал (выносим метод в отдельный интерфейс)
            //Далее в нашем методе явно объявлены Чекеры т.о. проблематично будет протестировать их работу, для этого вынесем
            //их в переменные класса, можем настроить конфигурацию для выбора нужно реализации
            // что даст нам возможность в случае необходимости написать тестовую реализацию, примитивно например в инит методе
        }





        Figure ball = new Ball(5.0);
        System.out.println(ball.toString());

        Figure cyl = new Cylinder(6.0, 2.0);
        System.out.println(cyl.getName() + " Volume = " + cyl.getVolume());
    }
}
