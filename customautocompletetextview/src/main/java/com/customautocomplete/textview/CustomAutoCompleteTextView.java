package com.customautocomplete.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by Catalina on 24/04/2015.
 * Keyboard-like AutoCompleteTextView control
 */
public class CustomAutoCompleteTextView extends LinearLayout
{
	private ArrayList<String> suggestions;
	private LinearLayout container;
	 private OnClickListener itemClickListener;

	public CustomAutoCompleteTextView(Context context, ArrayList<String> suggestions, int resource)
	{
		super(context);
		this.suggestions = suggestions;
		initialize(context, resource);
	}

	public CustomAutoCompleteTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		suggestions = new ArrayList<>();
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomAutoCompleteTextView);
		int resourceId = a.getResourceId(R.styleable.CustomAutoCompleteTextView_resource, 0);
		a.recycle();
		initialize(context, resourceId);
	}

	private void initialize(Context context, final int resource)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		final View view = inflater.inflate(R.layout.layout_customautocompletetextview, this);

		final EditText editText = (EditText) view.findViewById(R.id.editText);
		final TextSwitcher textSwitcher1 = (TextSwitcher) view.findViewById(R.id.textSwitcher1);
		final TextSwitcher textSwitcher2 = (TextSwitcher) view.findViewById(R.id.textSwitcher2);
		final TextSwitcher textSwitcher3 = (TextSwitcher) view.findViewById(R.id.textSwitcher3);
		container = (LinearLayout) view.findViewById(R.id.container);

		ViewSwitcher.ViewFactory factory = new ViewSwitcher.ViewFactory()
		{
			public View makeView()
			{
				TextView textView = new TextView(view.getContext());
				textView.setGravity(Gravity.CENTER);
				textView.setSingleLine(true);
				textView.setBackgroundResource(resource);
				return textView;
			}
		};

		OnClickListener listener = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				editText.setText(((TextView) ((TextSwitcher) v).getCurrentView()).getText());
				editText.setSelection(editText.getText().length());
				itemClickListener.onClick(v);
			}
		};


		textSwitcher1.setInAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.enter));
		textSwitcher1.setOutAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.exit));
		textSwitcher1.setFactory(factory);
		textSwitcher1.setOnClickListener(listener);

		textSwitcher2.setInAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.enter));
		textSwitcher2.setOutAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.exit));
		textSwitcher2.setFactory(factory);
		textSwitcher2.setOnClickListener(listener);

		textSwitcher3.setInAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.enter));
		textSwitcher3.setOutAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.exit));
		textSwitcher3.setOnClickListener(listener);
		textSwitcher3.setFactory(factory);

		if (suggestions.size() >= 3)
		{
			textSwitcher3.setText(suggestions.get(2));
		}
		if (suggestions.size() >= 2)
		{
			textSwitcher2.setText(suggestions.get(1));
		}
		if (suggestions.size() >= 1)
		{
			textSwitcher1.setText(suggestions.get(0));
		}

		editText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{


			}

			@Override
			public void afterTextChanged(Editable s)
			{
				ArrayList<String> items = new ArrayList<>();
				for (String item : suggestions)
				{
					if (item.contains(editText.getText().toString()))
					{
						items.add(item);
					}
				}

				if (items.size() >= 3)
				{
					if (!((TextView) textSwitcher3.getCurrentView()).getText().equals(items.get(2)))
					{
						textSwitcher3.setText(items.get(2));
						textSwitcher3.setVisibility(View.VISIBLE);
					}
				}
				else
				{
					textSwitcher3.setText("");
					textSwitcher3.setVisibility(View.INVISIBLE);
				}
				if (items.size() >= 2)
				{
					if (!((TextView) textSwitcher2.getCurrentView()).getText().equals(items.get(1)))
					{
						textSwitcher2.setText(items.get(1));
						textSwitcher2.setVisibility(View.VISIBLE);
					}
				}
				else
				{
					textSwitcher2.setText("");
					textSwitcher2.setVisibility(View.INVISIBLE);
				}
				if (items.size() >= 1)
				{

					container.setVisibility(View.VISIBLE);
					if (!((TextView) textSwitcher1.getCurrentView()).getText().equals(items.get(0)))
					{
						textSwitcher1.setText(items.get(0));
					}
				}
				else
				{
					container.setVisibility(View.GONE);
					textSwitcher1.setText("");
				}

			}
		});

	}

	/**
	 * Sets the words to be used as suggestions
	 * @param suggestions Suggested words
	 */
	public void setSuggestions(ArrayList<String> suggestions)
	{
		this.suggestions = suggestions;
	}

	/**
	 * Changes the visibility of the word suggestions
	 * @param visible If true the suggestions will be visible otherwise they will be hidden
	 */
	public void toggleSuggestionVisibility(boolean visible)
	{
		if (visible)
		{
			container.setVisibility(VISIBLE);
		}
		else
		{
			container.setVisibility(GONE);
		}
	}

	public void setItemClickListener(OnClickListener itemClickListener)
	{
		this.itemClickListener = itemClickListener;
	}
}
