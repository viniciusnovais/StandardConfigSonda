package br.com.pdasolucoes.standardconfig.managers;

import br.com.pdasolucoes.standardconfig.network.CredentialAuthRequest;
import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;

public class AuthManager {

    public static void AuthUser(String user, String pass) {
        NetworkManager.sendRequest(new CredentialAuthRequest(user, pass));
    }

    public static void logoutUser() {
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCode, -1);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserName, "");
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCodeFilial, -1);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserNameFilial, "");
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserCodeProfile, -1);
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserNameProfile, "");
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.UserLogin, "");
        ConfigurationHelper.savePreference(ConfigurationHelper.ConfigurationEntry.IsLoggedIn, false);
    }

}
