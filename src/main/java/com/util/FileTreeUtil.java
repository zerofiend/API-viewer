package com.util;

import com.alibaba.fastjson.JSON;
import com.entity.FileOrFolder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件目录生成json串
 */
public class FileTreeUtil {
    private int sid;

    /**
     * 通过文件夹路径生成树状结构 JSON 文件
     *
     * @param directory 根文件夹路径
     * @param fileName  生成的 JSON 文件名
     */
    public static void getJsonByPath(String directory, String fileName) {
        FileTreeUtil fileTreeUtil = new FileTreeUtil();
        List<FileOrFolder> list = new ArrayList<>();
//
        File file = new File(directory);
        String[] fileList = file.list();

        assert fileList != null;
        for (String name : fileList) {
            FileOrFolder FileOrFolder = new FileOrFolder();
            List<FileOrFolder> childrenList = new ArrayList<>();
            FileOrFolder = fileTreeUtil.getFileOrFolder(directory + "\\" + name, name, 0, FileOrFolder, childrenList);
            list.add(FileOrFolder);
        }
        String json = JSON.toJSONString(list);
        File out = new File(String.valueOf(Paths.get(FIlePathUtil.JSON_PATH,
                fileName + FIlePathUtil.JSON_POINT_SUFFIX)));
        OutputStream outputStream = null;
        try {
            outputStream = Files.newOutputStream(out.toPath());
            outputStream.write(json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("JSON生成成功");
    }

    /**
     * 获取文件或文件夹的对象
     *
     * @param path         路径
     * @param name         名称
     * @param pid          父id
     * @param fileOrFolder 进行操作的 fileOrFolder 对象
     * @param childrenList 子菜单 list
     * @return 返回操作完的对象
     */
    private FileOrFolder getFileOrFolder(String path, String name, int pid, FileOrFolder fileOrFolder,
                                         List<FileOrFolder> childrenList) {
        sid++;
        int id = sid;
        fileOrFolder.setId(id);
        fileOrFolder.setPid(pid);
        fileOrFolder.setName(name);
        fileOrFolder.setLabel(name);
        File file = new File(path);
        if (file.isDirectory()) {
            String[] list1 = file.list();
            assert list1 != null;
            for (String dd : list1) {
                List<FileOrFolder> l = new ArrayList<>();
                FileOrFolder fileOrFolderChild = new FileOrFolder();
                fileOrFolderChild.setId(sid);
                fileOrFolderChild.setName(name);
                fileOrFolderChild.setLabel(name);
                getFileOrFolder(path + "\\" + dd, dd, sid, fileOrFolderChild, l);
                fileOrFolderChild.setPid(id);
                childrenList.add(fileOrFolderChild);
                fileOrFolder.setChildren(childrenList);
            }
        } else {
            fileOrFolder.setPath(path);
        }
        return fileOrFolder;
    }
}
