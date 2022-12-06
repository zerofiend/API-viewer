package com.frame.file;

import com.util.FIlePathUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class FilePanel extends JFileChooser {
    public FilePanel(String currentPath) {
        super(currentPath);
        this.setPreferredSize(new Dimension(800, 500));
        this.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().endsWith(FIlePathUtil.ZIP_POINT_SUFFIX) || f.getName().endsWith(FIlePathUtil.JAR_POINT_SUFFIX);
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
    }
}
