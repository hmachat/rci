package com.orcaformation.calculetterci.adapter;

import android.content.Context;
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

    public TarificationAdapter(Context context, int textViewResourceId, ArrayList<XmlTarification> objects) {
        super(context, textViewResourceId, objects);
        xmlTarificationtList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.bareme_list_view_row, null);
        TextView textView = (TextView) v.findViewById(R.id.listBaremeText);
        textView.setText(xmlTarificationtList.get(position).getXmlTarificationLibelle());
        v.setTag(new Integer(Integer.valueOf(xmlTarificationtList.get(position).getXmlTarificationId())));
        return v;

    }
}

