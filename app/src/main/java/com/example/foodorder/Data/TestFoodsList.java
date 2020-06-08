package com.example.foodorder.Data;

import com.example.foodorder.Models.Food;

import java.util.ArrayList;

public class TestFoodsList {

	private ArrayList<Food> foods;

	private TestFoodsList foodsList;

	private TestFoodsList(){
		foods = new ArrayList<Food>();
		foods.add(new Food(
			"COMBO GÀ RÁN A",
			79.000,
			"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/100/combo-ga-ran-a.html",
			"geo:37.7749,-122.4194",
			"https://kfcvietnam.com.vn/uploads/combo/b09860e31866521c22705711916cc402.jpg")
		);
		foods.add(new Food(
				"COMBO GÀ RÁN B",
				79.000,
				"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/102/combo-ga-ran-b.html",
				"geo:37.7749,-122.4194",
				"https://kfcvietnam.com.vn/uploads/combo/7d36d8d380315c169ba830b0b5b4c26d.jpg")
		);
		foods.add(new Food(
				"COMBO GÀ RÁN C",
				85.000,
				"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/104/combo-ga-ran-c.html",
				"geo:37.7749,-122.4194",
				"https://kfcvietnam.com.vn/uploads/combo/c94db21b4ac5ab7333300f3f7e78671e.jpg")
		);
		foods.add(new Food(
				"COMBO GÀ RÁN D",
				89.000,
				"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/105/combo-ga-ran-d.html",
				"geo:37.7749,-122.4194",
				"https://kfcvietnam.com.vn/uploads/combo/f7e98746a52f1e24dbbe663be0ade3e0.jpg")
		);
		foods.add(new Food(
				"COMBO CƠM A",
				69.000,
				"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/106/combo-com-a.html",
				"geo:37.7749,-122.4194",
				"https://kfcvietnam.com.vn/uploads/combo/a8ef8a8b23927a56bbfd9a9884416c9b.jpg")
		);
		foods.add(new Food(
				"COMBO CƠM B",
				89.000,
				"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/107/combo-com-b.html",
				"geo:37.7749,-122.4194",
				"https://kfcvietnam.com.vn/uploads/combo/c72217be125e9ff11818a7cdce0b0a06.jpg")
		);
		foods.add(new Food(
				"COMBO CƠM C",
				95.000,
				"https://kfcvietnam.com.vn/vi/chi-tiet-thuc-don/109/combo-com-c.html",
				"geo:37.7749,-122.4194",
				"https://kfcvietnam.com.vn/uploads/combo/e72b0f625bc005513f52779da65c37df.jpg")
		);
	}

	public ArrayList<Food> GetFoodsList(){
		if(foodsList == null){
			foodsList = new TestFoodsList();
		}
		return foodsList.foods;
	}
}
