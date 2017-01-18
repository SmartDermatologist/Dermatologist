package dermatologyapp.shahanchor.com.dermatologist.fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by charilj on 12/20/2016.
 */

public class FontManager {

    public static Typeface obtainTypeFace(Context context)
    {
       return Typeface.createFromAsset(context.getAssets(), "fontawesome.ttf");

    }
}
