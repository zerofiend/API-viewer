package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * @Description TODO RightPaneUI
 * @Author ZFiend
 * @Create 2023.02.14 23:45
 */
public class MyRightPaneUI extends BasicTabbedPaneUI {
    private Color defaultColor = ColorUtil.BLACK_DEEP_3;

    private Color selectedColor = ColorUtil.BLACK_DEEP_2;
    private Color defaultBorderColor = ColorUtil.BLACK_DEEP_1;
    private Color selectedBorderColor = ColorUtil.PURPLE_TYPE_2;
    private Color backgroundColor = ColorUtil.BLACK_DEEP_5;

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
}
