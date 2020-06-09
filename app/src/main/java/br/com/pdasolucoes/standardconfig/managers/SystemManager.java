package br.com.pdasolucoes.standardconfig.managers;

import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.model.Sistema;
import br.com.pdasolucoes.standardconfig.network.SystemsRequest;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

public class SystemManager {

    private static List<Sistema> sistemas;
    public static SystemsRequest.onSucessResult onSucessResult;
    private static Sistema currentSystem;

    public static void setOnSucessListener(SystemsRequest.onSucessResult onSucessResult) {
        SystemManager.onSucessResult = onSucessResult;
    }


    public static List<Sistema> getSistemas() {
        return sistemas;
    }

    public static void setSistemas(List<Sistema> sistemas) {
        SystemManager.sistemas = sistemas;
    }

    public static Sistema getCurrentSystem() {
        return currentSystem;
    }

    public static void setCurrentSystem(Sistema currentSystem) {
        SystemManager.currentSystem = currentSystem;
    }

    public static void popupSair() {


        NavigationHelper.showDialog(R.string.deseja_sair, -1, R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AppCompatActivity appCompatActivity = NavigationHelper.getCurrentAppCompat();

                        if (appCompatActivity == null)
                            return;

                        AuthManager.logoutUser();
                        appCompatActivity.finish();
                    }
                }, R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    public static void loadSystems() {
        NetworkManager.sendRequest(new SystemsRequest());
    }
}
