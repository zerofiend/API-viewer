package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TabPaneUI extends JPanel implements MouseListener {

    private final JTabbedPane pane;    // 所在选项卡窗格

    private final JLabel lab1, lab2;

    public TabPaneUI(String title, JTabbedPane pane) {
        this.pane = pane;
        // 设置宽度
        this.setPreferredSize(new Dimension(70, 20));

        lab1 = new JLabel(title);
        // 设置字体
        lab1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lab1.setForeground(ColorUtil.WHITE);
        lab1.setHorizontalAlignment(JLabel.LEFT);    //设置文字显示在最左边
        // 设置宽度
        lab1.setPreferredSize(new Dimension(50, 15));

        lab2 = new JLabel("×");
        // 设置字体
        lab2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        lab2.setForeground(ColorUtil.WHITE);
        lab2.setHorizontalAlignment(JLabel.RIGHT);    //设置文字显示在最左边
        lab2.addMouseListener(this);
        // 设置宽度
        lab2.setPreferredSize(new Dimension(10, 15));

        // 设置背景颜色
        this.setBackground(ColorUtil.BLACK_DEEP_2);
        this.add(lab1);
        this.add(lab2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        JOptionPane option = new JOptionPane();
        option.setUI(new OptionPaneUI());
        int confirm = option.showConfirmDialog(this.pane, "确认关闭选项卡", "提示", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            this.pane.remove(this.pane.indexOfTabComponent(this));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        lab2.setForeground(ColorUtil.PURPLE_TYPE_2);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        lab2.setForeground(ColorUtil.WHITE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
