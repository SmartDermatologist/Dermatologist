package dermatologyapp.shahanchor.com.dermatologist;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by charilj on 12/25/2016.
 */

public class DermatologyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
