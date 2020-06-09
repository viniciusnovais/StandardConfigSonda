package br.com.pdasolucoes.standardconfig.managers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.pdasolucoes.standardconfig.model.Sistema;
import br.com.pdasolucoes.standardconfig.network.JsonRequestBase;
import br.com.pdasolucoes.standardconfig.network.OffLineRequest;
import br.com.pdasolucoes.standardconfig.network.RequestUpdateApkTask;
import br.com.pdasolucoes.standardconfig.network.SendRequestTask;
import br.com.pdasolucoes.standardconfig.network.SoapRequestBase;
import br.com.pdasolucoes.standardconfig.network.UpdateApkTaskRequest;
import br.com.pdasolucoes.standardconfig.network.interfaces.IRequest;
import br.com.pdasolucoes.standardconfig.utils.MyApplication;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

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

    public static boolean isPackageInstalled(String packageName) {

        AppCompatActivity activity = NavigationHelper.getCurrentAppCompat();

        if (activity == null)
            return false;

        try {
            return activity.getPackageManager().getApplicationInfo(packageName, 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getVersionName(String packageName) {

        AppCompatActivity activity = NavigationHelper.getCurrentAppCompat();

        if (activity == null)
            return "";

        try {
            return activity.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static void tryUpdateOrOpenApk(Sistema sistema) {
        String[] versions = sistema.getVersions().split("[;]");
        List<Boolean> isUpdates = new ArrayList<>();

        String currentNameVersion = getVersionName(sistema.getPackageName());

        for (String v : versions) {
            isUpdates.add(currentNameVersion.equals(v.trim()));
        }

        if (!isUpdates.contains(true)) {
            NetworkManager.sendRequestApk(new UpdateApkTaskRequest(sistema.getNamePaste(), sistema.getNameApk()));
            return;
        }

        openApk(sistema.getPackageName());


    }

    private static void openApk(String packageName) {

        AppCompatActivity activity = NavigationHelper.getCurrentAppCompat();

        if (activity == null)
            return;

        Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            activity.startActivity(launchIntent);
        }
    }
}
