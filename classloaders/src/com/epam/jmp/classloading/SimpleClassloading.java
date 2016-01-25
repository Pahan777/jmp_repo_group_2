package com.epam.jmp.classloading;

import java.lang.reflect.Method;

public class SimpleClassloading
{
    public static void main(String[] args)
    {

        ClassLoader classLoader = SimpleClassloading.class.getClassLoader();

        try
        {
            Class clazz = classLoader.loadClass("com.epam.mentoring.lesson.Semaphore");
            System.out.println("clazz.getName() = " + clazz.getName());
            Method leverMethod = clazz.getMethod("lever");
            Object semaphoreObj = clazz.newInstance();
            leverMethod.invoke(semaphoreObj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
