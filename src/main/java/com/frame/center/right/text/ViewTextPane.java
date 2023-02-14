package com.frame.center.right.text;

import com.service.FileService;
import com.service.impl.FileServiceImpl;
import com.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class ViewTextPane extends JEditorPane {
    private static ViewTextPane viewTextPane;
    private static FileService fileService = new FileServiceImpl();

    public static ViewTextPane getViewTextPane() {
        if (viewTextPane == null) {
            viewTextPane = new ViewTextPane();
        }
        return viewTextPane;
    }

    private ViewTextPane() {
        //设置背景透明
        this.setBackground(ColorUtil.BLACK_DEEP_4);
        // 设置边框
        this.setBorder(new LineBorder(ColorUtil.BLACK_DEEP_1));
        // 设置不可编辑
        this.setEditable(false);
        // 设置超链接跳转
        this.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    JEditorPane pane = (JEditorPane) e.getSource();
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                        HTMLDocument doc = (HTMLDocument) pane.getDocument();
                        doc.processHTMLFrameHyperlinkEvent(evt);
                    } else {
                        try {
                            pane.setPage(e.getURL());
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public static void addText(String path) {
        if (path.endsWith(".java")) {
            fileService.javaShow(path);
        } else {
            fileService.classShow(path);
        }
    }
}
