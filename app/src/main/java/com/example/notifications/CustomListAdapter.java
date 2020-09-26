package com.example.notifications;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testingpoc.R;

import java.util.ArrayList;

/**
 * Created by mukesh on 18/5/15.
 */
public class CustomListAdapter extends BaseAdapter {

    Context context;
    ArrayList<PaymentModel> modelList;

    public CustomListAdapter(Context context, ArrayList<PaymentModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, null, true);
        TextView txtFrom = (TextView) rowView.findViewById(R.id.txtFrom);
        TextView txtMessage = (TextView) rowView.findViewById(R.id.txtMessage);
        TextView txtTime = (TextView) rowView.findViewById(R.id.txtTime);
        PaymentModel m = modelList.get(position);
        txtFrom.setText(m.getFrom());
        txtMessage.setText(m.getMessage());
        txtTime.setText(m.getTime());
        return rowView;

    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}