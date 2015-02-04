package com.example.teacher.myapplication;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    final String LIST_NAME = "cars";
    ArrayList<String> cars = new ArrayList<String>();
    ListView list;
    Bundle s;
    Button btn;
    EditText et;
    ArrayAdapter<String> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button1);
        list = (ListView) findViewById(R.id.list);
        a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, cars);
        list.setAdapter(a);
        btn.setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                i.putExtra("item",((TextView)view).getText().toString());
                startActivity(i);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        cars = (ArrayList<String>) savedInstanceState.getSerializable(LIST_NAME);
        a.addAll(cars);
        list.setAdapter(a);
        super.onRestoreInstanceState(savedInstanceState);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LIST_NAME, cars);
    }


    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        et = new EditText(this);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(getResources().getString(R.string.hello_world))
                .setMessage(getResources().getString(R.string.add1))
                .setView(et)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cars.add(et.getText().toString());
                        a = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, cars);
                        list.setAdapter(a);
                    }
                })
                .create().show();


    }


}
