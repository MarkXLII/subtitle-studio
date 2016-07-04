/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Uploader.java
 *
 * Created on 23 Apr, 2012, 1:23:39 PM
 */
package in.swapnilbhoite.projects.subtitlestudio;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.ProgressListener;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Swapnil Bhoite
 */
public class Uploader extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Uploader
     */
    public Uploader() {
        initComponents();
        initiateUpload();
        startTime = System.currentTimeMillis();
    }

    private void login() {
        APP_KEY = APP_KEY.substring(0, APP_KEY.length() - 1);
        APP_SECRET = APP_SECRET.substring(0, APP_SECRET.length() - 1);
        AUTH_KEY = AUTH_KEY.substring(0, AUTH_KEY.length() - 1);

        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
        myDropBox = new DropboxAPI<WebAuthSession>(session);
        AccessTokenPair newAuth = new AccessTokenPair(AUTH_KEY, AUTH_SECRET);
        myDropBox.getSession().setAccessTokenPair(newAuth);
    }

    final void initiateUpload() {
        jLabelUploadHint.setText("Please enter Title, Artist & Album, It will make SEARCH easy");
        for (int i = 0; i < 100; i++) {
            jTableSourceFiles.setValueAt("", i, 0);
            jTableSourceFiles.setValueAt("", i, 1);
            jTableSourceFiles.setValueAt("", i, 2);
            jTableSourceFiles.setValueAt("", i, 3);
        }
        jButtonUpload.setText("Upload All In >> ");
        uploadInProgress = false;
        uploadComplete = false;
        totalFiles = 0;
        files.removeAll(files);
        uploadDIR.removeAll(uploadDIR);
        jLabelUploadStatusTitle.setText("Upload Files");
        jLabelStatus.setText("Status");
        jLabelCurrentFileValue.setText("-");
        jLabelTotalProgressValue.setText("0/0");
        jButtonStartUpload.setText("Start Upload");
        jProgressBarUploadStatusCurrent.setValue(0);
        jProgressBarUploadStatusTotal.setValue(0);
        folder = "Music Video Subtitles";
        jComboBoxUploadCategory.setSelectedIndex(0);
    }

    void uploadFile(String fileName, String contents, String dir) {
        ProgressListener pl = new ProgressListener() {
            @Override
            public long progressInterval() {
                return 1;
            }

            @Override
            public void onProgress(long l, long l1) {
                jProgressBarUploadStatusCurrent.setValue((int) ((l * 100) / l1));
            }
        };
        dir = "/" + jComboBoxUploadCategory.getSelectedItem() + dir;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(contents.getBytes());
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-" + fileName + " Ready for uploading...");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        jLabelStatus.setText(fileName + " Ready for uploading...");
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Uploading " + fileName + "...");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        jLabelStatus.setText("Uploading " + fileName + "...");
        try {
            DropboxAPI.Entry entry1 = myDropBox.putFile(dir + fileName, inputStream, contents.length(), null, pl);
        } catch (DropboxException ex) {
            jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-ERROR while uploading " + fileName + "!!!");
            jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
            jLabelStatus.setText("ERROR while uploading " + fileName + "!!!");
        }
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-" + fileName + " Uploaded...");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        jLabelStatus.setText(fileName + " Uploaded...");
        jProgressBarUploadStatusCurrent.setValue(100);
    }

    void startUpload() {
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Connecting to Subtitle Studio...");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        jLabelStatus.setText("Connecting to Subtitle Studio...");
        login();
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Connected to Subtitle Studio...");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        jLabelStatus.setText("Connected to Subtitle Studio...");
        for (int i = 0; i < totalFiles; i++) {
            String fileName = "" + jTableSourceFiles.getValueAt(i, 0);
            jLabelCurrentFileValue.setText(fileName);
            jLabelTotalProgressValue.setText((i + 1) + "/" + (totalFiles));
            jProgressBarUploadStatusTotal.setValue(i * 100 / totalFiles);
            jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Preparing " + fileName + " for upload...");
            jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
            jLabelStatus.setText("Preparing " + fileName + " for upload...");
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(files.get(i)));
            } catch (FileNotFoundException ex) {
                jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-ERROR " + fileName + "not found!!!");
                jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
                jLabelStatus.setText("ERROR " + fileName + "not found!!!");
            }
            String contents = "", line = "";
            try {
                while ((line = br.readLine()) != null) {
                    contents = contents + line + "\n";
                }
            } catch (IOException ex) {
                jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-ERROR Reading" + fileName + " !!!");
                jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
                jLabelStatus.setText("ERROR Reading" + fileName + " !!!");
            }
            uploadFile(fileName, contents, uploadDIR.get(i));
        }
        jProgressBarUploadStatusTotal.setValue(100);
        uploadComplete = true;
        uploadInProgress = false;
        jButtonStartUpload.setText("Close");
        jLabelUploadStatusTitle.setText("Upload Finish");
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "- " + files.size() + " Files Uploaded");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        jLabelStatus.setText(files.size() + " Files Uploaded");
        jDialogUploadStatus.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogUploadStatus = new javax.swing.JDialog();
        jPanelUploadStatusParent = new javax.swing.JPanel();
        jPanelUploadStatusTitle = new javax.swing.JPanel();
        jLabelUploadStatusTitle = new javax.swing.JLabel();
        jPanelUploadStatusBody = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jLabelCurrentFile = new javax.swing.JLabel();
        jLabelCurrentFileValue = new javax.swing.JLabel();
        jProgressBarUploadStatusCurrent = new javax.swing.JProgressBar();
        jLabelTotalProgress = new javax.swing.JLabel();
        jLabelTotalProgressValue = new javax.swing.JLabel();
        jProgressBarUploadStatusTotal = new javax.swing.JProgressBar();
        jButtonStartUpload = new javax.swing.JButton();
        jPanelUploaderParent = new javax.swing.JPanel();
        jPanelUploaderTitle = new javax.swing.JPanel();
        jLabelUploaderTitle = new javax.swing.JLabel();
        jPanelUploaderBody = new javax.swing.JPanel();
        jButtonAddFiles = new javax.swing.JButton();
        jLabelUploadHint = new javax.swing.JLabel();
        jScrollPaneSourceFiles = new javax.swing.JScrollPane();
        jTableSourceFiles = new javax.swing.JTable();
        jButtonUpload = new javax.swing.JButton();
        jComboBoxUploadCategory = new javax.swing.JComboBox();
        jPanelUploaderLog = new javax.swing.JPanel();
        jScrollPaneUploaderLog = new javax.swing.JScrollPane();
        jTextAreaUploaderLog = new javax.swing.JTextArea();

        jDialogUploadStatus.setModal(true);

        jPanelUploadStatusParent.setBackground(new java.awt.Color(102, 102, 102));

        jPanelUploadStatusTitle.setBackground(new java.awt.Color(0, 0, 0));
        jPanelUploadStatusTitle.setPreferredSize(new java.awt.Dimension(580, 60));

        jLabelUploadStatusTitle.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        jLabelUploadStatusTitle.setForeground(new java.awt.Color(51, 255, 255));
        jLabelUploadStatusTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUploadStatusTitle.setText("Upload Files");
        jLabelUploadStatusTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelUploadStatusTitleLayout = new javax.swing.GroupLayout(jPanelUploadStatusTitle);
        jPanelUploadStatusTitle.setLayout(jPanelUploadStatusTitleLayout);
        jPanelUploadStatusTitleLayout.setHorizontalGroup(
            jPanelUploadStatusTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploadStatusTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploadStatusTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelUploadStatusTitleLayout.setVerticalGroup(
            jPanelUploadStatusTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUploadStatusTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploadStatusTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelUploadStatusBody.setBackground(new java.awt.Color(0, 0, 0));

        jLabelStatus.setBackground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setText("Status");

        jLabelCurrentFile.setBackground(new java.awt.Color(0, 0, 0));
        jLabelCurrentFile.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCurrentFile.setText("Current File -");

        jLabelCurrentFileValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelCurrentFileValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelCurrentFileValue.setText("-");

        jProgressBarUploadStatusCurrent.setStringPainted(true);

        jLabelTotalProgress.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTotalProgress.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalProgress.setText("Total Progress -");

        jLabelTotalProgressValue.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTotalProgressValue.setForeground(new java.awt.Color(204, 255, 255));
        jLabelTotalProgressValue.setText("0/0");

        jProgressBarUploadStatusTotal.setStringPainted(true);

        jButtonStartUpload.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButtonStartUpload.setText("Start Upload");
        jButtonStartUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartUploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUploadStatusBodyLayout = new javax.swing.GroupLayout(jPanelUploadStatusBody);
        jPanelUploadStatusBody.setLayout(jPanelUploadStatusBodyLayout);
        jPanelUploadStatusBodyLayout.setHorizontalGroup(
            jPanelUploadStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploadStatusBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploadStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jProgressBarUploadStatusTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBarUploadStatusCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanelUploadStatusBodyLayout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jButtonStartUpload)
                .addGap(236, 236, 236))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUploadStatusBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploadStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUploadStatusBodyLayout.createSequentialGroup()
                        .addComponent(jLabelCurrentFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentFileValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(jPanelUploadStatusBodyLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelTotalProgress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTotalProgressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUploadStatusBodyLayout.setVerticalGroup(
            jPanelUploadStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploadStatusBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelStatus)
                .addGap(18, 18, 18)
                .addGroup(jPanelUploadStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCurrentFileValue)
                    .addComponent(jLabelCurrentFile, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jProgressBarUploadStatusCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelUploadStatusBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalProgress)
                    .addComponent(jLabelTotalProgressValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBarUploadStatusTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonStartUpload)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelUploadStatusParentLayout = new javax.swing.GroupLayout(jPanelUploadStatusParent);
        jPanelUploadStatusParent.setLayout(jPanelUploadStatusParentLayout);
        jPanelUploadStatusParentLayout.setHorizontalGroup(
            jPanelUploadStatusParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUploadStatusParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploadStatusParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelUploadStatusBody, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelUploadStatusTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelUploadStatusParentLayout.setVerticalGroup(
            jPanelUploadStatusParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploadStatusParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUploadStatusTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelUploadStatusBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogUploadStatusLayout = new javax.swing.GroupLayout(jDialogUploadStatus.getContentPane());
        jDialogUploadStatus.getContentPane().setLayout(jDialogUploadStatusLayout);
        jDialogUploadStatusLayout.setHorizontalGroup(
            jDialogUploadStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUploadStatusParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogUploadStatusLayout.setVerticalGroup(
            jDialogUploadStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUploadStatusParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subtitle Studio 3.1");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelUploaderParent.setBackground(new java.awt.Color(102, 102, 102));

        jPanelUploaderTitle.setBackground(new java.awt.Color(0, 0, 0));
        jPanelUploaderTitle.setPreferredSize(new java.awt.Dimension(878, 90));

        jLabelUploaderTitle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelUploaderTitle.setFont(new java.awt.Font("Candara", 0, 36)); // NOI18N
        jLabelUploaderTitle.setForeground(new java.awt.Color(0, 255, 255));
        jLabelUploaderTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUploaderTitle.setText("Upload");
        jLabelUploaderTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelUploaderTitleLayout = new javax.swing.GroupLayout(jPanelUploaderTitle);
        jPanelUploaderTitle.setLayout(jPanelUploaderTitleLayout);
        jPanelUploaderTitleLayout.setHorizontalGroup(
            jPanelUploaderTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploaderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelUploaderTitleLayout.setVerticalGroup(
            jPanelUploaderTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploaderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelUploaderBody.setBackground(new java.awt.Color(0, 0, 0));
        jPanelUploaderBody.setPreferredSize(new java.awt.Dimension(392, 385));

        jButtonAddFiles.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButtonAddFiles.setText("Add Files");
        jButtonAddFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFilesActionPerformed(evt);
            }
        });

        jLabelUploadHint.setBackground(new java.awt.Color(0, 0, 0));
        jLabelUploadHint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUploadHint.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUploadHint.setText("Please enter Title, Artist & Album, It will make SEARCH easy");

        jTableSourceFiles.setBackground(new java.awt.Color(51, 51, 51));
        jTableSourceFiles.setForeground(new java.awt.Color(255, 255, 255));
        jTableSourceFiles.setModel(new javax.swing.table.DefaultTableModel(
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
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSourceFiles.getTableHeader().setReorderingAllowed(false);
        jTableSourceFiles.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableSourceFilesPropertyChange(evt);
            }
        });
        jScrollPaneSourceFiles.setViewportView(jTableSourceFiles);

        jButtonUpload.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButtonUpload.setText("Upload All In >> ");
        jButtonUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadActionPerformed(evt);
            }
        });

        jComboBoxUploadCategory.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jComboBoxUploadCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Music Video Subtitles", "Movie Subtitles" }));

        javax.swing.GroupLayout jPanelUploaderBodyLayout = new javax.swing.GroupLayout(jPanelUploaderBody);
        jPanelUploaderBody.setLayout(jPanelUploaderBodyLayout);
        jPanelUploaderBodyLayout.setHorizontalGroup(
            jPanelUploaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneSourceFiles)
                    .addGroup(jPanelUploaderBodyLayout.createSequentialGroup()
                        .addGroup(jPanelUploaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUploaderBodyLayout.createSequentialGroup()
                                .addComponent(jButtonUpload)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxUploadCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelUploaderBodyLayout.createSequentialGroup()
                                .addComponent(jButtonAddFiles)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelUploadHint)))
                        .addGap(0, 285, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelUploaderBodyLayout.setVerticalGroup(
            jPanelUploaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddFiles)
                    .addComponent(jLabelUploadHint, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneSourceFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelUploaderBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUpload)
                    .addComponent(jComboBoxUploadCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jPanelUploaderBodyLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonUpload, jComboBoxUploadCategory});

        jPanelUploaderLog.setBackground(new java.awt.Color(0, 0, 0));

        jTextAreaUploaderLog.setEditable(false);
        jTextAreaUploaderLog.setBackground(new java.awt.Color(0, 0, 0));
        jTextAreaUploaderLog.setColumns(20);
        jTextAreaUploaderLog.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jTextAreaUploaderLog.setForeground(new java.awt.Color(102, 255, 51));
        jTextAreaUploaderLog.setRows(5);
        jTextAreaUploaderLog.setText("Log");
        jScrollPaneUploaderLog.setViewportView(jTextAreaUploaderLog);

        javax.swing.GroupLayout jPanelUploaderLogLayout = new javax.swing.GroupLayout(jPanelUploaderLog);
        jPanelUploaderLog.setLayout(jPanelUploaderLogLayout);
        jPanelUploaderLogLayout.setHorizontalGroup(
            jPanelUploaderLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneUploaderLog)
                .addContainerGap())
        );
        jPanelUploaderLogLayout.setVerticalGroup(
            jPanelUploaderLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneUploaderLog, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelUploaderParentLayout = new javax.swing.GroupLayout(jPanelUploaderParent);
        jPanelUploaderParent.setLayout(jPanelUploaderParentLayout);
        jPanelUploaderParentLayout.setHorizontalGroup(
            jPanelUploaderParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUploaderParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploaderParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUploaderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jPanelUploaderBody, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jPanelUploaderLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelUploaderParentLayout.setVerticalGroup(
            jPanelUploaderParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploaderParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUploaderTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelUploaderBody, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelUploaderLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUploaderParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUploaderParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFilesActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Subtitle Files", "srt", "ssa"));
        jfc.setVisible(true);
        jfc.showOpenDialog(this);
        File tempFiles[] = jfc.getSelectedFiles();
        for (int i = 0; i < tempFiles.length; i++) {
            String ext = tempFiles[i].getName();
            ext = ext.substring(ext.length() - 3);
            if (ext.equals("srt") || ext.equals("SRT") || ext.equals("ssa") || ext.equals("SSA")) {
                files.add(tempFiles[i]);
                uploadDIR.add("/Unknown/");
                jTableSourceFiles.setValueAt(files.get(totalFiles).getName(), totalFiles, 0);
                totalFiles++;
            }
        }
        jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-" + tempFiles.length + " Files added to upload list");
        jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
    }//GEN-LAST:event_jButtonAddFilesActionPerformed

    private void jButtonStartUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartUploadActionPerformed
        if (!uploadInProgress && !uploadComplete) {
            call = 1;
            new Thread(this).start();
            jButtonStartUpload.setText("Hide");
            jLabelUploadStatusTitle.setText("Uploading Files");
        } else if (uploadInProgress && !uploadComplete) {
            jDialogUploadStatus.dispose();
        } else {
            initiateUpload();
            jDialogUploadStatus.dispose();
        }
    }//GEN-LAST:event_jButtonStartUploadActionPerformed

    private void jTableSourceFilesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableSourceFilesPropertyChange
        if (totalFiles != 0) {
            if (jTableSourceFiles.getEditingRow() != -1 && jTableSourceFiles.getEditingColumn() != -1) {
                rowInU = jTableSourceFiles.getEditingRow();
            }
            if (!jTableSourceFiles.isEditing() && (rowInU < totalFiles)) {
                String title = "" + jTableSourceFiles.getValueAt(rowInU, 1);
                String artist = "" + jTableSourceFiles.getValueAt(rowInU, 2);
                String album = "" + jTableSourceFiles.getValueAt(rowInU, 3);
                String ext = jTableSourceFiles.getValueAt(rowInU, 0).toString();
                ext = "." + ext.substring(ext.length() - 3);
                if (!("".equals(title))) {
                    if (("".equals(artist)) && ("".equals(album))) {
                        uploadDIR.set(rowInU, "/Titles/");
                        jTableSourceFiles.setValueAt(title + ext, rowInU, 0);
                    } else if ((!("".equals(artist))) && ("".equals(album))) {
                        uploadDIR.set(rowInU, "/Titles & Artists/");
                        jTableSourceFiles.setValueAt(title + " By " + artist + ext, rowInU, 0);
                    } else if (("".equals(artist)) && (!("".equals(album)))) {
                        uploadDIR.set(rowInU, "/Titles & Albums/");
                        jTableSourceFiles.setValueAt(title + " Appears On " + album + ext, rowInU, 0);
                    } else {
                        uploadDIR.set(rowInU, "/Titles, Artists & Albums/");
                        jTableSourceFiles.setValueAt(title + " By " + artist + " Appears On " + album + ext, rowInU, 0);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTableSourceFilesPropertyChange

    private void jButtonUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadActionPerformed
        if (totalFiles != 0) {
            call = 0;
            new Thread(this).start();
        } else {
            jTextAreaUploaderLog.append("\n" + new MyTime(System.currentTimeMillis() - startTime) + "-Please add some files first");
            jTextAreaUploaderLog.setCaretPosition(jTextAreaUploaderLog.getText().length());
        }
    }//GEN-LAST:event_jButtonUploadActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MainWindow.getFrames()[0].setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddFiles;
    private javax.swing.JButton jButtonStartUpload;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JComboBox jComboBoxUploadCategory;
    private javax.swing.JDialog jDialogUploadStatus;
    private javax.swing.JLabel jLabelCurrentFile;
    private javax.swing.JLabel jLabelCurrentFileValue;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTotalProgress;
    private javax.swing.JLabel jLabelTotalProgressValue;
    private javax.swing.JLabel jLabelUploadHint;
    private javax.swing.JLabel jLabelUploadStatusTitle;
    private javax.swing.JLabel jLabelUploaderTitle;
    private javax.swing.JPanel jPanelUploadStatusBody;
    private javax.swing.JPanel jPanelUploadStatusParent;
    private javax.swing.JPanel jPanelUploadStatusTitle;
    private javax.swing.JPanel jPanelUploaderBody;
    private javax.swing.JPanel jPanelUploaderLog;
    private javax.swing.JPanel jPanelUploaderParent;
    private javax.swing.JPanel jPanelUploaderTitle;
    private javax.swing.JProgressBar jProgressBarUploadStatusCurrent;
    private javax.swing.JProgressBar jProgressBarUploadStatusTotal;
    private javax.swing.JScrollPane jScrollPaneSourceFiles;
    private javax.swing.JScrollPane jScrollPaneUploaderLog;
    private javax.swing.JTable jTableSourceFiles;
    private javax.swing.JTextArea jTextAreaUploaderLog;
    // End of variables declaration//GEN-END:variables

    //DROPBOX
    private static String APP_KEY = MainWindow.servers.get(MainWindow.servers.size() - 1).APPKEY;
    private static String APP_SECRET = MainWindow.servers.get(MainWindow.servers.size() - 1).APPSECRET;
    private static String AUTH_KEY = MainWindow.servers.get(MainWindow.servers.size() - 1).KEYTOKEN;
    private static String AUTH_SECRET = MainWindow.servers.get(MainWindow.servers.size() - 1).SECRETTOKEN;
    private static AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    private static DropboxAPI<WebAuthSession> myDropBox;
    //END DROPBOX

    //MyVariabels
    int call = 0;
    List<File> files = new ArrayList<File>(1);
    List<String> uploadDIR = new ArrayList<String>(1);
    int totalFiles = 0;
    long startTime = 0;
    boolean uploadInProgress = false, uploadComplete = false;
    int rowInU;
    String folder = "Music Video Subtitles";
    //END MyVariabels

    @Override
    public void run() {
        if (call == 0) {
            //upload dialog
            jDialogUploadStatus.setSize(640, 350);
            jDialogUploadStatus.setLocationRelativeTo(null);
            jDialogUploadStatus.setVisible(true);
        } else if (call == 1) {
            //upload
            uploadInProgress = true;
            uploadComplete = false;
            startUpload();
        } else {
        }
    }
}
