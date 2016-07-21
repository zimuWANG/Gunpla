
package com.example.android.gunpla;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
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

    int LEDnumberY = 0;
    int LEDnumberW = 0;

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

        //Figure out if the user wants rx782
        CheckBox rx782CreamCheckBox = (CheckBox) findViewById(R.id.rx782_checkbox);
        boolean rx782 = rx782CreamCheckBox.isChecked();
        Log.v("MainActivity", "Has rx782:" + rx782);

        //Figure out if the user wants r10
        CheckBox r10CheckBox = (CheckBox) findViewById(R.id.r10_checkbox);
        boolean r10 = r10CheckBox.isChecked();
        Log.v("MainActivity", "R10:" + r10);

        //Figure out if the user wants gp01
        CheckBox gp01CheckBox = (CheckBox) findViewById(R.id.gp01_checkbox);
        boolean gp01 = gp01CheckBox.isChecked();
        Log.v("MainActivity", "GP01:" + gp01);

        //Figure out if the user wants d2
        CheckBox d2CheckBox = (CheckBox) findViewById(R.id.d2_checkbox);
        boolean d2 = d2CheckBox.isChecked();
        Log.v("MainActivity", "D2:" + d2);


        display1(LEDnumberY);
        display2(LEDnumberW);
        int number = Quantity(rx782, r10, gp01, d2);
        int totalCost = number * 50 + LEDnumberY * 10 + LEDnumberW * 10;
        String OrderName = "Name: " + name;
        String gunplaOne = "\nChoose RX-78-2 GUNDAM: " + rx782;
        String gunplaTwo = "\nChoose ZETA GUNDAM: " + r10;
        String gunplaThree = "\nChoose GUNDAM GP01: " + gp01;
        String gunplaFour = "\nChoose AILE STRIKE GUNDAM: " + d2;
        String quantity = "\nGunpla's quantities: " + number;
        String total = "\ntotal: $" + totalCost;


        displayMessage(OrderName + gunplaOne + gunplaTwo + gunplaThree + gunplaFour + quantity + total + "\nThank you");
    }


    public void increment1(View view) {
        if (LEDnumberY == 100) {
            Toast.makeText(this, "You cannot have more than 100 LEDs", Toast.LENGTH_SHORT).show();
            return;
        }
        LEDnumberY = LEDnumberY + 1;
        display1(LEDnumberY);
    }

    public void decrement1(View view) {
        if (LEDnumberY <= 0) {
            Toast.makeText(this, "You cannot have less than 0 LEDs", Toast.LENGTH_SHORT).show();
            return;
        }
        LEDnumberY = LEDnumberY - 1;
        display1(LEDnumberY);
    }


    public void increment2(View view) {
        if (LEDnumberW == 100) {
            Toast.makeText(this, "You cannot have more than 100 LEDs", Toast.LENGTH_SHORT).show();
            return;
        }
        LEDnumberW = LEDnumberW + 1;
        display2(LEDnumberW);
    }

    public void decrement2(View view) {
        if (LEDnumberW <= 0) {
            Toast.makeText(this, "You cannot have less than 0 LEDs", Toast.LENGTH_SHORT).show();
            return;
        }
        LEDnumberW = LEDnumberW - 1;
        display2(LEDnumberW);
    }


    public int Quantity(boolean a, boolean b, boolean c, boolean d) {

        int number = 0;

        if (a) {
            number = number + 1;
        }

        if (b) {
            number = number + 1;
        }

        if (c) {
            number = number + 1;
        }

        if (d) {
            number = number + 1;
        }

        return number;
    }

    public void sendEmail(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Gunpla order for" + name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    public void rx78(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void r10(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        Intent myIntent = new Intent(MainActivity.this, Main22Activity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void gp01(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        Intent myIntent = new Intent(MainActivity.this, Main23Activity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void d2(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        Intent myIntent = new Intent(MainActivity.this, Main24Activity.class);
        MainActivity.this.startActivity(myIntent);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display1(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.ledy_text_view);
        quantityTextView.setText("" + number);
    }

    private void display2(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.ledw_text_view);
        quantityTextView.setText("" + number);
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

        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (hasChocolate) {
            basePrice = basePrice + 2;
        }

        return basePrice;
    }


}