package org.datafestdc.whymi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SurveyToSqlLiteHelper extends SQLiteOpenHelper {

	// Name of our database to store surveys
	private static final String SURVEY_DATABASE_NAME = "survey";
	private static final int SURVEY_DATABASE_VERSION = 1;

	// Surveys table name
	private static final String SURVEYS_TABLE = "surveys";

	// Surveys table columns
	private static final String COL_ID = "id";
	private static final String COL_SURVEY_LOCATION = "surveyLocation";
	private static final String COL_GENDER = "gender";
	private static final String COL_AGE = "age";
	private static final String COL_COUNTRY = "country";
	private static final String COL_PRIMARY_REASON = "primaryReason";
	private static final String COL_SECONDARY_REASON = "secondaryReason";

	private static final String[] COLS = { COL_ID, COL_SURVEY_LOCATION,
			COL_GENDER, COL_AGE, COL_COUNTRY, COL_PRIMARY_REASON,
			COL_SECONDARY_REASON };
	private static final String[] COL_TYPES = { "INTEGER PRIMARY KEY", "TEXT",
			"TEXT", "INTEGER", "TEXT", "TEXT", "TEXT" };

	public SurveyToSqlLiteHelper(Context context) {
		super(context, SURVEY_DATABASE_NAME, null, SURVEY_DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create surveys table
		StringBuilder builder = new StringBuilder("CREATE TABLE ");
		builder.append(SURVEYS_TABLE);
		builder.append("(");
		for (int i = 0; i < COLS.length; i++) {
			String columnName = COLS[i];
			String columnType = COL_TYPES[i];
			builder.append(columnName);
			builder.append(" ");
			builder.append(columnType);
			if (i < COLS.length - 1) {
				builder.append(",");
			}
		}
		builder.append(")");
		db.execSQL(builder.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SURVEYS_TABLE);
		// Create tables again
		onCreate(db);
	}

	public void addSurvey(Survey survey) {
		String[] columnValues = { survey.getId(), survey.getSurveyLocation(),
				survey.getGender(), Integer.toString(survey.getAge()),
				survey.getCountry(), survey.getPrimaryReason(),
				survey.getSecondaryReason() };
		ContentValues values = new ContentValues();
		for (int i = 0; i < columnValues.length; i++) {
			String columnValue = columnValues[i];
			String columnName = COLS[i];
			values.put(columnName, columnValue);
		}
		SQLiteDatabase db = getWritableDatabase();
		String nullColumnHack = null;
		db.insert(SURVEYS_TABLE, nullColumnHack, values);
		db.close();
	}
}
