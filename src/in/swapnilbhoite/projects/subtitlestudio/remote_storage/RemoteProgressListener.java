/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.remote_storage;

/**
 *
 * @author Swapnil Bhoite
 */
public interface RemoteProgressListener {

    void onProgressUpdate(int progress, String description);

}
