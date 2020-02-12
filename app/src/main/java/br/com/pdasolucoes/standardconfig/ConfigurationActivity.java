package br.com.pdasolucoes.standardconfig;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

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

//        if (!serverAddress.startsWith("http://") && !serverAddress.startsWith("https://")) {
//            serverAddress = "https://" + serverAddress;
//        }

        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.ServerAddress, serverAddress);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.Directory, directory);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.Store, store);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.IsConfigured, true);
        // NavigationHelper.startActivity(LoginActivity.class);

        onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
//        if (requestCode == PermissionHelper.READ_PHONE_STATE_REQUEST) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                this.validateServerAddress();
//            } else {
//                NavigationHelper.showConfirmDialog(R.string.alert_genericerror_title
//                        , R.string.alert_permissionerror_message);
//            }
//        } else if (requestCode == PermissionHelper.WRITE_EXTERNAL_STORAGE_REQUEST) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                this.validateServerAddress();
//            } else {
//                NavigationHelper.showConfirmDialog(R.string.alert_genericerror_title
//                        , R.string.alert_permissionerror_message);
//            }
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//    private void cancel() {
//        NavigationHelper.showDialog(
//                R.string.navigation_exit_title,
//                R.string.navigation_exit_message,
//                R.string.yes,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        finish();
//                    }
//                }, R.string.no);
//
//    }
}
