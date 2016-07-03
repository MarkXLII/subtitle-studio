/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Convert.java
 *
 * Created on 27 Nov, 2011, 7:16:50 PM
 */
package in.swapnilbhoite.projects.subtitlestudio;

import java.awt.GraphicsEnvironment;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author CrazyCoder
 */
public class Convert extends javax.swing.JFrame implements Runnable
{

    /** Creates new form Convert */
    public Convert() {
        initComponents();
    }
    
    public void initiateAll()
    {
        totalFiles = 0;
        jTextArea1.setText("Source Files");
        jTextArea1.setEditable(false);
        
        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
        jComboBox3.removeAllItems();
        jComboBox4.removeAllItems();
        jComboBox5.removeAllItems();
        jComboBox6.removeAllItems();
        jComboBox7.removeAllItems();
        jComboBox8.removeAllItems();
        
        GraphicsEnvironment gn = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fonts[] = gn.getAvailableFontFamilyNames();
        for(int i = 0; i < fonts.length; i++)
            jComboBox1.addItem(fonts[i]);
        for(int i = 10; i<=50; i++)
            jComboBox2.addItem(""+i);
        
        jComboBox3.addItem("Plain");
        jComboBox3.addItem("Bold");
        jComboBox3.addItem("Italic");
        jComboBox3.addItem("Bold Italic");
        
        for(int i = 0 ; i < 16; i++)
        {
            jComboBox4.addItem("" + i);
            jComboBox5.addItem("" + i);
        }
        
        for(int i = 0 ; i < 10; i++)
            jComboBox8.addItem("" + i);
        
        for(int i = 100 ; i <= 3000; i += 50)
        {
            jComboBox6.addItem(""+ i);
            jComboBox7.addItem(""+ i);
        }
        
        jComboBox1.setSelectedItem("Tahoma");
        jComboBox2.setSelectedItem("21");
        jComboBox4.setSelectedItem("0");
        jComboBox5.setSelectedItem("0");
        jComboBox6.setSelectedItem("500");
        jComboBox7.setSelectedItem("500");
        jComboBox8.setSelectedItem("7");
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setTitle("Converting");
        jDialog1.setModal(true);
        jDialog1.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialog1WindowClosing(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setForeground(new java.awt.Color(204, 204, 204));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Converting ");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("FileName");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jPanel2.setPreferredSize(new java.awt.Dimension(780, 90));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Candara", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subtitle Converter");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Files");
        jButton1.setToolTipText("Browse for source files");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(204, 204, 204));
        jTextArea1.setRows(5);
        jTextArea1.setText("Source Files...");
        jTextArea1.setToolTipText("List of added files");
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 255));
        jLabel2.setText("Settings...");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Font       (Recommended - Tahoma)");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Font Size (Recommended - 21)");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Font Style (Recommended - Plain)");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Outline    (Recommended - 1 to 3)");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Shadow   (Recommended - 1 to 5)");

        jCheckBox1.setBackground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Enable Animated Fade Effect ");

        jComboBox1.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setToolTipText("");

        jComboBox2.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox5.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fade In Duration - ");

        jComboBox6.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox6.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fade Out Duration -");

        jComboBox7.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox7.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 255, 255));
        jButton2.setText("Convert");
        jButton2.setToolTipText("Convert, App will exit after conversion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Ideally Fade IN+OUT duration shuld be less than duration of dialog");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("But don't worry, app will take care of that & will adjust duration for each dialog");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Offset from Bottom   (Recommended - 5 to 7)");

        jComboBox8.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox8.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(154, 154, 154)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10)))
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(75, 75, 75)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox1, 0, 314, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Output file(s) will be saved in same source file directory with name sourceFileName.ssa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // Add Files
        jTextArea1.setEditable(true);
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Subtitle File(.srt)", "srt"));
        jfc.setVisible(true);
        jfc.showOpenDialog(this);
        File tempFiles[] = jfc.getSelectedFiles();
        if(totalFiles == 0)
            jTextArea1.setText("");
        for(int i = 0; i < tempFiles.length; i++)
        {
            String ext = tempFiles[i].getName();
            ext = ext.substring(ext.length()-3);
            if(ext.equals("srt") || ext.equals("SRT"))
            {
                if(totalFiles != 0)
                    jTextArea1.setText(jTextArea1.getText() + "\n");
                jTextArea1.setText(jTextArea1.getText() + tempFiles[i].getPath());
                totalFiles++;
            }
        }
        if(totalFiles == 0)
            jTextArea1.setText("Source Files...");
        jTextArea1.setEditable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       //CONVERT CLICKED
        new Thread(this).start();
        jDialog1.setSize(500,600);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jDialog1WindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowClosing
       System.exit(1);
    }//GEN-LAST:event_jDialog1WindowClosing

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MainWindow.getFrames()[0].setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void convertFile(File f) throws FileNotFoundException, IOException
    {
        boolean fade = jCheckBox1.isSelected();
        String font = jComboBox1.getSelectedItem().toString();
        String size = ""+(Integer.parseInt(jComboBox2.getSelectedItem()+"")+50);
        int style = jComboBox3.getSelectedIndex();
        int bold = 0, italic = 0 ;
        if(style == 1)
            bold = 1;
        else if(style == 2)
            italic = 1;
        else if(style == 3)
            bold = italic = 1;
        String outline = jComboBox4.getSelectedItem().toString();
        String shadow = jComboBox5.getSelectedItem().toString();
        String fadein = jComboBox6.getSelectedItem().toString();
        String fadeout = jComboBox7.getSelectedItem().toString();
        String offset = ""+(10*(new Integer(jComboBox8.getSelectedItem().toString())));
        
        String outputPath = f.getPath();
        String outputName = f.getName();
        outputPath = outputPath.substring(0, outputPath.length() - outputName.length());
        outputName = outputName.substring(0, outputName.length()-3) + "ssa";
        
        BufferedReader in = new BufferedReader(new FileReader(f));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath+outputName));
        writer.write("[Script Info]");
        writer.newLine();
        writer.write("; Subtitle Studio By Swapnil");
        writer.newLine();
        writer.write("; http://subtitlestudio.blogspot.com");
        writer.newLine();
        writer.write("Title: Subtitle Studio By Swapnil");
        writer.newLine();
        writer.write("ScriptType: v4.00+");
        writer.newLine();
        writer.write("ScaledBorderAndShadow: yes");
        writer.newLine();
        writer.write("PlayResX: 1920");
        writer.newLine();
        writer.newLine();
        writer.write("[V4+ Styles]");
        writer.newLine();
        writer.write("Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding");
        writer.newLine();
        writer.write("Style: Default,"+font+","+size+",&H00FFFFFF,&H000000FF,&H00000000,&H00000000,"+bold+","+italic+",0,0,100,100,0,0,1,"+outline+","+shadow+",2,10,10,"+offset+",1");
        writer.newLine();
        writer.newLine();
        writer.write("[Events]");
        writer.newLine();
        writer.write("Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        writer.newLine();
        writer.write("Dialogue: 0,0:00:00.00,0:00:11.00,Default,,0000,0000,0000,,{"+s1.charAt(0)+"fad(1000,1000)}http://subtitlestudio.blogspot.com"+s1.charAt(0)+"NBy Swapnil http://www.facebook.com/djswap1216");
        writer.newLine();
        
        jTextArea2.append("\nSpliting in Single Lines...");
        
        String singleLine  = "", content = "";
        while((singleLine = in.readLine())!= null)
        {
            singleLine = singleLine.replace((char)65533, s2.charAt(0));
            singleLine = singleLine.replace('’', s2.charAt(0));
            singleLine = singleLine.replace('‘', s2.charAt(0));
            content = content + singleLine;
            content = content + "\n";
            
            jTextArea2.append("\n"+singleLine);
            jTextArea2.setCaretPosition(jTextArea2.getText().length());
        }
        String srtContent[] = content.split("\n");
        int totalDialogs = 1;
        for(int i = 0; i < srtContent.length; i++)
            if("".equals(srtContent[i]))
                totalDialogs++;
        MyMapping dialogs[] = new MyMapping[totalDialogs];
        
        jTextArea2.append("\nAdjusting Timecodes...");
        
        for(int i = 0, j = 0; i < srtContent.length; i++)
        {
            boolean flag = false;
            i++;
            dialogs[j] = new MyMapping();
            dialogs[j].setTime(srtContent[i]);
           
            i++;
            while(i < srtContent.length && !"".equals(srtContent[i]))
            {
                if(!flag)
                {
                    dialogs[j].setDialog(srtContent[i]);
                    flag = true;
                }
                else
                    dialogs[j].setDialog(dialogs[j].getDialog()+myNewLine+srtContent[i]);
                i++;
            }
            
            jTextArea2.append("\n"+dialogs[j].toString());
            jTextArea2.setCaretPosition(jTextArea2.getText().length());
            
            j++;
        }
        
        jTextArea2.append("\nWriting Output File...");
        
        if(fade)
        {
            dialogs[0].adjustFade(dialogs, fadein, fadeout, totalDialogs);
            for(int i = 0; i < totalDialogs; i++)
            {
                writer.write("Dialogue: 0,");
                String temp1 = new MyTime(dialogs[i].start).toString();
                temp1 = temp1 + " --> " + new MyTime(dialogs[i].end).toString();
                writer.write(MyTime.toSSA(temp1));
                writer.write("Default,,0000,0000,0000,,");
                writer.write("{"+s1.charAt(0));
                writer.write("fad("+dialogs[i].fadeIN+","+dialogs[i].fadeOUT+")}");
                
                jTextArea2.append("\n"+dialogs[i].dialog);
                jTextArea2.setCaretPosition(jTextArea2.getText().length());
        
                writer.write(dialogs[i].dialog);
                writer.newLine();
            }
        }
        else
        {
            for(int i = 0; i < totalDialogs; i++)
            {
                writer.write("Dialogue: 0,");
                String temp1 = new MyTime(dialogs[i].start).toString();
                temp1 = temp1 + " --> " + new MyTime(dialogs[i].end).toString();
                writer.write(MyTime.toSSA(temp1));
                writer.write("Default,,0000,0000,0000,,");
                writer.write(dialogs[i].dialog);
                
                jTextArea2.append("\n"+dialogs[i].dialog);
                jTextArea2.setCaretPosition(jTextArea2.getText().length());
                
                writer.newLine();
            }
        }
        writer.close();
        jTextArea2.append("\nConversion Complete, Output File Closed...");
        jTextArea2.setCaretPosition(jTextArea2.getText().length()-42);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

    int totalFiles = 0;
    String s1 = "\\", myNewLine = s1.charAt(0)+"N", s2 = "''";

    @Override
    public void run() 
    {
        try 
        {
            String files[] = jTextArea1.getText().split("\n");
            for(int i = 0; i< files.length; i++)
            {
                File f= new File(files[i]);
                jLabel14.setText(""+f.getName());
                convertFile(f);
            }
            jTextArea2.setEditable(false);
            jDialog1.dispose();
            this.initiateAll();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
