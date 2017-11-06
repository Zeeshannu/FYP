package com.example.zeeshan.tablename;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String JSON_DATA;
    JSONObject jsonObject,jsonObjectexact;
    JSONArray jsonArray;
    TextView textView;
    TableDataAdapter tableDataAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        JSON_DATA=getIntent().getExtras().getString("JSON");
         // Toast.makeText(this, ""+JSON_DATA, Toast.LENGTH_SHORT).show();


//constructor of adapter
        tableDataAdapter=new TableDataAdapter(this, R.layout.activity_list_row);

        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(tableDataAdapter);


        try {
            jsonObject=new JSONObject(JSON_DATA);
            jsonArray=jsonObject.getJSONArray("table");

            int count=0;
            String food_name,foodURL;
            StringBuilder foodnameSB=new StringBuilder();
            while (count<jsonArray.length()) {

                jsonObjectexact = jsonArray.getJSONObject(count);
                food_name = jsonObjectexact.getString("food_name");

                foodURL=jsonObjectexact.getString("url");


             foodURL=foodURL.replaceAll("\\\\","");
                TableData row=new TableData(food_name,foodURL);

                tableDataAdapter.add(row);

                foodnameSB.append(food_name);
                count++;

            }
            Toast.makeText(this, "Result : "+foodnameSB, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
