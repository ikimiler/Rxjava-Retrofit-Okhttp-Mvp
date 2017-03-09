package com.merchantshengdacar.net.okhttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class ProgressRequestBody extends RequestBody {

    private RequestBody mRequestBody;
    private ProgressListener mProgresssListener;
    private BufferedSink bufferedSink;

    public ProgressRequestBody(RequestBody responseBody, ProgressListener progressListener) {
        mRequestBody = responseBody;
        mProgresssListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }


    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(BufferedSink sink) {
        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                mProgresssListener.onProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
