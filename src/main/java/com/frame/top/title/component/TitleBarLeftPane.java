package com.frame.top.title.component;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class TitleBarLeftPane extends JPanel {
    public static JLabel jLabel;


    public TitleBarLeftPane(String title) {
        //左对齐
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 设置背景颜色
        this.setBackground(ColorUtil.setTransparent(0));

        // 设置标题栏
        jLabel = new JLabel(title);
        //设置ICON图标, "icon/logo.png" 是 resource 文件夹下的路径，如果没设置，默认是 classpath(src) 文件夹
        ImageIcon icon =
                new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("images/icon_white" +
                        ".png")).getPath());
        //设置icon大小，和缩放大小，Image.SCALE_AREA_AVERAGING = 16 最大
        icon.setImage(icon.getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
        jLabel.setIcon(icon);
        //设置字体
        jLabel.setFont(new Font("微软雅黑", 0, 16));
        //设置边框
        this.setBorder(new EmptyBorder(0, 5, 5, 5));
        this.add(jLabel);
    }

    public static void setTitle(String title) {
        jLabel.setText(title);
        jLabel.repaint();
    }
}

