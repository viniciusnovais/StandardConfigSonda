package br.com.pdasolucoes.standardconfig;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.network.TestRequest;
import br.com.pdasolucoes.standardconfig.network.UpdateTokenNotificationRequest;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationHelper.startActivity(ConfigurationActivity.class);
            }
        });


        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkManager.sendRequest(new UpdateTokenNotificationRequest(
                        "APA91bGAhGhuGZFnpMtxswOOcQxhUKoWTeweBT73VIqevbNsYL6e3FIa9iV_K9pHc0r6pT_4UFALKHjGwVR0L0fzbT9Z0V1kqGRudSNtfhHpTrLSufDvrzEJ18NtwurM2eRZw8uuWuPI"
                ));
            }
        });
    }

}
