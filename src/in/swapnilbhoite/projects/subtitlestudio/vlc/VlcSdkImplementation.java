/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.vlc;

import java.util.ArrayList;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.filter.VideoFileFilter;

/**
 *
 * @author Swapnil Bhoite
 */
public class VlcSdkImplementation implements VlcSdk {

    private VlcPlayer vlcPlayer;
    private String[] options;

    private VlcSdkImplementation() {
        ArrayList<String> optionsList = new ArrayList<String>();
        optionsList.add("--no-plugins-cache");
        optionsList.add("--no-video-title-show");
        optionsList.add("--no-snapshot-preview");
        options = optionsList.toArray(new String[optionsList.size()]);

        new NativeDiscovery().discover();
    }

    public static synchronized VlcSdkImplementation getInstance() {
        return LazyLoader.instance;
    }

    @Override
    public VlcPlayer getVlcPlayer() {
        if (vlcPlayer == null) {
            vlcPlayer = new VlcPlayerImplementation(options);
        }
        return vlcPlayer;
    }

    @Override
    public void setOptions(String[] options) {
        this.options = options;
    }

    @Override
    public String[] getSupportedFileExtensions() {
        return new VideoFileFilter().getExtensions();
    }

    private static class LazyLoader {

        private static VlcSdkImplementation instance = new VlcSdkImplementation();
    }

}
