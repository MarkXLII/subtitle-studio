/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio.dropbox;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.ProgressListener;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import in.swapnilbhoite.projects.subtitlestudio.remote_storage.RemoteProgressListener;
import in.swapnilbhoite.projects.subtitlestudio.remote_storage.RemoteStorage;
import in.swapnilbhoite.projects.subtitlestudio.remote_storage.RemoteStorageApis;
import in.swapnilbhoite.projects.subtitlestudio.remote_storage.RemoteStorageException;
import in.swapnilbhoite.projects.subtitlestudio.remote_storage.SearchResult;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swapnil Bhoite
 */
public class DropboxSdk implements RemoteStorageApis {

    //TODO: Add credentials here
    private static final String APP_KEY = "XXXX";
    private static final String APP_SECRET = "XXXX";
    private static final String AUTH_KEY = "XXXX";
    private static final String AUTH_SECRET = "XXXX";
    private static final Session.AccessType ACCESS_TYPE = Session.AccessType.APP_FOLDER;
    private static final String USERS_FILE_PATH = "/users/";
    private static final int MAX_SEARCH_RESULTS = 100;

    private DropboxAPI<WebAuthSession> dropboxAPI;
    private final DropboxServer mainServer;
    private List<DropboxServer> secondaryServers;
    private final String[] categaryDirs;
    private final String[] subCategaryDirs;

    public DropboxSdk() {
        this.mainServer = new DropboxServer(APP_KEY, APP_SECRET, AUTH_KEY, AUTH_SECRET);
        this.categaryDirs = RemoteStorage.CATEGARY_DIRS;
        this.subCategaryDirs = RemoteStorage.SUB_CATEGARY_DIRS;
    }

    private void initSession() {
        initSession(mainServer);
    }

    private void initSession(DropboxServer server) {
        AppKeyPair appKeyPair = new AppKeyPair(server.APP_KEY, server.APP_SECRET);
        WebAuthSession webAuthSession = new WebAuthSession(appKeyPair, DropboxSdk.ACCESS_TYPE);
        dropboxAPI = new DropboxAPI<WebAuthSession>(webAuthSession);
        AccessTokenPair accessTokenPair = new AccessTokenPair(server.AUTH_KEY, server.AUTH_SECRET);
        dropboxAPI.getSession().setAccessTokenPair(accessTokenPair);
    }

    private void initServers() {
        try {
            initSession();
            secondaryServers = new ArrayList<DropboxServer>();
            OutputStream outputStreamServerCount = new ByteArrayOutputStream();
            OutputStream outputStreamServerList = new ByteArrayOutputStream();
            //ByteArrayOutputStream outputStreamServerStatus = new ByteArrayOutputStream();
            dropboxAPI.getFile("/servers/count.txt", null, outputStreamServerCount, null);
            dropboxAPI.getFile("/servers/serverList.txt", null, outputStreamServerList, null);
            //dropboxAPI.getFile("/servers/serverStatus.txt", null, outputStreamServerStatus, null);

            int serverCount = new Integer(outputStreamServerCount.toString());
            String serverList = outputStreamServerList.toString();
            //String serverStatus = outputStreamServerStatus.toString();
            for (int i = 0; i < (serverCount * 4); i++) {
                String s[] = serverList.split("\n");
                DropboxServer myServer = new DropboxServer(s[i].trim(),
                        s[i + 1].trim(),
                        s[i + 2].trim(),
                        s[i + 3].trim());
                secondaryServers.add(myServer);
                i = i + 3;
            }
        } catch (DropboxException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void registerUser(String data) throws RemoteStorageException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        try {
            initSession();
            dropboxAPI.putFile(USERS_FILE_PATH + data + ".txt",
                    inputStream,
                    data.length(),
                    null,
                    null);
        } catch (DropboxException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteStorageException(ex);
        }
    }

    @Override
    public synchronized List<SearchResult> searchFile(int categary, String query) throws RemoteStorageException {
        String categaryDir = categaryDirs[categary];
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        try {
            initServers();
            for (DropboxServer dropboxServer : secondaryServers) {
                initSession(dropboxServer);
                for (int i = 0; i < subCategaryDirs.length; i++) {
                    String subCategaryDir = subCategaryDirs[i];
                    List<DropboxAPI.Entry> dropboxResults = dropboxAPI.search("/" + categaryDir
                            + "/" + subCategaryDir,
                            query,
                            MAX_SEARCH_RESULTS,
                            false);
                    for (DropboxAPI.Entry entry : dropboxResults) {
                        SearchResult result = new SearchResult(
                                entry.fileName(),
                                entry.parentPath() + "/" + entry.fileName(),
                                categary,
                                i,
                                dropboxServer);
                        entry.parentPath();
                        searchResults.add(result);
                    }
                }
            }
        } catch (DropboxException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteStorageException(ex);
        }
        return searchResults;
    }

    @Override
    public void downloadSearchedFile(final SearchResult searchResult, String oututDir, final RemoteProgressListener remoteProgressListener) throws RemoteStorageException, IOException {
        DropboxServer server = (DropboxServer) searchResult.getExtra();
        try {
            initSession(server);
            OutputStream outputStream = new ByteArrayOutputStream();
            dropboxAPI.getFile(searchResult.getRemotePath(), null, outputStream, new ProgressListener() {
                @Override
                public void onProgress(long bytesDownloaded, long totalBytes) {
                    int progress = (int) ((bytesDownloaded / totalBytes) * 100.0);
                    Logger.getLogger(DropboxSdk.class.getName()).log(Level.INFO, "Download progress for {0}: {1}%", new Object[]{searchResult.getRemotePath(), progress});
                    if (remoteProgressListener != null) {
                        remoteProgressListener.onProgressUpdate(progress, "Downloading " + searchResult.getFileName() + ": " + progress + "%");
                    }
                }
            });
            String content = outputStream.toString();
            String contents[] = content.split("\n");
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(oututDir + "\\" + searchResult.getFileName()));
            for (String line : contents) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteStorageException(ex);
        } catch (DropboxException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteStorageException(ex);
        }
    }

    @Override
    public void uploadFile(final String uploadDir, final File file, final RemoteProgressListener remoteProgressListener) throws RemoteStorageException, IOException {
        try {
            initSession();
            initServers();
            initSession(secondaryServers.get(secondaryServers.size() - 1));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String contents = "", line;
            while ((line = bufferedReader.readLine()) != null) {
                contents = contents + line + "\n";
            }
            InputStream inputStream = new ByteArrayInputStream(contents.getBytes());
            dropboxAPI.putFile(uploadDir, inputStream, contents.length(), null, new ProgressListener() {
                @Override
                public void onProgress(long bytesUploaded, long totalBytes) {
                    int progress = (int) ((bytesUploaded / totalBytes) * 100.0);
                    Logger.getLogger(DropboxSdk.class.getName()).log(Level.INFO, "Upload progress for {0}\\{1}: {2}%", new Object[]{uploadDir, file.getName(), progress});
                    if (remoteProgressListener != null) {
                        remoteProgressListener.onProgressUpdate(progress, "Uploading " + file.getName() + ": " + progress + "%");
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteStorageException(ex);
        } catch (DropboxException ex) {
            Logger.getLogger(DropboxSdk.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteStorageException(ex);
        }
    }

}
