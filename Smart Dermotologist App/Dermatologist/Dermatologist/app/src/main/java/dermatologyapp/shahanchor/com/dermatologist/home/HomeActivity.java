package dermatologyapp.shahanchor.com.dermatologist.home;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer.UserNavigationDrawerFragment;
import dermatologyapp.shahanchor.com.dermatologist.R;
import dermatologyapp.shahanchor.com.dermatologist.utility.Utility;


public class HomeActivity extends AppCompatActivity {
  private Toolbar toolbar;
  private DrawerLayout userDrawerLayout;
  private UserNavigationDrawerFragment userNavigationDrawerFragment;
  private Context context;
  private Activity  activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar= (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Home Activity");
        userDrawerLayout= (DrawerLayout) findViewById(R.id.userDrawerLayout);
        userNavigationDrawerFragment= (UserNavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragmnetUserDrawer);
        userNavigationDrawerFragment.setUp(userDrawerLayout,toolbar);
        context=HomeActivity.this;
        activity= (Activity) context;
    }

    @Override
    public void onBackPressed() {
        Utility.showAlertDialog(context,"Exit Application","Do You Want To Exit","Yes","No",activity);

    }
}
