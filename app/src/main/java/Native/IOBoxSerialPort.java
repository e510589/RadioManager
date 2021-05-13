package Native;

public class IOBoxSerialPort {

    public static native int open(String nodePath, int bRate);

    public static native int close(int fd);

    public static native int write(int fd, byte[] data, int ctsrts);

    public static native int read(int i, byte[] buffer2Read, int bufferSize);

    static {
        System.loadLibrary("serial-lib");
    }



}
