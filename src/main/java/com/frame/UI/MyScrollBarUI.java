package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * @Description TODO ScrollBarCustomize 自定义滚动条
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */

public class MyScrollBarUI extends BasicScrollBarUI {

    // 手柄宽度
    private static final int thumbWidth = 10;

    //手柄透明度
    private static final float opaque = 0.2f;
    // 手柄边框颜色
    private static final Color thumbColor = ColorUtil.PURPLE_TYPE_2;

    // 手柄颜色
    private static final Color thumbColorFrom = ColorUtil.PURPLE_TYPE_2;
    private static final Color thumbColorTo = ColorUtil.PURPLE_TYPE_2;

    // 滑道颜色
    private static final Color backColorFrom = ColorUtil.BLACK_DEEP_5;
    private static final Color backColorTo = ColorUtil.BLACK_DEEP_5;
    // 滚动条宽度
    private static final int width = 3;

    /**
     * @description: TODO [createUI] 默认构造UI函数
     * @author: ZFiend
     * @date: 2023/2/11 21:05
     * @param:
     * @return: javax.swing.plaf.ComponentUI
     */
    public static javax.swing.plaf.ScrollBarUI createUI() {
        return new MyScrollBarUI();
    }

    /**
     * @description: TODO [configureScrollBarColors] 设置滚动条
     * @author: ZFiend
     * @date: 2023/2/5 19:55
     * @param:
     * @return: void
     */
    @Override
    protected void configureScrollBarColors() {
        setThumbBounds(0, 0, width, 10);

    }

    /**
     * @description: TODO [getPreferredSize] 设置滚动条宽度
     * @author: ZFiend
     * @date: 2023/2/5 19:56
     * @param: c
     * @return: java.awt.Dimension
     */

    @Override
    public Dimension getPreferredSize(final JComponent c) {
        // TODO Auto-generated method stub
        //        c.setPreferredSize(new Dimension(thumbWidth, 0));
        c.setPreferredSize(new Dimension(thumbWidth, thumbWidth));
        return super.getPreferredSize(c);

    }

    /**
     * @description: TODO [paintTrack] 绘制滑道区域背景
     * @author: ZFiend
     * @date: 2023/2/5 19:56
     * @param: g
     * @param: c
     * @param: trackBounds
     * @return: void
     */

    @Override
    public void paintTrack(final Graphics g, final JComponent c, final Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = null;
        //判断滚动条是垂直的 还是水平的
        if (this.scrollbar.getOrientation() == Adjustable.VERTICAL) {
            //设置画笔
            // 颜色渐变
            gp = new GradientPaint(0, 0, backColorFrom, 0, trackBounds.height - width, backColorTo);

        }
        if (this.scrollbar.getOrientation() == Adjustable.HORIZONTAL) {
            gp = new GradientPaint(0, 0, backColorFrom, trackBounds.width - width, 0, backColorTo);
        }
        g2.setPaint(gp);
        //填充Track
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        //绘制Track的边框
        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT) {
            this.paintDecreaseHighlight(g);
        }
        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT) {
            this.paintIncreaseHighlight(g);
        }
    }

    /**
     * @description: TODO [paintThumb] 绘制滚动条
     * @author: ZFiend
     * @date: 2023/2/5 19:58
     * @param: g
     * @param: c
     * @param: thumbBounds
     * @return: void
     */
    @Override
    protected void paintThumb(final Graphics g, final JComponent c, final Rectangle thumbBounds) {
        // 把绘制区的x，y点坐标定义为坐标系的原点
        // 这句一定一定要加上啊，不然拖动就失效了
        g.translate(thumbBounds.x, thumbBounds.y);
        // 设置把手颜色
        //        g.setColor(new Color(230, 230, 250));
        g.setColor(thumbColor);
        // 画一个圆角矩形
        // 这里面前四个参数就不多讲了，坐标和宽高
        // 后两个参数需要注意一下，是用来控制角落的圆角弧度
        //         g.drawRoundRect(0, 0, 5, thumbBounds.height - 1, 5, 5);
        g.drawRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 5, 5);
        // 消除锯齿
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(rh);
        // 半透明
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));
        // 设置填充颜色，这里设置了渐变，由下往上
        g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, thumbColorFrom, c.getWidth() / 2, c.getHeight(),
                thumbColorTo));
        // 填充圆角矩形
        //        g2.fillRoundRect(0, 0, thumbWidth, thumbBounds.height - 1, 5, 5);
        g2.fillRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 5, 5);
    }

    /**
     * @description: TODO [createIncreaseButton] 绘制滚动条上方按钮
     * @author: ZFiend
     * @date: 2023/2/5 20:00
     * @param: orientation
     * @return: javax.swing.JButton
     */
    @Override
    protected JButton createIncreaseButton(final int orientation) {
        JButton button = new JButton();
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        // 设置为null, 禁止上方按钮
        button.setBorder(null);
        return button;
    }

    /**
     * @description: TODO [createDecreaseButton] 绘制滚动条下方按钮
     * @author: ZFiend
     * @date: 2023/2/5 19:59
     * @param: orientation
     * @return: javax.swing.JButton
     */
    @Override
    protected JButton createDecreaseButton(final int orientation) {
        JButton button = new JButton();
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setFocusable(false);
        // 设置为null, 禁止上方按钮
        button.setBorder(null);
        return button;
    }
}
