package com.frame.center.right;

import com.frame.UI.MyScrollBarUI;
import com.frame.center.right.text.ViewTextPane;
import com.service.FileService;
import com.service.impl.FileServiceImpl;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

/**
 * @Description TODO RightPanel 右面板
 * @Author ZFiend
 * @Create 2023.02.05 19:36
 */
public class RightPane extends JScrollPane {

    private static FileService fileService = new FileServiceImpl();
    private static ViewTextPane viewTextPanel;


    /**
     * @description: TODO [RightPanel] 右面板构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:38
     * @param:
     * @return:
     */
    public RightPane() {
        // 设置背景透明
        this.getViewport().setBackground(null);
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1, 1));
        // 添加文本域
        viewTextPanel = new ViewTextPane();
        this.getViewport().add(viewTextPanel);
        // 设置自定义滚动条
        this.getVerticalScrollBar().setUI(MyScrollBarUI.createUI());
        this.getHorizontalScrollBar().setUI(MyScrollBarUI.createUI());
    }

    public static void addText(String path) {
        if (path.endsWith(".java")) {
            String javadocPath = fileService.javaShow(path);
            try {
                viewTextPanel.setPage(javadocPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            String classText = fileService.classShow(path);
            viewTextPanel.setText(classText);
            viewTextPanel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            viewTextPanel.setForeground(ColorUtil.WHITE);
            viewTextPanel.setBorder(new EmptyBorder(10, 10, 0, 0));
        }
    }
}
