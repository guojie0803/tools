package com.guojie.tools.imagettool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class tools {

    //加载超大图片
    public static Bitmap decodePhoto(String path, int maxWidth, int maxHeight) {
        // 先获取原始照片的宽高
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int srcWidth  = options.outWidth;
        int srcHeight = options.outHeight;

        // 计算是否需要缩放
        float ratioW = 1F * srcWidth  / maxWidth;
        float ratioH = 1F * srcHeight / maxHeight;
        float ratio  = ratioW > ratioH ? ratioW : ratioH;

        // 尺寸没有超过最大尺寸时，不用缩放
        if (ratio < 1) {
            return BitmapFactory.decodeFile(path);
        }

        // 目标宽度
        int dstWidth = (int) (srcWidth / ratio);

        // 大于4倍时, 先使用 inSampleSize 缩放到目标尺寸的2-4倍
        int inSampleSize = ratio >= 4 ? (int)(ratio / 2) : 1;
        inSampleSize = (int) Math.pow(2, (int)(Math.log(inSampleSize) / Math.log(2)));

        // 再使用 inDensity 精确缩放剩余的部分
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        options.inScaled = true;
        options.inDensity = srcWidth;
        options.inTargetDensity = dstWidth * inSampleSize;
        return BitmapFactory.decodeFile(path, options);
    }
}
