package br.com.pdasolucoes.standardconfig.network;

import android.app.Activity;
import android.app.ProgressDialog;

import org.apache.http.HttpEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Date;

import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.network.enums.MessageConfiguration;
import br.com.pdasolucoes.standardconfig.network.enums.RequestInfo;
import br.com.pdasolucoes.standardconfig.network.enums.RequestType;
import br.com.pdasolucoes.standardconfig.network.enums.TypeService;
import br.com.pdasolucoes.standardconfig.network.interfaces.IRequest;
import br.com.pdasolucoes.standardconfig.network.interfaces.IRequestHandler;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

public abstract class RequestBase implements IRequest {

    private ProgressDialog progressDialog;
    private WeakReference<Activity> progressDialogContext;

    private Activity context;

    private IRequestHandler handler = null;
    private Date requestTimestamp;

    public RequestBase() {
        this.context = NavigationHelper.getCurrentAppCompat();
    }

    public RequestBase(Activity context) {
        this.context = context;
    }

    @Override
    public void setHandler(IRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void taskStart() {
        if (this.getRequestType() == RequestType.OnLine) {
            this.showProgressDialog(this.context, this.getDescription());
        }

        this.requestTimestamp = new Date();
    }

    @Override
    public void taskCompleted(Object result) {
        if (this.getRequestType() == RequestType.OnLine) {
            this.closeProgressDialog();
        }

        MessageConfiguration messageConfiguration;

        if (result != null) {
            //messageConfiguration = ResultCode.getResultCode(result.optInt("ResultCode", ResultCode.Success.getCode()));
            messageConfiguration = null;
        } else {
            messageConfiguration = MessageConfiguration.NetworkError;
        }

        if (this.handleError(messageConfiguration, result)
                || this.handleError(result)) {
            if (this.handler != null) {
                this.handler.onError();
            }
            return;
        }

        this.processResult(result);
        if (this.handler != null) {
            this.handler.onSuccess();
        }
    }

    private void showProgressDialog(Activity context, String message) {
        if (context == null || context.isFinishing()) {
            return;
        }

        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setTitle(R.string.dialog_progress_title);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage(message);

        this.progressDialogContext = new WeakReference<>(context);

        this.progressDialog.show();
    }

    private void closeProgressDialog() {
        if (this.progressDialogContext == null) {
            return;
        }

        Activity context = this.progressDialogContext.get();

        if (context != null && !context.isFinishing()) {
            if (this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
        }
    }

    private boolean handleError(Object object) {
        if (object instanceof Exception) {
            NavigationHelper.showDialog("Erro", ((Exception) object).getMessage(), null, null);
            return true;
        }

        return false;
    }

    private boolean handleError(MessageConfiguration messageConfiguration, Object object) {


//        if (messageConfiguration == ResultCode.InvalidToken) {
//            ApplicationManager.signOff();
//            ApplicationManager.stopManagers();
//
//            if (CredentialsManager.isLogged()) {
//                NavigationHelper.showAlertDialog(R.string.alert_resultcode_title, resultCode.getDescriptionId(), new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        CredentialsManager.confirmLogoff();
//                    }
//                });
//            } else {
//                AuthenticateRequest signInRequest = new AuthenticateRequest(this);
//                NetworkManager.sendRequest(signInRequest);
//            }
//            return true;
//        }

//        if (messageConfiguration == ResultCode.LogonRequired) {
//            ApplicationManager.stopManagers();
//
//            if (CredentialsManager.isLogged()) {
//                NavigationHelper.showAlertDialog(R.string.alert_resultcode_title, messageConfiguration.getMsg(), new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        CredentialsManager.confirmLogoff();
//                    }
//                });
//            }
//            return true;
//        }

//        if ((messageConfiguration == ResultCode.NotConnected || messageConfiguration == MessageConfiguration.NetworkError) &&
//                this.getRequestType() == RequestType.OffLine) {
//            NetworkManager.saveOffLineRequest((JsonRequestBase) this);
//            return false;
//        }

//        if (messageConfiguration != ResultCode.Success) {
//            if (this.getRequestType() == RequestType.OnLine) {
//                if (messageConfiguration == ResultCode.CustomError) {
//                    String title = SMobileApplication.getStringResource(R.string.alert_resultcode_title);
//                    String message = SMobileApplication.getStringResource(resultCode.getDescriptionId());
//                    message = result.optString("Message", message);
//                    NavigationHelper.showAlertDialog(title, message, null);
//                } else if (messageConfiguration == ResultCode.UnsupportedVersion) {
//                    String title = SMobileApplication.getStringResource(R.string.alert_resultcode_unsupport_version_title);
//                    String message = SMobileApplication.getStringResource(resultCode.getDescriptionId());
//                    message = message.concat("\n").concat(result.optString("Message").replaceAll("[;]","\n"));
//                    NavigationHelper.showAlertDialogCustomButton(title, message, R.string.alert_unsupport_version_update, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            NetworkManager.updateApkRequest(new UpdateApkRequest());
//                        }
//                    });
        //} else {

//                    if (messageConfiguration == ResultCode.InvalidUser || messageConfiguration == ResultCode.UserLoggedOn) {
//                        String message = SMobileApplication.getStringResource(resultCode.getDescriptionId());
//                        String user = result.optString("Message", null);
//                        message += " [" + user + "]";
//                        NavigationHelper.showAlertDialog(SMobileApplication.getStringResource(R.string.alert_resultcode_title), message, null);
//                    } else
//                        NavigationHelper.showAlertDialog(R.string.alert_resultcode_title, resultCode.getDescriptionId(), null);
        //}
        // }

        //this.processError(messageConfiguration);
        //return true;
        //}

        return false;
    }

    @Override
    public void taskProgress(Void... progress) {
        // Do nothing
    }

    protected abstract RequestInfo getRequestInfo();

    @Override
    public Date getRequestTimestamp() {
        return this.requestTimestamp;
    }

    @Override
    public String getService() {
        return this.getRequestInfo().getService();
    }

    @Override
    public String getAction() {
        return this.getRequestInfo().getAction();
    }

    @Override
    public RequestType getRequestType() {
        return this.getRequestInfo().getRequestType();
    }

    @Override
    public boolean getRequireAuthentication() {
        return this.getRequestInfo().getRequireAuthentication();
    }

    @Override
    public String getNameSpace() {
        return this.getRequestInfo().getNameSpace();
    }

    @Override
    public String getDescription() {
        return this.getRequestInfo().getDescription();
    }

    @Override
    public TypeService getTypeService() {
        return this.getRequestInfo().getTypeService();
    }

    @Override
    public HttpEntity getRequestEntity() throws UnsupportedEncodingException {
        return null;
    }
}
