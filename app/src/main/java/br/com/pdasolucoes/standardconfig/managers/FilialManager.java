package br.com.pdasolucoes.standardconfig.managers;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.model.Filial;
import br.com.pdasolucoes.standardconfig.network.GetFilialRequest;
import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;
import br.com.pdasolucoes.standardconfig.utils.SpinnerDialog;
import br.com.pdasolucoes.standardconfig.utils.interfaces.OnSpinerItemClick;

public class FilialManager {

    private static Filial currentFilial;
    private static List<Filial> filials;

    public static Filial getCurrentFilial() {
        return currentFilial;
    }

    public static void setCurrentFilial(Filial currentFilial) {
        FilialManager.currentFilial = currentFilial;
    }

    public static List<Filial> getFilials() {
        return filials;
    }

    public static void setFilials(List<Filial> filials) {
        FilialManager.filials = filials;
    }

    public static void popupSelectFilial(List<Filial> lista) {

        final AppCompatActivity activity = NavigationHelper.getCurrentAppCompat();

        if (activity == null)
            return;


        View.OnClickListener closeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFilial == null)
                    NavigationHelper.showToastShort(R.string.selecione_filial);
                else {
                    DialogInterface dialog = NavigationHelper.getCurrentDialog();
                    if (dialog == null)
                        return;
                    dialog.dismiss();
                }
            }
        };

        SpinnerDialog spinnerDialog =
                new SpinnerDialog(activity, (ArrayList<?>) lista, activity.getString(R.string.selecione_filial), closeClickListener);
        spinnerDialog.showSpinerDialog();
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(Object o) {
                currentFilial = (Filial) o;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(activity.getString(R.string.pref_filial_key), currentFilial.toString());
                editor.apply();

            }
        });
    }

    public static void loadFilial() {
        String codigoFilial = ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.UserCodeFilial, "");
        NetworkManager.sendRequest(new GetFilialRequest(codigoFilial));
    }

}
