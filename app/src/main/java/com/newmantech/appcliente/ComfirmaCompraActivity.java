package com.newmantech.appcliente;

import android.app.Activity;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newmantech.appcliente.jsonEntityObjects.PaymentResponseObject;
import com.newmantech.appcliente.jsonEntityObjects.ServerObject;
import com.newmantech.appcliente.network.GsonRequest;
import com.newmantech.appcliente.network.VolleySingleton;
import com.newmantech.appcliente.utils.Utilitario;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));
                    String jsonPaymentResponse = confirm.toJSONObject().toString(4);

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    PaymentResponseObject responseObject = gson.fromJson(jsonPaymentResponse, PaymentResponseObject.class);
                    if(responseObject != null){
                        String paymentId = responseObject.getResponse().getId();
                        String paymentState = responseObject.getResponse().getState();

                        Log.d(TAG, "Log payment id and state " + paymentId + " " + paymentState);

                        /*Registrar venta confirmada*/

                        CookieManager cookieManager = new CookieManager();
                        CookieHandler.setDefault(cookieManager);
                        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                        CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(getApplicationContext()), CookiePolicy.ACCEPT_ALL);

                        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                                .cookieJar(new JavaNetCookieJar(cookieHandler))
                                .build();
                        //client.newBuilder()
                        Log.i("DetalleActivity", "body: client " + client.toString());

                        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utilitario.baseUrlSWeb)
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ProductoService productoService = retrofit.create(ProductoService.class);

                        Call<BResult> lista = productoService.finalizarCompraPaypal(paymentId);
                        lista.enqueue(new Callback<BResult>() {
                            @Override
                            public void onResponse(Call<BResult> call, retrofit2.Response<BResult> response) {
                                Log.e("DetalleActivity ", "onResponse " + response.isSuccessful());

                                if(response.isSuccessful()) {

                                    //finish();

                                    BResult respuesta = response.body();

                                    if(respuesta!=null) {
                                        //Intent intent = new Intent(DetalleActivity.this, DireccionActivity.class);
                                        //startActivity(intent);
                                        if(respuesta.getEstado()==0){
                                            Toast.makeText(getApplicationContext(),
                                                    "Se registro la venta correctamente", Toast.LENGTH_SHORT).show();
                                        } else {
                                            //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(),
                                                    "Error al registrar la venta", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<BResult> call, Throwable t) {
                                Log.e("DetalleActivity ", "onFailure "+t.getMessage());
                            }
                        });






                        //send to your server for verification.
                        //sendPaymentVerificationToServer(paymentId, paymentState);
                        Intent comprarIntent = new Intent(ComfirmaCompraActivity.this, ListadoProductosNActivity.class);
                        startActivity(comprarIntent);
                        Toast.makeText(ComfirmaCompraActivity.this, getString(R.string.successful_payment), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
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
