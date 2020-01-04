package com.dy.fastframework.downloader.internal;

import com.dy.fastframework.downloader.Response;
import com.dy.fastframework.downloader.request.DownloadRequest;

public class SynchronousCall {

    public final DownloadRequest request;

    public SynchronousCall(DownloadRequest request) {
        this.request = request;
    }

    public Response execute() {
        DownloadTask downloadTask = DownloadTask.create(request);
        return downloadTask.run();
    }

}
