package br.com.pdasolucoes.standardconfig.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.pdasolucoes.standardconfig.R;

public class Helper {

    public static String formatDateToCsharp(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
    }

    public static String formatDateTimeToCsharp(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date).replaceAll(" ", "T");
    }

    public static void actionError(boolean vibrate, boolean song) {

        Context context = NavigationHelper.getCurrentAppCompat();

        if (context == null)
            return;

        if (vibrate) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (v != null)
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));

            } else if (v != null)
                v.vibrate(500);
        }
        if (song) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
            mediaPlayer.start(); //
        }
    }
}
