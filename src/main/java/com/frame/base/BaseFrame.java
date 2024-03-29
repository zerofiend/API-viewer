package com.frame.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BaseFrame extends JFrame {
    private boolean isTopLeft;// 是否处于左上角调整窗口状态
    private boolean isTop;// 是否处于上边界调整窗口状态
    private boolean isTopRight;// 是否处于右上角调整窗口状态
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    private boolean isBottomLeft;// 是否处于左下角调整窗口状态
    private boolean isLeft;// 是否处于左边界调整窗口状态
    private final static int RESIZE_WIDTH = 5;// 判定是否为调整窗口状态的范围与边界距离
    private int min_width = 500;// 窗口最小宽度
    private int min_height = 400;// 窗口最小高度

    public BaseFrame(int min_width, int min_height) {
        this.min_height = min_height;
        this.min_width = min_width;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                Component c = e.getComponent();
                c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent event) {
                Component c = event.getComponent();
                int x = event.getX();
                int y = event.getY();
                int width = c.getWidth();
                int height = c.getHeight();
                int cursorType = Cursor.DEFAULT_CURSOR;// 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
                // 先将所有调整窗口状态重置
                isTopLeft = isTop = isTopRight = isRight = isBottomRight = isBottom = isBottomLeft = isLeft = false;
                if (y <= RESIZE_WIDTH) {
                    if (x <= RESIZE_WIDTH) {// 左上角调整窗口状态
                        isTopLeft = true;
                        cursorType = Cursor.NW_RESIZE_CURSOR;
                    } else if (x >= width - RESIZE_WIDTH) {// 右上角调整窗口状态
                        isTopRight = true;
                        cursorType = Cursor.NE_RESIZE_CURSOR;
                    } else {// 上边界调整窗口状态
                        isTop = true;
                        cursorType = Cursor.N_RESIZE_CURSOR;
                    }
                } else if (y >= height - RESIZE_WIDTH) {
                    if (x <= RESIZE_WIDTH) {// 左下角调整窗口状态
                        isBottomLeft = true;
                        cursorType = Cursor.SW_RESIZE_CURSOR;
                    } else if (x >= width - RESIZE_WIDTH) {// 右下角调整窗口状态
                        isBottomRight = true;
                        cursorType = Cursor.SE_RESIZE_CURSOR;
                    } else {// 下边界调整窗口状态
                        isBottom = true;
                        cursorType = Cursor.S_RESIZE_CURSOR;
                    }
                } else if (x <= RESIZE_WIDTH) {// 左边界调整窗口状态
                    isLeft = true;
                    cursorType = Cursor.W_RESIZE_CURSOR;
                } else if (x >= width - RESIZE_WIDTH) {// 右边界调整窗口状态
                    isRight = true;
                    cursorType = Cursor.E_RESIZE_CURSOR;
                }
                // 最后改变鼠标光标
                c.setCursor(new Cursor(cursorType));
            }

            @Override
            public void mouseDragged(MouseEvent event) {
                Component c = event.getComponent();
                int x = event.getX();
                int y = event.getY();
                int width = c.getWidth();
                int height = c.getHeight();
                // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
                int nextX = c.getX();
                int nextY = c.getY();
                int nextWidth = width;
                int nextHeight = height;
                if (isTopLeft || isLeft || isBottomLeft) {// 所有左边调整窗口状态
                    nextX += x;
                    nextWidth -= x;
                }
                if (isTopLeft || isTop || isTopRight) {// 所有上边调整窗口状态
                    nextY += y;
                    nextHeight -= y;
                }
                if (isTopRight || isRight || isBottomRight) {// 所有右边调整窗口状态
                    nextWidth = x;
                }
                if (isBottomLeft || isBottom || isBottomRight) {// 所有下边调整窗口状态
                    nextHeight = y;
                }
                if (nextWidth <= min_width) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                    nextWidth = min_width;
                    if (isTopLeft || isLeft || isBottomLeft) {// 如果是从左边缩小的窗口，x坐标也要调整
                        nextX = c.getX() + width - nextWidth;
                    }
                }
                if (nextHeight <= min_height) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                    nextHeight = min_height;
                    if (isTopLeft || isTop || isTopRight) {// 如果是从上边缩小的窗口，y坐标也要调整
                        nextY = c.getY() + height - nextHeight;
                    }
                }
                // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
                setBounds(nextX, nextY, nextWidth, nextHeight);
            }
        });
    }

    public void setMin_width(int min_width) {
        this.min_width = min_width;
    }

    public void setMin_height(int min_height) {
        this.min_height = min_height;
    }

    public void showFrame() {
        super.setVisible(true);
    }

    public void setLocation() {
        int windowWidth = this.getWidth(); // 获得窗口宽
        int windowHeight = this.getHeight(); // 获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
    }
}

