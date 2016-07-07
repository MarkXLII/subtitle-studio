/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.dropbox;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import in.swapnilbhoite.projects.subtitlestudio.MyServers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Swapnil Bhoite
 */
public class DropboxSdk {

    //TODO: Add credentials here
    public static final String APP_KEY = "XXXX";
    public static final String APP_SECRET = "XXXX";
    public static final String AUTH_KEY = "XXXX";
    public static final String AUTH_SECRET = "XXXX";
    public static final Session.AccessType ACCESS_TYPE = Session.AccessType.APP_FOLDER;
    public static DropboxAPI<WebAuthSession> myDropBox;
    public static List<MyServers> servers = new ArrayList<MyServers>(1);

}
