package dermatologyapp.shahanchor.com.dermatologist.Register;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import dermatologyapp.shahanchor.com.dermatologist.login.LoginActivity;
import dermatologyapp.shahanchor.com.dermatologist.Model.M_NewUserDetails;
import dermatologyapp.shahanchor.com.dermatologist.R;
import dermatologyapp.shahanchor.com.dermatologist.utility.Utility;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewFontUserName, textViewFontUserLastName, textViewFontUserConfirmPassword, textViewFontUserPassword, textViewFontUserEmail, textViewFontUserAge, textViewFontUserSex;
    private EditText editTextRegisterUserName, editTextRegisterUserLastName, editTextRegisterUserAge, editTextRegisterUserEmail, editTextRegisterUserPassword, editTextRegisterUserConfirmPassword;
    private Spinner spinnerGenderValues;
    private Typeface font;
    private Button buttonSubmit;
    private DatabaseReference firebaseDatabaseReference;
    private M_NewUserDetails newUserDetails;
    private Context context;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context=RegisterActivity.this;

        try {
            font = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/fontawesome-webfont.ttf");
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("typeface", e.toString());
        }
        textViewFontUserName = (TextView) findViewById(R.id.textView_fontIconUserName);
        textViewFontUserLastName = (TextView) findViewById(R.id.textView_fontIconUserLastName);
        textViewFontUserConfirmPassword = (TextView) findViewById(R.id.textView_fontIconUserConfirmPassword);
        textViewFontUserPassword = (TextView) findViewById(R.id.textView_fontIconUserPassword);
        textViewFontUserEmail = (TextView) findViewById(R.id.textView_fontIconEmail);
        textViewFontUserAge = (TextView) findViewById(R.id.textView_fontAge);
        textViewFontUserSex = (TextView) findViewById(R.id.textView_fontIconUserSex);

        editTextRegisterUserName = (EditText) findViewById(R.id.registerFirstName);
        editTextRegisterUserLastName = (EditText) findViewById(R.id.registerLastName);
        editTextRegisterUserAge = (EditText) findViewById(R.id.registerAge);
        editTextRegisterUserEmail = (EditText) findViewById(R.id.registerEmail);
        editTextRegisterUserPassword = (EditText) findViewById(R.id.registerPassword);
        editTextRegisterUserConfirmPassword = (EditText) findViewById(R.id.registerConfirmPassword);

        spinnerGenderValues = (Spinner) findViewById(R.id.spinner_genderValues);


        buttonSubmit = (Button) findViewById(R.id.button_register_submit);
        buttonSubmit.setOnClickListener(this);


        textViewFontUserName.setTypeface(font);
        textViewFontUserName.setText("\uf2c0");
        textViewFontUserLastName.setTypeface(font);
        textViewFontUserLastName.setText("\uf2c0");
        textViewFontUserConfirmPassword.setTypeface(font);
        textViewFontUserConfirmPassword.setText("\uf01e");
        textViewFontUserPassword.setTypeface(font);
        textViewFontUserPassword.setText("\uf023");
        textViewFontUserEmail.setTypeface(font);
        textViewFontUserEmail.setText("\uf0e0");
        textViewFontUserAge.setTypeface(font);
        textViewFontUserAge.setText("\uf2c0");
        textViewFontUserSex.setTypeface(font);
        textViewFontUserSex.setText("\uf2c0");

       firebaseDatabaseReference= FirebaseDatabase.getInstance().getReference(Constants.childUserDetails);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register_submit:
                if (!validateDetails()) {
                    if (!checkInputValues()) {
                        if(Utility.isInternetAvailable(context))
                        {
                            Utility.startProgressDialog(context);
                            pushUserDetailsToFireBase();
                            Utility.stopProgressDialog();
                            Toast.makeText(context,Constants.registrationSucessfulMessage,Toast.LENGTH_SHORT).show();
                            Intent intentNavigateToLogin=new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intentNavigateToLogin);
                            finish();
                        }
                        else
                        {
                           Toast.makeText(context,Constants.noInternetMessage,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//             Intent registerDetails=new Intent(RegisterActivity.this, FeedbackActivity.class);
//             startActivity(registerDetails);
        }

    }

    private void pushUserDetailsToFireBase() {
        String userId=firebaseDatabaseReference.push().getKey();
        Log.v("userId",userId.toString());
        newUserDetails =new M_NewUserDetails();
        newUserDetails.setUser_first_name(editTextRegisterUserName.getText().toString().trim());
        newUserDetails.setUser_lastName(editTextRegisterUserLastName.getText().toString().trim());
        newUserDetails.setUser_sex(spinnerGenderValues.getSelectedItem().toString().trim());
        newUserDetails.setUser_age(editTextRegisterUserAge.getText().toString().trim());
        newUserDetails.setUser_email(editTextRegisterUserEmail.getText().toString().trim());
        newUserDetails.setUser_password(editTextRegisterUserPassword.getText().toString().trim());

        firebaseDatabaseReference.child(userId).setValue(newUserDetails);




    }

    private boolean validateDetails() {
        if (TextUtils.isEmpty(editTextRegisterUserName.getText())) {
            editTextRegisterUserName.setError(Constants.invalidFirstName);
            Toast.makeText(getBaseContext(), Constants.invalidFirstName, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterUserLastName.getText())) {
            editTextRegisterUserName.setError(Constants.invalidLastName);
            Toast.makeText(getBaseContext(), Constants.invalidLastName, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterUserAge.getText())) {
            editTextRegisterUserAge.setError(Constants.invalidAge);
            Toast.makeText(getBaseContext(), Constants.invalidAge, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterUserEmail.getText())) {
            editTextRegisterUserEmail.setError(Constants.invalidEmail);
            Toast.makeText(getBaseContext(), Constants.invalidEmail, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterUserPassword.getText())) {
            editTextRegisterUserPassword.setError(Constants.invalidPassword);
            Toast.makeText(getBaseContext(), Constants.invalidPassword, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterUserConfirmPassword.getText())) {
            editTextRegisterUserConfirmPassword.setError(Constants.invalidConfirmPassword);
            Toast.makeText(getBaseContext(), Constants.invalidConfirmPassword, Toast.LENGTH_SHORT).show();
            return true;
        } else
            return false;
    }

    @SuppressLint("LongLogTag")
    private boolean checkInputValues() {
        Log.v("Spinner Gender Selected Value", spinnerGenderValues.getSelectedItem().toString().trim());
        if (spinnerGenderValues.getSelectedItem().toString().contains("Gender")) {
            Toast.makeText(getBaseContext(), Constants.selectValidGender, Toast.LENGTH_SHORT).show();
            return true;
        } else if (editTextRegisterUserAge.getText().toString().trim().equals("0") || Integer.parseInt(editTextRegisterUserAge.getText().toString().trim()) < 0) {
            Toast.makeText(getBaseContext(), Constants.invalidAge, Toast.LENGTH_SHORT).show();
            editTextRegisterUserAge.setText("");
            editTextRegisterUserAge.setError(Constants.invalidAge);
            return true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editTextRegisterUserEmail.getText().toString().trim()).matches()) {
            Toast.makeText(getBaseContext(), Constants.invalidEmail, Toast.LENGTH_SHORT).show();
            editTextRegisterUserEmail.setText("");
            editTextRegisterUserEmail.setError(Constants.invalidEmail);
            return true;
        } else if (!editTextRegisterUserConfirmPassword.getText().toString().trim().equals(editTextRegisterUserPassword.getText().toString().trim())) {
            Toast.makeText(getBaseContext(), Constants.invalidPassword, Toast.LENGTH_SHORT).show();
            editTextRegisterUserConfirmPassword.setText("");
            editTextRegisterUserPassword.setText("");
            return true;
        } else {
            return false;
        }
    }
}
