package com.frame.top.menu.component;

import com.frame.UI.MyMenuItemUI;
import com.frame.UI.MyMenuUI;
import com.frame.UI.MyPopupMenuUI;
import com.frame.UI.MySeparatorUI;
import com.frame.action.OpenFileAction;
import com.service.FileService;
import com.service.impl.FileServiceImpl;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @Description TODO MenuBarPanel 菜单栏
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class MenuBarPane extends JMenuBar {
    FileService fileService;    // 文件操作服务类
    String folderPath;  // 文件生成目录

    /**
     * @description: TODO [MenuBarPanel] 菜单栏构造函数
     * @author: ZFiend
     * @date: 2023/2/6 21:22
     * @param: jFrame
     * @param: selectPanel
     * @return:
     */
    public MenuBarPane(JFrame jFrame, JPanel selectPanel) {
        fileService = new FileServiceImpl();
//        this.setOpaque(false);
        this.setBackground(ColorUtil.BLACK_DEEP_4);
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1, 1));
        this.setPreferredSize(new Dimension(0, 30));
        /*
         * 创建一级菜单
         */
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu guideMenu = new JMenu("导航");
        JMenu searchMenu = new JMenu("查找");
        JMenu helpMenu = new JMenu("帮助");
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
        // 设置图标
        ImageIcon openIcon = new ImageIcon("src/main/resources/images/open_white.png");
        openIcon = new ImageIcon(openIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        openMenuItem.setIcon(openIcon);

        ImageIcon exitIcon = new ImageIcon("src/main/resources/images/exit_white.png");
        exitIcon = new ImageIcon(exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        exitMenuItem.setIcon(exitIcon);


        // 设置子菜单的样式
        setMenuItemTheme(newMenuItem, openMenuItem, openReMenuItem, closeMenuItem, exitMenuItem);

        // 子菜单添加到一级菜单
        addMenuItems(fileMenu, newMenuItem, openMenuItem, openReMenuItem);
        fileMenu.add(new MySeparatorUI());
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
        editMenu.add(new MySeparatorUI());
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
        helpMenu.add(new MySeparatorUI());
        addMenuItems(helpMenu, registerMenuItem, updateMenuItem, aboutMenuItem);
        // 添加菜单行为
        openMenuItem.addActionListener(new OpenFileAction(jFrame));
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
            jMenu.setForeground(ColorUtil.WHITE);
            jMenu.getPopupMenu().setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1));
            jMenu.getPopupMenu().setUI(new MyPopupMenuUI());
            jMenu.setUI(new MyMenuUI());
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
            jMenuItem.setPreferredSize(new Dimension(200, 30));
            jMenuItem.setBackground(null);
            jMenuItem.setOpaque(false);
            jMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            jMenuItem.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_5));
            jMenuItem.setForeground(ColorUtil.WHITE);
            jMenuItem.setUI(new MyMenuItemUI());
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
//            jMenuItem.paintComponents();
        }
    }
}
