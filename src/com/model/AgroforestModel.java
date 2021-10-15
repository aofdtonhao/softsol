package com.model;

import java.util.ArrayList;
import java.util.List;

import com.entities.Agroforest;

import android.content.ContentValues;
import android.database.Cursor;

public class AgroforestModel {

	private static AgroforestModel instance;

	public static AgroforestModel getInstance() {
		if (instance == null) {
			instance = new AgroforestModel();
		}
		return instance;
	}

	public static final String TABLE = "agroforest";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String SCRIPT_DB_DELETE = "DROP TABLE IF EXISTS "
			+ TABLE;
	public static final String SCRIPT_DB_CREATE = "create table " + TABLE + "("
			+ ID + " integer primary key autoincrement," + NAME
			+ " text not null)";

	public long insert(Agroforest agroforest) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(NAME, agroforest.getName());
		return Model.getDatabase().insert(TABLE, null, contentValues);
	}

	public long update(Agroforest agroforest) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(NAME, agroforest.getName());
		return Model.getDatabase().update(TABLE, contentValues, ID + " = ?",
				new String[] { String.valueOf(agroforest.getId()) });
	}

	public int delete(int id) {
		return Model.getDatabase().delete(TABLE, ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	public Agroforest findByName(String name) {
		String[] columns = new String[] { ID, NAME };
		String[] args = new String[] { name };

		Cursor cursor = Model.getDatabase().query(TABLE, columns,
				NAME + " = ?", args, null, null, null);

		cursor.moveToFirst();
		Agroforest agroforest = createAgroforest(cursor);
		cursor.close();
		return agroforest;
	}

	public List<Agroforest> findAll() {
		List<Agroforest> agroforestList = new ArrayList<Agroforest>();
		String[] columns = new String[] { NAME };

		Cursor cursor = Model.getDatabase().query(TABLE, columns, null, null,
				null, null, NAME);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Agroforest agroforest = new Agroforest();
			agroforest.setName(cursor.getString(0));
			agroforestList.add(agroforest);
			cursor.moveToNext();
		}
		cursor.close();
		return agroforestList;
	}

	private Agroforest createAgroforest(Cursor cursor) {
		Agroforest agroforest = new Agroforest();
		agroforest.setId(cursor.getInt(0));
		agroforest.setName(cursor.getString(1));
		return agroforest;
	}

}
