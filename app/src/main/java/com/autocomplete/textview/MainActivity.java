package com.autocomplete.textview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.customautocomplete.textview.CustomAutoCompleteTextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		final CustomAutoCompleteTextView view = (CustomAutoCompleteTextView) findViewById(R.id.view);
		view.setItemClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				view.toggleSuggestionVisibility(false);
			}
		});

		ArrayList<String> suggestions = new ArrayList<>();
		suggestions.add("cherry");
		suggestions.add("choir");
		suggestions.add("church");
		suggestions.add("cherry pie");
		suggestions.add("check");
		suggestions.add("cherry glaze");
		suggestions.add("cherry gateau");

		view.setSuggestions(suggestions);    }
}
