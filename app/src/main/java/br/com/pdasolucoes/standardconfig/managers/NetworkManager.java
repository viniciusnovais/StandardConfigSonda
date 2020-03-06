package br.com.pdasolucoes.standardconfig.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.com.pdasolucoes.standardconfig.network.JsonRequestBase;
import br.com.pdasolucoes.standardconfig.network.OffLineRequest;
import br.com.pdasolucoes.standardconfig.network.RequestUpdateApkTask;
import br.com.pdasolucoes.standardconfig.network.SendRequestTask;
import br.com.pdasolucoes.standardconfig.network.SoapRequestBase;
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

    public static void saveOffLineRequest(SoapRequestBase request) {
//        NetworkRequestDataSource dataSource = SQLiteHelper.getInstance().getDataSource(NetworkRequestDataSource.class);
//        dataSource.addRequest(request);
//
//        pendingRequests = dataSource.getRequests();
    }

    public static void removeOffLineRequest(OffLineRequest request) {
//        NetworkRequestDataSource dataSource = SQLiteHelper.getInstance().getDataSource(NetworkRequestDataSource.class);
//        dataSource.deleteRequest(request);
//
//        pendingRequests = dataSource.getRequests();
    }

    public static void sendRequest(IRequest iRequest) {
        SendRequestTask sendRequestTask = new SendRequestTask(iRequest);
        sendRequestTask.execute();
    }

    public static void sendRequestApk(IRequest iRequest) {
        RequestUpdateApkTask requestUpdateApkTask = new RequestUpdateApkTask(iRequest);
        requestUpdateApkTask.execute();
    }
}
