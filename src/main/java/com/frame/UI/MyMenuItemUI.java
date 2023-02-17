package com.frame.UI;

import com.util.ColorUtil;

import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;

/**
 * @Description TODO MyMenuItemUI
 * @Author ZFiend
 * @Create 2023.02.17 17:01
 */
public class MyMenuItemUI extends BasicMenuItemUI {
    private static final Color selectedBackgroundColor = ColorUtil.BLACK_DEEP_1;
    private static final Color selectForegroundColor = ColorUtil.WHITE;

    /**
     * @description: TODO [MyMenuItemUI] 设置子菜单被选中时的前景和背景色
     * @author: ZFiend
     * @date: 2023/2/17 17:46
     * @param:
     * @return:
     */
    public MyMenuItemUI() {
        super.selectionBackground = selectedBackgroundColor;
        super.selectionForeground = selectForegroundColor;
    }

}
