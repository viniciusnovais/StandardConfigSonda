package br.com.pdasolucoes.standardconfig.utils;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class Service extends IntentService {

    public static final String ACTION = "br.com.pdasolucoes.standardconfig.utis.Service";
    /**
     * @deprecated
     */
    public Service() {
        super("service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String val = null;
        if (intent != null) {
            val = intent.getStringExtra("token");
        }
        Intent in = new Intent(ACTION);
        in.putExtra("resultCode", Activity.RESULT_OK);
        in.putExtra("resultValue", val);
        MyApplication.getInstance().sendBroadcast(in);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
