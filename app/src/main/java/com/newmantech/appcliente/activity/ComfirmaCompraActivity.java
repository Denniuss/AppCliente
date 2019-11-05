package com.newmantech.appcliente.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.newmantech.appcliente.R;
import com.newmantech.appcliente.jsonEntityObjects.ServerObject;
import com.newmantech.appcliente.network.GsonRequest;
import com.newmantech.appcliente.network.VolleySingleton;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ComfirmaCompraActivity extends AppCompatActivity {

    private static final String TAG = ComfirmaCompraActivity.class.getSimpleName();

    private static final int MY_SOCKET_TIMEOUT_MS = 5000;

    private static final String SERVER_PATH = "Path_to_Server_To_Store_Token";

    private double totalCostPrice;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId("ENTER PAY PAL CLIENT ID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirma_compra);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTitle(getString(R.string.pay_with_paypal));

        totalCostPrice = getIntent().getExtras().getDouble("TOTAL_PRICE");
        Log.d(TAG, "Price " + totalCostPrice);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        Button payPalButton = (Button)findViewById(R.id.pay_pal_button);
        assert payPalButton != null;
        payPalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializePayPalPayment();
            }
        });
    }

    private void initializePayPalPayment(){
        Log.d(TAG, "initializePayPalPayment totalCostPrice " + totalCostPrice);

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(totalCostPrice)), "USD", "Piscos Personalizados", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, 0);
    }


    private void sendPaymentVerificationToServer(String id, String state){
        Map<String, String> params = new HashMap<String,String>();
        params.put("PAYMENT_ID", id);
        params.put("PAYMENT_STATE", state);

        GsonRequest<ServerObject> serverRequest = new GsonRequest<ServerObject>(
                Request.Method.POST,
                SERVER_PATH,
                ServerObject.class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(ComfirmaCompraActivity.this).addToRequestQueue(serverRequest);
    }

    private Response.Listener<ServerObject> createRequestSuccessListener() {
        return new Response.Listener<ServerObject>() {
            @Override
            public void onResponse(ServerObject response) {
                try {
                    Log.d(TAG, "Json Response " + response.getSuccess());
                    if(!TextUtils.isEmpty(response.getSuccess())){
                        Toast.makeText(ComfirmaCompraActivity.this, getString(R.string.successful_payment), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ComfirmaCompraActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

}
