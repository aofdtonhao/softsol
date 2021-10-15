package com.application;

import java.util.ArrayList;
import java.util.List;

import com.entities.Culture;
import com.model.CultureModel;

public class CultureApplication {

	private static CultureApplication instance;

	public static CultureApplication getInstance() {
		if (instance == null) {
			instance = new CultureApplication();
		}
		return instance;
	}

	public void add(ArrayList<String> textValues) {
		Culture culture = buildCultureWithTextValues(textValues);
		CultureModel.getInstance().insert(culture);
	}

	public List<String> retrieveAll() {
		List<Culture> cultureList = CultureModel.getInstance().findAll();
		List<String> cultureNameList = new ArrayList<String>();

		for (Culture culture : cultureList) {
			cultureNameList.add(culture.getName());
		}
		return cultureNameList;
	}

	public List<Culture> retrieveByType(String type){
	    return CultureModel.getInstance().findAllByType(type);
	}
	
	public Culture retrieveByName(String name) {
		return CultureModel.getInstance().findByName(name);
	}

	public void update(int id, ArrayList<String> textValues) {
		Culture culture = buildCultureWithTextValues(textValues);
		culture.setId(id);
		CultureModel.getInstance().update(culture);
	}

	public int delete(int id) {
		return CultureModel.getInstance().delete(id);
	}

	private Culture buildCultureWithTextValues(ArrayList<String> textValues) {
		Culture culture = new Culture();
		culture.setName(textValues.get(0));
		culture.getArea().setHeight(Integer.parseInt(textValues.get(1)));
		culture.getTreetop().getArea().setWidth(Integer.parseInt(textValues.get(2)));
		culture.getTreetop().getArea().setHeight(Integer.parseInt(textValues.get(3)));
		culture.setType(textValues.get(4));
		return culture;
	}

}
