package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorder.Data.TestFoodsList;
import com.example.foodorder.Models.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private ArrayList<CheckBox> foodCheckBoxes;
	private int checkBoxIndex = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		foodCheckBoxes = new ArrayList<>();
		loadFoods();
	}

	private void loadFoods(){
		LinearLayout list = findViewById(R.id.foodsList);
		for (Food food: TestFoodsList.GetFoodsList()) {
			View listItem = createListItem(food);
			list.addView(listItem);
			foodCheckBoxes.add((CheckBox) ((ViewGroup) listItem).getChildAt(checkBoxIndex));
		}
	}

	private View createListItem(Food food){
		LinearLayout item = new LinearLayout(this);

		LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(
			LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
			LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

		item.setOrientation(LinearLayout.HORIZONTAL);

		ImageView itemImage = new ImageView(this);
		itemImage.setLayoutParams(params);
		Picasso.get()
				.load(food.GetImageUrl())
				.resize(200, 200)
				.centerCrop()
				.into(itemImage);
		item.addView(itemImage);

		TextView itemName = new TextView(this);
		itemName.setText(food.GetName());
		itemName.setLayoutParams(params);
		item.addView(itemName);

		CheckBox itemCheckBox = new CheckBox(this);
		itemCheckBox.setLayoutParams(params);
		item.addView(itemCheckBox, checkBoxIndex);

		return item;
	}
}