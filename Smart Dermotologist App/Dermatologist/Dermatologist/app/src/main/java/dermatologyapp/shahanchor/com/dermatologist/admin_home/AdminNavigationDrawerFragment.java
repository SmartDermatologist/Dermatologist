package dermatologyapp.shahanchor.com.dermatologist.admin_home;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dermatologyapp.shahanchor.com.dermatologist.Constants;
import dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer.UserNavigationDrawerController;
import dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer.UserNavigationMenuModel;
import dermatologyapp.shahanchor.com.dermatologist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminNavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mUserDrawerToggle;
    private DrawerLayout mAdminDrawerLayout;
    private RecyclerView mAdminRecyclerView;
    private TextView emailId,userName;
    private UserNavigationMenuModel userNavigationMenuModel;
    private List<UserNavigationMenuModel> adminMenuListDetails=new ArrayList<>();
    private AdminNavigationDrawerController adminNavigationDrawerController;
   public AdminNavigationDrawerFragment() {
        // Required empty public constructor
    }


    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_navigation_drawer, container, false);
        mAdminRecyclerView= (RecyclerView) view.findViewById(R.id.adminRecylerView);
        createMenuDetails();
        adminNavigationDrawerController=new AdminNavigationDrawerController(getActivity(),adminMenuListDetails);
        mAdminRecyclerView.setAdapter(adminNavigationDrawerController);
        mAdminRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void createMenuDetails() {
        adminMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemViewUsers,"\uf2c0"));
        adminMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemViewDoctors,"\uf0f0"));
        adminMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemViewFeedbacks,"\uf1c0"));
        adminMenuListDetails.add(new UserNavigationMenuModel(Constants.menuItemSignOut,"\uf08b"));
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolBar) {
        mAdminDrawerLayout=drawerLayout;
        mUserDrawerToggle=new ActionBarDrawerToggle(getActivity(),mAdminDrawerLayout,toolBar,R.string.drawer_open,R.string.drawer_close)
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
        mAdminDrawerLayout.addDrawerListener(mUserDrawerToggle);
        mAdminDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mUserDrawerToggle.syncState();
            }
        });

    }
}
