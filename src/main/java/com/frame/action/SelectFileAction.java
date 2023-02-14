package com.frame.action;

import com.frame.center.right.text.ViewTextPane;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @Description TODO SelectFileAction 点击树状文件时反馈
 * @Author ZFiend
 * @Create 2023.02.12 19:31
 */
public class SelectFileAction implements MouseListener {
    JTree tree;

    public SelectFileAction(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 如果在这棵树上点击了2次,即双击
        if (e.getClickCount() == 2) {
            // 按照鼠标点击的坐标点获取路径
            TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
            if (selPath != null)// 谨防空指针异常!双击空白处是会这样
            {
                StringBuilder sb = new StringBuilder();
                Object[] path = selPath.getPath();
                for (int i = 0; i < path.length; i++) {
                    sb.append(path[i]);
                    if (i != path.length - 1) sb.append("\\");
                }
                String filePath = sb.toString();
                if (filePath.endsWith(".class") || filePath.endsWith(".java")) {
                    ViewTextPane.addText(filePath);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
