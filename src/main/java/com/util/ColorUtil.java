package com.util;

import java.awt.*;

public class ColorUtil {
    //  通用
    public final static Color SIDE_PANEL_COLOR = new Color(0, 21, 41);
    public final static Color BLACK = new Color(20, 20, 20);
    public final static Color DARK_GRAY = new Color(100, 100, 100);
    public final static Color GRAY = new Color(167, 171, 175);
    public final static Color LIGHT_GRAY = new Color(200, 200, 200);
    public final static Color GHOST_WHITE = new Color(240, 242, 245);
    public final static Color WHITE = new Color(255, 255, 255);

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

//    // 透明色
//    public final static Color transparent = new Color(0, 0, 0, 0);
//    // 毛玻璃
//    public final static Color glass_1 = new Color(255, 255, 255, 30);

    // 获取透明色
    public static Color setTransparent(float num) {
        return new Color(1, 1, 1, num);
    }
}
