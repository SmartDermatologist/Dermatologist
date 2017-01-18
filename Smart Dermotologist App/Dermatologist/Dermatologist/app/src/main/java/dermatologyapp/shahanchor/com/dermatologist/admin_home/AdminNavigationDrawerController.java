package dermatologyapp.shahanchor.com.dermatologist.admin_home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.SimpleOnItemTouchListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer.UserNavigationDrawerController;
import dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer.UserNavigationMenuModel;
import dermatologyapp.shahanchor.com.dermatologist.R;
import dermatologyapp.shahanchor.com.dermatologist.showUsers.ShowUsers;
import dermatologyapp.shahanchor.com.dermatologist.utility.Utility;

/**
 * Created by charilj on 1/2/2017.
 */

public class AdminNavigationDrawerController extends RecyclerView.Adapter<AdminNavigationDrawerController.MyViewHolder> {
    Typeface font;
    Context context;
    LayoutInflater inflater;
    List<UserNavigationMenuModel> userNavigationMenuModels;
    ItemClickListener itemClickListener;
    Activity activity;
    AdminNavigationDrawerController(Context context, List<UserNavigationMenuModel> adminMenuListDetails)
    {
        this.context=context;
        activity= (Activity) context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userNavigationMenuModels=adminMenuListDetails;
    }

    @Override
    public AdminNavigationDrawerController.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.user_menu_inflater,parent,false);
        return new AdminNavigationDrawerController.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewMenuIcon.setText(userNavigationMenuModels.get(position).getImageId());
        holder.textViewMenuTitle.setText(userNavigationMenuModels.get(position).getTitle());
    }

    @SuppressLint("LongLogTag")
    @Override
    public int getItemCount() {
        Log.v("userNavigationMenuModels Size",String.valueOf(userNavigationMenuModels.size()));
        return userNavigationMenuModels.size();

    }

    public  interface ItemClickListener
    {
        public void onItemClick(int position,View v);
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMenuIcon,textViewMenuTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            try {
                font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("typeface", e.toString());
            }
            textViewMenuIcon= (TextView) itemView.findViewById(R.id.textView_MenuIcon);
            textViewMenuTitle= (TextView) itemView.findViewById(R.id.textView_MenuTitle);
            textViewMenuIcon.setTypeface(font);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (getAdapterPosition())
                    {
                        case 0:
                               Intent navigateShowUsers=new Intent(context, ShowUsers.class);
                               context.startActivity(navigateShowUsers);
                               break;
                        case 1:break;
                        case 2:break;
                        case 3:
                             Utility.showAlertDialog(context,"Quit","Do You Want To Exit","Yes","No",activity);
                             break;

                    }
                }
            });
        }



    }
}