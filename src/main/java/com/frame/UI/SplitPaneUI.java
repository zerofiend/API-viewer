package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

/**
 * @Description TODO SplitPaneUI
 * @Author ZFiend
 * @Create 2023.02.10 19:12
 */
public class SplitPaneUI extends BasicSplitPaneUI {
    private static final Color backgroundColor = ColorUtil.BLACK_DEEP_2;    // 分隔栏背景色
    private static final Color borderColor = ColorUtil.BLACK_DEEP_1;    // 分隔栏边框色

    /**
     * @description: TODO [SplitPaneUI] 构造函数
     * @author: ZFiend
     * @date: 2023/2/10 19:25
     * @param:
     * @return:
     */
    public SplitPaneUI() {
        super();
    }

    /**
     * @description: TODO [createUI] 默认构造UI函数
     * @author: ZFiend
     * @date: 2023/2/10 19:16
     * @param: c
     * @return: javax.swing.plaf.ComponentUI
     */
    public static ComponentUI createUI() {
        return new SplitPaneUI();
    }

    /**
     * @description: TODO [createDefaultDivider] 创建默认分隔条
     * @author: ZFiend
     * @date: 2023/2/10 19:27
     * @param:
     * @return: javax.swing.plaf.basic.BasicSplitPaneDivider
     **/
    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        return new MyBasicSplitPaneDivider(this);
    }

    private static class MyBasicSplitPaneDivider extends BasicSplitPaneDivider {
        public MyBasicSplitPaneDivider(SplitPaneUI ui) {
            super(ui);
        }

        /**
         * @description: TODO [paint] 设置分隔栏的UI样式
         * @author: ZFiend
         * @date: 2023/2/10 20:02
         * @param: g
         * @return: void
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Dimension size = getSize();
            // 设置背景颜色
            g.setColor(backgroundColor);
            g.fillRect(0, 0, size.width, size.height);
        }
    }
}
