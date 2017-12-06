package com.orcaformation.calculetterci.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.entity.TblXmlBaremes;
import com.orcaformation.calculetterci.entity.XmlTarification;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 07/11/2017.
 */

public class TauxAdapter extends ArrayAdapter<TblXmlBaremes>{

    ArrayList<TblXmlBaremes> tblXmlBaremesList = new ArrayList<>();
    private Context context;
    public Resources res;
    TblXmlBaremes currRowVal  = null;
    LayoutInflater inflater;

    public TauxAdapter(Context context, int textViewResourceId, ArrayList<TblXmlBaremes> tblXmlBaremesList, Resources resLocal) {
        super(context, textViewResourceId, tblXmlBaremesList);
        this.context = context;
        this.tblXmlBaremesList = tblXmlBaremesList;
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
        View row = inflater.inflate(R.layout.taux_list_view_row, parent, false);
        currRowVal = null;
        currRowVal = (TblXmlBaremes) tblXmlBaremesList.get(position);
        TextView label = (TextView) row.findViewById(R.id.listTauxText);
        label.setText(currRowVal.getXmlBaremeTxVr());
        row.setTag(new Integer(Integer.valueOf(tblXmlBaremesList.get(position).getXmlBaremeTxVr())));
        return row;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.taux_list_view_row, null);
        TextView textView = (TextView) v.findViewById(R.id.listTauxText);
        textView.setText(tblXmlBaremesList.get(position).getXmlBaremeTxVr());
        v.setTag(new Integer(Integer.valueOf(tblXmlBaremesList.get(position).getXmlBaremeTxVr())));
        return v;

    }*/
}
