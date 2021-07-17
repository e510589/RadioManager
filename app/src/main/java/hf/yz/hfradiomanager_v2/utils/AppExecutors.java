package hf.yz.hfradiomanager_v2.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.concurrent.Executor;

public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private final Executor diskIO;

    private final Executor mainThread;

    @VisibleForTesting
    AppExecutors(Executor diskIO, Executor mainThread){
        this.diskIO = diskIO;
        this.mainThread = mainThread;
    }

    public AppExecutors(){
        this(new DiskIOThreadExecutor(),new MainThreadExecutor());
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getMainThread(){
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }


}
