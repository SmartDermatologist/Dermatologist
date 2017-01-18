package dermatologyapp.shahanchor.com.dermatologist.showUsers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dermatologyapp.shahanchor.com.dermatologist.R;

public class UserDetailDescription extends AppCompatActivity {
  TextView  textViewUserFirstName,textViewUserLastName,textViewUserSex,textViewUserAge,textViewUserEmailId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b1=getIntent().getExtras();
        setContentView(R.layout.activity_user_detail_description);
        textViewUserFirstName= (TextView) findViewById(R.id.userFirstName);
        textViewUserLastName= (TextView) findViewById(R.id.userLastName);
        textViewUserAge= (TextView) findViewById(R.id.userAge);
        textViewUserSex= (TextView) findViewById(R.id.userGender);
        textViewUserEmailId= (TextView) findViewById(R.id.userEmail);

        textViewUserFirstName.setText(b1.get("firstName").toString());
        textViewUserLastName.setText(b1.get("lastName").toString());
        textViewUserAge.setText(b1.get("age").toString());
        textViewUserSex.setText(b1.get("sex").toString());
        textViewUserEmailId.setText(b1.get("email").toString());
    }
}
