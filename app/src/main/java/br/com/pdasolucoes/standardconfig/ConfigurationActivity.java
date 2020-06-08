package br.com.pdasolucoes.standardconfig;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;

public class ConfigurationActivity extends AppCompatActivity {

    EditText mEditTextServer;
    EditText mEditTextDirectory;
    EditText mEditTextStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_configuration);

        mEditTextServer = findViewById(R.id.etServer);
        mEditTextDirectory = findViewById(R.id.etDirectory);
        mEditTextStore = findViewById(R.id.etStore);

        if (!ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.ServerAddress, "").equals("")) {
            mEditTextServer.setText(ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.ServerAddress, ""));
            mEditTextDirectory.setText(ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.Directory, ""));
            mEditTextStore.setText(ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.Store, ""));
        }

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateServerAddress();
            }
        });
    }

    private void validateServerAddress() {
        String serverAddress = mEditTextServer.getText().toString();
        String directory = mEditTextDirectory.getText().toString();
        String store = mEditTextStore.getText().toString();

        serverAddress = serverAddress.replace("\\", "/");

        if (!serverAddress.endsWith("/")) {
            serverAddress = serverAddress + "/";
        }

        if (!serverAddress.startsWith("http://")) {
            serverAddress = "http://" + serverAddress;
        }

        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.ServerAddress, serverAddress);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.Directory, directory);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.Store, store);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.IsConfigured, true);

        onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
