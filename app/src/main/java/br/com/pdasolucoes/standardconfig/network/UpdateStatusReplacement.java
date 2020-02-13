package br.com.pdasolucoes.standardconfig.network;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pdasolucoes.standardconfig.model.Reposicao;
import br.com.pdasolucoes.standardconfig.enums.MarshalType;
import br.com.pdasolucoes.standardconfig.network.enums.MessageConfiguration;
import br.com.pdasolucoes.standardconfig.network.enums.RequestInfo;
import br.com.pdasolucoes.standardconfig.network.enums.RequestType;
import br.com.pdasolucoes.standardconfig.network.enums.TypeService;
import br.com.pdasolucoes.standardconfig.utils.Helper;

public class UpdateStatusReplacement extends SoapRequestBase {
    @Override
    public SoapObject getBodySoap() {

        List<Reposicao> list = new ArrayList<>();
        Reposicao r = new Reposicao();

        r.setProduto(1);
        r.setDataHora(Helper.formatDateTimeToCsharp(new Date()));
        r.setCodigo("28542374");
        r.setStatus("S");

        list.add(r);

        SoapObject soapObject = getSoapObjectToList();
        for (Reposicao reposicao : list) {
            soapObject.addProperty(getObjectName(), reposicao);
        }

        return soapObject;
    }

    @Override
    protected RequestInfo getRequestInfo() {
        return new RequestInfo("reposicaoservice.asmx",
                "UpdateStatus",
                RequestType.OnLine,
                -1,
                false,
                "list",
                "Reposicao",
                Reposicao.class,
                "http://tempuri.org/",
                new MarshalType[]{MarshalType.DATETIME},
                TypeService.SOAP);
    }

    @Override
    public void processResult(Object data) {

    }

    @Override
    public void processError(MessageConfiguration result) {

    }
}
