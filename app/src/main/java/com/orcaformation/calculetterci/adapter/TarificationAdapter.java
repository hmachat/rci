package com.orcaformation.calculetterci.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orcaformation.calculetterci.R;
import com.orcaformation.calculetterci.entity.XmlTarification;

import java.util.ArrayList;

/**
 * Created by PC_MA22 on 07/11/2017.
 */

public class TarificationAdapter extends ArrayAdapter<XmlTarification>{

    ArrayList<XmlTarification> xmlTarificationtList = new ArrayList<>();
    private Context context;
    public Resources res;
    XmlTarification currRowVal  = null;
    LayoutInflater inflater;

    public TarificationAdapter(Context context, int textViewResourceId, ArrayList<XmlTarification> xmlTarificationtList, Resources resLocal) {
        super(context, textViewResourceId, xmlTarificationtList);
        this.context = context;
        this.xmlTarificationtList = xmlTarificationtList;
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
        View row = inflater.inflate(R.layout.bareme_list_view_row, parent, false);
        currRowVal = null;
        currRowVal = (XmlTarification) xmlTarificationtList.get(position);
        TextView label = (TextView) row.findViewById(R.id.listBaremeText);
        label.setText(currRowVal.getXmlTarificationLibelle());
        row.setTag(new Integer(Integer.valueOf(xmlTarificationtList.get(position).getXmlTarificationId())));
        return row;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.bareme_list_view_row, null);
        TextView textView = (TextView) v.findViewById(R.id.listBaremeText);
        textView.setText(xmlTarificationtList.get(position).getXmlTarificationLibelle());
        v.setTag(new Integer(Integer.valueOf(xmlTarificationtList.get(position).getXmlTarificationId())));
        return v;

    }*/
}

