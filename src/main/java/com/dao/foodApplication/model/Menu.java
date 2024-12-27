package com.dao.foodApplication.model;

public class Menu {
	
	private int menuId;
	private int restaurantId;
	private String name;
	private String description;
	private int price;
	private boolean isAvailable;
	private String imgPath;
	
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
	
	public Menu() {
		super();
	}
	public Menu(int menuId, int restaurantId, String name, String description, int price, boolean isAvailable,
			String imgPath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgPath = imgPath;
	}
	
	
	@Override
	public String toString() {
		return  menuId + "    " + restaurantId + "   " + name + "    "
				+ description + "   " + price + "    " + isAvailable + "    " + imgPath;
	}
	
	
	

}
