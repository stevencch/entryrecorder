/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.common;

import com.cch.aj.entryrecorder.frame.SettingsJFrame;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chacao
 */
public class AppHelper {

    public static String currentDir = System.getProperty("user.dir");
    public static JFileChooser fc = new JFileChooser(currentDir + "\\images");
    public static String defaultShift="shift";

    public static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public static ImageIcon getScaledImage(ImageIcon imageIcon, int w, int h) {
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon imageIconNew = new ImageIcon(newimg);
        return imageIconNew;
    }

    public static void selectImage(JPanel panel, JLabel lable) {
        int returnVal = fc.showOpenDialog(panel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String dir = file.getPath().replaceAll(Pattern.quote(currentDir), "");
            lable.setText(dir);
            DisplayImage(dir, panel);
        }
    }

    public static void DisplayImage(String dir, JPanel panel) {
        int size=300;
        DisplayImage(dir, panel,size);
    }

    public static void DisplayImage(String dir,  JPanel panel,int size) {
        try {
            BufferedImage myPicture = ImageIO.read(new File(currentDir + dir));
            JLabel picLabel = new JLabel((AppHelper.getScaledImage(new ImageIcon(myPicture), size,size)));
            panel.setLayout(new FlowLayout());
            panel.removeAll();
            panel.add(picLabel);
        } catch (IOException ex) {
            Logger.getLogger(SettingsJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean CheckTwoDigit(String input){
        boolean result=false;
        result=input.matches("\\d*\\.[\\d]{2}");
        return result;
    }
}
