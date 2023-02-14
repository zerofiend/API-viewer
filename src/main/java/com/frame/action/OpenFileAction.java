package com.frame.action;

import com.frame.center.CenterPane;
import com.frame.top.menu.file.FilePane;
import com.service.FileService;
import com.service.impl.FileServiceImpl;
import com.util.FIlePathUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @Description TODO OpenFileAction 打开压缩包反馈
 * @Author ZFiend
 * @Create 2023.02.12 15:28
 */
public class OpenFileAction implements ActionListener {
    JFrame frame;   // 操作面板
    FileService fileService;    // 文件操作服务
    String path;    // 压缩包路径
    String folderPath;  // 解压缩文件夹路径
    String name;    // 压缩包名称
    String type;    // 压缩包类型

    public OpenFileAction(JFrame frame) {
        this.frame = frame;
        fileService = new FileServiceImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //显示一个文件选择器
        FilePane filePanel = new FilePane("D:");
        filePanel.showOpenDialog(frame);
        File file = filePanel.getSelectedFile();
        if (file != null) {
            //进行显示
            path = file.getPath();
            name = file.getName().split("\\.")[0];
            type = file.getName().split("\\.")[1];
            if (type.equals(FIlePathUtil.ZIP_SUFFIX)) {
                folderPath = fileService.zipReader(path, name);
                CenterPane.setFileTab(name, folderPath);
            } else if (type.equals(FIlePathUtil.JAR_SUFFIX)) {
                folderPath = fileService.jarReader(path, name);
                CenterPane.setFileTab(name, folderPath);
            } else {
                System.out.println("文件格式错误！");
            }
        }
    }
}
