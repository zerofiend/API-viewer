package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * @Description TODO TabbedPaneUI
 * @Author ZFiend
 * @Create 2023.02.06 23:17
 */
public class MyCenterPaneUI extends BasicTabbedPaneUI {
    private Color defaultColor = ColorUtil.BLACK_DEEP_3;

    private Color selectedColor = ColorUtil.BLACK_DEEP_2;
    private Color defaultBorderColor = ColorUtil.BLACK_DEEP_1;
    private Color selectedBorderColor = ColorUtil.PURPLE_TYPE_2;

    private int select_x = 0;
    private int select_y = 0;
    private int select_w = 0;
    private int select_h = 0;

    /**
     * @description: TODO [createUI] 默认构造UI函数
     * @author: ZFiend
     * @date: 2023/2/10 19:15
     * @param: c
     * @return: javax.swing.plaf.ComponentUI
     */
    public static ComponentUI createUI() {
        return new MyCenterPaneUI();
    }

    /**
     * @description: TODO [paintTabBorder] 设置标签边框样式
     * @author: ZFiend
     * @date: 2023/2/7 0:21
     * @param: g
     * @param: tabPlacement
     * @param: tabIndex
     * @param: x
     * @param: y
     * @param: w
     * @param: h
     * @param: isSelected
     * @return: void
     */
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        if (isSelected) g.setColor(selectedBorderColor);
        else g.setColor(defaultBorderColor);
        switch (tabPlacement) {
            case LEFT:
                g.drawLine(x, y, x, y + h - 1);
                g.drawLine(x, y, x + w - 1, y);
                g.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
                break;
            case RIGHT:
                g.drawLine(x, y, x + w - 1, y);
                g.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
                g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
                break;
            case BOTTOM:
                g.drawLine(x, y, x, y + h - 1);
                g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
                g.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
                break;
            case TOP:
            default:
                g.drawLine(x, y, x, y + h - 1);
                g.drawLine(x, y, x + w - 1, y);
                g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
        }
    }

    /**
     * @description: TODO [calculateTabWidth] 设置标签宽度
     * @author: ZFiend
     * @date: 2023/2/7 0:22
     * @param: tabPlacement
     * @param: tabIndex
     * @param: metrics
     * @return: int
     */
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        // 可根据placement来做不同的调整
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }

    /**
     * @description: TODO [paintFocusIndicator] 标签获取焦点时样式，用于去除虚线
     * @author: ZFiend
     * @date: 2023/2/7 0:23
     * @param: g
     * @param: tabPlacement
     * @param: rects
     * @param: tabIndex
     * @param: iconRect
     * @param: textRect
     * @param: isSelected
     * @return: void
     */
    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        // 重写空方法，主要用来去掉虚线
    }

    /**
     * @description: TODO [paintTabBackground] 设置选项卡样式
     * @author: ZFiend
     * @date: 2023/2/7 0:10
     * @param: g
     * @param: tabPlacement
     * @param: tabIndex
     * @param: x
     * @param: y
     * @param: w
     * @param: h
     * @param: isSelected
     * @return: void
     */
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        g.setColor(!isSelected || selectedColor == null ? defaultColor : selectedColor);
        if (isSelected) {
            select_x = x;
            select_y = y;
            select_w = w;
            select_h = h;
        }

        switch (tabPlacement) {
            case LEFT:
                g.fillRect(x + 1, y + 1, w - 1, h - 3);
                break;
            case RIGHT:
                g.fillRect(x, y + 1, w - 2, h - 3);
                break;
            case BOTTOM:
                g.fillRect(x + 1, y, w - 3, h - 1);
                break;
            case TOP:

            default:
                g.fillRect(x + 1, y + 1, w - 3, h - 1);

        }

    }

    protected LayoutManager createLayoutManager() {


        return new TabbedPaneLayout();

    }// 设置Layout

    public class TabbedPaneLayout extends BasicTabbedPaneUI.TabbedPaneLayout {
        /**
         * @description: TODO [calculateTabRects] 计算选项卡矩形
         * @author: ZFiend
         * @date: 2023/2/6 23:50
         * @param: tabPlacement
         * @param: tabCount
         * @return: void
         */
        protected void calculateTabRects(int tabPlacement, int tabCount) {
            super.calculateTabRects(tabPlacement, tabCount);

            setRec(5);
            // 设置间距
            tabInsets.bottom = 8;//设置选项卡高度
        }

        public void setRec(int rec) {
            for (int i = 0; i < rects.length; i++) {
                rects[i].x = rects[i].x + rec * i;
            }
        }
    }

    /**
     * @description: TODO [setDefaultColor] 设置默认颜色
     * @author: ZFiend
     * @date: 2023/2/6 23:48
     * @param: defaultColor
     * @return: void
     */
    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    /**
     * @description: TODO [setSelectedColor] 设置选中颜色
     * @author: ZFiend
     * @date: 2023/2/6 23:48
     * @param: selectedColor
     * @return: void
     */
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    /**
     * @description: TODO [installDefaults] 设置边框不可见
     * @author: ZFiend
     * @date: 2023/2/6 23:48
     * @param:
     * @return: void
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();
        if (contentBorderInsets != null) {
            contentBorderInsets = new Insets(0, 0, 0, 0);
        }
    }

    /*    /**
     * @description: TODO [paintContentBorder] 设置主题内容边框
     * @author: ZFiend
     * @date: 2023/2/6 23:48
     * @param: g
     * @param: tabPlacement
     * @param: selectedIndex
     * @return: void
     *//*
    protected void paintContentBorder(Graphics g, int tabPlacement,
                                      int selectedIndex) {
        int width = tabPane.getWidth();
        int height = tabPane.getHeight();
        Insets insets = tabPane.getInsets();
        Insets tabAreaInsets = getTabAreaInsets(tabPlacement);

        int x = insets.left;
        int y = insets.top;
        int w = width - insets.right - insets.left;
        int h = height - insets.top - insets.bottom;

        switch (tabPlacement) {
            case LEFT:
                x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);

                w -= (x - insets.left);
                break;
            case RIGHT:
                w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);

                w += tabAreaInsets.left;

                break;
            case BOTTOM:
                h -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);

                h += tabAreaInsets.top;

                break;
            case TOP:


            default:
                y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);

                y -= tabAreaInsets.bottom;

                h -= (y - insets.top);
        }

        // Fill region behind content area
        Color color = UIManager.getColor("TabbedPane.contentAreaColor");
        if (color != null) {
            g.setColor(color);
        } else if (selectedColor == null) {
            g.setColor(tabPane.getBackground());
        } else {
            g.setColor(selectedColor);
        }
        g.fillRect(x, y, w, h);
        g.setColor(selectedColor);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10.0f));

        g.drawLine(x + 6, y + 5, w, y + 5);
        g2d.setStroke(new BasicStroke(1.0f));

    }*/
}
