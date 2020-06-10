package br.com.pdasolucoes.standardconfig.network;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

import br.com.pdasolucoes.standardconfig.LoginActivity;
import br.com.pdasolucoes.standardconfig.PrincipalActivity;
import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.SistemaActivity;
import br.com.pdasolucoes.standardconfig.enums.MarshalType;
import br.com.pdasolucoes.standardconfig.managers.AuthManager;
import br.com.pdasolucoes.standardconfig.model.Reposicao;
import br.com.pdasolucoes.standardconfig.model.Sistema;
import br.com.pdasolucoes.standardconfig.model.Usuario;
import br.com.pdasolucoes.standardconfig.network.enums.MessageConfiguration;
import br.com.pdasolucoes.standardconfig.network.enums.RequestInfo;
import br.com.pdasolucoes.standardconfig.network.enums.RequestType;
import br.com.pdasolucoes.standardconfig.network.enums.TypeService;
import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;
import br.com.pdasolucoes.standardconfig.utils.NavigationHelper;

public class CredentialAuthRequest extends SoapRequestBase {

    private String user;
    private String pass;

    public CredentialAuthRequest(String user, String pass) {
        this.user = user;
        this.pass = pass;

    }

    @Override
    public SoapObject getBodySoap() {

        SoapObject soapObject = new SoapObject();

        soapObject.addProperty("login_", user);
        soapObject.addProperty("senha_", pass);

        return soapObject;

    }

    @Override
    protected RequestInfo getRequestInfo() {
        return new RequestInfo("autenticacao.asmx",
                "GetAutenticacao",
                RequestType.OnLine,
                -1,
                "http://tempuri.org/",
                TypeService.SOAP);
    }

    @Override
    public void processResult(Object data) {
        SoapObject response = (SoapObject) data;

        if (!response.getProperty("mensagemErro").toString().equals("anyType{}")) {

            AppCompatActivity activity = NavigationHelper.getCurrentAppCompat();
            if (activity == null) return;
            NavigationHelper.showConfirmDialog(activity.getString(R.string.error_auth),
                    response.getProperty("mensagemErro").toString());
            return;
        }


        //Usuario usuario = new Usuario();
//        usuario.setCodigo(Integer.parseInt(response.getPropertyAsString("Codigo")));
//        usuario.setCodigoFilial(response.getPropertyAsString("CodigoFilial"));
//        usuario.setFilial(response.getPropertyAsString("NomeFilial"));
//        usuario.setNome(response.getPropertyAsString("Nome"));
//        usuario.setPerfil(response.getPropertyAsString("DescricaoPefil"));
//        usuario.setCodigoPerfil(Integer.parseInt(response.getPropertyAsString("CodigoPerfil")));

        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCode,Integer.parseInt(response.getPropertyAsString("Codigo")));
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserName,response.getPropertyAsString("Nome"));
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCodeFilial,response.getPropertyAsString("CodigoFilial"));
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserNameFilial,response.getPropertyAsString("NomeFilial"));
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCodeProfile,Integer.parseInt(response.getPropertyAsString("CodigoPerfil")));
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserNameProfile,response.getPropertyAsString("DescricaoPefil"));
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserLogin,user);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.IsLoggedIn,true);

        //AuthManager.setCurrentUser(usuario);

        NavigationHelper.startActivity(SistemaActivity.class);


    }

    @Override
    public void processError(MessageConfiguration result) {

    }

}
