package com.example.toutiao.utils.image;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/30 22:11
 * @description:
 */
public class ImageUtil {

    /**
     * 图片保存文件夹地址
     */
    public static final String IMAGE_DIR = "/usr/local/upload";

    /**
     * 网站域名
     */
    public static final String WEB_DOMAIN = "127.0.0.1:8080/";

    /**
     * 需保存的图片后缀
     */
    private static final String[] IMAGE_FILE_EXT = new String[] {"png", "bmp", "jpg", "jpeg"};

    /**
     * 上传文件是否为图片
     * @param file
     * @return
     */
    public static boolean isFileAllowed(MultipartFile file) {
        String fileExt = getFileExt(file);
        if (StringUtils.isBlank(fileExt)) {
            return false;
        }

        for (String ext : IMAGE_FILE_EXT) {
            if (ext.equals(fileExt)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取文件后缀名
     * @param file
     * @return
     */
    public static String getFileExt(MultipartFile file) {
        int doPos = file.getOriginalFilename().lastIndexOf(".");

        if (doPos < 1) {
            return null;
        }

        String fileExt = file.getOriginalFilename().substring(doPos + 1).toLowerCase();

        return fileExt;
    }
}
