package com.example.admin.pillalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PillsList extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView viewOfPills = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> theAdapter=MainActivity.getTheAdapter();
        System.out.println("COUNT INSIDE INPUT PILLS:" +theAdapter.getCount());
        viewOfPills.setAdapter(theAdapter);
        viewOfPills.setOnItemClickListener(this);
    }
    static int theID;
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.println("POSITION:"+position);
        switch(position){
            case 0:
                Intent alarmIntent0= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent0);
                theID=0;
                break;
            case 1:
                Intent alarmIntent1= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent1);
                theID=1;
                break;
            case 2:
                Intent alarmIntent2= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent2);
                theID=2;
                break;
            case 3:
                Intent alarmIntent3= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent3);
                theID=3;
                break;
            case 4:
                Intent alarmIntent4= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent4);
                theID=4;
                break;
            case 5:
                Intent alarmIntent5= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent5);
                theID=5;
                break;
            case 6:
                Intent alarmIntent6= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent6);
                break;
            case 7:
                Intent alarmIntent7= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent7);;
                break;
            case 8:
                Intent alarmIntent8= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent8);
                break;
            case 9:
                Intent alarmIntent9= new Intent(PillsList.this,Alarm.class);
                startActivity(alarmIntent9);
                break;
        }

    }
    static public int getID(){
        return theID;
    }
}
