/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.vlc;

import java.awt.Canvas;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

/**
 *
 * @author Swapnil Bhoite
 */
class VlcPlayerImplementation implements VlcPlayer {

    private final EmbeddedMediaPlayer mediaPlayer;
    private final MediaPlayerFactory mediaPlayerFactory;

    protected VlcPlayerImplementation(String[] options) {
        this.mediaPlayerFactory = new MediaPlayerFactory(options);
        mediaPlayer = this.mediaPlayerFactory.newEmbeddedMediaPlayer(null);
    }

    @Override
    public void setVolume(int value) {
        mediaPlayer.setVolume(value);
    }

    @Override
    public void play() {
        mediaPlayer.play();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void setVideoSurface(Canvas videoSurface) {
        CanvasVideoSurface canvasVideoSurface = mediaPlayerFactory.newVideoSurface(videoSurface);
        mediaPlayer.setVideoSurface(canvasVideoSurface);
    }

    @Override
    public void prepareMedia(String filepath) {
        mediaPlayer.prepareMedia(filepath);
    }

    @Override
    public void release() {
        mediaPlayer.release();
    }

    @Override
    public long getTime() {
        return mediaPlayer.getTime();
    }

    @Override
    public long getLength() {
        return mediaPlayer.getLength();
    }

}
