package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class FoodDetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_detail);

		String foodUrl = getIntent().getStringExtra("website_url");

		WebView webView = findViewById(R.id.foodDetailWebView);
		webView.loadUrl(foodUrl);
	}
}
