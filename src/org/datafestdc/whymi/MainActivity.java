package org.datafestdc.whymi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends Activity {

	public static final String EMPTY_STRING = "";
	private static final int ECONOMIC_POSITION = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createReasonSpinner(R.id.primaryReasonSpinner, R.array.primaryReasonList);
		createReasonSpinner(R.id.secondaryReasonSpinner, R.array.secondaryReasonList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void saveFields(View view) {

	}

	public void resetAction(View view) {
		resetResettableViews();
	}
	
	private void resetResettableViews() {
		RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.radioGender);
		genderRadioGroup.clearCheck();
		
		int[] resettableEditTextIds = {R.id.ageText, R.id.countryText};
		for (int textId : resettableEditTextIds) {
			EditText editText = (EditText) findViewById(textId);
			editText.setText(EMPTY_STRING);
		}
		
		int[] spinnerIds = {R.id.primaryReasonSpinner, R.id.secondaryReasonSpinner};
		for (int spinnerId : spinnerIds) {
			Spinner spinner = (Spinner) findViewById(spinnerId);
			boolean animate = true;
			spinner.setSelection(ECONOMIC_POSITION, animate);
		}
	}
	
	private void createReasonSpinner(int spinnerId, int reasonArrayId) {
		Spinner spinner = (Spinner) findViewById(spinnerId);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				reasonArrayId, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}
}
