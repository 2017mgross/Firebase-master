package com.mattattackapps.firebase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        // must always set before setContentView
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button);
        final EditText ET = (EditText) findViewById(R.id.editText);
        final EditText ET2 = (EditText) findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase ref = new Firebase("https://glaring-fire-1918.firebaseio.com");
                ref.authWithPassword(ET.getText().toString(), ET2.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(myIntent);
                        //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
        //final EditText ET = (EditText) findViewById(R.id.ET);
        //final EditText ET2 = (EditText) findViewById(R.id.ET2);
        //Button B1 = (Button) findViewById(R.id.button);
        Firebase.setAndroidContext(this);
        //Firebase.setAndroidContext(R.layout.activity_main);
        //Firebase rootRef = new Firebase("https://docs-examples.firebaseio.com/web/data");
        //Firebase ref = new Firebase("https://docs-examples.firebaseio.com/android/saving-data/fireblog");
    }
    @Override
    public void onClick(View V)
    {
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        //Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
        Firebase ref = new Firebase("https://glaring-fire-1918.firebaseio.com");
        //ref.authWithPassword("abcdef@gmail.com", "password", new Firebase.AuthResultHandler()
        EditText ET = (EditText) findViewById(R.id.editText);
        EditText ET2 = (EditText) findViewById(R.id.editText2);
        //System.out.print(ET.getText().toString());
        if(V==button)
        {
            ref.authWithPassword(ET.getText().toString(), ET2.getText().toString(), new Firebase.AuthResultHandler(){
                public void onAuthenticated(AuthData authData) {
                    System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    if(authData.getUid().equals("abcdef@gmail.com") && authData.getProvider().equals("password"))
                    {
                        System.out.println("success");
                        Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(myIntent);
                    }
                }
                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    // there was an error
                }
            });
        }
    }


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