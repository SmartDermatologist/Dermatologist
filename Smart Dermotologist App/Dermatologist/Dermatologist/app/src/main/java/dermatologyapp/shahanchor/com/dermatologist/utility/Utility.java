package dermatologyapp.shahanchor.com.dermatologist.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import dermatologyapp.shahanchor.com.dermatologist.Constants;

/**
 * Created by charilj on 12/26/2016.
 */

public class Utility {
   public static ProgressDialog progressDialog;
   public static boolean isInternetAvailable(Context context)
   {
       ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
       if(networkInfo==null)
       {
           return false;
       }
       else {
           Log.v("network Information",networkInfo.toString());

           if (networkInfo.isConnected()) {
               return true;
           } else
               return false;
       }
   }

   public static void startProgressDialog(Context context)
   {
        progressDialog=new ProgressDialog(context);
       progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       progressDialog.setCancelable(false);
       progressDialog.setMessage(Constants.registrationProgressDialogMessage);
       progressDialog.show();
   }

   public static void stopProgressDialog()
   {
       if(progressDialog.isShowing())
       {
          progressDialog.dismiss();
       }
   }
   public static void showAlertDialog(final Context context, String title, String message, String optionName1, String optionName2, final Activity activity)
   {
       AlertDialog.Builder builder=new AlertDialog.Builder(context);
       builder.setTitle(title);
       builder.setMessage(message);
       builder.setNegativeButton(optionName1, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               activity.finish();
           }
       });
       builder.setPositiveButton(optionName2,new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });
       AlertDialog alertDialog=builder.create();
       alertDialog.show();

   }


}
