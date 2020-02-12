package br.com.pdasolucoes.standardconfig.network.enums;


public class RequestInfo {
    private String service;
    private String action;
    private int descriptionResourceId;
    private RequestType requestType;
    private boolean requireAuthentication;
    private TypeService typeService;
    private String nameSpace;

    public RequestInfo(String service, String action, RequestType requestType, int descriptionResourceId) {
        this(service, action, requestType, descriptionResourceId, true, TypeService.SOAP);
    }

    public RequestInfo(String service, String action, RequestType requestType, int descriptionResourceId, boolean requireAuthentication, TypeService typeService) {
        this.service = service;
        this.action = action;
        this.descriptionResourceId = descriptionResourceId;
        this.requestType = requestType;
        this.requireAuthentication = requireAuthentication;
        this.typeService = typeService;
    }

    public RequestInfo(String service, String action, RequestType requestType, int descriptionResourceId, boolean requireAuthentication, String nameSpace, TypeService typeService) {
        this.service = service;
        this.action = action;
        this.descriptionResourceId = descriptionResourceId;
        this.requestType = requestType;
        this.requireAuthentication = requireAuthentication;
        this.typeService = typeService;
        this.nameSpace = nameSpace;
    }

    public String getService() {
        return this.service;
    }

    public String getAction() {
        return this.action;
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public String getDescription() {
        return "";
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public boolean getRequireAuthentication() {
        return this.requireAuthentication;
    }
}
