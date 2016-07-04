package in.swapnilbhoite.projects.subtitlestudio;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author Swapnil Bhoite
 */
public class Downloader extends javax.swing.JFrame implements Runnable {

    public Downloader() {
        initComponents();
        initiateDownload();
        startTime = System.currentTimeMillis();
    }

    private void login() {
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
        myDropBox = new DropboxAPI<WebAuthSession>(session);
        AccessTokenPair newAuth = new AccessTokenPair(AUTH_KEY, AUTH_SECRET);
        myDropBox.getSession().setAccessTokenPair(newAuth);
    }

    final void initiateDownload() {
        for (int i = 0; i < 100; i++) {
            jTableSearchResults.setValueAt("", i, 0);
            jTableSearchResults.setValueAt("", i, 1);
            jTableSearchResults.setValueAt("", i, 2);
            jTableSearchResults.setValueAt("", i, 3);
        }
        jLabelFileNameValue.setText("-");
        jLabelTitleValue.setText("-");
        jLabelArtistValue.setText("-");
        jLabelAlbumValue.setText("-");
        resultDIR.removeAll(resultDIR);
        serverNo.removeAll(serverNo);
        totalResults = 0;
        jLabelSearchStatusTitle.setText("Search File");
        jLabelSearchStatus.setText("Status");
        jLabelSearchStatusFileName.setText("-");
        jLabelSearchStatusFileTitle.setText("-");
        jLabelSearchStatusFileArtist.setText("-");
        jLabelSearchStatusFileAlbum.setText("-");
        jLabelSearchStatusFileNameValue.setText("-");
        jLabelSearchStatusTitleValue.setText("-");
        jLabelSearchStatusArtistValue.setText("-");
        jLabelSearchStatusAlbumValue.setText("-");
        jLabelSearchStatusFile.setText("File");
        jLabelSearchStatusFileValue.setText("-");
        jProgressBarSearchStatus.setValue(0);
        jButtonSearchStatusSearch.setText("Search");
        searching = false;
        haveOutDir = false;
    }

    void searchFile() {
        int pre = totalResults;

        jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Connecting to Subtitle Studio...");
        jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
        jLabelSearchStatus.setText("Connecting to Subtitle Studio...");
        jLabelSearchStatusFile.setText("Searching");
        jLabelSearchStatusFileValue.setText(" " + searchKey + " On Server" + currentSerevr);
        login();
        jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Connected to Subtitle Studio...");
        jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
        jLabelSearchStatus.setText("Connected to Subtitle Studio...");
        jProgressBarSearchStatus.setValue(17);
        String folder = jComboBoxSearchCategory.getSelectedItem() + "";
        try {
            results = myDropBox.search("/" + folder + "/Titles, Artists & Albums/", searchKey, 100, false);
            for (int i = 0; i < results.size(); i++) {
                resultDIR.add(results.get(i).parentPath());
                jTableSearchResults.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();

                String a[] = temp.split(" By ");
                jTableSearchResults.setValueAt(a[0], totalResults, 1);
                for (int j = 2; j < a.length; j++) {
                    a[1] = a[1] + a[j];
                }

                String b[] = a[1].split(" Appears On ");
                jTableSearchResults.setValueAt(b[0], totalResults, 2);
                for (int j = 2; j < b.length; j++) {
                    b[1] = b[1] + b[j];
                }

                String c[] = b[1].split(".srt");
                c = c[0].split(".SRT");
                jTableSearchResults.setValueAt(c[0], totalResults, 3);

                String d[] = b[1].split(".ssa");
                d = d[0].split(".SSA");
                jTableSearchResults.setValueAt(d[0], totalResults, 3);

                totalResults++;
            }
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Seacrching in Titles, Artists & Albums...");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Seacrching in Titles, Artists & Albums...");
            jProgressBarSearchStatus.setValue(34);

            results = myDropBox.search("/" + folder + "/Titles & Artists/", searchKey, 100, false);
            for (int i = 0; i < results.size(); i++) {
                resultDIR.add(results.get(i).parentPath());
                jTableSearchResults.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();

                String a[] = temp.split(" By ");
                jTableSearchResults.setValueAt(a[0], totalResults, 1);
                for (int j = 2; j < a.length; j++) {
                    a[1] = a[1] + a[j];
                }

                String c[] = a[1].split(".srt");
                c = c[0].split(".SRT");
                jTableSearchResults.setValueAt(c[0], totalResults, 2);

                totalResults++;
            }
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Seacrching in Titles & Artists...");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Seacrching in Titles & Artists...");
            jProgressBarSearchStatus.setValue(51);

