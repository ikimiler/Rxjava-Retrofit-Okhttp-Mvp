package com.merchantshengdacar.net.okhttp;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public interface ProgressListener {

    void onProgress(long progress,long total,boolean done);
}
