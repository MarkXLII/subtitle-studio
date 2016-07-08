/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.remote_storage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Swapnil Bhoite
 */
public interface RemoteStorageApis {

    void registerUser(String data) throws RemoteStorageException;

    List<SearchResult> searchFile(int categary, String query) throws RemoteStorageException;

    void downloadSearchedFile(SearchResult searchResult, String outputDir, RemoteProgressListener remoteProgressListener) throws RemoteStorageException, IOException;

    void uploadFile(String uploadDir, File file, RemoteProgressListener remoteProgressListener) throws RemoteStorageException, IOException;
}
