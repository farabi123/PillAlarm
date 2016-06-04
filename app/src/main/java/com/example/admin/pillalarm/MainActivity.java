package com.example.admin.pillalarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    int i=0;
    static EditText pillName1;
    static EditText pillName2;
    static EditText pillName3;
    static EditText pillName4;
    static EditText pillName5;
    static EditText pillName6;
    static EditText pillName7;
    static EditText pillName8;
    static EditText pillName9;
    static EditText pillName10;
    String[] pillArray;
    static ArrayAdapter<String> adapterOfTitles;
    static SharedPreferences sharedPref;
    Button retrieve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        final String empty= "";
        pillName1.setText(empty);
        pillName2.setText(empty);
        pillName3.setText(empty);
        pillName4.setText(empty);
        pillName5.setText(empty);
        pillName6.setText(empty);
        pillName7.setText(empty);
        pillName8.setText(empty);
        pillName9.setText(empty);
        pillName10.setText(empty);

        //Initialize the sharedpreference
        sharedPref = getSharedPreferences("Pills",Context.MODE_PRIVATE);
        //Initialize the String array
        pillArray = new String[10];
        //Initialize the adapter
        adapterOfTitles = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //Initialize the GO button
        Button GO=(Button) findViewById(R.id.button);

        //Create an on click listener to GO
        GO.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setPill();
                for(i=0;i<10;i++) {
                    if (pillArray[i].equals(empty)){
                        System.out.println("THROW IT AWAY");
                        continue;
                    } else{
                        adapterOfTitles.add(pillArray[i]);
                        System.out.println("COUNT IS:" + adapterOfTitles.getCount());
                    }
                }
                Intent pillIntent= new Intent(MainActivity.this,PillsList.class);
                startActivity(pillIntent);

            }
        });
        //Initialize the save button
        retrieve=(Button) findViewById(R.id.retrieve);
        retrieve.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayData();
            }
        });
    }

    private void setPill() {
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

        SharedPreferences pref = getSharedPreferences("Pills",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("pill1",pillName1.getText().toString());
        editor.putString("pill2",pillName2.getText().toString());
        editor.putString("pill3",pillName3.getText().toString());
        editor.putString("pill4",pillName4.getText().toString());
        editor.putString("pill5",pillName5.getText().toString());
        editor.putString("pill6",pillName6.getText().toString());
        editor.putString("pill7",pillName7.getText().toString());
        editor.putString("pill8",pillName8.getText().toString());
        editor.putString("pill9",pillName9.getText().toString());
        editor.putString("pill10",pillName10.getText().toString());
        editor.apply();

        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

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

    //Retrieve info on the screen in editTexts
    public void displayData(){
        String pill1 = sharedPref.getString("pill1","");
        String pill2 = sharedPref.getString("pill2","");
        String pill3 = sharedPref.getString("pill3","");
        String pill4 = sharedPref.getString("pill4","");
        String pill5 = sharedPref.getString("pill5","");
        String pill6 = sharedPref.getString("pill6","");
        String pill7 = sharedPref.getString("pill7","");
        String pill8 = sharedPref.getString("pill8","");
        String pill9 = sharedPref.getString("pill9","");
        String pill10 = sharedPref.getString("pill10","");

        pillName1.setText(pill1);
        pillName2.setText(pill2);
        pillName3.setText(pill3);
        pillName4.setText(pill4);
        pillName5.setText(pill5);
        pillName6.setText(pill6);
        pillName7.setText(pill7);
        pillName8.setText(pill8);
        pillName9.setText(pill9);
        pillName10.setText(pill10);
    }
}