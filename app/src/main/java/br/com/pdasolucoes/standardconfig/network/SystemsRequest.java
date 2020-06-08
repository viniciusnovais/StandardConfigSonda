package br.com.pdasolucoes.standardconfig.network;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import br.com.pdasolucoes.standardconfig.managers.AuthManager;
import br.com.pdasolucoes.standardconfig.managers.SystemManager;
import br.com.pdasolucoes.standardconfig.model.Sistema;
import br.com.pdasolucoes.standardconfig.network.enums.MessageConfiguration;
import br.com.pdasolucoes.standardconfig.network.enums.RequestInfo;
import br.com.pdasolucoes.standardconfig.network.enums.RequestType;

public class SystemsRequest extends SoapRequestBase {

    public interface onSucessResult {
        void onSucess();
    }

    @Override
    public SoapObject getBodySoap() {

        SoapObject soapObject = new SoapObject();
        soapObject.addProperty("codigoPerfil_", AuthManager.getCurrentUser().getCodigoPerfil());
        return soapObject;
    }

    @Override
    protected RequestInfo getRequestInfo() {
        return new RequestInfo("Autenticacao.asmx",
                "GetListaSistema",
                RequestType.OnLine,
                -1,
                "http://tempuri.org/");
    }

    @Override
    public void processResult(Object data) {
        SoapObject soapObject = (SoapObject) data;

        List<Sistema> list = new ArrayList<>();
        for (int i = 0; i < ((SoapObject) data).getPropertyCount(); i++) {
            SoapObject item = (SoapObject) soapObject.getProperty(i);
            Sistema s = new Sistema();

            s.setCodigo(Integer.parseInt(item.getPropertyAsString("Codigo")));
            s.setNome(item.getPropertyAsString("Descricao"));
            s.setSigla(item.getPropertyAsString("Sigla"));
            s.setImagePath(item.getPropertyAsString("MobilePathIcon"));
            s.setUpdateDescription(item.getPropertyAsString("MobileUpdateDescription"));
            s.setVersions(item.getPropertyAsString("MobileVersions"));
            s.setPackageName(item.getPropertyAsString("MobilePackageName"));
            s.setNameApk(item.getPropertyAsString("MobileNameApk"));
            s.setNamePaste(item.getPropertyAsString("MobileNamePaste"));

            list.add(s);
        }

        SystemManager.setSistemas(list);

        SystemManager.onSucessResult.onSucess();

    }

    @Override
    public void processError(MessageConfiguration result) {

    }
}
