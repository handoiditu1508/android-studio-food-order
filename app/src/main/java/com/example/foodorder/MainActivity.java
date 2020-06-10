package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
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

	final private int REQUEST_SEND_SMS = 123;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
		}

		foodCheckBoxes = new ArrayList<>();
		loadFoods();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
		switch (requestCode){
			case REQUEST_SEND_SMS:
				if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
					Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
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
		itemImage.setTag(food);
		Picasso.get()
				.load(food.GetImageUrl())
				.resize(200, 200)
				.centerCrop()
				.into(itemImage);
		itemImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleImageClick(v);
			}
		});
		item.addView(itemImage);

		TextView itemName = new TextView(this);
		itemName.setText(food.GetName());
		itemName.setLayoutParams(params);
		itemName.setTag(food);
		itemName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleNameClick(v);
			}
		});
		item.addView(itemName);

		CheckBox itemCheckBox = new CheckBox(this);
		itemCheckBox.setLayoutParams(params);
		item.addView(itemCheckBox, checkBoxIndex);

		return item;
	}

	private void handleImageClick(View v){
		Food f = (Food)v.getTag();

		// Create a Uri from an intent string. Use the result to create an Intent.
		Uri gmmIntentUri = Uri.parse(f.GetMapUrl());

		// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		// Make the Intent explicit by setting the Google Maps package
		mapIntent.setPackage("com.google.android.apps.maps");

		// Attempt to start an activity that can handle the Intent
		if (mapIntent.resolveActivity(getPackageManager()) != null) {
			startActivity(mapIntent);
		}
	}

	private void handleNameClick(View v){
		Food f = (Food)v.getTag();

		Intent i = new Intent("com.example.foodorder.FoodDetailActivity");
		i.putExtra("website_url", f.GetWebsiteUrl());
		startActivity(i);
	}

	private void sendSMS(String phoneNumber, String message){
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	public void handleOrderBtnClick(View v){
		String message = "";
		for (int i=0;i<foodCheckBoxes.size();i++){
			CheckBox checkBox = foodCheckBoxes.get(i);
			if(checkBox.isChecked()){
				message+=TestFoodsList.GetFoodsList().get(i).GetName()+"\n";
			}
		}
		message = message.trim();
		if(message == null || message.isEmpty())
			return;

		TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);

		if (ContextCompat.checkSelfPermission( this, Manifest.permission.READ_PHONE_STATE ) != PackageManager.PERMISSION_GRANTED )
		{
			ActivityCompat.requestPermissions(
					this,
					new String [] { android.Manifest.permission.READ_PHONE_STATE },
					1
			);

		}

		String mPhoneNumber = tMgr.getLine1Number();
		sendSMS(mPhoneNumber,message);
	}
}