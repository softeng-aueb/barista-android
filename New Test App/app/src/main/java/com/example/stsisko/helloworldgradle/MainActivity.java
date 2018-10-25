package com.example.stsisko.helloworldgradle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * InteliJ Bug => AppCompactActivity cannot be resolved
 *  Solution: File -> Invalidate Caches/Restart
 *  Source: https://stackoverflow.com/questions/29199891/cannot-resolve-symbol-appcompatactivity
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String ERROR_MESSAGE = "The Field cannot be empty!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendMessage(View view){
       String input = ((EditText) findViewById(R.id.editText3)).getText().toString();
        ((TextView)findViewById(R.id.textView2)).setText(input.toUpperCase());

    }

    public void submitInout(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText text = (EditText) findViewById(R.id.editText3);
        String message = text.getText().toString();
        if(message.isEmpty()){ // check if inout is empty
            //disply error message on same activity
            ((TextView)findViewById(R.id.textView2)).setText(ERROR_MESSAGE);
        }
        else{
            intent.putExtra(EXTRA_MESSAGE, message); // pass given input to the other activity
            startActivity(intent);
        }

    }
}
