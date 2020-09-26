package com.example.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.testingpoc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    ListView list;
    CustomListAdapter adapter;
    ArrayList<PaymentModel> modelList;
    ArrayList<String> paymentHotKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentFCMToken();
        paymentHotKeys.add("credited".toUpperCase());
        paymentHotKeys.add("received".toUpperCase());
        paymentHotKeys.add("transferred".toUpperCase());
        paymentHotKeys.add("paytm".toUpperCase());
        paymentHotKeys.add("axis bank".toUpperCase());
        modelList = new ArrayList<PaymentModel>();
        adapter = new CustomListAdapter(getApplicationContext(), modelList);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
        getSupportActionBar().setTitle("Home");
      /*  Intent intent = new Intent(
                "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        changeMenuIconColor(menu, R.id.action_refresh, R.color.color_setting);
        changeMenuIconColor(menu, R.id.action_settings, R.color.color_setting);
        changeMenuIconColor(menu, R.id.action_enable_send_payment_notification, R.color.color_setting);
        changeMenuIconColor(menu, R.id.action_disable_send_payment_notification, R.color.color_setting);
        return true;
    }

    private void changeMenuIconColor(Menu menu, int menuId, @ColorRes int color) {
        Drawable drawable = menu.findItem(menuId).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, color));
        menu.findItem(menuId).setIcon(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent intent = new Intent(
                        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
                return true;
            }
            case R.id.action_refresh: {
               showToast("Refreshing");
                return true;
            }
            case R.id.action_enable_send_payment_notification: {
                enableSendNotification(true);
                showToast("Sending payment notifications enabled");
                return true;
            }
            case R.id.action_disable_send_payment_notification: {
                enableSendNotification(false);
                showToast("Sending payment notifications disabled");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BroadcastReceiver onNotice = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            // String pack = intent.getStringExtra("package");
            String sender = intent.getStringExtra("title");
            String message = intent.getStringExtra("text");
            if (sender == null)
                sender = "";
            if (message == null)
                message = "";
            //int id = intent.getIntExtra("icon",0);

            try {
/*                byte[] byteArray =intent.getByteArrayExtra("icon");
                Bitmap bmp = null;
                if(byteArray !=null) {
                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                }*/
                SimpleDateFormat sdf = new SimpleDateFormat(PaymentModel.dateFormat, Locale.US);
                if (!isPaymentMessage(message, sender))
                    return;
                PaymentModel model = new PaymentModel();
                model.setFrom(sender);
                model.setMessage(message);
                model.setTime(sdf.format(new Date()));
/*
                model.setImage(bmp);
*/

                if (modelList != null) {
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                } else {
                    modelList = new ArrayList<>();
                    modelList.add(model);
                    adapter = new CustomListAdapter(getApplicationContext(), modelList);
                    list = (ListView) findViewById(R.id.list);
                    list.setAdapter(adapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private boolean isPaymentMessage(String message, String sender) {
        if (TextUtils.isEmpty(message))
            message = "";
        if (TextUtils.isEmpty(sender))
            sender = "";
        message = message.toUpperCase();
        sender = sender.toUpperCase();
        boolean isPaymentMessage = false;
        for (String currHotKey : paymentHotKeys) {
            if (message.contains(currHotKey) || sender.contains(currHotKey)) {
                isPaymentMessage = true;
                break;
            }
        }
        return isPaymentMessage;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getCurrentFCMToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }
                    }
                });
    }

    private final String sharedPrefName = "MySharedPref";
    private final String keyIsNotificationEnabled = "IsNotificationEnabled";

    private void enableSendNotification(boolean enable) {
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean(keyIsNotificationEnabled, enable);
        myEdit.commit();
    }

    private boolean isSendNotificationEnabled() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
        return sharedPreferences.getBoolean(keyIsNotificationEnabled, false);
    }
}
