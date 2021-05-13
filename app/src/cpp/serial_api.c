//
// Created by FC-TS4 on 2020/4/17.
//

#include "serial_port.h"

JNIEXPORT int JNICALL
Java_Native_IOBoxSerialPort_open(JNIEnv* env, jclass thiz, jstring nodePath, jint bRate){
    int fd = serial_port_open( env, nodePath, bRate);
    if (fd == RESULT_OPEN_ERROR) {
        return -1;
    }


    return fd;
}

JNIEXPORT int JNICALL
Java_Native_IOBoxSerialPort_close(JNIEnv* env, jclass thiz, jint fd){

    int rtn = serial_port_close( env, fd);
    return rtn;

}

JNIEXPORT int JNICALL
Java_Native_IOBoxSerialPort_write(JNIEnv* env, jclass thiz, jint fd, jbyteArray data, jint ctsrts) {

    if (fd == RESULT_OPEN_ERROR ) {
        return fd;
    }
    int len =0;
    // Write data
    if(ctsrts){
        len = serial_port_write_ctsrts(env, fd, data)        ;
    } else{
        len = serial_port_write(env, fd, data);
    }

    int t = tcdrain(fd);
    // If cannot write, return write error string
    if(len < 0){
        return len;
    }
    return len;
}

JNIEXPORT jint JNICALL
Java_Native_IOBoxSerialPort_read(JNIEnv* env, jclass thiz, jint fd, jbyteArray buffer,jint bufferSize){

    jbyte *jByteArray = (*env) ->GetByteArrayElements(env,buffer,0);

    char byte2Read[1024];
    memset(byte2Read,0, sizeof(byte2Read));
    int len = serial_port_read(env,fd,byte2Read,1024);
    for(int i =0 ; i<len ;i++){
        jByteArray[i] = byte2Read[i];
    }

    (*env)->ReleaseByteArrayElements(env, buffer, jByteArray, 0);

    return len;
}
