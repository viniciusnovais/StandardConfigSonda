package br.com.pdasolucoes.standardconfig.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.com.pdasolucoes.standardconfig.network.SendRequestTask;
import br.com.pdasolucoes.standardconfig.network.interfaces.IRequest;
import br.com.pdasolucoes.standardconfig.utils.MyApplication;

public class NetworkManager {

    public static boolean isNetworkOnline() {
        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = null;
            if (connectivityManager != null)
                activeNetwork = connectivityManager.getActiveNetworkInfo();

            if (activeNetwork != null && activeNetwork.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void sendRequest(IRequest iRequest) {
        SendRequestTask sendRequestTask = new SendRequestTask(iRequest);
        sendRequestTask.execute();
    }
}
