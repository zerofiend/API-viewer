package com.frame.center;

import com.frame.UI.MyCenterPaneUI;
import com.frame.UI.MyTabPaneUI;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @Description TODO CenterPane 主面板
 * @Author ZFiend
 * @Create 2023.02.05 18:21
 */
public class CenterPane extends JTabbedPane {
    private static CenterPane centerPane;  // 单例模式对象
    private static int selectNumber = 0;

    /**
     * @description: TODO [getCenterPane] 获取单例模式对象
     * @author: ZFiend
     * @date: 2023/2/12 16:42
     * @param:
     * @return: com.frame.center.CenterPane
     */
    public static CenterPane getCenterPane() {
        if (centerPane == null) {
            centerPane = new CenterPane();
        }
        return centerPane;
    }

    /**
     * @description: TODO [CenterPanel] 主面板构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:18
     * @param: c1
     * @param: c2
     * @return:
     */
    private CenterPane() {
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1));
        // 设置样式
        this.setUI(MyCenterPaneUI.createUI());
        // 设置文字颜色
        this.setForeground(ColorUtil.WHITE);
        this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        // 添加默认界面
        DefaultPane defaultPane = new DefaultPane();
        this.addTab("", defaultPane);
        // 添加切换标签监视器
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                selectNumber = getSelectedIndex() + 1;
            }
        });
    }

    public static void setFileTab(String title, String path) {
        centerPane.addTab("", new SplitCenterPane(path));
        int count = centerPane.getTabCount();
        centerPane.setTabComponentAt(count - 1, new MyTabPaneUI(title, centerPane));
        centerPane.setSelectedIndex(count - 1);
    }
}
