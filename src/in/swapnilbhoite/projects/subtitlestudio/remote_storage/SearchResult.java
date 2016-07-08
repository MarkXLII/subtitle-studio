/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.remote_storage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swapnil Bhoite
 */
public class SearchResult {

    private final String fileName;
    private final String remotePath;
    private final int categary;
    private final int subCategary;
    private final Object extra;
    private String title;
    private String artist;
    private String album;

    public SearchResult(String fileName,
            String remotePath,
            int categary,
            int subCategary,
            Object extra) {
        this.fileName = fileName;
        this.remotePath = remotePath;
        this.categary = categary;
        this.subCategary = subCategary;
        this.extra = extra;
        try {
            title = artist = album = "";
            initMetaData();
        } catch (Exception ex) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    private void initMetaData() {
        String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length() - 1);
        String[] spilts;
        switch (subCategary) {
            case RemoteStorage.SUB_CATEGARY_TITLE:
                spilts = fileName.split(extension);
                title = spilts[0];
                break;

            case RemoteStorage.SUB_CATEGARY_TITLE_ALBUM:
                spilts = fileName.split(RemoteStorage.DELIMITER_ALBUM);
                title = spilts[0];
                spilts = spilts[1].split(extension);
                album = spilts[0];
                break;

            case RemoteStorage.SUB_CATEGARY_TITLE_ARTIST:
                spilts = fileName.split(RemoteStorage.DELIMITER_ARTIST);
                title = spilts[0];
                spilts = spilts[1].split(extension);
                artist = spilts[0];
                break;

            case RemoteStorage.SUB_CATEGARY_TITLE_ARTIST_ALBUM:
                spilts = fileName.split(RemoteStorage.DELIMITER_ARTIST);
                title = spilts[0];
                spilts = spilts[1].split(RemoteStorage.DELIMITER_ALBUM);
                artist = spilts[0];
                spilts = spilts[1].split(extension);
                album = spilts[0];
                break;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public int getCategary() {
        return categary;
    }

    public int getSubCategary() {
        return subCategary;
    }

    public Object getExtra() {
        return extra;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.fileName != null ? this.fileName.hashCode() : 0);
        hash = 59 * hash + (this.remotePath != null ? this.remotePath.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SearchResult other = (SearchResult) obj;
        if ((this.fileName == null) ? (other.fileName != null) : !this.fileName.equals(other.fileName)) {
            return false;
        }
        return !((this.remotePath == null) ? (other.remotePath != null) : !this.remotePath.equals(other.remotePath));
    }

    @Override
    public String toString() {
        return "SearchResult{" + "fileName=" + fileName + ", remotePath=" + remotePath + ", categary=" + categary + ", subCategary=" + subCategary + ", extra=" + extra + ", title=" + title + ", artist=" + artist + ", album=" + album + '}';
    }
}
