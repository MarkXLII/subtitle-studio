/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.remote_storage;

import in.swapnilbhoite.projects.subtitlestudio.dropbox.DropboxSdk;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Swapnil Bhoite
 */
public class RemoteStorage implements RemoteStorageApis {

    public static final int CATEGARY_MUSIC_SUBTITLE = 0;
    public static final int CATEGARY_MOVIE_SUBTITLE = 1;

    public static final int SUB_CATEGARY_TITLE_ARTIST_ALBUM = 0;
    public static final int SUB_CATEGARY_TITLE_ARTIST = 1;
    public static final int SUB_CATEGARY_TITLE_ALBUM = 2;
    public static final int SUB_CATEGARY_TITLE = 3;
    public static final int SUB_CATEGARY_UNKNOWN = 4;

    public static final String[] CATEGARY_DIRS = new String[]{
        "Music Video Subtitles",
        "Movie Subtitles"
    };
    public static final String[] SUB_CATEGARY_DIRS = new String[]{
        "Titles, Artists & Albums",
        "Titles & Artists",
        "Titles & Albums",
        "Titles",
        "Unknown"
    };

    public static final String DELIMITER_ARTIST = " By ";
    public static final String DELIMITER_ALBUM = " Appears On ";

    private final RemoteStorageApis remoteStorage;

    public static synchronized RemoteStorageApis getInstance() {
        return LazyLoader.INSTANCE;
    }

    private RemoteStorage() {
        this.remoteStorage = new DropboxSdk();
    }

    @Override
    public void registerUser(String data) throws RemoteStorageException {
        remoteStorage.registerUser(data);
    }

    @Override
    public List<SearchResult> searchFile(int categary, String query) throws RemoteStorageException {
        return remoteStorage.searchFile(categary, query);
    }

    @Override
    public void downloadSearchedFile(SearchResult searchResult, String outputDir, RemoteProgressListener remoteProgressListener) throws RemoteStorageException, IOException {
        remoteStorage.downloadSearchedFile(searchResult, outputDir, remoteProgressListener);
    }

    @Override
    public void uploadFile(String uploadDir, File file, RemoteProgressListener remoteProgressListener) throws RemoteStorageException, IOException {
        remoteStorage.uploadFile(uploadDir, file, remoteProgressListener);
    }

    private static class LazyLoader {

        private static RemoteStorage INSTANCE = new RemoteStorage();
    }

}
