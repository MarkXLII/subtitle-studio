/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.vlc;

import java.awt.Canvas;

/**
 *
 * @author Swapnil Bhoite
 */
public interface VlcPlayer {

    void setVolume(int value);

    void play();

    void pause();

    void stop();

    boolean isPlaying();

    void setVideoSurface(Canvas videoSurface);

    void prepareMedia(String filepath);

    void release();

    long getTime();

    long getLength();

}
