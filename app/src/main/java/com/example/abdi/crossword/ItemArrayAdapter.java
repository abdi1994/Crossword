package com.example.abdi.crossword;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdimohammed on 11/01/2017.
 */

public class ItemArrayAdapter extends ArrayAdapter<String[]> {

    private List<String[]> hintList = new ArrayList<String[]>();

    static class ItemViewHolder {
        TextView word;
        TextView hint;
        Spinner spinner;
    }

    public ItemArrayAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(String[] object) {
        hintList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.hintList.size();
    }

    @Override
    public String[] getItem(int position) {
        return this.hintList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.word = (TextView) row.findViewById(R.id.name);
            viewHolder.spinner = (Spinner) row.findViewById(R.id.spinner1);

            row.setTag(viewHolder);
        }
        else {
            viewHolder = (ItemViewHolder) row.getTag();
        }
        String[] stat = getItem(position);
        viewHolder.word.setText(stat[2]);
        //viewHolder.spinner.setText(stat[2]);
        return row;
    }
}
