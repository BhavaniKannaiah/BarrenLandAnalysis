package com.target.barren.land;

public class Axis {
	private Integer x;
	private Integer y;
	private boolean isVisited =false;
	private boolean isBarren;
	private String marked = new String();

	public Axis() {
		
	}

	public Axis(Integer x, Integer y) {
		this.x =x;
		this.y =y;
		
	}
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
		if(isVisited) {
			marked="*";
			
		}
	}

	public boolean isBarren() {
		return isBarren;
	}

	public void setBarren(boolean isBarren) {
		this.isBarren = isBarren;
	}
	
	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}
	

}
