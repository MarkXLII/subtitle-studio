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
 * @author Crazy_Coder
 */
public class Uploader extends javax.swing.JFrame implements Runnable
{
    /** Creates new form Uploader */
    public Uploader() 
    {
        initComponents();
        initiateUpload();
        startTime = System.currentTimeMillis();
    }
    
    private void login()
    {
        APP_KEY = APP_KEY.substring(0, APP_KEY.length()-1);
        APP_SECRET = APP_SECRET.substring(0, APP_SECRET.length()-1);
        AUTH_KEY = AUTH_KEY.substring(0, AUTH_KEY.length()-1);
        
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
        myDropBox = new DropboxAPI<WebAuthSession>(session);
        AccessTokenPair newAuth = new AccessTokenPair(AUTH_KEY, AUTH_SECRET);
        myDropBox.getSession().setAccessTokenPair(newAuth);
    }
    
    final void initiateUpload()
    {
        jLabel3.setText("Please enter Title, Artist & Album, It will make SEARCH easy");
        for(int i = 0; i < 100; i++)
        {
            jTable1.setValueAt("", i, 0);
            jTable1.setValueAt("", i, 1);
            jTable1.setValueAt("", i, 2);
            jTable1.setValueAt("", i, 3);
        }
        jButton2.setText("Upload All In >> ");
        uploadInProgress = false;
        uploadComplete = false;
        totalFiles = 0;
        files.removeAll(files);
        uploadDIR.removeAll(uploadDIR);
        jLabel14.setText("Upload Files");
        jLabel15.setText("Status");
        jLabel17.setText("-");
        jLabel19.setText("0/0");
        jButton5.setText("Start Upload");
        jProgressBar1.setValue(0);
        jProgressBar2.setValue(0);
        folder = "Music Video Subtitles";
        jComboBox1.setSelectedIndex(0);
    }
    
    void uploadFile(String fileName, String contents, String dir)
    {
        ProgressListener pl = new ProgressListener() 
                {
                    @Override
                    public long progressInterval()
                    {
                        return 1;
                    }
                    @Override
                    public void onProgress(long l, long l1) 
                    {
                        jProgressBar1.setValue((int)((l*100)/l1));
                    }
                };
        dir = "/"+jComboBox1.getSelectedItem()+dir;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(contents.getBytes());
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-"+fileName+" Ready for uploading...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel15.setText(fileName+" Ready for uploading...");
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Uploading "+fileName+"...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel15.setText("Uploading "+fileName+"...");
        try 
        {
            DropboxAPI.Entry entry1= myDropBox.putFile(dir+fileName, inputStream, contents.length(), null, pl);
        }
        catch (DropboxException ex) 
        {
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-ERROR while uploading "+fileName+"!!!");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel15.setText("ERROR while uploading "+fileName+"!!!");
        }
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-"+fileName+" Uploaded...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel15.setText(fileName+" Uploaded...");
        jProgressBar1.setValue(100);
    }
    
    void startUpload()
    {
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Connecting to Subtitle Studio...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel15.setText("Connecting to Subtitle Studio...");
        login();
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Connected to Subtitle Studio...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel15.setText("Connected to Subtitle Studio...");
        for(int i = 0; i < totalFiles; i++)
        {
            String fileName = ""+jTable1.getValueAt(i, 0);
            jLabel17.setText(fileName);
            jLabel19.setText((i+1)+"/"+(totalFiles));
            jProgressBar2.setValue(i*100/totalFiles);
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Preparing "+fileName+" for upload...");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel15.setText("Preparing "+fileName+" for upload...");
            BufferedReader br = null;
            try 
            {
                br = new BufferedReader(new FileReader(files.get(i)));
            } 
            catch (FileNotFoundException ex) 
            {
                jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-ERROR "+fileName+"not found!!!");
                jTextArea1.setCaretPosition(jTextArea1.getText().length());
                jLabel15.setText("ERROR "+fileName+"not found!!!");
            }
            String contents = "", line = "";
            try 
            {
                while((line = br.readLine()) != null)
                    contents = contents+line + "\n";
            } 
            catch (IOException ex) 
            {
                jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-ERROR Reading"+fileName+" !!!");
                jTextArea1.setCaretPosition(jTextArea1.getText().length());
                jLabel15.setText("ERROR Reading"+fileName+" !!!");
            }
            uploadFile(fileName, contents, uploadDIR.get(i));
        }
        jProgressBar2.setValue(100);
        uploadComplete = true;
        uploadInProgress = false;
        jButton5.setText("Close");
        jLabel14.setText("Upload Finish");
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"- "+files.size()+ " Files Uploaded");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel15.setText(files.size()+ " Files Uploaded");
        jDialog1.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jDialog1.setModal(true);

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setPreferredSize(new java.awt.Dimension(580, 60));

