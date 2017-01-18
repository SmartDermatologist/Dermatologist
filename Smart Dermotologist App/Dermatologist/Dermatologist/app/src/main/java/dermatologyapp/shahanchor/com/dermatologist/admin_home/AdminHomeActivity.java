package dermatologyapp.shahanchor.com.dermatologist.admin_home;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer.UserNavigationDrawerFragment;
import dermatologyapp.shahanchor.com.dermatologist.R;

public class AdminHomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout adminDrawerLayout;
    private AdminNavigationDrawerFragment adminNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        toolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Admin Activity");
        adminDrawerLayout = (DrawerLayout) findViewById(R.id.adminDrawerLayout);
        adminNavigationDrawerFragment = (AdminNavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentAdminDrawer);
        adminNavigationDrawerFragment.setUp(adminDrawerLayout, toolbar);
    }
    }