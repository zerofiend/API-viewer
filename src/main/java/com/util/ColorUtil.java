package com.util;

import java.awt.*;

/**
 * @Description TODO ColorUtil 颜色工具类
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class ColorUtil {
    //  通用
    public final static Color SIDE_PANEL_COLOR = new Color(0, 21, 41);
    public final static Color BLACK = new Color(20, 20, 20);
    public final static Color DARK_GRAY = new Color(100, 100, 100);
    public final static Color GRAY = new Color(167, 171, 175);
    public final static Color LIGHT_GRAY = new Color(200, 200, 200);
    public final static Color GHOST_WHITE = new Color(240, 242, 245);
    public final static Color WHITE = new Color(240, 240, 240);

    public final static Color GREEN = new Color(82, 196, 26);
    public final static Color LIGHT_GREEN = new Color(183, 235, 143);
    public final static Color WHITE_GREEN = new Color(246, 255, 237);


    public final static Color ORANGE = new Color(250, 173, 20);
    public final static Color LIGHT_ORANGE = new Color(255, 229, 143);
    public final static Color WHITE_ORANGE = new Color(255, 251, 230);


    public final static Color RED = new Color(255, 77, 79);
    public final static Color LIGHT_RED = new Color(255, 163, 158);
    public final static Color WHITE_RED = new Color(255, 241, 240);


    public final static Color BLUE = new Color(24, 144, 255);
    public final static Color LIGHT_BLUE = new Color(145, 213, 255);
    public final static Color WHITE_BLUE = new Color(230, 247, 255);

    //  常用蓝色
    public final static Color BLUE_DEEP_1 = new Color(236, 245, 255);
    public final static Color BLUE_DEEP_2 = new Color(217, 236, 255);
    public final static Color BLUE_DEEP_3 = new Color(198, 226, 255);
    public final static Color BLUE_DEEP_4 = new Color(179, 216, 255);
    public final static Color BLUE_DEEP_5 = new Color(160, 207, 255);
    public final static Color BLUE_DEEP_6 = new Color(140, 197, 255);
    public final static Color BLUE_DEEP_7 = new Color(121, 187, 255);
    public final static Color BLUE_DEEP_8 = new Color(102, 177, 255);
    public final static Color BLUE_DEEP_9 = new Color(83, 168, 255);
    public final static Color BLUE_DEEP_10 = new Color(64, 158, 255);

    // 常用黑色
    public final static Color BLACK_DEEP_1 = new Color(51, 51, 51);
    public final static Color BLACK_DEEP_2 = new Color(28, 28, 28);
    public final static Color BLACK_DEEP_3 = new Color(25, 25, 25);
    public final static Color BLACK_DEEP_4 = new Color(10, 10, 10);
    public final static Color BLACK_DEEP_5 = new Color(0, 0, 0);

    // 常用紫色
    public final static Color PURPLE_TYPE_1 = new Color(94, 53, 191);
    public final static Color PURPLE_TYPE_2 = new Color(98, 63, 165);
    public final static Color PURPLE_TYPE_3 = new Color(118, 88, 208);
    public final static Color PURPLE_TYPE_4 = new Color(129, 102, 195);
    public final static Color PURPLE_TYPE_5 = new Color(114, 9, 229);

    /**
     * @description: TODO [setTransparent] 获取透明色
     * @author: ZFiend
     * @date: 2023/2/5 20:04
     * @param: num
     * @return: java.awt.Color
     */
    public static Color setTransparent(int num) {
        return setTransparent(1, 1, 1, num);
    }

    /**
     * @description: TODO [setTransparent] 获取透明色
     * @author: ZFiend
     * @date: 2023/2/5 20:06
     * @param: color
     * @param: transparency
     * @return: java.awt.Color
     */
    public static Color setTransparent(Color color, int transparency) {
        return setTransparent(color.getRed(), color.getGreen(), color.getBlue(), transparency);
    }

    /**
     * @description: TODO [setTransparent] 获取透明色
     * @author: ZFiend
     * @date: 2023/2/5 20:07
     * @param: red
     * @param: green
     * @param: blue
     * @param: transparency
     * @return: java.awt.Color
     */
    public static Color setTransparent(int red, int green, int blue, int transparency) {

        return new Color(red, green, blue, transparency);
    }
}
