/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.vlc;

/**
 *
 * @author Swapnil Bhoite
 */
public interface VlcSdk {

    VlcPlayer getVlcPlayer();

    void setOptions(String[] options);

    String[] getSupportedFileExtensions();
}
