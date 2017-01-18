package dermatologyapp.shahanchor.com.dermatologist.feedback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dermatologyapp.shahanchor.com.dermatologist.Constants;
import dermatologyapp.shahanchor.com.dermatologist.R;
import dermatologyapp.shahanchor.com.dermatologist.Register.RegisterActivity;
import dermatologyapp.shahanchor.com.dermatologist.home.HomeActivity;
import dermatologyapp.shahanchor.com.dermatologist.login.LoginActivity;
import dermatologyapp.shahanchor.com.dermatologist.utility.Utility;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener{
TextView textViewFeedbackFontUserName,textViewFeedbackFontUserEmail;
EditText textViewFeedbackUserName,textViewFeedbackUserEmail,textViewFeedbackUserDetails;
private Typeface font;
private Toolbar toolbarFeeedback;
private Spinner spinnerFeedbackType;
private Context context;
private Button feedbackButton;
private DatabaseReference firebaseDbReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        toolbarFeeedback= (Toolbar) findViewById(R.id.appBarFeedback);
        setSupportActionBar(toolbarFeeedback);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarFeeedback.setTitle("Feedback");
        context=FeedbackActivity.this;
        firebaseDbReference= FirebaseDatabase.getInstance().getReference(Constants.userFeedbackDetails);

        try {
            font = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/fontawesome-webfont.ttf");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.v("typeface", e.toString());
        }
        textViewFeedbackFontUserName= (TextView) findViewById(R.id.textView_feedback_fontIconUserName);
        textViewFeedbackFontUserEmail= (TextView) findViewById(R.id.textView_feedback_fontIconEmail);
        textViewFeedbackUserName=(EditText) findViewById(R.id.textView_Feedback_UserName);
        textViewFeedbackUserEmail=(EditText) findViewById(R.id.textView_Feedback_UserEmail);
        textViewFeedbackUserDetails=(EditText) findViewById(R.id.feedbackDetails);
        feedbackButton= (Button) findViewById(R.id.buttonFeedback);
        feedbackButton.setOnClickListener(this);

        textViewFeedbackFontUserName.setTypeface(font);
        textViewFeedbackFontUserName.setText("\uf2c0");
        textViewFeedbackFontUserEmail.setTypeface(font);
        textViewFeedbackFontUserEmail.setText("\uf0e0");

        spinnerFeedbackType= (Spinner) findViewById(R.id.spinner_feedbackType);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonFeedback:
                if (!validateDetails()) {
                    if (!checkInputValues()) {
                        if(Utility.isInternetAvailable(context))
                        {
                            Utility.startProgressDialog(context);
                            pushUserFeedbackDetailsToFireBase();
                            Utility.stopProgressDialog();
                            Toast.makeText(context,Constants.registrationSucessfulMessage,Toast.LENGTH_SHORT).show();
                            Intent intentNavigateToLogin=new Intent(FeedbackActivity.this, HomeActivity.class);
                            startActivity(intentNavigateToLogin);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(context,Constants.noInternetMessage,Toast.LENGTH_SHORT).show();
                        }
                    }
                }



        }
    }

    private void pushUserFeedbackDetailsToFireBase() {
        String feedBackId=firebaseDbReference.push().getKey();
       Log.v("Key",firebaseDbReference.push().getKey()) ;
       FeedbackDetailsModel feedbackDetailsModel =new FeedbackDetailsModel();
        feedbackDetailsModel.setEnteredNameForFeedback(textViewFeedbackUserName.getText().toString().trim());
        feedbackDetailsModel.setEnteredEmailForFeedback(textViewFeedbackUserEmail.getText().toString().trim());
        feedbackDetailsModel.setFeedbackType(spinnerFeedbackType.getSelectedItem().toString().trim());
        feedbackDetailsModel.setFeedBackDetails(textViewFeedbackUserDetails.getText().toString().trim());
        firebaseDbReference.child(feedBackId).setValue(feedbackDetailsModel);
    }

    private boolean validateDetails() {
        if (TextUtils.isEmpty(textViewFeedbackUserName.getText())) {
            textViewFeedbackUserName.setError(Constants.invalidName);
            Toast.makeText(getBaseContext(), Constants.invalidName, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(textViewFeedbackUserEmail.getText())) {
            textViewFeedbackUserEmail.setError(Constants.invalidEmail);
            Toast.makeText(getBaseContext(), Constants.invalidEmail, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(textViewFeedbackUserDetails.getText())) {
            textViewFeedbackUserDetails.setError(Constants.invalidFeedbackDetails);
            Toast.makeText(getBaseContext(), Constants.invalidFeedbackDetails, Toast.LENGTH_SHORT).show();
            return true;
        }  else
            return false;
    }

    @SuppressLint("LongLogTag")
    private boolean checkInputValues() {
        Log.v("Spinner Gender Selected Value", spinnerFeedbackType.getSelectedItem().toString().trim());
        if (spinnerFeedbackType.getSelectedItem().toString().contains("Select")) {
            Toast.makeText(getBaseContext(), Constants.invalidFeedbackType, Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(textViewFeedbackUserEmail.getText().toString().trim()).matches()) {
            Toast.makeText(getBaseContext(), Constants.invalidEmail, Toast.LENGTH_SHORT).show();
            textViewFeedbackUserEmail.setText("");
            textViewFeedbackUserEmail.setError(Constants.invalidEmail);
            return true;
        } else {
            return false;
        }
    }
}
