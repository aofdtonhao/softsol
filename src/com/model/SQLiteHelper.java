package com.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	public SQLiteHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CultureModel.SCRIPT_DB_CREATE);
		db.execSQL(AgroforestModel.SCRIPT_DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(CultureModel.SCRIPT_DB_DELETE);
		db.execSQL(AgroforestModel.SCRIPT_DB_DELETE);
		onCreate(db);
	}

}
