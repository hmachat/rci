package com.orcaformation.calculetterci.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.entity.RefTypeClient;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 01/11/2017.
 */

public class TypeClientAdapter extends ArrayAdapter<RefTypeClient>{

    ArrayList<RefTypeClient> refTypeClientList = new ArrayList<>();
    private Context context;
    public TypeClientAdapter(Context context, int textViewResourceId, ArrayList<RefTypeClient> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        refTypeClientList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.client_list_view_row, null);
        TextView textView = (TextView) v.findViewById(R.id.listClientText);
        textView.setText(refTypeClientList.get(position).getTypeClientLibelle());
        ImageView typeClientImage = (ImageView) v.findViewById(R.id.typeClientImage);
        int resId = context.getResources().getIdentifier("typeclient" + refTypeClientList.get(position).getTypeClientId(), "drawable", context.getPackageName());
        typeClientImage.setImageResource(resId);
        v.setTag(new Integer(Integer.valueOf(refTypeClientList.get(position).getTypeClientId())));
        return v;

    }
}
