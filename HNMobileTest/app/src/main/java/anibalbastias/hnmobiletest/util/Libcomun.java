package anibalbastias.hnmobiletest.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.SpannableString;
import android.widget.TextView;
import android.widget.Toast;

import com.ocpsoft.pretty.time.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import anibalbastias.hnmobiletest.R;

/**
 * Created by anibalbastias on 16-03-16.
 */
public class Libcomun {
    public static void setAnimationActivity(Activity act) {
        act.overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
    }

    public static void toast(Context context, String msj) {
        Toast.makeText(context, msj, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, SpannableString msj) {
        Toast.makeText(context, msj, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String msj, int duracion) {
        switch (duracion) {
            case 0:
                Toast.makeText(context, msj, Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
                break;
        }
    }

    public static void toast(Context context, int msj, int duracion) {
        switch (duracion) {
            case 0:
                Toast.makeText(context, msj, Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
                break;
        }
    }

    public static void setFontTextViewBlack(TextView text, Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Black.otf");
        text.setTypeface(type);
    }

    public static void setFontTextViewBold(TextView text, Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Bold.otf");
        text.setTypeface(type);
    }

    public static void setFontTextViewLight(TextView text, Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Light.otf");
        text.setTypeface(type);
    }

    public static void setFontTextViewRegular(TextView text, Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.otf");
        text.setTypeface(type);
    }

    public static String decodeHtmlEntitiesString(String text) {
        return Html.fromHtml(text).toString();
    }

    public static String getPrettyTime(String time) {
        PrettyTime p = new PrettyTime(Locale.getDefault());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return p.format(formatter.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
