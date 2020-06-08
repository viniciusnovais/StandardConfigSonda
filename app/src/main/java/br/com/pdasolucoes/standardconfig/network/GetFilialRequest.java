package br.com.pdasolucoes.standardconfig.network;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.managers.FilialManager;
import br.com.pdasolucoes.standardconfig.managers.NetworkManager;
import br.com.pdasolucoes.standardconfig.model.Filial;
import br.com.pdasolucoes.standardconfig.network.enums.MessageConfiguration;
import br.com.pdasolucoes.standardconfig.network.enums.RequestInfo;
import br.com.pdasolucoes.standardconfig.network.enums.RequestType;

public class GetFilialRequest extends SoapRequestBase {

    private String codigoFilial;

    public GetFilialRequest(String codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    @Override
    public SoapObject getBodySoap() {

        SoapObject soapObject = new SoapObject();

        soapObject.addProperty("codigoFilial", codigoFilial);

        return soapObject;
    }

    @Override
    protected RequestInfo getRequestInfo() {
        return new RequestInfo(
                "autenticacao.asmx",
                "GetFilial",
                RequestType.OnLine,
                R.string.load_filial,
                "http://tempuri.org/"
        );
    }

    @Override
    public void processResult(Object data) {

        SoapObject response = (SoapObject) data;

        List<Filial> lista = new ArrayList<>();
        for (int i = 0; i < response.getPropertyCount(); i++) {
            SoapObject item = (SoapObject) response.getProperty(i);
            Filial l = new Filial();

            l.setCodigo(Integer.parseInt(item.getPropertyAsString("Codigo")));
            l.setNome(item.getPropertyAsString("Nome"));

            lista.add(l);
        }

        FilialManager.setFilials(lista);

        if (lista.size() > 1)
            FilialManager.popupSelectFilial(lista);
        else
            FilialManager.setCurrentFilial(lista.get(0));
    }

    @Override
    public void processError(MessageConfiguration result) {

    }
}
