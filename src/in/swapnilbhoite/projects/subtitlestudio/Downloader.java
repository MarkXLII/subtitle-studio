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
 * @author Crazy_Coder
 */
public class Downloader extends javax.swing.JFrame implements Runnable
{
    public Downloader() 
    {
        initComponents();
        initiateDownload();
        startTime = System.currentTimeMillis();
    }
    
    private void login()
    {
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);
        myDropBox = new DropboxAPI<WebAuthSession>(session);
        AccessTokenPair newAuth = new AccessTokenPair(AUTH_KEY, AUTH_SECRET);
        myDropBox.getSession().setAccessTokenPair(newAuth);
    }
    
    final void initiateDownload()
    {
        for(int i = 0; i < 100; i++)
        {
            jTable2.setValueAt("", i, 0);
            jTable2.setValueAt("", i, 1);
            jTable2.setValueAt("", i, 2);
            jTable2.setValueAt("", i, 3);
        }
        jLabel7.setText("-");
        jLabel8.setText("-");
        jLabel9.setText("-");
        jLabel10.setText("-");
        resultDIR.removeAll(resultDIR);
        serverNo.removeAll(serverNo);
        totalResults = 0;
        jLabel20.setText("Search File");
        jLabel21.setText("Status");
        jLabel24.setText("-");
        jLabel25.setText("-");
        jLabel26.setText("-");
        jLabel27.setText("-");
        jLabel28.setText("-");
        jLabel29.setText("-");
        jLabel30.setText("-");
        jLabel31.setText("-");
        jLabel22.setText("File");
        jLabel23.setText("-");
        jProgressBar3.setValue(0);
        jButton6.setText("Search");
        searching = false;
        haveOutDir = false;
    }
    
    void searchFile()
    {
        int pre = totalResults;
        
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Connecting to Subtitle Studio...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel21.setText("Connecting to Subtitle Studio...");
        jLabel22.setText("Searching");
        jLabel23.setText(" "+searchKey+" On Server"+currentSerevr);
        login();
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Connected to Subtitle Studio...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel21.setText("Connected to Subtitle Studio...");
        jProgressBar3.setValue(17);
        String folder = jComboBox1.getSelectedItem()+"";
        try 
        {
            results = myDropBox.search("/"+folder+"/Titles, Artists & Albums/", searchKey, 100, false);
            for(int i = 0; i < results.size(); i++)
            {
                resultDIR.add(results.get(i).parentPath());
                jTable2.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();
                
                String a[] = temp.split(" By ");
                jTable2.setValueAt(a[0], totalResults, 1);
                for(int j = 2; j < a.length; j++)
                    a[1] = a[1] + a[j];
                
                String b[] = a[1].split(" Appears On ");
                jTable2.setValueAt(b[0], totalResults, 2);
                for(int j = 2; j < b.length; j++)
                    b[1] = b[1] + b[j];
                
                String c[] = b[1].split(".srt");
                c = c[0].split(".SRT");
                jTable2.setValueAt(c[0], totalResults, 3);
                
                String d[] = b[1].split(".ssa");
                d = d[0].split(".SSA");
                jTable2.setValueAt(d[0], totalResults, 3);
                
                totalResults++;
            }
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Seacrching in Titles, Artists & Albums...");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Seacrching in Titles, Artists & Albums...");
            jProgressBar3.setValue(34);
            
            results = myDropBox.search("/"+folder+"/Titles & Artists/", searchKey, 100, false);
            for(int i = 0; i < results.size(); i++)
            {
                resultDIR.add(results.get(i).parentPath());
                jTable2.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();
                
                String a[] = temp.split(" By ");
                jTable2.setValueAt(a[0], totalResults, 1);
                for(int j = 2; j < a.length; j++)
                    a[1] = a[1] + a[j];
                
                String c[] = a[1].split(".srt");
                c = c[0].split(".SRT");
                jTable2.setValueAt(c[0], totalResults, 2);
                
                totalResults++;
            }
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Seacrching in Titles & Artists...");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Seacrching in Titles & Artists...");
            jProgressBar3.setValue(51);
            
            results = myDropBox.search("/"+folder+"/Titles/", searchKey, 100, false);
            for(int i = 0; i < results.size(); i++)
            {
                resultDIR.add(results.get(i).parentPath());
                jTable2.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();
   
                String c[] = temp.split(".srt");
                c = c[0].split(".SRT");
                jTable2.setValueAt(c[0], totalResults, 1);
                
                totalResults++;
            }
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Seacrching in Titles...");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Seacrching in Titles...");
            jProgressBar3.setValue(68);
            
            results = myDropBox.search("/"+folder+"/Titles & Albums/", searchKey, 100, false);
            for(int i = 0; i < results.size(); i++)
            {
                resultDIR.add(results.get(i).parentPath());
                jTable2.setValueAt(results.get(i).fileName(), totalResults, 0);
                String temp = results.get(i).fileName();
                
                String b[] = temp.split(" Appears On ");
                jTable2.setValueAt(b[0], totalResults, 1);
                for(int j = 2; j < b.length; j++)
                    b[1] = b[1] + b[i];
                
                String c[] = b[1].split(".srt");
                c = c[0].split(".SRT");
                jTable2.setValueAt(c[0], totalResults, 3);
                
                totalResults++;
            }
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Seacrching in Titles & Albums...");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Seacrching in Titles & Albums...");
            jProgressBar3.setValue(85);
            
            results = myDropBox.search("/"+folder+"/Unknown/", searchKey, 100, false);
            for(int i = 0; i < results.size(); i++)
            {
                resultDIR.add(results.get(i).parentPath());
                jTable2.setValueAt(results.get(i).fileName(), totalResults, 0);
                totalResults++;
            }
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Seacrching in Unknown...");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Seacrching in Unknown...");
            jProgressBar3.setValue(100);
        } 
        catch (DropboxException ex) 
        {
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Error in Search!!!");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Error in Search!!!");
        }
        
        
        
        for(int k = pre; k < totalResults; k++)
            serverNo.add(currentSerevr);
    }
    
    void downloadFile(String fileName, String dir)
    {
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Connecting to Subtitle Studio...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel21.setText("Connecting to Subtitle Studio...");
        
        APP_KEY = MainWindow.servers.get(serverNo.get(jTable2.getSelectedRow())).APPKEY;
        APP_SECRET = MainWindow.servers.get(serverNo.get(jTable2.getSelectedRow())).APPSECRET;
        AUTH_KEY = MainWindow.servers.get(serverNo.get(jTable2.getSelectedRow())).KEYTOKEN;
        AUTH_SECRET = MainWindow.servers.get(serverNo.get(jTable2.getSelectedRow())).SECRETTOKEN;

        APP_KEY = APP_KEY.substring(0, APP_KEY.length()-1);
        APP_SECRET = APP_SECRET.substring(0, APP_SECRET.length()-1);
        AUTH_KEY = AUTH_KEY.substring(0, AUTH_KEY.length()-1);
        if((serverNo.get(jTable2.getSelectedRow())) != MainWindow.servers.size()-1)
            AUTH_SECRET = AUTH_SECRET.substring(0, AUTH_SECRET.length()-1);
        login();
        
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Connected to Subtitle Studio...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel21.setText("Connected to Subtitle Studio...");
        jProgressBar3.setValue(20);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try 
        {
            DropboxAPI.DropboxFileInfo newEntry2 = myDropBox.getFile(dir+"/"+fileName, null, outputStream, null);
            jProgressBar3.setValue(40);
        } 
        catch (DropboxException ex) 
        {
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Error Downloading File");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Error Downloading File");
        }
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-File downloaded, writing output file - "+fileName+"...");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel21.setText("File downloaded, writing output file - "+fileName+"...");
        String temp = outputStream.toString();
        String contents[] = temp.split("\n");
        BufferedWriter writer = null;
        try 
        {
            writer = new BufferedWriter(new FileWriter(outDIR+"\\"+fileName));
        } 
        catch (IOException ex) 
        {
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Error Creating Output File");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Error Creating Output File");
        }
        try
        {
            int l1 = contents.length;
            for(int i = 0; i < contents.length; i++)
            {
                writer.write(contents[i]);
                writer.newLine();
                jProgressBar3.setValue((int)((i*40)/l1)+60);
            }
            writer.close();
        }
        catch(IOException ex)
        {
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Error Wriring Output File");
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Error Writing Output File");
        }
        
        jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Download Complete");
        jTextArea1.setCaretPosition(jTextArea1.getText().length());
        jLabel21.setText("Download Complete");
        jLabel20.setText("Download Complete");
        jButton6.setText("OK");
        downloading = false;
        jProgressBar3.setValue(100);
        jDialog2.setSize(640,360);
        jDialog2.setLocationRelativeTo(null);
        jDialog2.setVisible(true);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog2 = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jDialog2.setModal(true);

        jPanel9.setBackground(new java.awt.Color(102, 102, 102));

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setPreferredSize(new java.awt.Dimension(580, 60));

        jLabel20.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Search File");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Status");

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("File -");

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setForeground(new java.awt.Color(204, 255, 255));
        jLabel23.setText("-");

        jProgressBar3.setStringPainted(true);

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("File Name");

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Title");

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Artist");

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Album");

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setForeground(new java.awt.Color(204, 255, 255));
        jLabel28.setText("-");

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setForeground(new java.awt.Color(204, 255, 255));
        jLabel29.setText("-");

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setForeground(new java.awt.Color(204, 255, 255));
        jLabel30.setText("-");

        jLabel31.setBackground(new java.awt.Color(0, 0, 0));
        jLabel31.setForeground(new java.awt.Color(204, 255, 255));
        jLabel31.setText("-");

        jButton6.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton6)
                            .addComponent(jProgressBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
                .addGap(0, 144, 144))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel24, jLabel25, jLabel26, jLabel27});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subtitle Studio 3.1");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(878, 90));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Candara", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Downloader");

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

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(392, 385));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setText("Search Keywords (Minimum 3 characters)");

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selected File -");

        jButton4.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButton4.setText("Download");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Music Video Subtitles", "Movie Subtitles" }));

        jButton2.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jButton2.setText("Search IN >>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("File Name");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Title");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Artist");

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Album");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("-");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("-");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("-");

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4)
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel6});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(11, 11, 11)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jComboBox1, jTextField1});

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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!searching)
        {
            searchKey = jTextField1.getText();
            if(searchKey.length() >= 3)
            {
                initiateDownload();
                searchClick = true;
                call = 2;
                new Thread(this).start();
            }
            else
            {
                jTextField1.setText("Enter minimum 3 characters...");
            }
        }
        else
        {
            jDialog2.setSize(640,300);
            jDialog2.setLocationRelativeTo(null);
            jDialog2.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(searchClick)
        {
            if(jButton6.getText().equals("OK"))
                jDialog2.dispose();
            else
            {
                if(!searching)
                {
                    jButton6.setText("Hide");
                    call = 3;
                    new Thread(this).start();
                    searching = true;
                    jTextField1.setEditable(false);
                }
                else
                {
                    jDialog2.dispose();
                }
            }
        }
        else
        {
            if(jButton6.getText().equals("OK"))
                jDialog2.dispose();
            else
            {
                if(!downloading)
                {
                    jButton6.setText("Hide");
                    call = 5;
                    new Thread(this).start();
                    downloading = true;
                }
                else
                {
                    jDialog2.dispose();
                }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if(totalResults != 0)
        {
            int fileNo = jTable2.getSelectedRow();
            if(fileNo < totalResults)
            {
                jLabel7.setText("- "+jTable2.getValueAt(fileNo, 0));
                jLabel8.setText("- "+jTable2.getValueAt(fileNo, 1));
                jLabel9.setText("- "+jTable2.getValueAt(fileNo, 2));
                jLabel10.setText("- "+jTable2.getValueAt(fileNo, 3));
            }
            else
            {
                jLabel7.setText("-");
                jLabel8.setText("-");
                jLabel9.setText("-");
                jLabel10.setText("-");
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(totalResults > 0 && !searching && !"-".equals(jLabel7.getText()))
        {
            if(downloading)
            {
                jDialog2.setSize(640,360);
                jDialog2.setLocationRelativeTo(null);
                jDialog2.setVisible(true);
            }
            else
            {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.setVisible(true);
                jfc.showSaveDialog(this);
                if(jfc.getSelectedFile() != null)
                {
                    outDIR = jfc.getSelectedFile()+"";
                    call = 4;
                    searchClick = false;
                    new Thread(this).start();
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MainWindow.getFrames()[0].setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
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
    public void run() 
    {
        if(call == 2)
        {
            //search
            jLabel24.setText("");
            jLabel25.setText("");
            jLabel26.setText("");
            jLabel27.setText("");
            jLabel28.setText("");
            jLabel29.setText("");
            jLabel30.setText("");
            jLabel31.setText("");
            jLabel23.setText(searchKey);
            
            jDialog2.setSize(640,300);
            jDialog2.setLocationRelativeTo(null);
            jDialog2.setVisible(true);
        }
        else if(call == 3)
        {
            //other
            jLabel20.setText("Searching File");
            
            for(int i = 0; i < MainWindow.servers.size(); i++)
            {
                currentSerevr = i;
                APP_KEY = MainWindow.servers.get(i).APPKEY;
                APP_SECRET = MainWindow.servers.get(i).APPSECRET;
                AUTH_KEY = MainWindow.servers.get(i).KEYTOKEN;
                AUTH_SECRET = MainWindow.servers.get(i).SECRETTOKEN;
                
                APP_KEY = APP_KEY.substring(0, APP_KEY.length()-1);
                APP_SECRET = APP_SECRET.substring(0, APP_SECRET.length()-1);
                AUTH_KEY = AUTH_KEY.substring(0, AUTH_KEY.length()-1);
                if(i != MainWindow.servers.size()-1)
                    AUTH_SECRET = AUTH_SECRET.substring(0, AUTH_SECRET.length()-1);
                
                searchFile();
            }
            
            searching = false;
            jTextField1.setEditable(true);
            jLabel20.setText("Search");
            jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Match found - "+totalResults);
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
            jLabel21.setText("Match found - "+totalResults);
            jButton6.setText("OK");
            jDialog2.setSize(640,300);
            jDialog2.setLocationRelativeTo(null);
            jDialog2.setVisible(true);
            jTable2.setRowSelectionInterval(0, 0);
            jTable2MouseClicked(null);
        }
        else if(call == 4)
        {
            //download
            jLabel20.setText("Download File");
            jLabel21.setText("Status");
            jLabel22.setText("Saving to - ");
            jLabel23.setText(outDIR);
            
            int fileNo = jTable2.getSelectedRow();
            jLabel24.setText("File Name");
            jLabel25.setText("Title");
            jLabel26.setText("Artist");
            jLabel27.setText("Album");
            
            jLabel28.setText("- "+jTable2.getValueAt(fileNo, 0));
            jLabel29.setText("- "+jTable2.getValueAt(fileNo, 1));
            jLabel30.setText("- "+jTable2.getValueAt(fileNo, 2));
            jLabel31.setText("- "+jTable2.getValueAt(fileNo, 3));
            
            
            jProgressBar3.setValue(0);
            jButton6.setText("Download Now");
            
            jDialog2.setSize(640,360);
            jDialog2.setLocationRelativeTo(null);
            jDialog2.setVisible(true);
        }
        else
        {
            jLabel20.setText("Downloading File");
            try 
            {
                downloadFile(jTable2.getValueAt(jTable2.getSelectedRow(), 0)+"", resultDIR.get(jTable2.getSelectedRow()));
            }
            catch (Exception ex) 
            {
                jTextArea1.append("\n"+new MyTime(System.currentTimeMillis()-startTime)+"-Error in input table");
                jTextArea1.setCaretPosition(jTextArea1.getText().length());
                jLabel21.setText("Error Downloading File");
            }
        }
    }
}