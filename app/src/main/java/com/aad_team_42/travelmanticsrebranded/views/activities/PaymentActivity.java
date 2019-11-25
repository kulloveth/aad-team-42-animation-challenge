package com.aad_team_42.travelmanticsrebranded.views.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aad_team_42.travelmanticsrebranded.R;
import com.aad_team_42.travelmanticsrebranded.utils.PaymentUtils;

import java.util.Calendar;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class PaymentActivity extends AppCompatActivity {
    EditText cardNo, expiryMonth, expiryYear, cvv;
    String getCardNo, getCvv;
    int getExpiryMonth, getExpiryYear;
    //Charge charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        PaystackSdk.initialize(getApplicationContext());
        cardNo = findViewById(R.id.card_no);
        expiryMonth = findViewById(R.id.expiry_month);
        expiryYear = findViewById(R.id.expiry_year);
        cvv = findViewById(R.id.cvv_no);
        Button button = findViewById(R.id.validate);
        button.setOnClickListener(view -> {
            checkCard();
        });

    }

    public void checkCard() {
        try {
            getCardNo = cardNo.getText().toString().trim();
            getExpiryMonth = Integer.parseInt(expiryMonth.getText().toString().trim());
            getExpiryYear = Integer.parseInt(expiryYear.getText().toString().trim());
            getCvv = cvv.getText().toString().trim();

            PaymentUtils.card = new Card(getCardNo, getExpiryMonth, getExpiryYear, getCvv);

            if (PaymentUtils.card.isValid()) {
                Toast.makeText(PaymentActivity.this, "Card is Valid", Toast.LENGTH_LONG).show();
                PaymentUtils.makePayment(PaymentActivity.this);

            } else {
                Toast.makeText(PaymentActivity.this, "Card not Valid", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