        jLabel14.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Upload Files");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Status");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Current File -");

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setForeground(new java.awt.Color(204, 255, 255));
        jLabel17.setText("-");

        jProgressBar1.setStringPainted(true);

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Progress -");

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setForeground(new java.awt.Color(204, 255, 255));
        jLabel19.setText("0/0");

        jProgressBar2.setStringPainted(true);

        jButton5.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButton5.setText("Start Upload");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel8Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jButton5)
                .addGap(236, 236, 236))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subtitle Studio 3.1");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(878, 90));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Candara", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Upload");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(392, 385));

        jButton1.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButton1.setText("Add Files");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Please enter Title, Artist & Album, It will make SEARCH easy");

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButton2.setText("Upload All In >> ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Music Video Subtitles", "Movie Subtitles" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)))
                        .addGap(0, 285, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jComboBox1});

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(102, 255, 51));
        jTextArea1.setRows(5);
        jTextArea1.setText("Log");
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Subtitle Files", "srt", "ssa"));
        jfc.setVisible(true);
        jfc.showOpenDialog(this);
        File tempFiles[] = jfc.getSelectedFiles();
        for(int i = 0; i < tempFiles.length; i++)
        {
            String ext = tempFiles[i].getName();
            ext = ext.substring(ext.length()-3);
            if(ext.equals("srt") || ext.equals("SRT") || ext.equals("ssa") || ext.equals("SSA"))
            {
                files.add(tempFiles[i]);
                uploadDIR.add("/Unknown/");
                jTable1.setValueAt(files.get(totalFiles).getName(), totalFiles, 0);
                totalFiles++;
            }
        }
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-"+tempFiles.length+" Files added to upload list");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!uploadInProgress && !uploadComplete)
        {
            call = 1;
            new Thread(this).start();
            jButton5.setText("Hide");
            jLabel14.setText("Uploading Files");
        }
        else if(uploadInProgress && !uploadComplete)
        {
            jDialog1.dispose();
        }
        else
        {
            initiateUpload();
            jDialog1.dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        if(totalFiles != 0)
        {
            if(jTable1.getEditingRow() != -1 && jTable1.getEditingColumn() != -1)
                rowInU = jTable1.getEditingRow();
            if(!jTable1.isEditing() && (rowInU < totalFiles))
            {
                String title = ""+jTable1.getValueAt(rowInU, 1);
                String artist = ""+jTable1.getValueAt(rowInU, 2);
                String album = ""+jTable1.getValueAt(rowInU, 3);
                String ext = jTable1.getValueAt(rowInU, 0).toString();
                ext = "."+ext.substring(ext.length()-3);
                if(!("".equals(title)))
                {
                    if(("".equals(artist)) && ("".equals(album)))
                    {
                        uploadDIR.set(rowInU, "/Titles/");
                        jTable1.setValueAt(title+ext, rowInU, 0);
                    }
                    else if((!("".equals(artist))) && ("".equals(album)))
                    {
                        uploadDIR.set(rowInU, "/Titles & Artists/");
                        jTable1.setValueAt(title+" By "+artist+ext, rowInU, 0);
                    }
                    else if(("".equals(artist)) && (!("".equals(album))))
                    {
                        uploadDIR.set(rowInU, "/Titles & Albums/");
                        jTable1.setValueAt(title+" Appears On "+album+ext, rowInU, 0);
                    }
                    else
                    {
                        uploadDIR.set(rowInU, "/Titles, Artists & Albums/");
                        jTable1.setValueAt(title+" By "+artist+" Appears On "+album+ext, rowInU, 0);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTable1PropertyChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(totalFiles != 0)
        {
            call = 0;
            new Thread(this).start();
        }
        else
        {
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Please add some files first");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MainWindow.getFrames()[0].setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
    //DROPBOX
    private static String APP_KEY = MainWindow.servers.get(MainWindow.servers.size()-1).APPKEY;
    private static String APP_SECRET = MainWindow.servers.get(MainWindow.servers.size()-1).APPSECRET;
    private static String AUTH_KEY = MainWindow.servers.get(MainWindow.servers.size()-1).KEYTOKEN;
    private static String AUTH_SECRET = MainWindow.servers.get(MainWindow.servers.size()-1).SECRETTOKEN;
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
    public void run() 
    {
        if(call == 0)
        {
            //upload dialog
            jDialog1.setSize(640,350);
            jDialog1.setLocationRelativeTo(null);
            jDialog1.setVisible(true);
        }
        else if(call == 1)
        {
            //upload
            uploadInProgress = true;
            uploadComplete = false;
            startUpload();
        }
        else
        {
        }
    }
}