package com.example.admin.pillalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    ListView viewOfPills = (ListView) findViewById(R.id.listview);
    ArrayAdapter<String> adapterOfTitles = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

    for(PillsList enumOfPills :PillsList.values()) {
        adapterOfTitles.add(getString(enumOfPills.title));
    }
    viewOfPills.setAdapter(adapterOfTitles);
    viewOfPills.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        PillsList listPill= PillsList.values()[position];
        System.out.println("pill:"+listPill);
        switch(listPill) {
            case Choice1:
                System.out.println("1");
                Intent alarmIntent1= new Intent(MainActivity.this,Alarm.class);
                startActivity(alarmIntent1);
                break;

            case Choice2:
                System.out.println("2");
                Intent alarmIntent2= new Intent(MainActivity.this,Alarm.class);
                startActivity(alarmIntent2);
                break;

            case Choice3:
                System.out.println("3");
                Intent alarmIntent3= new Intent(MainActivity.this,Alarm.class);
                startActivity(alarmIntent3);
                break;

            case Choice4:
                System.out.println("4");
                Intent alarmIntent4= new Intent(MainActivity.this,Alarm.class);
                startActivity(alarmIntent4);
                break;
        }
    }
}
