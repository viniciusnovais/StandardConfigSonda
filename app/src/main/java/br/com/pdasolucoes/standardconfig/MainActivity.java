package br.com.pdasolucoes.standardconfig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.network.TestRequest;
import br.com.pdasolucoes.standardconfig.network.UpdateStatusReplacement;
import br.com.pdasolucoes.standardconfig.network.UpdateTokenNotificationRequest;
import br.com.pdasolucoes.standardconfig.utils.Helper;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.actionError(true, true);

            }
        });
    }

}
