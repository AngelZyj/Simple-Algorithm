package com.tsintergy.simple.algorithm.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * ZipUtil
 * zip压缩和解压
 *
 * @author angel
 * @date 2020/3/9 10:30
 */
public class ZipUtil {

    private static final int BUFFER_SIZE = 2 * 1024;

    public static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 压缩成ZIP 方法
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出路径
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException
     */
    public static void toZip(String srcDir, String out, boolean KeepDirStructure) throws Exception {
        try (FileOutputStream outputStream = new FileOutputStream(out);
             ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
        }

    }

    /**
     * 压缩成ZIP 方法
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtil", e);
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean keepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                if (keepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }

            } else {
                for (File file : listFiles) {
                    if (keepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        compress(file, zos, name + "/" + file.getName(), keepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), keepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * zip解压
     *
     * @param srcFilePath zip文件路径
     * @param destDirPath 解压后的目标文件夹
     * @throws RuntimeException
     */
    public static File unZip(String srcFilePath, String destDirPath) throws RuntimeException {
        return unZip(new File(srcFilePath), destDirPath);
    }

    /**
     * zip解压
     *
     * @param srcFile     zip源文件
     * @param destDirPath 解压后的目标文件夹
     * @throws RuntimeException
     */

    public static File unZip(File srcFile, String destDirPath) throws RuntimeException {
        File unZipFile = null;
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + " not exist");
        }
        try (ZipFile zipFile = new ZipFile(srcFile)) {
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                unZipFile = new File(destDirPath + File.separator + entry.getName().substring(0, entry.getName().indexOf("/")));
                logger.debug("解压{}", entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    //写入
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.close();
                    is.close();
                }
            }
            logger.debug("解压后路径：{}",unZipFile.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtil", e);
        }
        return unZipFile;
    }


}
