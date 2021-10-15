package com.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Model {

	public static final String DATABASE = "softsol.db";

	private static SQLiteHelper helper;

	public static void build(Context context) {
		if (helper == null) {
			context.openOrCreateDatabase(DATABASE,
					SQLiteDatabase.CREATE_IF_NECESSARY, null);
			helper = new SQLiteHelper(context, DATABASE,3);
		}
	}

	public static SQLiteDatabase getDatabase() {
		return helper.getWritableDatabase();
	}

}
