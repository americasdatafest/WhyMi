package org.datafestdc.whymi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends Activity {

	public static final String NOT_SELECTED = "NotSelected";
	public static final String EMPTY_STRING = "";
	private static final int ECONOMIC_POSITION = 0;
	
	//must match activity_main.xml definition in radio group!
	private static final int GENDER_MALE_INDEX = 0;
	private static final int GENDER_FEMALE_INDEX = 1;

	private SurveyToSqlLiteHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new SurveyToSqlLiteHelper(this);
		setContentView(R.layout.activity_main);

		createReasonSpinner(R.id.primaryReasonSpinner,
				R.array.primaryReasonList);
		createReasonSpinner(R.id.secondaryReasonSpinner,
				R.array.secondaryReasonList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void saveAction(View view) {
		String id = UUID.randomUUID().toString();
		String surveyLocation = getEditTextAsString(R.id.surveyLocationText);
		String gender = getGenderRadioButtonValue();
		String age = getEditTextAsString(R.id.ageText);
		String country = getEditTextAsString(R.id.countryText);
		String primaryReason = getSpinnerValue(R.id.primaryReasonSpinner);
		String secondaryReason = getSpinnerValue(R.id.secondaryReasonSpinner);
		Survey survey = new Survey(id, surveyLocation, gender, age, country,
				primaryReason, secondaryReason);
		dbHelper.addSurvey(survey);
	}

	public void resetAction(View view) {
		resetResettableViews();
	}

	private void resetResettableViews() {
		RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.radioGender);
		genderRadioGroup.clearCheck();

		int[] resettableEditTextIds = { R.id.ageText, R.id.countryText };
		for (int textId : resettableEditTextIds) {
			EditText editText = (EditText) findViewById(textId);
			editText.setText(EMPTY_STRING);
		}

		int[] spinnerIds = { R.id.primaryReasonSpinner,
				R.id.secondaryReasonSpinner };
		for (int spinnerId : spinnerIds) {
			Spinner spinner = (Spinner) findViewById(spinnerId);
			boolean animate = true;
			spinner.setSelection(ECONOMIC_POSITION, animate);
		}
	}

	private void createReasonSpinner(int spinnerId, int reasonArrayId) {
		Spinner spinner = (Spinner) findViewById(spinnerId);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, reasonArrayId, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}

	private String getEditTextAsString(int editTextId) {
		EditText editText = (EditText) findViewById(editTextId);
		return editText.getText().toString();
	}
	
	private String getGenderRadioButtonValue() {
		String gender = NOT_SELECTED;
		RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.radioGender);
		int checkedButtonId = genderRadioGroup.getCheckedRadioButtonId();
		View radioButton = genderRadioGroup.findViewById(checkedButtonId);
		int idx = genderRadioGroup.indexOfChild(radioButton);
		if (idx == GENDER_MALE_INDEX) {
			gender = "Male";
		} else if (idx == GENDER_FEMALE_INDEX) {
			gender = "Female";
		}
		return gender;
	}

	private String getSpinnerValue(int spinnerId) {
		Spinner spinner = (Spinner) findViewById(spinnerId);
		String spinnerValue = spinner.getSelectedItem().toString();
		return spinnerValue;
	}

}
