package com.example.basisproject.Bookpart_2;

public interface DownloadListener {
    /*回调接口，用来对下载过程状态进行监听和回调*/
    void onProgress(int progress); //通知当前下载进度

    void onSuccess();  //通知下载成功事件

    void onFailed();  //通知下载失败事件

    void onPaused();  //通知下载暂停事件

    void onCanceled();  //通知下载取消事件
}
