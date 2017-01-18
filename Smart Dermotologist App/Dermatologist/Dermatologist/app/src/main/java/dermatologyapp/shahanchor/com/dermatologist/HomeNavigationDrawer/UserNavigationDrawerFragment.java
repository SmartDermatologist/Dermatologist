package dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dermatologyapp.shahanchor.com.dermatologist.Constants;
import dermatologyapp.shahanchor.com.dermatologist.R;

import dermatologyapp.shahanchor.com.dermatologist.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserNavigationDrawerFragment extends Fragment {
private ActionBarDrawerToggle mUserDrawerToggle;
private DrawerLayout mUserDrawerLayout;
private RecyclerView mUserrecyclerView;
private TextView emailId,userName;
private UserNavigationMenuModel userNavigationMenuModel;
private List<UserNavigationMenuModel> userMenuListDetails=new ArrayList<>();
private UserNavigationDrawerController userNavigationDrawerController;
public SharedPreferences sharedPrefenceObject;
    public UserNavigationDrawerFragment() {
        // Required empty public constructor
    }


    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_navigation_drawer, container, false);
        sharedPrefenceObject=getActivity().getSharedPreferences("myPrefernces",Context.MODE_PRIVATE);
        emailId= (TextView) view.findViewById(R.id.fragmentUserEmailId);
        userName= (TextView) view.findViewById(R.id.fragmentUserName);
        emailId.setText(sharedPrefenceObject.getString(Constants.sharedPrefernceUserEmailId,""));
        userName.setText(sharedPrefenceObject.getString(Constants.sharedPrefernceUserFirstName,"").toUpperCase()+" "+
                sharedPrefenceObject.getString(Constants.sharedPrefernceUserLastName,"").toUpperCase());


        mUserrecyclerView= (RecyclerView) view.findViewById(R.id.userRecylerView);
        createMenuDetails();
        userNavigationDrawerController=new UserNavigationDrawerController(getActivity(),userMenuListDetails);
        mUserrecyclerView.setAdapter(userNavigationDrawerController);
        mUserrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void createMenuDetails() {
        userMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemViewCases,"\uf002"));
        userMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemNewCases,"\uf030"));
        userMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemHelp,"\uf059"));
        userMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemFeedBack,"\uf0e5"));
        userMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemSignOut,"\uf08b"));
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolBar) {
        mUserDrawerLayout=drawerLayout;
        mUserDrawerToggle=new ActionBarDrawerToggle(getActivity(),mUserDrawerLayout,toolBar,R.string.drawer_open,R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mUserDrawerLayout.addDrawerListener(mUserDrawerToggle);
        mUserDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mUserDrawerToggle.syncState();
            }
        });

    }
}
