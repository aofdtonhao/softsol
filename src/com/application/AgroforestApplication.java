package com.application;

import java.util.ArrayList;
import java.util.List;

import com.entities.Agroforest;
import com.model.AgroforestModel;

public class AgroforestApplication {

	private static AgroforestApplication instance;

	public static AgroforestApplication getInstance() {
		if (instance == null) {
			instance = new AgroforestApplication();
		}
		return instance;
	}

	public void add(ArrayList<String> textValues) {
		Agroforest culture = buildCultureWithTextValues(textValues);
		AgroforestModel.getInstance().insert(culture);
	}

	public List<String> retrieveAll() {
		List<Agroforest> agroforestList = AgroforestModel.getInstance()
				.findAll();
		List<String> agroforestNameList = new ArrayList<String>();

		for (Agroforest agroforest : agroforestList) {
			agroforestNameList.add(agroforest.getName());
		}
		return agroforestNameList;
	}

	public Agroforest retrieveByName(String name) {
		return AgroforestModel.getInstance().findByName(name);
	}

	public void update(int id, ArrayList<String> textValues) {
		Agroforest agroforest = buildCultureWithTextValues(textValues);
		agroforest.setId(id);
		AgroforestModel.getInstance().update(agroforest);
	}

	public int delete(int id) {
		return AgroforestModel.getInstance().delete(id);
	}

	private Agroforest buildCultureWithTextValues(ArrayList<String> textValues) {
		Agroforest agroforest = new Agroforest();
		agroforest.setName(textValues.get(0));
		return agroforest;
	}

}
