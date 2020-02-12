package br.com.pdasolucoes.standardconfig.network.enums;

import org.json.JSONException;
import org.json.JSONObject;
import br.com.pdasolucoes.standardconfig.R;

public enum MessageConfiguration {


    NetworkError(1, R.string.title_error, R.string.message_error_network);


    private int code;
    private int resourceTitle;
    private int resourceMsg;

    MessageConfiguration(int code, int resourceTitle, int resourceMsg) {
        this.code = code;
        this.resourceTitle = resourceTitle;
        this.resourceMsg = resourceMsg;
    }

    public int getCode() {
        return code;
    }

    public int getTitle() {
        return resourceTitle;
    }

    public int getMsg() {
        return resourceTitle;
    }


    public JSONObject getJSONResult() {
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse.put("ResultCode", this.getCode());
        } catch (JSONException jsonEx) {
            //Do nothing
        }
        return jsonResponse;
    }

    public static JSONObject getJSONResultMessage(String msg) {
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse.put("Message", msg);
        } catch (JSONException jsonEx) {
            //Do nothing
        }
        return jsonResponse;
    }
}
