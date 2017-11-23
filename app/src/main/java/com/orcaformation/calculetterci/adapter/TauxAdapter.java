package com.orcaformation.calculetterci.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.entity.TblXmlBaremes;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 07/11/2017.
 */

public class TauxAdapter extends ArrayAdapter<TblXmlBaremes>{

    ArrayList<TblXmlBaremes> tblXmlBaremesList = new ArrayList<>();

    public TauxAdapter(Context context, int textViewResourceId, ArrayList<TblXmlBaremes> objects) {
        super(context, textViewResourceId, objects);
        tblXmlBaremesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.taux_list_view_row, null);
        TextView textView = (TextView) v.findViewById(R.id.listTauxText);

            textView.setText(tblXmlBaremesList.get(position).getXmlBaremeTxVr());

        v.setTag(new Integer(Integer.valueOf(tblXmlBaremesList.get(position).getXmlBaremeTxVr())));
        return v;

    }
}
