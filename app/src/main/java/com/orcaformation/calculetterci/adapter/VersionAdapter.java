package com.orcaformation.calculetterci.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.entity.TblVersions;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 07/11/2017.
 */

public class VersionAdapter extends ArrayAdapter<TblVersions>{

    ArrayList<TblVersions> tblVersionsList = new ArrayList<>();
    private Context context;
    TblVersions currRowVal  = null;
    LayoutInflater inflater;

    public VersionAdapter(Context context, int textViewResourceId, ArrayList<TblVersions> tblVersionsList) {
        super(context, textViewResourceId, tblVersionsList);
        this.context = context;
        this.tblVersionsList = tblVersionsList;
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
        View row = inflater.inflate(R.layout.version_list_view_row, parent, false);
        currRowVal = null;
        currRowVal = (TblVersions) tblVersionsList.get(position);
        TextView label = (TextView) row.findViewById(R.id.listVersionText);
        label.setText(currRowVal.getVersionLib());
        if(tblVersionsList.get(position).getPrixTtc().equals("null")){
            row.setTag(0);
        }else{
            row.setTag(new Float(Float.valueOf(tblVersionsList.get(position).getPrixTtc())));
        }
        return row;
    }

}
