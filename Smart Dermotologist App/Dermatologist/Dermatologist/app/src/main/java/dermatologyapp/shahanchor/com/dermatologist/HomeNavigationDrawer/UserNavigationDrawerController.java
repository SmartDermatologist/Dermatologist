package dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dermatologyapp.shahanchor.com.dermatologist.R;
import dermatologyapp.shahanchor.com.dermatologist.feedback.FeedbackActivity;
import dermatologyapp.shahanchor.com.dermatologist.utility.Utility;

/**
 * Created by charilj on 12/28/2016.
 */

public class UserNavigationDrawerController extends RecyclerView.Adapter<UserNavigationDrawerController.MyViewHolder> {
    Typeface font;
    Context context;
    LayoutInflater inflater;
    Activity activity;
    List<UserNavigationMenuModel> userNavigationMenuModels;

    UserNavigationDrawerController(Context context, List<UserNavigationMenuModel> userMenuListDetails) {
        this.context = context;
        activity = (Activity) context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userNavigationMenuModels = userMenuListDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_menu_inflater, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewMenuIcon.setText(userNavigationMenuModels.get(position).getImageId());
        holder.textViewMenuTitle.setText(userNavigationMenuModels.get(position).getTitle());
    }

    @SuppressLint("LongLogTag")
    @Override
    public int getItemCount() {
        Log.v("userNavigationMenuModels Size", String.valueOf(userNavigationMenuModels.size()));
        return userNavigationMenuModels.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMenuIcon, textViewMenuTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            try {
                font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("typeface", e.toString());
            }
            textViewMenuIcon = (TextView) itemView.findViewById(R.id.textView_MenuIcon);
            textViewMenuTitle = (TextView) itemView.findViewById(R.id.textView_MenuTitle);
            textViewMenuIcon.setTypeface(font);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (getAdapterPosition()) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            Intent navigateToFeedBack = new Intent(activity, FeedbackActivity.class);
                            context.startActivity(navigateToFeedBack);
                            break;
                        case 4:
                            Utility.showAlertDialog(context, "Quit", "Do You Want To Exit", "Yes", "No", activity);
                            break;


                    }
                }
            });

        }
    }
}
