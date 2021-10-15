package com.entities;

import java.util.Date;

public class Culture {
	
	private int id;
	private String name;
	private boolean inShadow;
	private int state;
	private Date flowering;
	private Date fructification;
	private Area area;
	private Treetop treetop;
	private String type;
	public static final int NONE = 0;
    public static final int BLOOMING = 1;
    public static final int FRUITING = 2;
    public static final String NATIVE_CULTURE = "native";
    public static final String ECONOMIC_CULTURE = "economic";
    
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isInShadow() {
		return inShadow;
	}
	
	public void setInShadow(boolean inShadow) {
		this.inShadow = inShadow;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public Date getFlowering() {
		return flowering;
	}
	
	public void setFlowering(Date flowering) {
		this.flowering = flowering;
	}
	
	public Date getFructification() {
		return fructification;
	}
	
	public void setFructification(Date fructification) {
		this.fructification = fructification;
	}
	
	public Area getArea() {
		if (area == null)
			area = new Area();
		return area;
	}
	
	public void setArea(Area area) {
		this.area = area;
	}
	
	public Treetop getTreetop() {
		if (treetop == null)
			treetop = new Treetop();
		return treetop;
	}
	
	public void setTreetop(Treetop treetop) {
		this.treetop = treetop;
	}
    
}
