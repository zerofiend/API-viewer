package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.FileOrFolder;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Description TODO FileJSONUtil 生成文件路径JSON文件
 * @Author ZFiend
 * @Create 2023.02.05 19:10
 */
public class FileJSONUtil {
    private int sid;

    /**
     * @description: TODO [getJsonByZip] 通过zip压缩包生成文件结构JSON文件
     * @author: ZFiend
     * @date: 2023/2/14 15:17
     * @param: directory
     * @param: fileName
     * @return: void
     */
    public static void getJsonByJar(String directory, String fileName) {
//获取文件输入流
        FileInputStream input = null;
        ZipInputStream zipInputStream = null;
        try {
            input = new FileInputStream(directory);        //获取ZIP输入流(一定要指定字符集Charset.forName("GBK")否则会报java.lang
            // .IllegalArgumentException: MALFORMED)
            zipInputStream = new ZipInputStream(new BufferedInputStream(input), Charset.forName("GBK"));
            //定义ZipEntry置为null,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
            ZipEntry ze = null;
            //循环遍历
            while ((ze = zipInputStream.getNextEntry()) != null) {
                System.out.println("文件名：" + ze.getName() + " 文件大小：" + ze.getSize() + " bytes");
            }
            //一定记得关闭流
            zipInputStream.closeEntry();
            input.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @description: TODO [getJsonByPath] 通过文件夹路径生成文件结构 JSON 文件
     * @author: ZFiend
     * @date: 2023/2/5 19:14
     * @param: directory
     * @param: fileName
     * @return: void
     */
    public static void getJsonByPath(String directory, String fileName) {
        FileJSONUtil fileTreeUtil = new FileJSONUtil();
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
     * @description: TODO [getFileOrFolder] 获取文件或者文件夹对象
     * @author: ZFiend
     * @date: 2023/2/5 19:15
     * @param: path
     * @param: name
     * @param: pid
     * @param: fileOrFolder
     * @param: childrenList
     * @return: com.entity.FileOrFolder
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

    /**
     * @description: TODO [getJSONList] 打印 JSON 文件
     * @author: ZFiend
     * @date: 2023/2/5 19:15
     * @param: path
     * @return: void
     */
    public static void getJSONList(String path) {
        File file = new File(path);
        String fileToString = null;
        try {
            fileToString = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = JSON.parseObject(fileToString);
        System.out.println(jsonObject);
    }
}
