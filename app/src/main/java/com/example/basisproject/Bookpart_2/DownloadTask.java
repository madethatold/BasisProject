package com.example.basisproject.Bookpart_2;

import android.os.AsyncTask;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    /*传入三个参数 第一个是传一个字符串给后台任务，
     * 第二个是表示用整数显示进度，第三个是用整数来反馈结果*/

    /*定义了四种下载状态*/
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    //在DownloadTask的构造函数中传入刚刚定义的DownloadListener参数。
    public DownloadTask(DownloadListener listener) {
        this.listener =listener;
    }

    @Override
    /*在后台执行的下载逻辑*/
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;//记录已下载文件的长度
            String downloadUrl = params[0];  //参数中获得下载的URL地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/")); //根据url解析出下载的文件名
            String directory = Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath(); //指定将文件下载到Download目录下

            /*判断Download中是否有要下载的文件，如果有读取已经下载的字节数
             * 可以在后面启动断点续传功能*/
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadedLength = file.length();
            }
            /*获取待下载文件的长度，如果为0则文件有误 下载失败，如果等于已下载文件长度则说明下载完成*/
            long contentLength = getContentLength(downloadUrl);  //待下载文件的总长度
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                return TYPE_SUCCESS;
            }

            /*发送一条网络请求*/
            OkHttpClient client = new OkHttpClient();
            //断点下载，指定从那个字节开始下载
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")  //断点下载
                    .url(downloadUrl)
                    .build();

            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength);//跳过已经下载的字节
                byte[] b = new byte[1024];
                int total = 0; //本次已下载的总长度
                int len;  //要下载的长度
                /*当还有下载任务时就一直执行，在下载过程中不停判断是否有触发取消和暂停操作*/
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED; //如果下载过程中取消
                    } else if (isPaused) {
                        return TYPE_PAUSED;  //如果下载过程中暂停
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        //计算已经下载的百分比
                        int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                        publishProgress(progress);  //传入下载百分比
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*清空下载过程中产生的数据和文件*/
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;  //如果在之前没有返回下载完成和其他情况，就是下载失败了。
    }

    @Override
    /*在界面上更新下载进度*/
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    /*通知最终的下载结果*/
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    /*得到下载任务总长度方法*/
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }
}
