package com.orcaformation.calculetterci.adapter;

import android.content.Context;
import android.content.res.Resources;
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
    public Resources res;
    RefTypeClient currRowVal  = null;
    LayoutInflater inflater;

    public TypeClientAdapter(Context context, int textViewResourceId, ArrayList<RefTypeClient> refTypeClientList, Resources resLocal) {
        super(context, textViewResourceId, refTypeClientList);
        this.context = context;
        this.refTypeClientList = refTypeClientList;
        this.res = resLocal;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.client_list_view_row, parent, false);
        currRowVal = null;
        currRowVal = (RefTypeClient) refTypeClientList.get(position);
        TextView label = (TextView) row.findViewById(R.id.listClientText);
        label.setText(currRowVal.getTypeClientLibelle());
        ImageView typeClientImage = (ImageView) row.findViewById(R.id.typeClientImage);
        int resId = context.getResources().getIdentifier("typeclient" + currRowVal.getTypeClientId(), "drawable", context.getPackageName());
        typeClientImage.setImageResource(resId);
        row.setTag(new Integer(Integer.valueOf(refTypeClientList.get(position).getTypeClientId())));
        return row;
    }


    /*@Override
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
    }*/
}
