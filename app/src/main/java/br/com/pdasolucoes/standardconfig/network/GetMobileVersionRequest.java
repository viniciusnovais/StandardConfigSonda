package br.com.pdasolucoes.standardconfig.network;

import android.app.Activity;
import android.content.Context;
import org.ksoap2.serialization.SoapObject;

import br.com.pdasolucoes.standardconfig.BuildConfig;
import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.network.enums.MessageConfiguration;
import br.com.pdasolucoes.standardconfig.network.enums.RequestInfo;
import br.com.pdasolucoes.standardconfig.network.enums.RequestType;
import br.com.pdasolucoes.standardconfig.utils.MyApplication;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;
import br.com.pdasolucoes.standardconfig.utils.PermissionHelper;

public class GetMobileVersionRequest extends SoapRequestBase {

    @Override
    public SoapObject getBodySoap() {
        return null;
    }

    @Override
    protected RequestInfo getRequestInfo() {
        return new RequestInfo("autenticacao.asmx",
                "MobileConfiguration",
                RequestType.Backgroud,
                R.string.verify_update_version,
                "http://tempuri.org/");
    }

    @Override
    public void processResult(Object data) {

        Context context = NavigationHelper.getCurrentAppCompat();

        if (context == null || data == null)
            return;

        if (!PermissionHelper.checkWriteExternalStoragePermission((Activity) context))
            return;

        String mobileVersion = ((SoapObject) data).getPropertyAsString("MobileVersion");
        //usar posteriormente, caso necessário
        String mobileUpdateDescription = ((SoapObject) data).getPropertyAsString("MobileUpdateDescription");
        String mobileNamePaste = ((SoapObject) data).getPropertyAsString("MobileNamePaste");
        String mobileNameApk = ((SoapObject) data).getPropertyAsString("MobileNameApk");

        //usar posteriormente, caso necessário
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        if (!versionName.equals(mobileVersion)) {
            NetworkManager.sendRequestApk(new UpdateApkTaskRequest(mobileNamePaste, mobileNameApk));
            return;
        }

        MyApplication.setCorrectVersion(true);

    }

    @Override
    public void processError(MessageConfiguration result) {

    }


}
