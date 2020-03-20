package br.com.pdasolucoes.standardconfig.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import java.util.Objects;

import br.com.pdasolucoes.standardconfig.ConfigurationActivity;
import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.network.GetMobileVersionRequest;

public class MyApplication extends Application {

    private static MyApplication instance;

    private static boolean correctVersion = false;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        MyApplication.instance = this;
        super.onCreate();

        MultiDex.install(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                NavigationHelper.setCurrentAppCompat((AppCompatActivity) activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                NavigationHelper.setCurrentAppCompat((AppCompatActivity) activity);
                if (!ConfigurationHelper.loadPreference(
                        ConfigurationHelper.ConfigurationEntry.IsConfigured, false))
                    return;

                if (!correctVersion)
                    NetworkManager.sendRequest(new GetMobileVersionRequest());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                clearReferences(activity);
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                clearReferences(activity);
            }
        });
    }

    private void clearReferences(Activity activity) {
        Activity currActivity = NavigationHelper.getCurrentAppCompat();
        if (currActivity != null && currActivity.equals(activity)) {
            NavigationHelper.setCurrentAppCompat(null);
        }
    }

    public static String getStringResource(int resourceId) {
        return MyApplication.instance.getResources().getString(resourceId);
    }


    public static void closeKeyBoard(Context context) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View focusedView = ((Activity) context).getCurrentFocus();
        if (focusedView != null) {
            if (inputMethodManager != null) {
                (inputMethodManager).hideSoftInputFromWindow(
                        focusedView.getWindowToken(), 0);
            }
        }
    }

    public static boolean isCorrectVersion() {
        return correctVersion;
    }

    public static void setCorrectVersion(boolean correctVersion) {
        MyApplication.correctVersion = correctVersion;
    }
}
