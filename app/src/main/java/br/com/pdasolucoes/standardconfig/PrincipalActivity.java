package br.com.pdasolucoes.standardconfig;

import android.app.Activity;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;

public abstract class PrincipalActivity extends AppCompatActivity {

    private LinearLayout activityContainer;
    private View viewHeader;
    private ImageView imageView;

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout llparentView = (LinearLayout) getLayoutInflater().inflate(R.layout.principal_activity, null);
        initViews(llparentView);
        View view = getLayoutInflater().inflate(layoutResID, activityContainer, true);
        imageView = view.findViewById(R.id.imageCliente);

        super.setContentView(llparentView);
    }

    private void initViews(View view) {

        LinearLayout linearLayout = view.findViewById(R.id.parent_activity);
        Activity context = (Activity) linearLayout.getContext();

        TextView tvModulo = view.findViewById(R.id.tvModulo);
        TextView tvUsuario = view.findViewById(R.id.tvUsuario);
        TextView tvPerfil = view.findViewById(R.id.tvPerfil);
        viewHeader = view.findViewById(R.id.header);
        View viewFooter = view.findViewById(R.id.footer);
        TextView tvVersao = viewFooter.findViewById(R.id.tvVersion);
        activityContainer = view.findViewById(R.id.linearLayoutContent);


        if (getIntent() != null) {
            if (getIntent().getExtras() != null && getIntent().hasExtra(ConfigurationHelper.ConfigurationEntry.UserCode.getKeyName())) {
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCode, getIntent().getExtras().getInt(ConfigurationHelper.ConfigurationEntry.UserCode.getKeyName(), -1));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserName, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.UserName.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCodeFilial, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.UserCodeFilial.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserNameFilial, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.UserNameFilial.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCodeProfile, getIntent().getExtras().getInt(ConfigurationHelper.ConfigurationEntry.UserCodeProfile.getKeyName(), -1));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserNameProfile, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.UserNameProfile.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserLogin, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.UserLogin.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.IsLoggedIn, getIntent().getExtras().getBoolean(ConfigurationHelper.ConfigurationEntry.IsLoggedIn.getKeyName(), false));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.PackageName, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.PackageName.getKeyName(), ""));

                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.ServerAddress, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.ServerAddress.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.Directory, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.Directory.getKeyName(), ""));
                ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.Store, getIntent().getExtras().getString(ConfigurationHelper.ConfigurationEntry.Store.getKeyName(), ""));
            }
        }


        String profile = ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.UserNameProfile, "-");
        String userName = ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.UserName, "-");
        String packageName = ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.PackageName, "");

        tvPerfil.setText(getString(R.string.perfil_dois_pontos).concat(" ").concat(profile));
        tvUsuario.setText(getString(R.string.usuario_dois_pontos).concat(" ").concat(userName));
        tvModulo.setText(getString(R.string.modulo_dois_pontos).concat(" ").concat(context.getTitle().toString()));

        if (!TextUtils.isEmpty(packageName))
            tvVersao.setText(getString(R.string.versao_description).concat(" ")
                    .concat(NetworkManager.getVersionName(packageName)));
        else
            tvVersao.setText(getString(R.string.versao));


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewHeader.setVisibility(View.GONE);

            if (imageView != null) {
                imageView.setVisibility(View.GONE);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewHeader.setVisibility(View.VISIBLE);
            if (imageView != null) {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
