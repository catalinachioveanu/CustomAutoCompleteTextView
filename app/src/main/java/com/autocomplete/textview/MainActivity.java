package com.autocomplete.textview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.customautocomplete.textview.CustomAutoCompleteTextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomAutoCompleteTextView view = (CustomAutoCompleteTextView)findViewById(R.id.view);
    }
}
