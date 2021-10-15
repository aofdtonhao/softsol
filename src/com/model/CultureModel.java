package com.model;

import java.util.ArrayList;
import java.util.List;

import com.entities.Culture;

import android.content.ContentValues;
import android.database.Cursor;

public class CultureModel {

	private static CultureModel instance;

	public static CultureModel getInstance() {
		if (instance == null) {
			instance = new CultureModel();
		}
		return instance;
	}

	public static final String TABLE = "culture";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String HEIGHT = "height";
	public static final String TREETOP_WIDTH = "treetopWidth";
	public static final String TREETOP_HEIGHT = "treetopHeight";
	public static final String TYPE = "type";
	public static final String SCRIPT_DB_DELETE = "DROP TABLE IF EXISTS "
			+ TABLE;
	public static final String SCRIPT_DB_CREATE = "create table " + TABLE + "("
			+ ID + " integer primary key autoincrement," + NAME
			+ " text not null," + HEIGHT + " integer not null," + TREETOP_WIDTH
			+ " integer not null," + TREETOP_HEIGHT + " integer not null, " + TYPE + " text not null);";

	public long insert(Culture culture) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(NAME, culture.getName());
		contentValues.put(HEIGHT, culture.getArea().getHeight());
		contentValues.put(TREETOP_WIDTH, culture.getTreetop().getArea().getWidth());
		contentValues.put(TREETOP_HEIGHT, culture.getTreetop().getArea().getHeight());
		contentValues.put(TYPE, culture.getType());
		return Model.getDatabase().insert(TABLE, null, contentValues);
	}

	public long update(Culture culture) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(NAME, culture.getName());
		contentValues.put(HEIGHT, culture.getArea().getHeight());
		contentValues.put(TREETOP_WIDTH, culture.getTreetop().getArea().getWidth());
        contentValues.put(TREETOP_HEIGHT, culture.getTreetop().getArea().getHeight());
		contentValues.put(TYPE, culture.getType());
		return Model.getDatabase().update(TABLE, contentValues, ID + " = ?",
				new String[] { String.valueOf(culture.getId()) });
	}

	public int delete(int id) {
		return Model.getDatabase().delete(TABLE, ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	public Culture findByName(String name) {
		String[] columns = new String[] { ID, NAME, HEIGHT, TREETOP_WIDTH,
				TREETOP_HEIGHT, TYPE };
		String[] args = new String[] { name };

		Cursor cursor = Model.getDatabase().query(TABLE, columns,
				NAME + " = ?", args, null, null, null);

		cursor.moveToFirst();
		Culture culture = createCulture(cursor);
		cursor.close();
		return culture;
	}

	public List<Culture> findAll() {
		List<Culture> cultureList = new ArrayList<Culture>();
		String[] columns = new String[] { NAME };

		Cursor cursor = Model.getDatabase().query(TABLE, columns, null, null,
				null, null, NAME);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Culture culture = new Culture();
			culture.setName(cursor.getString(0));
			cultureList.add(culture);
			cursor.moveToNext();
		}
		cursor.close();
		return cultureList;
	}
	
	/* String type = native or economic*/
	public List<Culture> findAllByType(String type){
		List<Culture> cultureList = new ArrayList<Culture>();
		
		String[] columns = new String[] { ID, NAME, HEIGHT, TREETOP_WIDTH,
				TREETOP_HEIGHT, TYPE };
		String[] args = new String[] { type };

		Cursor cursor = Model.getDatabase().query(TABLE, columns,
				TYPE + " = ?", args, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Culture culture = createCulture(cursor);
			cultureList.add(culture);
			cursor.moveToNext();
		}
		cursor.close();
		
		return cultureList;
	}
	

	private Culture createCulture(Cursor cursor) {
		Culture culture = new Culture();
		culture.setId(cursor.getInt(0));
		culture.setName(cursor.getString(1));
		culture.getArea().setHeight(cursor.getInt(2));
		culture.getTreetop().getArea().setWidth(cursor.getInt(3));
		culture.getTreetop().getArea().setHeight(cursor.getInt(4));
		culture.setType(cursor.getString(5));
		return culture;
	}

}