            results = myDropBox.search("/" + folder + "/Titles/", searchKey, 100, false);
            for (int i = 0; i < results.size(); i++) {
                resultDIR.add(results.get(i).parentPath());
                jTableSearchResults.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();

                String c[] = temp.split(".srt");
                c = c[0].split(".SRT");
                jTableSearchResults.setValueAt(c[0], totalResults, 1);

                totalResults++;
            }
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Seacrching in Titles...");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Seacrching in Titles...");
            jProgressBarSearchStatus.setValue(68);

            results = myDropBox.search("/" + folder + "/Titles & Albums/", searchKey, 100, false);
            for (int i = 0; i < results.size(); i++) {
                resultDIR.add(results.get(i).parentPath());
                jTableSearchResults.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();

                String b[] = temp.split(" Appears On ");
                jTableSearchResults.setValueAt(b[0], totalResults, 1);
                for (int j = 2; j < b.length; j++) {
                    b[1] = b[1] + b[i];
                }

                String c[] = b[1].split(".srt");
                c = c[0].split(".SRT");
                jTableSearchResults.setValueAt(c[0], totalResults, 3);

                totalResults++;
            }
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Seacrching in Titles & Albums...");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Seacrching in Titles & Albums...");
            jProgressBarSearchStatus.setValue(85);

            results = myDropBox.search("/" + folder + "/Unknown/", searchKey, 100, false);
            for (int i = 0; i < results.size(); i++) {
                resultDIR.add(results.get(i).parentPath());
                jTableSearchResults.setValueAt(results.get(i).fileName(), totalResults, 0);
                totalResults++;
            }
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Seacrching in Unknown...");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Seacrching in Unknown...");
            jProgressBarSearchStatus.setValue(100);
        } catch (DropboxException ex) {
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Error in Search!!!");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Error in Search!!!");
        }

        for (int k = pre; k < totalResults; k++) {
            serverNo.add(currentSerevr);
        }
    }

    void downloadFile(String fileName, String dir) {
        jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Connecting to Subtitle Studio...");
        jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
        jLabelSearchStatus.setText("Connecting to Subtitle Studio...");

        APP_KEY = MainWindow.servers.get(serverNo.get(jTableSearchResults.getSelectedRow())).APPKEY;
        APP_SECRET = MainWindow.servers.get(serverNo.get(jTableSearchResults.getSelectedRow())).APPSECRET;
        AUTH_KEY = MainWindow.servers.get(serverNo.get(jTableSearchResults.getSelectedRow())).KEYTOKEN;
        AUTH_SECRET = MainWindow.servers.get(serverNo.get(jTableSearchResults.getSelectedRow())).SECRETTOKEN;

        APP_KEY = APP_KEY.substring(0, APP_KEY.length() - 1);
        APP_SECRET = APP_SECRET.substring(0, APP_SECRET.length() - 1);
        AUTH_KEY = AUTH_KEY.substring(0, AUTH_KEY.length() - 1);
        if ((serverNo.get(jTableSearchResults.getSelectedRow())) != MainWindow.servers.size() - 1) {
            AUTH_SECRET = AUTH_SECRET.substring(0, AUTH_SECRET.length() - 1);
        }
        login();

        jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Connected to Subtitle Studio...");
        jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
        jLabelSearchStatus.setText("Connected to Subtitle Studio...");
        jProgressBarSearchStatus.setValue(20);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            DropboxAPI.DropboxFileInfo newEntry2 = myDropBox.getFile(dir + "/" + fileName, null, outputStream, null);
            jProgressBarSearchStatus.setValue(40);
        } catch (DropboxException ex) {
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Error Downloading File");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Error Downloading File");
        }
        jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-File downloaded, writing output file - " + fileName + "...");
        jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
        jLabelSearchStatus.setText("File downloaded, writing output file - " + fileName + "...");
        String temp = outputStream.toString();
        String contents[] = temp.split("\n");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outDIR + "\\" + fileName));
        } catch (IOException ex) {
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Error Creating Output File");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Error Creating Output File");
        }
        try {
            int l1 = contents.length;
            for (int i = 0; i < contents.length; i++) {
                writer.write(contents[i]);
                writer.newLine();
                jProgressBarSearchStatus.setValue((int) ((i * 40) / l1) + 60);
            }
            writer.close();
        } catch (IOException ex) {
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Error Wriring Output File");
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Error Writing Output File");
        }

        jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Download Complete");
        jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
        jLabelSearchStatus.setText("Download Complete");
        jLabelSearchStatusTitle.setText("Download Complete");
        jButtonSearchStatusSearch.setText("OK");
        downloading = false;
        jProgressBarSearchStatus.setValue(100);
        jDialogjSearchStatus.setSize(640, 360);
        jDialogjSearchStatus.setLocationRelativeTo(null);
        jDialogjSearchStatus.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogjSearchStatus = new javax.swing.JDialog();
        jPanelSearchStatusParent = new javax.swing.JPanel();
        jPanelSearchStatusTitle = new javax.swing.JPanel();
        jLabelSearchStatusTitle = new javax.swing.JLabel();
        jPanelSearchStatusBody = new javax.swing.JPanel();
        jLabelSearchStatus = new javax.swing.JLabel();
        jLabelSearchStatusFile = new javax.swing.JLabel();
        jLabelSearchStatusFileValue = new javax.swing.JLabel();
        jProgressBarSearchStatus = new javax.swing.JProgressBar();
        jLabelSearchStatusFileName = new javax.swing.JLabel();
        jLabelSearchStatusFileTitle = new javax.swing.JLabel();
        jLabelSearchStatusFileArtist = new javax.swing.JLabel();
        jLabelSearchStatusFileAlbum = new javax.swing.JLabel();
        jLabelSearchStatusFileNameValue = new javax.swing.JLabel();
        jLabelSearchStatusTitleValue = new javax.swing.JLabel();
        jLabelSearchStatusArtistValue = new javax.swing.JLabel();
        jLabelSearchStatusAlbumValue = new javax.swing.JLabel();
        jButtonSearchStatusSearch = new javax.swing.JButton();
        jPanelDownloaderParent = new javax.swing.JPanel();
        jPanelDownloaderTitle = new javax.swing.JPanel();
        jLabelDownloaderTitle = new javax.swing.JLabel();
        jPanelDownloaderBody = new javax.swing.JPanel();
        jTextFieldSearchKeywords = new javax.swing.JTextField();
        jScrollPaneSearchResults = new javax.swing.JScrollPane();
        jTableSearchResults = new javax.swing.JTable();
        jLabelSelectedFile = new javax.swing.JLabel();
        jButtonDownload = new javax.swing.JButton();
        jComboBoxSearchCategory = new javax.swing.JComboBox();
        jButtonSearch = new javax.swing.JButton();
        jLabelFileName = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelArtist = new javax.swing.JLabel();
        jLabelAlbum = new javax.swing.JLabel();
        jLabelFileNameValue = new javax.swing.JLabel();
        jLabelTitleValue = new javax.swing.JLabel();
        jLabelArtistValue = new javax.swing.JLabel();
        jLabelAlbumValue = new javax.swing.JLabel();
        jPanelDownloaderLog = new javax.swing.JPanel();
        jScrollPaneDownloaderLog = new javax.swing.JScrollPane();
        jTextAreaDownloaderLog = new javax.swing.JTextArea();

        jDialogjSearchStatus.setModal(true);

        jPanelSearchStatusParent.setBackground(new java.awt.Color(102, 102, 102));

        jPanelSearchStatusTitle.setBackground(new java.awt.Color(0, 0, 0));
        jPanelSearchStatusTitle.setPreferredSize(new java.awt.Dimension(580, 60));

        jLabelSearchStatusTitle.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        jLabelSearchStatusTitle.setForeground(new java.awt.Color(51, 255, 255));
        jLabelSearchStatusTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSearchStatusTitle.setText("Search File");
        jLabelSearchStatusTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelSearchStatusTitleLayout = new javax.swing.GroupLayout(jPanelSearchStatusTitle);
        jPanelSearchStatusTitle.setLayout(jPanelSearchStatusTitleLayout);
        jPanelSearchStatusTitleLayout.setHorizontalGroup(
            jPanelSearchStatusTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchStatusTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSearchStatusTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelSearchStatusTitleLayout.setVerticalGroup(
            jPanelSearchStatusTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSearchStatusTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSearchStatusTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelSearchStatusBody.setBackground(new java.awt.Color(0, 0, 0));

        jLabelSearchStatus.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatus.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabelSearchStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchStatus.setText("Status");

        jLabelSearchStatusFile.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFile.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchStatusFile.setText("File -");

        jLabelSearchStatusFileValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFileValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelSearchStatusFileValue.setText("-");

        jProgressBarSearchStatus.setStringPainted(true);

        jLabelSearchStatusFileName.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFileName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchStatusFileName.setText("File Name");

        jLabelSearchStatusFileTitle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFileTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchStatusFileTitle.setText("Title");

        jLabelSearchStatusFileArtist.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFileArtist.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchStatusFileArtist.setText("Artist");

        jLabelSearchStatusFileAlbum.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFileAlbum.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSearchStatusFileAlbum.setText("Album");

        jLabelSearchStatusFileNameValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusFileNameValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelSearchStatusFileNameValue.setText("-");

        jLabelSearchStatusTitleValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusTitleValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelSearchStatusTitleValue.setText("-");

        jLabelSearchStatusArtistValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusArtistValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelSearchStatusArtistValue.setText("-");

        jLabelSearchStatusAlbumValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSearchStatusAlbumValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelSearchStatusAlbumValue.setText("-");

        jButtonSearchStatusSearch.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButtonSearchStatusSearch.setText("Search");
        jButtonSearchStatusSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchStatusSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSearchStatusBodyLayout = new javax.swing.GroupLayout(jPanelSearchStatusBody);
        jPanelSearchStatusBody.setLayout(jPanelSearchStatusBodyLayout);
        jPanelSearchStatusBodyLayout.setHorizontalGroup(
            jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                        .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButtonSearchStatusSearch)
                            .addComponent(jProgressBarSearchStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSearchStatusBodyLayout.createSequentialGroup()
                        .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSearchStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                            .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                                .addComponent(jLabelSearchStatusFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelSearchStatusFileValue, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
            .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                        .addComponent(jLabelSearchStatusFileAlbum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSearchStatusAlbumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                        .addComponent(jLabelSearchStatusFileArtist)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSearchStatusArtistValue, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                        .addComponent(jLabelSearchStatusFileTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSearchStatusTitleValue, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                        .addComponent(jLabelSearchStatusFileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSearchStatusFileNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
                .addGap(0, 144, 144))
        );

        jPanelSearchStatusBodyLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelSearchStatusFileAlbum, jLabelSearchStatusFileArtist, jLabelSearchStatusFileName, jLabelSearchStatusFileTitle});

        jPanelSearchStatusBodyLayout.setVerticalGroup(
            jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchStatusBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSearchStatus)
                .addGap(18, 18, 18)
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchStatusFile)
                    .addComponent(jLabelSearchStatusFileValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchStatusFileName)
                    .addComponent(jLabelSearchStatusFileNameValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchStatusFileTitle)
                    .addComponent(jLabelSearchStatusTitleValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchStatusFileArtist)
                    .addComponent(jLabelSearchStatusArtistValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSearchStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearchStatusFileAlbum)
                    .addComponent(jLabelSearchStatusAlbumValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jProgressBarSearchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSearchStatusSearch)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout jPanelSearchStatusParentLayout = new javax.swing.GroupLayout(jPanelSearchStatusParent);
        jPanelSearchStatusParent.setLayout(jPanelSearchStatusParentLayout);
        jPanelSearchStatusParentLayout.setHorizontalGroup(
            jPanelSearchStatusParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSearchStatusParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSearchStatusParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelSearchStatusBody, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSearchStatusTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSearchStatusParentLayout.setVerticalGroup(
            jPanelSearchStatusParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchStatusParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSearchStatusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSearchStatusBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogjSearchStatusLayout = new javax.swing.GroupLayout(jDialogjSearchStatus.getContentPane());
        jDialogjSearchStatus.getContentPane().setLayout(jDialogjSearchStatusLayout);
        jDialogjSearchStatusLayout.setHorizontalGroup(
            jDialogjSearchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSearchStatusParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogjSearchStatusLayout.setVerticalGroup(
            jDialogjSearchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSearchStatusParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subtitle Studio 3.1");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelDownloaderParent.setBackground(new java.awt.Color(51, 51, 51));

        jPanelDownloaderTitle.setBackground(new java.awt.Color(0, 0, 0));
        jPanelDownloaderTitle.setPreferredSize(new java.awt.Dimension(878, 90));

        jLabelDownloaderTitle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelDownloaderTitle.setFont(new java.awt.Font("Candara", 0, 36)); // NOI18N
        jLabelDownloaderTitle.setForeground(new java.awt.Color(0, 255, 255));
        jLabelDownloaderTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDownloaderTitle.setText("Downloader");

        javax.swing.GroupLayout jPanelDownloaderTitleLayout = new javax.swing.GroupLayout(jPanelDownloaderTitle);
        jPanelDownloaderTitle.setLayout(jPanelDownloaderTitleLayout);
        jPanelDownloaderTitleLayout.setHorizontalGroup(
            jPanelDownloaderTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDownloaderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDownloaderTitleLayout.setVerticalGroup(
            jPanelDownloaderTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDownloaderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelDownloaderBody.setBackground(new java.awt.Color(0, 0, 0));
        jPanelDownloaderBody.setPreferredSize(new java.awt.Dimension(392, 385));

        jTextFieldSearchKeywords.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldSearchKeywords.setText("Search Keywords (Minimum 3 characters)");

        jTableSearchResults.setBackground(new java.awt.Color(51, 51, 51));
        jTableSearchResults.setForeground(new java.awt.Color(255, 255, 255));
        jTableSearchResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "File Name", "Title", "Artist", "Album"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSearchResults.getTableHeader().setReorderingAllowed(false);
        jTableSearchResults.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSearchResultsMouseClicked(evt);
            }
        });
        jScrollPaneSearchResults.setViewportView(jTableSearchResults);

        jLabelSelectedFile.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSelectedFile.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSelectedFile.setText("Selected File -");

        jButtonDownload.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButtonDownload.setText("Download");
        jButtonDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDownloadActionPerformed(evt);
            }
        });

        jComboBoxSearchCategory.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jComboBoxSearchCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Music Video Subtitles", "Movie Subtitles" }));

        jButtonSearch.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButtonSearch.setText("Search IN >>");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jLabelFileName.setBackground(new java.awt.Color(0, 0, 0));
        jLabelFileName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFileName.setText("File Name");

        jLabelTitle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("Title");

        jLabelArtist.setBackground(new java.awt.Color(0, 0, 0));
        jLabelArtist.setForeground(new java.awt.Color(255, 255, 255));
        jLabelArtist.setText("Artist");

        jLabelAlbum.setBackground(new java.awt.Color(51, 51, 51));
        jLabelAlbum.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAlbum.setText("Album");

        jLabelFileNameValue.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFileNameValue.setText("-");

        jLabelTitleValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTitleValue.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitleValue.setText("-");

        jLabelArtistValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelArtistValue.setForeground(new java.awt.Color(255, 255, 255));
        jLabelArtistValue.setText("-");

        jLabelAlbumValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAlbumValue.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAlbumValue.setText("-");

        javax.swing.GroupLayout jPanelDownloaderBodyLayout = new javax.swing.GroupLayout(jPanelDownloaderBody);
        jPanelDownloaderBody.setLayout(jPanelDownloaderBodyLayout);
        jPanelDownloaderBodyLayout.setHorizontalGroup(
            jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneSearchResults, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                        .addComponent(jTextFieldSearchKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSearchCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonDownload)
                    .addComponent(jLabelSelectedFile)
                    .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                                .addComponent(jLabelFileName)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelFileNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                                .addComponent(jLabelTitle)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelTitleValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                                .addComponent(jLabelAlbum)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAlbumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                                .addComponent(jLabelArtist)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelArtistValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanelDownloaderBodyLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelAlbum, jLabelArtist, jLabelFileName, jLabelTitle});

        jPanelDownloaderBodyLayout.setVerticalGroup(
            jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearchKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSearchCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneSearchResults, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelSelectedFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFileName)
                    .addComponent(jLabelFileNameValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitle)
                    .addComponent(jLabelTitleValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelArtist)
                    .addComponent(jLabelArtistValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDownloaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAlbum)
                    .addComponent(jLabelAlbumValue))
                .addGap(11, 11, 11)
                .addComponent(jButtonDownload)
                .addContainerGap())
        );

        jPanelDownloaderBodyLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonSearch, jComboBoxSearchCategory, jTextFieldSearchKeywords});

        jPanelDownloaderLog.setBackground(new java.awt.Color(0, 0, 0));

        jTextAreaDownloaderLog.setEditable(false);
        jTextAreaDownloaderLog.setBackground(new java.awt.Color(0, 0, 0));
        jTextAreaDownloaderLog.setColumns(20);
        jTextAreaDownloaderLog.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jTextAreaDownloaderLog.setForeground(new java.awt.Color(102, 255, 51));
        jTextAreaDownloaderLog.setRows(5);
        jTextAreaDownloaderLog.setText("Log");
        jScrollPaneDownloaderLog.setViewportView(jTextAreaDownloaderLog);

        javax.swing.GroupLayout jPanelDownloaderLogLayout = new javax.swing.GroupLayout(jPanelDownloaderLog);
        jPanelDownloaderLog.setLayout(jPanelDownloaderLogLayout);
        jPanelDownloaderLogLayout.setHorizontalGroup(
            jPanelDownloaderLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDownloaderLog, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDownloaderLogLayout.setVerticalGroup(
            jPanelDownloaderLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDownloaderLog)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelDownloaderParentLayout = new javax.swing.GroupLayout(jPanelDownloaderParent);
        jPanelDownloaderParent.setLayout(jPanelDownloaderParentLayout);
        jPanelDownloaderParentLayout.setHorizontalGroup(
            jPanelDownloaderParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDownloaderParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDownloaderParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDownloaderLog, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDownloaderTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(jPanelDownloaderBody, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelDownloaderParentLayout.setVerticalGroup(
            jPanelDownloaderParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloaderParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDownloaderTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDownloaderBody, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDownloaderLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDownloaderParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDownloaderParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        if (!searching) {
            searchKey = jTextFieldSearchKeywords.getText();
            if (searchKey.length() >= 3) {
                initiateDownload();
                searchClick = true;
                call = 2;
                new Thread(this).start();
            } else {
                jTextFieldSearchKeywords.setText("Enter minimum 3 characters...");
            }
        } else {
            jDialogjSearchStatus.setSize(640, 300);
            jDialogjSearchStatus.setLocationRelativeTo(null);
            jDialogjSearchStatus.setVisible(true);
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonSearchStatusSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchStatusSearchActionPerformed
        if (searchClick) {
            if (jButtonSearchStatusSearch.getText().equals("OK")) {
                jDialogjSearchStatus.dispose();
            } else if (!searching) {
                jButtonSearchStatusSearch.setText("Hide");
                call = 3;
                new Thread(this).start();
                searching = true;
                jTextFieldSearchKeywords.setEditable(false);
            } else {
                jDialogjSearchStatus.dispose();
            }
        } else if (jButtonSearchStatusSearch.getText().equals("OK")) {
            jDialogjSearchStatus.dispose();
        } else if (!downloading) {
            jButtonSearchStatusSearch.setText("Hide");
            call = 5;
            new Thread(this).start();
            downloading = true;
        } else {
            jDialogjSearchStatus.dispose();
        }
    }//GEN-LAST:event_jButtonSearchStatusSearchActionPerformed

    private void jTableSearchResultsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSearchResultsMouseClicked
        if (totalResults != 0) {
            int fileNo = jTableSearchResults.getSelectedRow();
            if (fileNo < totalResults) {
                jLabelFileNameValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 0));
                jLabelTitleValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 1));
                jLabelArtistValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 2));
                jLabelAlbumValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 3));
            } else {
                jLabelFileNameValue.setText("-");
                jLabelTitleValue.setText("-");
                jLabelArtistValue.setText("-");
                jLabelAlbumValue.setText("-");
            }
        }
    }//GEN-LAST:event_jTableSearchResultsMouseClicked

    private void jButtonDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDownloadActionPerformed
        if (totalResults > 0 && !searching && !"-".equals(jLabelFileNameValue.getText())) {
            if (downloading) {
                jDialogjSearchStatus.setSize(640, 360);
                jDialogjSearchStatus.setLocationRelativeTo(null);
                jDialogjSearchStatus.setVisible(true);
            } else {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.setVisible(true);
                jfc.showSaveDialog(this);
                if (jfc.getSelectedFile() != null) {
                    outDIR = jfc.getSelectedFile() + "";
                    call = 4;
                    searchClick = false;
                    new Thread(this).start();
                }
            }
        }
    }//GEN-LAST:event_jButtonDownloadActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MainWindow.getFrames()[0].setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDownload;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSearchStatusSearch;
    private javax.swing.JComboBox jComboBoxSearchCategory;
    private javax.swing.JDialog jDialogjSearchStatus;
    private javax.swing.JLabel jLabelAlbum;
    private javax.swing.JLabel jLabelAlbumValue;
    private javax.swing.JLabel jLabelArtist;
    private javax.swing.JLabel jLabelArtistValue;
    private javax.swing.JLabel jLabelDownloaderTitle;
    private javax.swing.JLabel jLabelFileName;
    private javax.swing.JLabel jLabelFileNameValue;
    private javax.swing.JLabel jLabelSearchStatus;
    private javax.swing.JLabel jLabelSearchStatusAlbumValue;
    private javax.swing.JLabel jLabelSearchStatusArtistValue;
    private javax.swing.JLabel jLabelSearchStatusFile;
    private javax.swing.JLabel jLabelSearchStatusFileAlbum;
    private javax.swing.JLabel jLabelSearchStatusFileArtist;
    private javax.swing.JLabel jLabelSearchStatusFileName;
    private javax.swing.JLabel jLabelSearchStatusFileNameValue;
    private javax.swing.JLabel jLabelSearchStatusFileTitle;
    private javax.swing.JLabel jLabelSearchStatusFileValue;
    private javax.swing.JLabel jLabelSearchStatusTitle;
    private javax.swing.JLabel jLabelSearchStatusTitleValue;
    private javax.swing.JLabel jLabelSelectedFile;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitleValue;
    private javax.swing.JPanel jPanelDownloaderBody;
    private javax.swing.JPanel jPanelDownloaderLog;
    private javax.swing.JPanel jPanelDownloaderParent;
    private javax.swing.JPanel jPanelDownloaderTitle;
    private javax.swing.JPanel jPanelSearchStatusBody;
    private javax.swing.JPanel jPanelSearchStatusParent;
    private javax.swing.JPanel jPanelSearchStatusTitle;
    private javax.swing.JProgressBar jProgressBarSearchStatus;
    private javax.swing.JScrollPane jScrollPaneDownloaderLog;
    private javax.swing.JScrollPane jScrollPaneSearchResults;
    private javax.swing.JTable jTableSearchResults;
    private javax.swing.JTextArea jTextAreaDownloaderLog;
    private javax.swing.JTextField jTextFieldSearchKeywords;
    // End of variables declaration//GEN-END:variables

    //DROPBOX
    private static String APP_KEY;
    private static String APP_SECRET;
    private static String AUTH_KEY;
    private static String AUTH_SECRET;
    private static final AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    private static DropboxAPI<WebAuthSession> myDropBox;
    //END DROPBOX

    //MyVariabels
    int call = 0;
    long startTime = 0;
    String searchKey = "";
    List<DropboxAPI.Entry> results;
    List<Integer> serverNo = new ArrayList<Integer>(1);
    List<String> resultDIR = new ArrayList<String>(1);
    int totalResults;
    boolean searching = false;
    boolean downloading = false;
    boolean haveOutDir = false;
    boolean searchClick;
    String outDIR = "";
    int currentSerevr;
    //END MyVariabels

    @Override
    public void run() {
        if (call == 2) {
            //search
            jLabelSearchStatusFileName.setText("");
            jLabelSearchStatusFileTitle.setText("");
            jLabelSearchStatusFileArtist.setText("");
            jLabelSearchStatusFileAlbum.setText("");
            jLabelSearchStatusFileNameValue.setText("");
            jLabelSearchStatusTitleValue.setText("");
            jLabelSearchStatusArtistValue.setText("");
            jLabelSearchStatusAlbumValue.setText("");
            jLabelSearchStatusFileValue.setText(searchKey);

            jDialogjSearchStatus.setSize(640, 300);
            jDialogjSearchStatus.setLocationRelativeTo(null);
            jDialogjSearchStatus.setVisible(true);
        } else if (call == 3) {
            //other
            jLabelSearchStatusTitle.setText("Searching File");

            for (int i = 0; i < MainWindow.servers.size(); i++) {
                currentSerevr = i;
                APP_KEY = MainWindow.servers.get(i).APPKEY;
                APP_SECRET = MainWindow.servers.get(i).APPSECRET;
                AUTH_KEY = MainWindow.servers.get(i).KEYTOKEN;
                AUTH_SECRET = MainWindow.servers.get(i).SECRETTOKEN;

                APP_KEY = APP_KEY.substring(0, APP_KEY.length() - 1);
                APP_SECRET = APP_SECRET.substring(0, APP_SECRET.length() - 1);
                AUTH_KEY = AUTH_KEY.substring(0, AUTH_KEY.length() - 1);
                if (i != MainWindow.servers.size() - 1) {
                    AUTH_SECRET = AUTH_SECRET.substring(0, AUTH_SECRET.length() - 1);
                }

                searchFile();
            }

            searching = false;
            jTextFieldSearchKeywords.setEditable(true);
            jLabelSearchStatusTitle.setText("Search");
            jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Match found - " + totalResults);
            jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
            jLabelSearchStatus.setText("Match found - " + totalResults);
            jButtonSearchStatusSearch.setText("OK");
            jDialogjSearchStatus.setSize(640, 300);
            jDialogjSearchStatus.setLocationRelativeTo(null);
            jDialogjSearchStatus.setVisible(true);
            jTableSearchResults.setRowSelectionInterval(0, 0);
            jTableSearchResultsMouseClicked(null);
        } else if (call == 4) {
            //download
            jLabelSearchStatusTitle.setText("Download File");
            jLabelSearchStatus.setText("Status");
            jLabelSearchStatusFile.setText("Saving to - ");
            jLabelSearchStatusFileValue.setText(outDIR);

            int fileNo = jTableSearchResults.getSelectedRow();
            jLabelSearchStatusFileName.setText("File Name");
            jLabelSearchStatusFileTitle.setText("Title");
            jLabelSearchStatusFileArtist.setText("Artist");
            jLabelSearchStatusFileAlbum.setText("Album");

            jLabelSearchStatusFileNameValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 0));
            jLabelSearchStatusTitleValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 1));
            jLabelSearchStatusArtistValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 2));
            jLabelSearchStatusAlbumValue.setText("- " + jTableSearchResults.getValueAt(fileNo, 3));

            jProgressBarSearchStatus.setValue(0);
            jButtonSearchStatusSearch.setText("Download Now");

            jDialogjSearchStatus.setSize(640, 360);
            jDialogjSearchStatus.setLocationRelativeTo(null);
            jDialogjSearchStatus.setVisible(true);
        } else {
            jLabelSearchStatusTitle.setText("Downloading File");
            try {
                downloadFile(jTableSearchResults.getValueAt(jTableSearchResults.getSelectedRow(), 0) + "", resultDIR.get(jTableSearchResults.getSelectedRow()));
            } catch (Exception ex) {
                jTextAreaDownloaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Error in input table");
                jTextAreaDownloaderLog.setCaretPosition(jTextAreaDownloaderLog.getText().length());
                jLabelSearchStatus.setText("Error Downloading File");
            }
        }
    }
}
