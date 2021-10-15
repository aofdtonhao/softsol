package com.entities;

public class Treetop {

	private Area area;

	public Area getArea() {
		if (area == null)
			area = new Area();
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
