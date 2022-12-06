package com.frame.menu.component;

import com.frame.file.FilePanel;
import com.service.FileService;
import com.service.impl.FileServiceImpl;
import com.util.ColorUtil;
import com.util.FIlePathUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class MenuBarPanel extends JMenuBar {
    FileService fileService;

    public MenuBarPanel(JFrame jFrame) {
        fileService = new FileServiceImpl();
//        this.setOpaque(false);
        this.setBackground(ColorUtil.setTransparent(0.15f));
        this.setBorder(new LineBorder(ColorUtil.BLUE_DEEP_3, 1));
        this.setPreferredSize(new Dimension(0, 30));
        /*
         * 创建一级菜单
         */
        JMenu fileMenu = new JMenu("<html><span color=#c6e2ff style=\"font-size:12px;\">文件</span></html>");
        JMenu editMenu = new JMenu("<html><span color=#c6e2ff style=\"font-size:12px;\">编辑</span></html>");
        JMenu guideMenu = new JMenu("<html><span color=#c6e2ff style=\"font-size:12px;\">导航</span></html>");
        JMenu searchMenu = new JMenu("<html><span color=#c6e2ff style=\"font-size:12px;\">查找</span></html>");
        JMenu helpMenu = new JMenu("<html><span color=#c6e2ff style=\"font-size:12px;\">帮助</span></html>");
        // 一级菜单样式设置
        setMenuTheme(fileMenu, editMenu, guideMenu, searchMenu, helpMenu);
        // 一级菜单添加到菜单栏
        this.addMenus(fileMenu, editMenu, guideMenu, searchMenu, helpMenu);

        /*
         * 创建 "文件" 一级菜单的子菜单
         */
        JMenuItem newMenuItem = new JMenuItem("新建");
        JMenuItem openMenuItem = new JMenuItem("打开");
        JMenuItem openReMenuItem = new JMenuItem("打开最近");
        JMenuItem closeMenuItem = new JMenuItem("关闭");
        JMenuItem exitMenuItem = new JMenuItem("退出");

        // 设置子菜单的样式
        setMenuItemTheme(newMenuItem, openMenuItem, openReMenuItem, closeMenuItem, exitMenuItem);

        // 子菜单添加到一级菜单
        addMenuItems(fileMenu, newMenuItem, openMenuItem, openReMenuItem);
        fileMenu.addSeparator();
        addMenuItems(fileMenu, closeMenuItem, exitMenuItem);

        /*
         * 创建 "编辑" 一级菜单的子菜单
         */
        JMenuItem cancelMenuItem = new JMenuItem("撤销");
        JMenuItem renewalMenuItem = new JMenuItem("重做");
        JMenuItem shearMenuItem = new JMenuItem("剪切");
        JMenuItem copyReMenuItem = new JMenuItem("复制");
        JMenuItem pasteMenuItem = new JMenuItem("粘贴");

        // 设置子菜单的样式
        setMenuItemTheme(cancelMenuItem, renewalMenuItem, copyReMenuItem, shearMenuItem, pasteMenuItem);
        // 子菜单添加到一级菜单
        addMenuItems(editMenu, cancelMenuItem, renewalMenuItem);
        editMenu.addSeparator();
        addMenuItems(editMenu, shearMenuItem, copyReMenuItem, pasteMenuItem);

        /*
         * 创建 "导航" 一级菜单的子菜单
         */
        JMenuItem retreatMenuItem = new JMenuItem("撤退");
        JMenuItem forwardMenuItem = new JMenuItem("前进");

        // 设置子菜单的样式
        setMenuItemTheme(retreatMenuItem, forwardMenuItem);
        // 子菜单添加到一级菜单
        addMenuItems(guideMenu, retreatMenuItem, forwardMenuItem);

        /*
         * 创建 "查找" 一级菜单的子菜单
         */
        JMenuItem searchMenuItem = new JMenuItem("搜索");

        // 设置子菜单的样式
        setMenuItemTheme(searchMenuItem);
        // 子菜单添加到一级菜单
        addMenuItems(searchMenu, searchMenuItem);

        /*
         * 创建 "帮助" 一级菜单的子菜单
         */
        JMenuItem helpMenuItem = new JMenuItem("帮助");
        JMenuItem registerMenuItem = new JMenuItem("注册");
        JMenuItem updateMenuItem = new JMenuItem("检查更新");
        JMenuItem aboutMenuItem = new JMenuItem("关于");

        // 设置子菜单的样式
        setMenuItemTheme(helpMenuItem, registerMenuItem, updateMenuItem, aboutMenuItem);
        // 子菜单添加到一级菜单
        addMenuItems(helpMenu, helpMenuItem);
        helpMenu.addSeparator();
        addMenuItems(helpMenu, registerMenuItem, updateMenuItem, aboutMenuItem);

        /*
          添加菜单的行为
         */
        openMenuItem.addActionListener(e -> {
            //显示一个文件选择器
            FilePanel filePanel = new FilePanel("D:");
            filePanel.showOpenDialog(jFrame);
            File file = filePanel.getSelectedFile();
            if (file != null) {
                //进行显示
                String path = file.getPath();
                String name = file.getName().split("\\.")[0];
                String type = file.getName().split("\\.")[1];
                if (type.equals(FIlePathUtil.ZIP_SUFFIX)) {
                    fileService.zipReader(path, name);
                } else if (type.equals(FIlePathUtil.JAR_SUFFIX)) {
                    fileService.jarReader(path, name);
                } else {
                    System.out.println("文件格式错误！");
                }
            }
        });
        exitMenuItem.addActionListener(e -> System.exit(0));
    }

    /**
     * 设置一级菜单样式
     *
     * @param jMenus 一级菜单列表
     */
    private static void setMenuTheme(JMenu... jMenus) {
        for (JMenu jMenu : jMenus) {
            jMenu.setFont(new Font("微软雅黑", Font.BOLD, 16));
            jMenu.setBorder(new EmptyBorder(0, 10, 0, 10));
        }
    }

    private void addMenus(JMenu... jMenus) {
        for (JMenu jMenu : jMenus) {
            add(jMenu);
        }
    }

    /**
     * 设置子菜单样式
     *
     * @param jMenuItems 子菜单列表
     */
    private static void setMenuItemTheme(JMenuItem... jMenuItems) {
        for (JMenuItem jMenuItem : jMenuItems) {
            jMenuItem.setPreferredSize(new Dimension(120, 25));
            jMenuItem.setBorder(new EmptyBorder(0, 20, 0, 0));
            jMenuItem.setFont(new Font("微软雅黑", Font.BOLD, 16));
        }
    }

    /**
     * 子菜单添加至一级菜单
     *
     * @param jMenu      一级菜单
     * @param jMenuItems 子菜单
     */
    private static void addMenuItems(JMenu jMenu, JMenuItem... jMenuItems) {
        for (JMenuItem jMenuItem : jMenuItems) {
            jMenu.add(jMenuItem);
        }
    }

}
