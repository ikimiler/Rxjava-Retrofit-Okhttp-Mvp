package com.merchantshengdacar.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 项目名称 hejuhui
 * 创建时间 2016/11/29
 * TODO xxxx
 * Author mi
 */

public class PicturesUtils {

    /**
     * 图片压缩
     * @param filePath
     * @return
     */
    public static Bitmap compressBitmap(String filePath,int reqWidth,int reqHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(filePath, opts);
        opts.inJustDecodeBounds = false;
        int w = opts.outWidth;
        int h = opts.outHeight;
        int ratio = 1;

        if (h > reqHeight || w > reqWidth) {
            while (h >= reqHeight && w >= reqWidth) {
                ratio += 1;
                h = opts.outHeight / ratio;
                w = opts.outWidth / ratio;
            }
        }
        opts.inSampleSize = ratio;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, opts);
        return bitmap;
    }

    /**
     * 转换图片
     * @param bit
     * @param outfile
     * @return
     * @throws FileNotFoundException
     */
    public static void compressBitmap(Bitmap bit, File outfile) throws FileNotFoundException {
         bit.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(outfile));
    }
}
