/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Convertor.java
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
 * @author Swapnil Bhoite
 */
public class Convertor extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Convert
     */
    public Convertor() {
        initComponents();
    }

    public void initiateAll() {
        totalFiles = 0;
        jTextAreaSourceFiles.setText("Source Files");
        jTextAreaSourceFiles.setEditable(false);

        jComboBoxFont.removeAllItems();
        jComboBoxFontSize.removeAllItems();
        jComboBoxFontStyle.removeAllItems();
        jComboBoxOutline.removeAllItems();
        jComboBoxShadow.removeAllItems();
        jComboBoxFadeInDuration.removeAllItems();
        jComboBoxFadeOutDuration.removeAllItems();
        jComboBoxBottomOffset.removeAllItems();

        GraphicsEnvironment gn = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fonts[] = gn.getAvailableFontFamilyNames();
        for (int i = 0; i < fonts.length; i++) {
            jComboBoxFont.addItem(fonts[i]);
        }
        for (int i = 10; i <= 50; i++) {
            jComboBoxFontSize.addItem("" + i);
        }

        jComboBoxFontStyle.addItem("Plain");
        jComboBoxFontStyle.addItem("Bold");
        jComboBoxFontStyle.addItem("Italic");
        jComboBoxFontStyle.addItem("Bold Italic");

        for (int i = 0; i < 16; i++) {
            jComboBoxOutline.addItem("" + i);
            jComboBoxShadow.addItem("" + i);
        }

        for (int i = 0; i < 10; i++) {
            jComboBoxBottomOffset.addItem("" + i);
        }

        for (int i = 100; i <= 3000; i += 50) {
            jComboBoxFadeInDuration.addItem("" + i);
            jComboBoxFadeOutDuration.addItem("" + i);
        }

        jComboBoxFont.setSelectedItem("Tahoma");
        jComboBoxFontSize.setSelectedItem("21");
        jComboBoxOutline.setSelectedItem("0");
        jComboBoxShadow.setSelectedItem("0");
        jComboBoxFadeInDuration.setSelectedItem("500");
        jComboBoxFadeOutDuration.setSelectedItem("500");
        jComboBoxBottomOffset.setSelectedItem("7");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogConversionProgress = new javax.swing.JDialog();
        jPanelConversionProgressParent = new javax.swing.JPanel();
        jLabelConverting = new javax.swing.JLabel();
        jLabelCurrentFile = new javax.swing.JLabel();
        jScrollPaneConvertorLog = new javax.swing.JScrollPane();
        jTextAreaConvertorLog = new javax.swing.JTextArea();
        jPanelConvertorParent = new javax.swing.JPanel();
        jPanelConvertorTitle = new javax.swing.JPanel();
        jLabelConvertorTitle = new javax.swing.JLabel();
        jButtonAddFiles = new javax.swing.JButton();
        jScrollPaneSourceFiles = new javax.swing.JScrollPane();
        jTextAreaSourceFiles = new javax.swing.JTextArea();
        jPanelConvertorSettings = new javax.swing.JPanel();
        jLabelSettings = new javax.swing.JLabel();
        jLabelFont = new javax.swing.JLabel();
        jLabelFontSize = new javax.swing.JLabel();
        jLabelFontStyle = new javax.swing.JLabel();
        jLabelOutline = new javax.swing.JLabel();
        jLabelShadow = new javax.swing.JLabel();
        jCheckBoxEnableAnimation = new javax.swing.JCheckBox();
        jComboBoxFont = new javax.swing.JComboBox();
        jComboBoxFontSize = new javax.swing.JComboBox();
        jComboBoxFontStyle = new javax.swing.JComboBox();
        jComboBoxOutline = new javax.swing.JComboBox();
        jComboBoxShadow = new javax.swing.JComboBox();
        jLabelFadInDuration = new javax.swing.JLabel();
        jComboBoxFadeInDuration = new javax.swing.JComboBox();
        jLabelFadeOutDuration = new javax.swing.JLabel();
        jComboBoxFadeOutDuration = new javax.swing.JComboBox();
        jButtonConvert = new javax.swing.JButton();
        jLabelAnimationHint1 = new javax.swing.JLabel();
        jLabelAnimationHint2 = new javax.swing.JLabel();
        jLabelBottomOffset = new javax.swing.JLabel();
        jComboBoxBottomOffset = new javax.swing.JComboBox();
        jLabelOutputHint = new javax.swing.JLabel();

        jDialogConversionProgress.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogConversionProgress.setTitle("Converting");
        jDialogConversionProgress.setModal(true);
        jDialogConversionProgress.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialogConversionProgressWindowClosing(evt);
            }
        });

        jPanelConversionProgressParent.setBackground(new java.awt.Color(51, 51, 51));
        jPanelConversionProgressParent.setForeground(new java.awt.Color(204, 204, 204));

        jLabelConverting.setBackground(new java.awt.Color(0, 0, 0));
        jLabelConverting.setForeground(new java.awt.Color(204, 204, 204));
        jLabelConverting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConverting.setText("Converting ");

        jLabelCurrentFile.setBackground(new java.awt.Color(0, 0, 0));
        jLabelCurrentFile.setFont(new java.awt.Font("Candara", 0, 12)); // NOI18N
        jLabelCurrentFile.setForeground(new java.awt.Color(102, 255, 255));
        jLabelCurrentFile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCurrentFile.setText("FileName");

        jTextAreaConvertorLog.setColumns(20);
        jTextAreaConvertorLog.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextAreaConvertorLog.setRows(5);
        jScrollPaneConvertorLog.setViewportView(jTextAreaConvertorLog);

        javax.swing.GroupLayout jPanelConversionProgressParentLayout = new javax.swing.GroupLayout(jPanelConversionProgressParent);
        jPanelConversionProgressParent.setLayout(jPanelConversionProgressParentLayout);
        jPanelConversionProgressParentLayout.setHorizontalGroup(
            jPanelConversionProgressParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConversionProgressParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConversionProgressParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConversionProgressParentLayout.createSequentialGroup()
                        .addComponent(jScrollPaneConvertorLog)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelConversionProgressParentLayout.createSequentialGroup()
                        .addComponent(jLabelConverting)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentFile, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelConversionProgressParentLayout.setVerticalGroup(
            jPanelConversionProgressParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConversionProgressParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConversionProgressParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConverting)
                    .addComponent(jLabelCurrentFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneConvertorLog, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogConversionProgressLayout = new javax.swing.GroupLayout(jDialogConversionProgress.getContentPane());
        jDialogConversionProgress.getContentPane().setLayout(jDialogConversionProgressLayout);
        jDialogConversionProgressLayout.setHorizontalGroup(
            jDialogConversionProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConversionProgressParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogConversionProgressLayout.setVerticalGroup(
            jDialogConversionProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConversionProgressParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subtitle Studio 3.1");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelConvertorParent.setBackground(new java.awt.Color(51, 51, 51));

        jPanelConvertorTitle.setBackground(new java.awt.Color(0, 0, 0));
        jPanelConvertorTitle.setPreferredSize(new java.awt.Dimension(780, 90));

        jLabelConvertorTitle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelConvertorTitle.setFont(new java.awt.Font("Candara", 0, 36)); // NOI18N
        jLabelConvertorTitle.setForeground(new java.awt.Color(0, 255, 255));
        jLabelConvertorTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConvertorTitle.setText("Subtitle Converter");
        jLabelConvertorTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelConvertorTitleLayout = new javax.swing.GroupLayout(jPanelConvertorTitle);
        jPanelConvertorTitle.setLayout(jPanelConvertorTitleLayout);
        jPanelConvertorTitleLayout.setHorizontalGroup(
            jPanelConvertorTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConvertorTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConvertorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelConvertorTitleLayout.setVerticalGroup(
            jPanelConvertorTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConvertorTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConvertorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonAddFiles.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAddFiles.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddFiles.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddFiles.setText("Add Files");
        jButtonAddFiles.setToolTipText("Browse for source files");
        jButtonAddFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFilesActionPerformed(evt);
            }
        });

        jTextAreaSourceFiles.setBackground(new java.awt.Color(0, 0, 0));
        jTextAreaSourceFiles.setColumns(20);
        jTextAreaSourceFiles.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextAreaSourceFiles.setForeground(new java.awt.Color(204, 204, 204));
        jTextAreaSourceFiles.setRows(5);
        jTextAreaSourceFiles.setText("Source Files...");
        jTextAreaSourceFiles.setToolTipText("List of added files");
        jScrollPaneSourceFiles.setViewportView(jTextAreaSourceFiles);

        jPanelConvertorSettings.setBackground(new java.awt.Color(0, 0, 0));

        jLabelSettings.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSettings.setForeground(new java.awt.Color(0, 255, 255));
        jLabelSettings.setText("Settings...");

        jLabelFont.setBackground(new java.awt.Color(0, 0, 0));
        jLabelFont.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFont.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFont.setText("Font       (Recommended - Tahoma)");

        jLabelFontSize.setBackground(new java.awt.Color(0, 0, 0));
        jLabelFontSize.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFontSize.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFontSize.setText("Font Size (Recommended - 21)");

        jLabelFontStyle.setBackground(new java.awt.Color(0, 0, 0));
        jLabelFontStyle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFontStyle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFontStyle.setText("Font Style (Recommended - Plain)");

        jLabelOutline.setBackground(new java.awt.Color(0, 0, 0));
        jLabelOutline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelOutline.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOutline.setText("Outline    (Recommended - 1 to 3)");

        jLabelShadow.setBackground(new java.awt.Color(0, 0, 0));
        jLabelShadow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelShadow.setForeground(new java.awt.Color(255, 255, 255));
        jLabelShadow.setText("Shadow   (Recommended - 1 to 5)");

        jCheckBoxEnableAnimation.setBackground(new java.awt.Color(0, 0, 0));
        jCheckBoxEnableAnimation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxEnableAnimation.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxEnableAnimation.setSelected(true);
        jCheckBoxEnableAnimation.setText("Enable Animated Fade Effect ");

        jComboBoxFont.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxFont.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxFont.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxFont.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxFont.setToolTipText("");

        jComboBoxFontSize.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxFontSize.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxFontSize.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxFontSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxFontStyle.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxFontStyle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxFontStyle.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxFontStyle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxOutline.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxOutline.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxOutline.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxOutline.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxShadow.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxShadow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxShadow.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxShadow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelFadInDuration.setBackground(new java.awt.Color(0, 0, 0));
        jLabelFadInDuration.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFadInDuration.setText("Fade In Duration - ");

        jComboBoxFadeInDuration.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxFadeInDuration.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxFadeInDuration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelFadeOutDuration.setBackground(new java.awt.Color(0, 0, 0));
        jLabelFadeOutDuration.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFadeOutDuration.setText("Fade Out Duration -");

        jComboBoxFadeOutDuration.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxFadeOutDuration.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxFadeOutDuration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonConvert.setBackground(new java.awt.Color(0, 0, 0));
        jButtonConvert.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButtonConvert.setForeground(new java.awt.Color(0, 255, 255));
        jButtonConvert.setText("Convert");
        jButtonConvert.setToolTipText("Convert, App will exit after conversion");
        jButtonConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertActionPerformed(evt);
            }
        });

        jLabelAnimationHint1.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAnimationHint1.setForeground(new java.awt.Color(204, 204, 204));
        jLabelAnimationHint1.setText("Ideally Fade IN+OUT duration shuld be less than duration of dialog");

        jLabelAnimationHint2.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAnimationHint2.setForeground(new java.awt.Color(204, 204, 204));
        jLabelAnimationHint2.setText("But don't worry, app will take care of that & will adjust duration for each dialog");

        jLabelBottomOffset.setBackground(new java.awt.Color(0, 0, 0));
        jLabelBottomOffset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBottomOffset.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBottomOffset.setText("Offset from Bottom   (Recommended - 5 to 7)");

        jComboBoxBottomOffset.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxBottomOffset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxBottomOffset.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxBottomOffset.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelConvertorSettingsLayout = new javax.swing.GroupLayout(jPanelConvertorSettings);
        jPanelConvertorSettings.setLayout(jPanelConvertorSettingsLayout);
        jPanelConvertorSettingsLayout.setHorizontalGroup(
            jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConvertorSettingsLayout.createSequentialGroup()
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConvertorSettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSettings)
                            .addGroup(jPanelConvertorSettingsLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBoxEnableAnimation, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConvertorSettingsLayout.createSequentialGroup()
                                        .addGap(154, 154, 154)
                                        .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAnimationHint2)
                                            .addComponent(jLabelAnimationHint1)))
                                    .addComponent(jButtonConvert, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConvertorSettingsLayout.createSequentialGroup()
                                        .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelShadow)
                                            .addComponent(jLabelOutline)
                                            .addComponent(jLabelFontStyle)
                                            .addComponent(jLabelFontSize)
                                            .addComponent(jLabelFont))
                                        .addGap(75, 75, 75)
                                        .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBoxFont, 0, 314, Short.MAX_VALUE)
                                            .addComponent(jComboBoxFontSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxFontStyle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxOutline, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxShadow, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConvertorSettingsLayout.createSequentialGroup()
                                        .addComponent(jLabelBottomOffset)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxBottomOffset, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanelConvertorSettingsLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabelFadInDuration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxFadeInDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFadeOutDuration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxFadeOutDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelConvertorSettingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelFont, jLabelFontSize, jLabelFontStyle, jLabelOutline, jLabelShadow});

        jPanelConvertorSettingsLayout.setVerticalGroup(
            jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConvertorSettingsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFont))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFontSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFontStyle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxOutline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOutline))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxShadow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelShadow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBottomOffset)
                    .addComponent(jComboBoxBottomOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxEnableAnimation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFadeInDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFadInDuration)
                    .addComponent(jLabelFadeOutDuration)
                    .addComponent(jComboBoxFadeOutDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAnimationHint1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConvertorSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonConvert)
                    .addComponent(jLabelAnimationHint2))
                .addContainerGap())
        );

        jLabelOutputHint.setBackground(new java.awt.Color(0, 0, 0));
        jLabelOutputHint.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelOutputHint.setForeground(new java.awt.Color(204, 204, 204));
        jLabelOutputHint.setText("Output file(s) will be saved in same source file directory with name sourceFileName.ssa");

        javax.swing.GroupLayout jPanelConvertorParentLayout = new javax.swing.GroupLayout(jPanelConvertorParent);
        jPanelConvertorParent.setLayout(jPanelConvertorParentLayout);
        jPanelConvertorParentLayout.setHorizontalGroup(
            jPanelConvertorParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConvertorParentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConvertorParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneSourceFiles)
                    .addGroup(jPanelConvertorParentLayout.createSequentialGroup()
                        .addComponent(jButtonAddFiles)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelOutputHint))
                    .addComponent(jPanelConvertorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(jPanelConvertorSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelConvertorParentLayout.setVerticalGroup(
            jPanelConvertorParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConvertorParentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConvertorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConvertorParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOutputHint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneSourceFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelConvertorSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConvertorParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConvertorParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFilesActionPerformed
        // Add Files
        jTextAreaSourceFiles.setEditable(true);
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Subtitle File(.srt)", "srt"));
        jfc.setVisible(true);
        jfc.showOpenDialog(this);
        File tempFiles[] = jfc.getSelectedFiles();
        if (totalFiles == 0) {
            jTextAreaSourceFiles.setText("");
        }
        for (int i = 0; i < tempFiles.length; i++) {
            String ext = tempFiles[i].getName();
            ext = ext.substring(ext.length() - 3);
            if (ext.equals("srt") || ext.equals("SRT")) {
                if (totalFiles != 0) {
                    jTextAreaSourceFiles.setText(jTextAreaSourceFiles.getText() + "\n");
                }
                jTextAreaSourceFiles.setText(jTextAreaSourceFiles.getText() + tempFiles[i].getPath());
                totalFiles++;
            }
        }
        if (totalFiles == 0) {
            jTextAreaSourceFiles.setText("Source Files...");
        }
        jTextAreaSourceFiles.setEditable(false);
    }//GEN-LAST:event_jButtonAddFilesActionPerformed

    private void jButtonConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertActionPerformed
        //CONVERT CLICKED
        new Thread(this).start();
        jDialogConversionProgress.setSize(500, 600);
        jDialogConversionProgress.setLocationRelativeTo(null);
        jDialogConversionProgress.setVisible(true);
    }//GEN-LAST:event_jButtonConvertActionPerformed

    private void jDialogConversionProgressWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialogConversionProgressWindowClosing
        System.exit(1);
    }//GEN-LAST:event_jDialogConversionProgressWindowClosing

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MainWindow.getFrames()[0].setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void convertFile(File f) throws FileNotFoundException, IOException {
        boolean fade = jCheckBoxEnableAnimation.isSelected();
        String font = jComboBoxFont.getSelectedItem().toString();
        String size = "" + (Integer.parseInt(jComboBoxFontSize.getSelectedItem() + "") + 50);
        int style = jComboBoxFontStyle.getSelectedIndex();
        int bold = 0, italic = 0;
        if (style == 1) {
            bold = 1;
        } else if (style == 2) {
            italic = 1;
        } else if (style == 3) {
            bold = italic = 1;
        }
        String outline = jComboBoxOutline.getSelectedItem().toString();
        String shadow = jComboBoxShadow.getSelectedItem().toString();
        String fadein = jComboBoxFadeInDuration.getSelectedItem().toString();
        String fadeout = jComboBoxFadeOutDuration.getSelectedItem().toString();
        String offset = "" + (10 * (new Integer(jComboBoxBottomOffset.getSelectedItem().toString())));

        String outputPath = f.getPath();
        String outputName = f.getName();
        outputPath = outputPath.substring(0, outputPath.length() - outputName.length());
        outputName = outputName.substring(0, outputName.length() - 3) + "ssa";

        BufferedReader in = new BufferedReader(new FileReader(f));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath + outputName));
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
        writer.write("Style: Default," + font + "," + size + ",&H00FFFFFF,&H000000FF,&H00000000,&H00000000," + bold + "," + italic + ",0,0,100,100,0,0,1," + outline + "," + shadow + ",2,10,10," + offset + ",1");
        writer.newLine();
        writer.newLine();
        writer.write("[Events]");
        writer.newLine();
        writer.write("Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        writer.newLine();
        writer.write("Dialogue: 0,0:00:00.00,0:00:11.00,Default,,0000,0000,0000,,{" + s1.charAt(0) + "fad(1000,1000)}http://subtitlestudio.blogspot.com" + s1.charAt(0) + "NBy Swapnil http://www.facebook.com/djswap1216");
        writer.newLine();

        jTextAreaConvertorLog.append("\nSpliting in Single Lines...");

        String singleLine = "", content = "";
        while ((singleLine = in.readLine()) != null) {
            singleLine = singleLine.replace((char) 65533, s2.charAt(0));
            singleLine = singleLine.replace('’', s2.charAt(0));
            singleLine = singleLine.replace('‘', s2.charAt(0));
            content = content + singleLine;
            content = content + "\n";

            jTextAreaConvertorLog.append("\n" + singleLine);
            jTextAreaConvertorLog.setCaretPosition(jTextAreaConvertorLog.getText().length());
        }
        String srtContent[] = content.split("\n");
        int totalDialogs = 1;
        for (int i = 0; i < srtContent.length; i++) {
            if ("".equals(srtContent[i])) {
                totalDialogs++;
            }
        }
        MyMapping dialogs[] = new MyMapping[totalDialogs];

        jTextAreaConvertorLog.append("\nAdjusting Timecodes...");

        for (int i = 0, j = 0; i < srtContent.length; i++) {
            boolean flag = false;
            i++;
            dialogs[j] = new MyMapping();
            dialogs[j].setTime(srtContent[i]);

            i++;
            while (i < srtContent.length && !"".equals(srtContent[i])) {
                if (!flag) {
                    dialogs[j].setDialog(srtContent[i]);
                    flag = true;
                } else {
                    dialogs[j].setDialog(dialogs[j].getDialog() + myNewLine + srtContent[i]);
                }
                i++;
            }

            jTextAreaConvertorLog.append("\n" + dialogs[j].toString());
            jTextAreaConvertorLog.setCaretPosition(jTextAreaConvertorLog.getText().length());

            j++;
        }

        jTextAreaConvertorLog.append("\nWriting Output File...");

        if (fade) {
            dialogs[0].adjustFade(dialogs, fadein, fadeout, totalDialogs);
            for (int i = 0; i < totalDialogs; i++) {
                writer.write("Dialogue: 0,");
                String temp1 = new MyTime(dialogs[i].start).toString();
                temp1 = temp1 + " --> " + new MyTime(dialogs[i].end).toString();
                writer.write(MyTime.toSSA(temp1));
                writer.write("Default,,0000,0000,0000,,");
                writer.write("{" + s1.charAt(0));
                writer.write("fad(" + dialogs[i].fadeIN + "," + dialogs[i].fadeOUT + ")}");

                jTextAreaConvertorLog.append("\n" + dialogs[i].dialog);
                jTextAreaConvertorLog.setCaretPosition(jTextAreaConvertorLog.getText().length());

                writer.write(dialogs[i].dialog);
                writer.newLine();
            }
        } else {
            for (int i = 0; i < totalDialogs; i++) {
                writer.write("Dialogue: 0,");
                String temp1 = new MyTime(dialogs[i].start).toString();
                temp1 = temp1 + " --> " + new MyTime(dialogs[i].end).toString();
                writer.write(MyTime.toSSA(temp1));
                writer.write("Default,,0000,0000,0000,,");
                writer.write(dialogs[i].dialog);

                jTextAreaConvertorLog.append("\n" + dialogs[i].dialog);
                jTextAreaConvertorLog.setCaretPosition(jTextAreaConvertorLog.getText().length());

                writer.newLine();
            }
        }
        writer.close();
        jTextAreaConvertorLog.append("\nConversion Complete, Output File Closed...");
        jTextAreaConvertorLog.setCaretPosition(jTextAreaConvertorLog.getText().length() - 42);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddFiles;
    private javax.swing.JButton jButtonConvert;
    private javax.swing.JCheckBox jCheckBoxEnableAnimation;
    private javax.swing.JComboBox jComboBoxBottomOffset;
    private javax.swing.JComboBox jComboBoxFadeInDuration;
    private javax.swing.JComboBox jComboBoxFadeOutDuration;
    private javax.swing.JComboBox jComboBoxFont;
    private javax.swing.JComboBox jComboBoxFontSize;
    private javax.swing.JComboBox jComboBoxFontStyle;
    private javax.swing.JComboBox jComboBoxOutline;
    private javax.swing.JComboBox jComboBoxShadow;
    private javax.swing.JDialog jDialogConversionProgress;
    private javax.swing.JLabel jLabelAnimationHint1;
    private javax.swing.JLabel jLabelAnimationHint2;
    private javax.swing.JLabel jLabelBottomOffset;
    private javax.swing.JLabel jLabelConverting;
    private javax.swing.JLabel jLabelConvertorTitle;
    private javax.swing.JLabel jLabelCurrentFile;
    private javax.swing.JLabel jLabelFadInDuration;
    private javax.swing.JLabel jLabelFadeOutDuration;
    private javax.swing.JLabel jLabelFont;
    private javax.swing.JLabel jLabelFontSize;
    private javax.swing.JLabel jLabelFontStyle;
    private javax.swing.JLabel jLabelOutline;
    private javax.swing.JLabel jLabelOutputHint;
    private javax.swing.JLabel jLabelSettings;
    private javax.swing.JLabel jLabelShadow;
    private javax.swing.JPanel jPanelConversionProgressParent;
    private javax.swing.JPanel jPanelConvertorParent;
    private javax.swing.JPanel jPanelConvertorSettings;
    private javax.swing.JPanel jPanelConvertorTitle;
    private javax.swing.JScrollPane jScrollPaneConvertorLog;
    private javax.swing.JScrollPane jScrollPaneSourceFiles;
    private javax.swing.JTextArea jTextAreaConvertorLog;
    private javax.swing.JTextArea jTextAreaSourceFiles;
    // End of variables declaration//GEN-END:variables

    int totalFiles = 0;
    String s1 = "\\", myNewLine = s1.charAt(0) + "N", s2 = "''";

    @Override
    public void run() {
        try {
            String files[] = jTextAreaSourceFiles.getText().split("\n");
            for (int i = 0; i < files.length; i++) {
                File f = new File(files[i]);
                jLabelCurrentFile.setText("" + f.getName());
                convertFile(f);
            }
            jTextAreaConvertorLog.setEditable(false);
            jDialogConversionProgress.dispose();
            this.initiateAll();
        } catch (Exception ex) {
            Logger.getLogger(Convertor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
