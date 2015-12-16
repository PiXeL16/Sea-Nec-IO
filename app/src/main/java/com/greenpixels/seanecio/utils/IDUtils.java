package com.greenpixels.seanecio.utils;

import java.util.Random;

/**
 * Utils para generar unique Ids
 *
 * @author PiXeL16
 * @date 9/29/14
 */
public class IDUtils {


    /**
     * Obtiene un randon int para ser utilizado como id
     * @return
     */
    public static int getRandomIntID()
    {
        Random rand = new Random();

        return rand.nextInt();
    }

    /**
     * Obtiene un randon string para ser utilziado como id
     * @return
     */
    public static String getRandomStringID()
    {
        int rand =  getRandomIntID();

        return Integer.toString(rand);

    }


}
