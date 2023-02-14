package com.frame.UI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 * @Description TODO OptionPaneUI
 * @Author ZFiend
 * @Create 2023.02.11 20:56
 */
public class OptionPaneUI extends BasicOptionPaneUI {
    /**
     * @description: TODO [OptionPaneUI] 构造函数
     * @author: ZFiend
     * @date: 2023/2/11 20:59
     * @param:
     * @return:
     */
    public OptionPaneUI() {
        super();
    }

    /**
     * @description: TODO [createUI] 默认构造UI函数
     * @author: ZFiend
     * @date: 2023/2/11 21:03
     * @param: c
     * @return: javax.swing.plaf.ComponentUI
     */
    public static ComponentUI createUI(JComponent c) {
        return new OptionPaneUI();
    }
}
