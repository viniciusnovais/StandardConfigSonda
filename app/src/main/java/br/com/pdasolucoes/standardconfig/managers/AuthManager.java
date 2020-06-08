package br.com.pdasolucoes.standardconfig.managers;

import br.com.pdasolucoes.standardconfig.model.Usuario;
import br.com.pdasolucoes.standardconfig.network.CredentialAuthRequest;

public class AuthManager {

    private static Usuario currentUser;

    public static Usuario getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Usuario currentUser) {
        AuthManager.currentUser = currentUser;
    }

    public static void AuthUser(String user, String pass) {
        NetworkManager.sendRequest(new CredentialAuthRequest(user, pass));
    }

    public static void logoutUser() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }


}
