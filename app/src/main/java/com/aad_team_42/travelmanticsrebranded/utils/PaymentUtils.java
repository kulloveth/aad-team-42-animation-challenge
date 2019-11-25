package com.aad_team_42.travelmanticsrebranded.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.aad_team_42.travelmanticsrebranded.views.activities.DetailActivity;
import com.aad_team_42.travelmanticsrebranded.views.activities.PaymentActivity;

import java.util.Calendar;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class PaymentUtils {

    public static Card card;

    public static void makePayment(final Context context) {
        Charge charge = new Charge();
        charge.setCard(card);
        charge.setEmail("kulloveth@yahoo.com");
        charge.setAmount(100);
        charge.setReference("ChargedFromAndroid_" + Calendar.getInstance().getTimeInMillis());

        PaystackSdk.chargeCard((Activity) context, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                String paymentReference = transaction.getReference();
                Toast.makeText(context, "Transaction Successful! payment reference: "
                        + paymentReference, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeValidate(Transaction transaction) {


            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                Toast.makeText(context, "error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
