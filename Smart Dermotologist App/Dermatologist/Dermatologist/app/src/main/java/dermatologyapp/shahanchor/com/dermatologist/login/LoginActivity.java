package dermatologyapp.shahanchor.com.dermatologist.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dermatologyapp.shahanchor.com.dermatologist.Constants;
import dermatologyapp.shahanchor.com.dermatologist.R;
import dermatologyapp.shahanchor.com.dermatologist.Register.RegisterActivity;
import dermatologyapp.shahanchor.com.dermatologist.admin_home.AdminHomeActivity;
import dermatologyapp.shahanchor.com.dermatologist.home.HomeActivity;
import dermatologyapp.shahanchor.com.dermatologist.utility.Utility;

public class LoginActivity extends Activity implements View.OnClickListener {
    TextView createAccount;
    private TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    private Button loginButton;
    private EditText loginEmail, loginPassword;
    private Context context;
    private DatabaseReference fireBaseDbReference;
    private boolean isDetailsValid=false;
    private ProgressDialog progressDialog;
    public SharedPreferences sharedPrefenceObject;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=LoginActivity.this;

        createAccount = (TextView) findViewById(R.id.textView_createAccount);
        loginButton = (Button) findViewById(R.id.login_button);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.text_input_email);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.text_input_password);
        loginEmail = (EditText) findViewById(R.id.editText_loginEmail);
        loginPassword = (EditText) findViewById(R.id.editText_loginPassword);

        loginButton.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        fireBaseDbReference= FirebaseDatabase.getInstance().getReference(Constants.childUserDetails);
        sharedPrefenceObject=getApplicationContext().getSharedPreferences("myPrefernces",Context.MODE_PRIVATE);
        editor=sharedPrefenceObject.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_createAccount:
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.login_button:
                if (!validateDetails()) {
                    if(!checkInputValues())
                    {
                       if(Utility.isInternetAvailable(context))
                       {
                           Utility.startProgressDialog(context);
                           validateUserLogin();
                       }
                       else
                       {
                           Toast.makeText(context,Constants.noInternetMessage,Toast.LENGTH_SHORT).show();
                       }
                    }
                }

        }

    }

    private void validateUserLogin() {

        if(loginEmail.getText().toString().trim().equals("smartdermatolgist@gmail.com")&&loginPassword.getText().toString().trim().equals("admin"))
        {
           Intent navigateToAdminPanel=new Intent(LoginActivity.this, AdminHomeActivity.class);
           startActivity(navigateToAdminPanel);
           finish();
        }

      else {
            fireBaseDbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.v("Entered Email", loginEmail.getText().toString());
                    Log.v("Entered Password", loginPassword.getText().toString());

                    for (DataSnapshot messageSnapShot : dataSnapshot.getChildren()) {
                        Log.v("Ueser Email", messageSnapShot.child("user_email").getValue().toString());
                        Log.v("User Password", messageSnapShot.child("user_password").getValue().toString());
                        if (loginEmail.getText().toString().trim().equals(messageSnapShot.child("user_email").getValue().toString())) {
                            Log.v("  match", " match");
                            String emailId = messageSnapShot.child("user_email").getValue().toString();
                            String firstName = messageSnapShot.child("user_first_name").getValue().toString();
                            String lastName = messageSnapShot.child("user_lastName").getValue().toString();
                            putDatainSharedPreferences(emailId, firstName, lastName);
                            isDetailsValid = true;
                        }
                    }
                    Utility.stopProgressDialog();
                    if (isDetailsValid) {
                        Toast.makeText(context, Constants.loginSucessfulMessage, Toast.LENGTH_SHORT).show();
                        Intent navigateToHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(navigateToHomeActivity);
                        finish();
                    } else {
                        Toast.makeText(context, Constants.loginUnsucessfulMessage, Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private boolean validateDetails() {
        if (TextUtils.isEmpty(loginEmail.getText())) {
            textInputLayoutEmail.setError(Constants.invalidEmail);
            Toast.makeText(getBaseContext(), Constants.invalidEmail, Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.isEmpty(loginPassword.getText())) {
            textInputLayoutPassword.setError(Constants.invalidPassword);
            Toast.makeText(getBaseContext(), Constants.invalidPassword, Toast.LENGTH_SHORT).show();
            return true;
        } else
            return false;
    }


    private boolean checkInputValues() {
        if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail.getText().toString().trim()).matches()) {
            Toast.makeText(getBaseContext(), Constants.invalidEmail, Toast.LENGTH_SHORT).show();
            loginEmail.setText("");
            loginEmail.setError(Constants.invalidEmail);
            return true;
        }
        else
            return false;

    }

    private  void putDatainSharedPreferences(String emailId,String firstName,String lastName)
    {
        editor.putString(Constants.sharedPrefernceUserEmailId,emailId);
        editor.putString(Constants.sharedPrefernceUserFirstName,firstName);
        editor.putString(Constants.sharedPrefernceUserLastName,lastName);
        editor.commit();
    }
}

