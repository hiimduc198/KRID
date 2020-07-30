package com.example.krid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.krid.model.Job;

import java.util.List;

public class JobSpinnerAdapter extends ArrayAdapter<Job> {

    private Context context;

    private List<Job> values;

    public JobSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Job> list) {
        super(context, resource, list);
        this.context = context;
        this.values = list;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Job getItem(int position){
        return values.get(position);
    }
    // And the "magic" goes here
    // This is for the "passive" state of the spinner

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Jobs array) and the current position
        // You can NOW reference each method you has created in your bean object (Job class)
        label.setText(values.get(position).getName());
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getName());

        return label;
    }
}
