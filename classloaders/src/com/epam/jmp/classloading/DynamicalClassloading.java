package com.epam.jmp.classloading;

import com.epam.mentoring.lesson.dynamic.SemaforeInterface;

import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicalClassloading
{
    public static void main(String[] args)
    {
        ClassLoader classLoader = SimpleClassloading.class.getClassLoader();
        SemaforeInterface semaphoreObj;
        Class clazz;
        Method leverMethod;
        Scanner scanner = new Scanner(System.in);
        String packageName = null;
        String packageFullName = "com.epam.mentoring.lesson.dynamic.";
        String path = null;
        while(true)
        {
            System.out.println("Enter package");
            packageName = scanner.next();
            path = packageFullName + packageName;
            try
            {
                clazz = classLoader.loadClass(path);
                System.out.println("clazz.getName() = " + clazz.getName());
                leverMethod = clazz.getMethod("lever");
                semaphoreObj = (SemaforeInterface) clazz.newInstance();
                leverMethod.invoke(semaphoreObj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }


    }
}
