package com.frame.top.title;

import com.frame.top.title.component.TitleBarPane;

import javax.swing.*;
import java.awt.*;

/**
 * @Description TODO TitlePanel 标题面板
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class TitlePane extends JPanel {
    public TitleBarPane titleBarPanel; //  标题栏面板

    /**
     * @description: TODO [TitlePanel] 标题面板构造函数
     * @author: ZFiend
     * @date: 2023/2/6 21:13
     * @param: jFrame
     * @return:
     */
    public TitlePane(JFrame jFrame) {
        // 设置布局
        this.setLayout(new BorderLayout());
        // 添加标题栏
        titleBarPanel = new TitleBarPane(jFrame);
        this.add(titleBarPanel, BorderLayout.NORTH);
    }
}


