package com.frame.center.left.selection;

import com.frame.UI.MyScrollBarUI;
import com.frame.action.SelectFileAction;

import javax.swing.*;

/**
 * @Description TODO RightPanel 选项面板
 * @Author ZFiend
 * @Create 2023.02.05 19:36
 */
public class SelectionPane extends JScrollPane {
    FileListTree fileListTree;

    /**
     * @description: TODO [SelectionPanel] 选项面板构造函数
     * @author: ZFiend
     * @date: 2023/2/5 19:39
     * @param: path
     * @return:
     */
    public SelectionPane(String path) {
        //  设置背景透明
        this.getViewport().setBackground(null);
        this.setOpaque(false);
        //  设置边框
        this.setBorder(null);
        //  读取文件内容
        fileListTree = FileListTree.init(path);
        fileListTree.addMouseListener(new SelectFileAction(fileListTree));
        this.getViewport().add(fileListTree);
        // 设置自定义滚动条
        this.getVerticalScrollBar().setUI(MyScrollBarUI.createUI());
        this.getHorizontalScrollBar().setUI(MyScrollBarUI.createUI());
    }

    public void init(String path) {
        fileListTree = FileListTree.init(path);
    }
}
