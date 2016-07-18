
package com.example.android.gunpla;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gunpla.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();

        //Figure out if the user wants whipped cream
        CheckBox whippedCreamCheckBox =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped cream:" + hasWhippedCream);

        //Figure out if the user wants chocolate
        CheckBox chocolateCheckBox =(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has chocolate:" + hasChocolate);


        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
        String priceMessage =   "Name: " + name +"\n" + "Add whipped cream? " + hasWhippedCream + "\nAdd chocolate? " + hasChocolate + "\nQuantity: " + numberOfCoffees  + "\n"+ "total " + "$ " + numberOfCoffees*calculatePrice(hasWhippedCream,hasChocolate) + "\n" + "Thank you";
        displayMessage(priceMessage);
    }



    public void increment(View view) {
        if (numberOfCoffees == 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees = numberOfCoffees + 1 ;
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        if (numberOfCoffees <= 1){
            Toast.makeText(this, "You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees = numberOfCoffees - 1 ;
        display(numberOfCoffees);

    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        int basePrice = 5;

        if(hasWhippedCream){
            basePrice = basePrice + 1;
        }

        if(hasChocolate){
            basePrice = basePrice +2;
        }

        return basePrice;
    }


}