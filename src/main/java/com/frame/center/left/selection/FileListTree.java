package com.frame.center.left.selection;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

public class FileListTree extends JTree {
    private static FileListTree fileListTree;
    private static DefaultMutableTreeNode fileTree;
    private static String tree_elbow = "src/main/resources/images/tree_elbow.png";
    private static String tree_elbow_add = "src/main/resources/images/tree_elbow_add.png";
    private static String tree_file = "src/main/resources/images/tree_file.png";
    private static String tree_folder = "src/main/resources/images/tree_folder.png";
    private static String tree_folder_open = "src/main/resources/images/tree_folder_open.png";

    private FileListTree() {
        super(fileTree);
        // 隐藏线条
        this.putClientProperty("JTree.lineStyle", "None");
        // 设置背景颜色
        this.setBackground(ColorUtil.BLACK_DEEP_4);
        // 设置边框
        this.setBorder(new EmptyBorder(10, 10, 0, 0));
        // 设置字体
        this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        // 设置子图标
        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) (this.getCellRenderer());
        BasicTreeUI ui = (BasicTreeUI) (this.getUI());
        render.setLeafIcon(new ImageIcon(tree_file));
        render.setClosedIcon(new ImageIcon(tree_folder));
        render.setOpenIcon(new ImageIcon(tree_folder_open));
        ui.setCollapsedIcon(new ImageIcon(tree_elbow_add));
        ui.setExpandedIcon(new ImageIcon(tree_elbow));
        // 设置子图标背景色
        render.setBackgroundNonSelectionColor(null);
        render.setBackgroundSelectionColor(ColorUtil.BLACK_DEEP_3);
        // 设置子图标字体颜色
        render.setTextNonSelectionColor(ColorUtil.GRAY);
        render.setTextSelectionColor(ColorUtil.PURPLE_TYPE_2);
        // 设置选中选项的边框
        render.setBorderSelectionColor(ColorUtil.BLACK_DEEP_1);
    }

    public static FileListTree init(String path) {
        fileTree = traverseFolder(path);
        return new FileListTree();
    }

    private static DefaultMutableTreeNode traverseFolder(String path) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new File(path).getName());
        File file = new File(path);

        if (file.exists()) {
            File[] files = file.listFiles();
            assert files != null;
            if (files.length == 0) {
                if (file.isDirectory()) {//如果是空文件夹
                    DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(file.getName(), false);
                    return leaf;
                }
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //是目录的话，生成节点，并添加里面的节点
                        root.add(traverseFolder(file2.getAbsolutePath()));
                    } else {
                        //是文件的话直接生成节点，并把该节点加到对应父节点上
                        DefaultMutableTreeNode temp = new DefaultMutableTreeNode(file2.getName());
                        if (!file2.getName().contains("$")) {
                            root.add(temp);
                        }
                    }
                }
            }
        } else {//文件不存在
            return null;
        }
        return root;
    }
}
