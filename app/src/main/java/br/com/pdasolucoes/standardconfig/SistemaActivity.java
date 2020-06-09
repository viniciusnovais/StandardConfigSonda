package br.com.pdasolucoes.standardconfig;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.pdasolucoes.standardconfig.adapter.ListaSistemasAdapter;
import br.com.pdasolucoes.standardconfig.managers.FilialManager;
import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.managers.SystemManager;
import br.com.pdasolucoes.standardconfig.model.Sistema;
import br.com.pdasolucoes.standardconfig.network.SystemsRequest;
import br.com.pdasolucoes.standardconfig.network.UpdateApkTaskRequest;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;


public class SistemaActivity extends PrincipalActivity implements SystemsRequest.onSucessResult {

    RecyclerView recyclerView;
    ListaSistemasAdapter adapter;
    private static final int REQUEST_CODE = 0x11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_home_activity);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

        SystemManager.setOnSucessListener(this);
        SystemManager.loadSystems();

    }

    @Override
    public void onSucess() {
        adapter = new ListaSistemasAdapter(this, SystemManager.getSistemas());
        recyclerView.setAdapter(adapter);
        adapter.ItemClickListener(new ListaSistemasAdapter.ItemClick() {
            @Override
            public void onClick(Sistema sistema) {
                if (sistema.getSigla().equals("PRE")) {
                    NavigationHelper.startActivity(SettingsActivity.class);
                    return;
                }
                SystemManager.setCurrentSystem(sistema);

                if (NetworkManager.isPackageInstalled(sistema.getPackageName()))
                    NetworkManager.tryUpdateOrOpenApk(sistema);
                else
                    NetworkManager.sendRequestApk(new UpdateApkTaskRequest(sistema.getNamePaste(), sistema.getNameApk()));
            }
        });
        FilialManager.loadFilial();
    }

    @Override
    public void onBackPressed() {
        SystemManager.popupSair();
    }
}
