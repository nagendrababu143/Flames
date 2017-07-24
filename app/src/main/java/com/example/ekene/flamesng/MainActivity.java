package com.example.ekene.flamesng;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.FLAMESNG.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.flames_logo);
        setContentView(R.layout.activity_main);
    }

    private Hashtable<Integer, String> results = new Hashtable<Integer, String>();

    private void setResults(){
        results.put(1, "Friends");
        results.put(2, "In Love");
        results.put(3, "Admirers");
        results.put(4, "Married");
        results.put(5, "Enemies");
        results.put(6, "Soul mates");
        results.put(0, "Incompatible");
    }

    private void setUpActionBar() {
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void match(View view){
        setResults();

        //get the players' names from the text fields
        EditText textOne = (EditText) findViewById(R.id.user_name);
        EditText textTwo = (EditText) findViewById(R.id.crush_name);
        String user_name = textOne.getText().toString();
        String crush_name = textTwo.getText().toString();

        char[] stringOne = (user_name.replaceAll("\\s","")).toCharArray();
        char[] stringTwo = (crush_name.replaceAll("\\s","")).toCharArray();

        int strOne_length = stringOne.length;
        int strTwo_length = stringTwo.length;
        int result_no = 0;

        if(TextUtils.isEmpty(user_name)){
            textOne.setError("Please enter your name");

        }
        else if(TextUtils.isEmpty(crush_name)){
            textTwo.setError("Please enter your crush\'s name");
        }
        else {

            //get the common characters
            for(int i = 0; i < stringOne.length; i++){
                for(int j = 0; j < stringTwo.length; j++){
                    if(stringOne[i] == stringTwo[j] && stringOne[i] != '-' && stringTwo[j] != '-'){
                        //replace the strings and subtract from their length
                        stringOne[i] = '-';
                        stringTwo[j] = '-';
                        strOne_length--;
                        strTwo_length--;
                    }
                }
            }

            result_no = strOne_length + strTwo_length;
            //int xL = result_no;
            //while the count is greater than 6, keep subtracting
            while (result_no > 6) {
                result_no -= 6;
            }

            //set the match result to the corresponding value of the count
            String match = user_name.toUpperCase() + " and " + crush_name.toUpperCase() +  " will be " + results.get(result_no);

//            Log.d("h", "hee " + stringOne.length);
//            Log.d("hh", "heee " + stringTwo.length);
//            Log.d("hhh", "heeee" + xL);

            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra(EXTRA_MESSAGE, match);
            startActivity(intent);
        }

    }
}
