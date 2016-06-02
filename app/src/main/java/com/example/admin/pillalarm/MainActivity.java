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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    EditText pillName1;
    EditText pillName2;
    String[] pillArray;
    int i=0;
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

//Trying the strings

        pillName1 = (EditText) findViewById(R.id.editText1);
        pillName2 = (EditText) findViewById(R.id.editText2);
        pillArray = new String[2];
        ListView viewOfPills = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> adapterOfTitles = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //Initialize the off button
        Button GO=(Button) findViewById(R.id.button);

        //Create an on click listener to GO
        GO.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                set_pill();

                for(i=0;i<2;i++){
                    adapterOfTitles.add(pillArray[i]);
                    System.out.println("COUNT IS:" +adapterOfTitles.getCount());
                }

            }
        });

       /* for(PillsList enumOfPills :PillsList.values()) {
        adapterOfTitles.add(getString(enumOfPills.title));
    }*/
       viewOfPills.setAdapter(adapterOfTitles);
        viewOfPills.setOnItemClickListener(this);
    }
    private void set_pill() {
        pillArray[0]=pillName1.getText().toString();
        pillArray[1]=pillName2.getText().toString();
        System.out.println("The first PILL IS: "+pillArray[0]);
        System.out.println("The second PILL IS: "+pillArray[1]);
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
        Intent alarmIntent= new Intent(MainActivity.this,Alarm.class);
        startActivity(alarmIntent);
        // PillsList listPill= PillsList.values()[position];
     //   String chosenPill=pillArray[position];
        //System.out.println("pill:"+listPill);
        //switch(listPill) {
        /*switch(chosenPill){
            case Choice1:
            case pillArray[0]:
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
        }*/
    }
}
