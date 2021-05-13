//
// Created by FC-TS4 on 2020/4/17.
//

#ifndef HFRADIOMANAGER_V2_SERIAL_PORT_H
#define HFRADIOMANAGER_V2_SERIAL_PORT_H

#include <jni.h>
#include <strings.h>
#include <stdbool.h>
#include <stdio.h>
#include <jni.h>
#include <poll.h>
#include <fcntl.h>
#include <unistd.h>
#include <termios.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <linux/input.h>
#include <android/log.h>
#include <errno.h>
#include <stdlib.h> // Add by Kevin
#include <string.h>  // Add by Kevin

#define RESULT_OPEN_ERROR 10000


int serial_port_open(JNIEnv* env, jstring nodePath, int bRate);
int serial_port_close(JNIEnv* env,int fd);
int serial_port_write(JNIEnv* env, int fd, jbyteArray jdata  );
int serial_port_write_ctsrts(JNIEnv* env, int fd, jbyteArray jdata  );
int serial_port_read(JNIEnv* env,int fd,char *buffer,int size);
int serial_port_setting_byte(int fd, int bRate, int databits, int parity, int stopbits, int cts_rts);
int serial_port_setting_byte_attr(int fd, int bRate, int databits, int parity, int stopbits, int cts_rts, int attr);
int setRTS(int fd, int level);
int setCTS(int fd, int level);
int getRTS(int fd);
int getCTS(int fd);
void Free(char* p);



jbyteArray CharAry2JbArray(JNIEnv* env, char* pResult, int length);

#endif //HFRADIOMANAGER_V2_SERIAL_PORT_H
