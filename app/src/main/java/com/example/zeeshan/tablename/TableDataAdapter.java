package com.example.zeeshan.tablename;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 9/28/2017.
 */

public class TableDataAdapter extends ArrayAdapter {

    List list=new ArrayList();

    Context context;


    public TableDataAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);

        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        tableDataHolder holder;

        View row;
        row=convertView;
        if(row==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.activity_list_row,parent,false);

            holder=new tableDataHolder();
            holder.foodname=(TextView) row.findViewById(R.id.TVfoodname);
            holder.foodImage=(ImageView) row.findViewById(R.id.list_image);


        row.setTag(holder);
        }
        else {
            holder= (tableDataHolder) row.getTag();
        }

        TableData tableData= (TableData) this.getItem(position);
        holder.foodname.setText(tableData.getFoodname());
        //holder.foodImage.setImageResource();
        //tableData.foodImageURL=

        Picasso.with(context)
                .load(tableData.foodImageURL)
                //.placeholder(R.drawable.)   // optional
                //.error(R.drawable.ic_error_fallback)      // optional
                //.resize(250, 200)                        // optional
                //.rotate(90)                             // optional
                .into(holder.foodImage);

        return row;
        }


    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public void add( TableData object) {
        super.add(object);
        list.add(object);
    }

    static class tableDataHolder
    {
        TextView foodname;
        ImageView foodImage;
    }

}
