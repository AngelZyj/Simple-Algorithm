package com.tsintergy.simple.algorithm.core.util;


import com.tsintergy.simple.algorithm.core.exception.StoreException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *     https://www.cnblogs.com/yangyudexiaobai/p/4452776.html
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/5/31 09:26
 */
public class Md5ValidUtil {

    /**
     * The default buffer size used why copying bytes.
     */
    public static final int BUFFER_SIZE = 8 * 1024;
    public static final String ALGORITHM = "MD5";

    public static void valid(String filePath, String md5) throws StoreException {
        FileInputStream fStream = null;
        try {
            fStream = new FileInputStream(filePath);

            String realMd5 = digestMd5(fStream);

            if(!realMd5.equalsIgnoreCase(md5)){
                throw new StoreException("文件校验失败" + filePath + ", md5 = " + md5 + " 与实际值" + realMd5 + "不相等" );
            }
        }catch (FileNotFoundException e) {
            throw new StoreException("文件不存在" + filePath, e);
        }catch (IOException e) {
            throw new StoreException("读取文件失败" + filePath, e);
        }catch (StoreException e) {
            throw e;
        }finally{
            if( fStream != null ){
                try {
                    fStream.close();
                } catch (IOException e) {
                }
            }

        }
    }

    /**
     * 得到文件的md5值
     * @param fStream
     * @return
     * @throws IOException
     */
    public static String digestMd5(FileInputStream fStream) throws StoreException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);

            FileChannel fChannel = fStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            for ( int count = fChannel.read( buffer ); count !=-1 ; count = fChannel.read( buffer )
            ) {
                buffer.flip();
                messageDigest.update( buffer );
                if( !buffer.hasRemaining() ){
                    buffer.clear();
                }
            }

            return byteArrayToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new StoreException("不支持的算法" + ALGORITHM, e);
        } catch (IOException e) {
            throw new StoreException("读取文件失败", e);
        }
    }

    //下面这个函数用于将字节数组换成成16进制的字符串

    public static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };

        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];

        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;

        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
