package com.azus.lesson3a;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int displayQuantity;
    int displayPrice;
    int calculatePrice;
    String displayMessage;
    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment (View view){

        quantity = quantity + 1;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }
    public void decrement (View view){
        quantity = quantity - 1;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }





    public void submitOrder (View view){

     EditText nameField = (EditText) findViewById(R.id.name_field);
     String name = nameField.getText().toString();

     CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.coklat_checkbox);
     boolean hasChocolate = chocolateCheckBox.isChecked();

     CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
     boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


     int harga = calculatePrice(hasWhippedCream, hasChocolate);
     String priceMessage = createOrderSummary(name, harga, hasWhippedCream, hasChocolate);

     Intent intent = new Intent(Intent.ACTION_SENDTO);
     intent.setData(Uri.parse("mailto:"));
     intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
     intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
     if (intent.resolveActivity(getPackageManager())!= null){
         startActivity(intent);
     }



 }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;

        if(addWhippedCream){
            basePrice = basePrice +1;
        }
        if(addChocolate){
            basePrice = basePrice +2;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int harga, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Nama : " +name;
        priceMessage +="\nAdd whipped cream?" +addWhippedCream;
        priceMessage +="\nAdd chocolate?" +addChocolate;
        priceMessage +="\nQuantity : " +quantity;
        priceMessage +="\nTotal : $" +harga;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

    public void displayQuantity(int jumlah) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + jumlah);
    }




}
