package demo.concurrent.first;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Downloader extends Thread {
    private InputStream in;
    private OutputStream out;
    private ArrayList<ProgressListener> listeners;

    public Downloader(URL url, String outputFilename) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFilename);
        listeners = new ArrayList<>();
    }

    public synchronized void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private  void updateProgress(int n) {
        ArrayList<ProgressListener> listenersCopy;
        synchronized (this) {
            listenersCopy = (ArrayList<ProgressListener>) listeners.clone();
        }

        for (ProgressListener listener : listenersCopy) {
            listener.onProgress(n);
        }
    }

    @Override
    public void run() {
        // ...
    }

    interface ProgressListener {
        void onProgress(int n);
    }
}


