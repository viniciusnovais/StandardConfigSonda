package br.com.pdasolucoes.standardconfig;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.pdasolucoes.standardconfig.managers.AuthManager;
import br.com.pdasolucoes.standardconfig.utils.MyApplication;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

public class LoginActivity extends AppCompatActivity {

    EditText editUser;
    EditText editPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity_login);

        editUser = findViewById(R.id.etUser);
        editPass = findViewById(R.id.etPassword);

        Button btnEnter = findViewById(R.id.btnEntrar);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAuth(editUser.getText().toString(), editPass.getText().toString());
            }
        });
    }

    void verifyAuth(String user, String pass) {
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
            if (user.equals("12345678") && pass.equals("12345678")) {
                NavigationHelper.startActivity(ConfigurationActivity.class);
                cleanEdit();
            } else {
                AuthManager.AuthUser(user, pass);
            }
        } else {
            NavigationHelper.showToastShort(R.string.required_all_labels);
        }
    }

    void cleanEdit() {
        editPass.setText("");
        editUser.setText("");
    }
}
