package com.android.simone.github.marvelapp.presentation;

import java.util.Random;

/**
 * @author Simone Bellotti
 */

public class Utils {

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
