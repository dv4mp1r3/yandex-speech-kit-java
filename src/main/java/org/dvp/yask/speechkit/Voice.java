package org.dvp.yask.speechkit;

public class Voice extends En
{
    public static String random()
    {
        int val = generateRandomNumber(0, 1);
        return val == 0 ? En.ALYSS : En.NICK;
    }

    private static int generateRandomNumber(int min, int max)
    {
        Double val = (Math.random() * ((max - min) + 1)) + min;
        return val.intValue();
    }
}
