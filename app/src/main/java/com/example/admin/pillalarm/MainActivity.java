package com.example.admin.pillalarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    int i=0;
    EditText pillName1;
    EditText pillName2;
    EditText pillName3;
    EditText pillName4;
    EditText pillName5;
    EditText pillName6;
    EditText pillName7;
    EditText pillName8;
    EditText pillName9;
    EditText pillName10;
    String[] pillArray;
    static ArrayAdapter<String> adapterOfTitles;
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

        //Initialize the EditTexts
        pillName1 = (EditText) findViewById(R.id.editText1);
        pillName2 = (EditText) findViewById(R.id.editText2);
        pillName3 = (EditText) findViewById(R.id.editText3);
        pillName4 = (EditText) findViewById(R.id.editText4);
        pillName5 = (EditText) findViewById(R.id.editText5);
        pillName6 = (EditText) findViewById(R.id.editText6);
        pillName7 = (EditText) findViewById(R.id.editText7);
        pillName8 = (EditText) findViewById(R.id.editText8);
        pillName9 = (EditText) findViewById(R.id.editText9);
        pillName10 = (EditText) findViewById(R.id.editText10);

        //Initialize the String array
        pillArray = new String[10];
        //Initialize the adapter
        adapterOfTitles = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //Initialize the off button
        Button GO=(Button) findViewById(R.id.button);

        //Create an on click listener to GO
        GO.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                set_pill();
                for(i=0;i<10;i++){
                   // if(pillArray[i]!=" ") {
                        adapterOfTitles.add(pillArray[i]);
                        System.out.println("COUNT IS:" + adapterOfTitles.getCount());
                    //}
                }
                Intent pillIntent= new Intent(MainActivity.this,InputPills.class);
                startActivity(pillIntent);

            }
        });
    }

    private void set_pill() {
        pillArray[0]=pillName1.getText().toString();
        pillArray[1]=pillName2.getText().toString();
        pillArray[2]=pillName3.getText().toString();
        pillArray[3]=pillName4.getText().toString();
        pillArray[4]=pillName5.getText().toString();
        pillArray[5]=pillName6.getText().toString();
        pillArray[6]=pillName7.getText().toString();
        pillArray[7]=pillName8.getText().toString();
        pillArray[8]=pillName9.getText().toString();
        pillArray[9]=pillName10.getText().toString();

        System.out.println("The first PILL IS: "+pillArray[0]);
        System.out.println("The second PILL IS: "+pillArray[1]);
        System.out.println("The third PILL IS: "+pillArray[2]);
        System.out.println("The fourth PILL IS: "+pillArray[3]);
        System.out.println("The fifth PILL IS: "+pillArray[4]);
        System.out.println("The sixth PILL IS: "+pillArray[5]);
        System.out.println("The seventh PILL IS: "+pillArray[6]);
        System.out.println("The eighth PILL IS: "+pillArray[7]);
        System.out.println("The ninth PILL IS: "+pillArray[8]);
        System.out.println("The tenth PILL IS: "+pillArray[9]);
    }

    public static ArrayAdapter<String> getTheAdapter () {
        System.out.println("COUNT INSIDE METHOD:" +adapterOfTitles.getCount());
        return adapterOfTitles;
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


}
