package com.example.foodorder.Models;

public class Food {
	private String name;
	private double price;
	private String websiteUrl;
	private String mapUrl;
	private String imageUrl;

	public Food(String name, double price, String websiteUrl, String mapUrl, String imageUrl){
		this.name = name;
		this.price = price;
		this.websiteUrl = websiteUrl;
		this.mapUrl = mapUrl;
		this.imageUrl = imageUrl;
	}

	public String GetName(){
		return name;
	}

	public double GetPrice(){
		return price;
	}

	public String GetWebsiteUrl(){
		return websiteUrl;
	}

	public String GetMapUrl(){
		return mapUrl;
	}

	public String GetImageUrl(){
		return imageUrl;
	}
}