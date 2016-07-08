/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.dropbox;

/**
 *
 * @author Swapnil Bhoite
 */
public class DropboxServer {

    public String APP_KEY = "";
    public String APP_SECRET = "";
    public String AUTH_KEY = "";
    public String AUTH_SECRET = "";

    public DropboxServer(String appKey, String appSecret, String keyToken, String secretToken) {
        APP_KEY = appKey;
        APP_SECRET = appSecret;
        AUTH_KEY = keyToken;
        AUTH_SECRET = secretToken;
    }

    @Override
    public String toString() {
        return APP_KEY + "\n" + APP_SECRET + "\n" + AUTH_KEY + "\n" + AUTH_SECRET;
    }
}
