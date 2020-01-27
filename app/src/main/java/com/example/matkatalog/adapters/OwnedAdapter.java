package com.example.matkatalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matkatalog.R;

import java.util.List;

public class OwnedAdapter extends ArrayAdapter {

    private final Context context;
    private final List<String> values;

    public OwnedAdapter(Context context, List values){
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.owned_list_row, parent, false);

        ImageView img = rowView.findViewById(R.id.img);
        TextView txt = rowView.findViewById(R.id.txt);

        img.setImageResource(R.drawable.ic_launcher_foreground);
        txt.setText(values.get(position));

        return rowView;
    }

}

