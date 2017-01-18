package dermatologyapp.shahanchor.com.dermatologist.showUsers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dermatologyapp.shahanchor.com.dermatologist.Constants;
import dermatologyapp.shahanchor.com.dermatologist.R;

public class ShowUsers extends AppCompatActivity {
private ListView listViewShowUsers;
private ShowUsersAdapterController showUsersAdapterController;
private DatabaseReference dbRefrenceForUserDetails;
private Context showUsersContext;

 List<ShowUserModel> listShowUserModel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        showUsersContext=ShowUsers.this;
        listViewShowUsers= (ListView) findViewById(R.id.listViewShowUsers);
        dbRefrenceForUserDetails= FirebaseDatabase.getInstance().getReference(Constants.childUserDetails);
        obtainUserDetails();




    }

    private void obtainUserDetails() {
        listShowUserModel=new ArrayList<ShowUserModel>();
        dbRefrenceForUserDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("dataSnapshot",""+dataSnapshot.getChildrenCount());
                for(DataSnapshot childrenDataSnapshot:dataSnapshot.getChildren())
                {
//                        showUserModel=new ShowUserModel();
                    Log.v("Unique Key",childrenDataSnapshot.getKey().toString());
                    Log.v("First Name",childrenDataSnapshot.child("user_first_name").toString());
                    Log.v("Last Name",childrenDataSnapshot.child("user_lastName").toString());
                    Log.v("Age",childrenDataSnapshot.child("user_age").toString());
                    Log.v("Email",childrenDataSnapshot.child("user_email").toString());
                    Log.v("Sex",childrenDataSnapshot.child("user_sex").toString());
                    String uniqueKey=childrenDataSnapshot.getKey();
                    String firstName=childrenDataSnapshot.child("user_first_name").getValue().toString();
                    String lastName=childrenDataSnapshot.child("user_lastName").getValue().toString();
                    String age=childrenDataSnapshot.child("user_age").getValue().toString();
                    String email=childrenDataSnapshot.child("user_email").getValue().toString();
                    String sex=childrenDataSnapshot.child("user_sex").getValue().toString();
                    listShowUserModel.add(new ShowUserModel(uniqueKey,firstName,lastName,age,email,sex));
                }
                if(listShowUserModel.size()>0) {
                    showUsersAdapterController=new ShowUsersAdapterController(showUsersContext,listShowUserModel);
                    listViewShowUsers.setAdapter(showUsersAdapterController);
                }
                else
                {
                    Toast.makeText(showUsersContext,"Array List Not Filled",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                  Log.e("databaseError",databaseError.toString());

            }
        });


    }



}
