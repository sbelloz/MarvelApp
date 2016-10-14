package com.android.simone.github.marvelapp.presentation;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import java.util.Random;

/**
 * @author Simone Bellotti
 */

public class Utils {

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(source);
        }
    }
}
