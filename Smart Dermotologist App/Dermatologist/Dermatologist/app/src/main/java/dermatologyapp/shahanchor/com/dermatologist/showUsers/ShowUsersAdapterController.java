package dermatologyapp.shahanchor.com.dermatologist.showUsers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dermatologyapp.shahanchor.com.dermatologist.R;

/**
 * Created by charilj on 1/14/2017.
 */

public class ShowUsersAdapterController extends BaseAdapter
{
    private List<ShowUserModel> showUserModelArrayList;
    private  View view;
    private  Context context;
    private LayoutInflater layoutInflater;
    ShowUsersAdapterController(Context context, List<ShowUserModel> arrayListShowUserModel)
    {
        this.context=context;
        this.showUserModelArrayList=arrayListShowUserModel;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return showUserModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         Log.i("getView","get View Method called");
            view=layoutInflater.inflate(R.layout.inflater_userdetails_layout,parent,false);
            TextView userNameFromFireBase= (TextView) view.findViewById(R.id.userNameFromFireBase);
            userNameFromFireBase.setText(showUserModelArrayList.get(position).getFirstName()+" "+showUserModelArrayList.get(position).getLastName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent navigateToUserDetails=new Intent(context,UserDetailDescription.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("firstName",showUserModelArrayList.get(position).getFirstName());
                    bundle.putString("lastName",showUserModelArrayList.get(position).getLastName());
                    bundle.putString("age",showUserModelArrayList.get(position).getAge());
                    bundle.putString("email",showUserModelArrayList.get(position).getEmail());
                    bundle.putString("sex",showUserModelArrayList.get(position).getSex());
                    navigateToUserDetails.putExtras(bundle);
                    context.startActivity(navigateToUserDetails);
                }
            });
            return view;


    }
}
