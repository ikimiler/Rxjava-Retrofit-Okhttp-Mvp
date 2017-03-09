package com.merchantshengdacar.net.okhttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class ProgressResponseBody extends ResponseBody {

    private ResponseBody mResponseBody;
    private ProgressListener mProgresssListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody,ProgressListener listener) {
        mResponseBody = responseBody;
        mProgresssListener = listener;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (null == bufferedSource){
            bufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                mProgresssListener.onProgress(totalBytesRead, mResponseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}
