package com.example.matkatalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.matkatalog.R;
import com.example.matkatalog.models.Thing;
import com.example.matkatalog.utils.ImageHelpers;

import java.util.List;

public class OwnedAdapter extends RecyclerView.Adapter<OwnedAdapter.MyViewHolder> {
    private List<Thing> things;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

     static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView txt;
        private ImageView img;

        public MyViewHolder(View v) {
            super(v);
            txt = v.findViewById(R.id.txt);
            img = v.findViewById(R.id.img);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public OwnedAdapter (List<Thing> things) {
        this.things = things;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OwnedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View rowView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.owned_list_row, parent, false);
        MyViewHolder vh = new MyViewHolder(rowView);
        return vh;


        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Thing t = things.get(position);
        holder.txt.setText(t.getName());
        holder.img.setImageBitmap(ImageHelpers.scaleImage(100, 100, t.getImgAddress()));
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return things.size();
    }

    public void addItem(List<Thing> things){
         this.things.clear();
         this.things.addAll(things);
         notifyDataSetChanged();

    }
}


