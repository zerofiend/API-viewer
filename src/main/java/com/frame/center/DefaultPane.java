package com.frame.center;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @Description TODO DefaultPane
 * @Author ZFiend
 * @Create 2023.02.12 14:17
 */
public class DefaultPane extends JPanel {
    Toolkit kit = Toolkit.getDefaultToolkit();  // 获取工具包
    Dimension screenSize = kit.getScreenSize(); // 获取屏幕大小
    int paneWidth = screenSize.width;    // 面板宽度
    int paneHeight = screenSize.height;  // 面板高度
    String iconPath = "src/main/resources/images/icon_black_deep_2.png";    // icon 路径
    int iconWidth = 400; // icon 宽度
    int iconHeight = 400;// icon 高度

    public DefaultPane() {
        // 设置布局
        this.setLayout(new BorderLayout());
        // 设置背景颜色
        this.setBackground(ColorUtil.BLACK_DEEP_4);
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1));
        // 设置图标
        ImageIcon icon = new ImageIcon(iconPath);
        icon.setImage(icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_DEFAULT));
        JLabel iconImage = new JLabel(icon, JLabel.CENTER);
        // 添加到新面板中
        this.add(iconImage, BorderLayout.CENTER);
    }
}
