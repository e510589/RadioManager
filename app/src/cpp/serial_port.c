//
// Created by FC-TS4 on 2020/4/17.
//

#include "serial_port.h"

int setRTS(int fd, int level) {
    int status = 0;

    if (level)
        status |= TIOCM_RTS;
    else
        status &= ~TIOCM_RTS;

    if (ioctl(fd, TIOCMSET, &status) == -1) {
        //LOGE("setRTS(): TIOCMSET");
        return 0;
    }
    return 1;
}

int setCTS(int fd, int level) {
    int status =0 ;
//    if (ioctl(fd, TIOCMGET, &status) == -1) {
//        perror("setRTS(): TIOCMGET");
//        return 0;
//    }
    if (level)
        status |= TIOCM_CTS;
    else
        status &= ~TIOCM_CTS;
    if (ioctl(fd, TIOCMSET, &status) == -1) {
        perror("setRTS(): TIOCMSET");
        return 0;
    }
    return 1;
}
int getRTS(int fd) {
    int status;
    if (ioctl(fd, TIOCMGET, &status) == -1) {
        //LOGE("setRTS(): TIOCMGET");
        return 0;
    }
    int rtn = status & TIOCM_RTS;
    return rtn;
}
int getCTS(int fd) {
    int status;
    if (ioctl(fd, TIOCMGET, &status) == -1) {
        //LOGE("setRTS(): TIOCMGET");
        return 0;
    }
    int rtn = status & TIOCM_CTS;
    return rtn;
}
int serial_port_setting_byte(int fd, int bRate, int databits, int parity, int stopbits, int cts_rts) // configure the port
{
    char Barcode_type[100];
    /*#ifndef E430M
        property_get("Barcode_type", Barcode_type, "");
    #endif*/
    struct termios port_settings;
    bzero(&port_settings, sizeof(port_settings));
    tcflush(fd, TCIOFLUSH);
    int BAUDRATE = _baud_rate(bRate);
    cfsetispeed(&port_settings, BAUDRATE);
    cfsetospeed(&port_settings, BAUDRATE);
    port_settings.c_cflag &= ~CRTSCTS; //disable RTS/CTS flow control.
    port_settings.c_cflag &= ~PARENB; //disable parity generation on output	and parity checking for input.
    port_settings.c_cflag &= ~CSTOPB; //set one stop bit
    port_settings.c_cflag &= ~CSIZE; //clean data bit size
    port_settings.c_cflag |= (BAUDRATE | CLOCAL | CREAD);
    port_settings.c_line = 0;
    port_settings.c_lflag = 0;
    port_settings.c_oflag = 0;
    port_settings.c_iflag = 0;

/*********************************************************
* Set parity
**********************************************************/
    switch (parity) {
        case 0://N
            port_settings.c_iflag &= ~INPCK; //enable parity checking
            port_settings.c_cflag &= ~PARENB; //disable parity generation on output and parity checking for input.
            break;
        case 1: //O
            port_settings.c_iflag |= INPCK; //enable parity checking
            port_settings.c_cflag |= PARENB; //enable parity checking
            port_settings.c_cflag |= PARODD; //odd parity
            break;
        case 2: //E
            port_settings.c_iflag |= INPCK; //enable parity checking
            port_settings.c_cflag |= PARENB; //enable parity checking
            port_settings.c_cflag &= ~PARODD; //even parity
            break;
        default://N
            port_settings.c_iflag &= ~INPCK; //enable parity checking
            port_settings.c_cflag &= ~PARENB; //disable parity generation on output and parity checking for input.
            break;
    }
/*********************************************************
* Set data bits
**********************************************************/
    switch (databits) {
        case 7: //7 bits
            port_settings.c_cflag |= CS7; //7 data bits
            break;
        case 8: //8 bits
            port_settings.c_cflag |= CS8; //8 data bits
            break;
        default://8 bits
            port_settings.c_cflag |= CS8; //8 data bits
            break;
    }
/*********************************************************
* Set stop bits
**********************************************************/
    switch (stopbits) {
        case 1: //1 bits
            port_settings.c_cflag &= ~CSTOPB; //set one stop bit
            break;
        case 2: //2 bits
            port_settings.c_cflag |= CSTOPB; //set two stop bits
            break;
        default://1 bits
            port_settings.c_cflag &= ~CSTOPB; //set one stop bit
            break;
    }
/*********************************************************
* Set RTS/CTS
**********************************************************/
    switch (cts_rts) {
        case 0: //disable
            port_settings.c_cflag &= ~CRTSCTS; //disable RTS/CTS flow control.
            break;
        case 1: //enable
            port_settings.c_cflag |= CRTSCTS; //enable RTS/CTS flow control.
            break;
//        case 2:	// Enable SW flow control
////		port_settings.c_cflag |= IXON|IXOFF|IXANY; //enable RTS/CTS SW flow control.
//            port_settings.c_cflag |= IXON|IXOFF;
//            break;
        default://disable
            port_settings.c_cflag &= ~CRTSCTS; //disable RTS/CTS flow control.
            break;
    }
    tcflush(fd, TCIOFLUSH);
    tcsetattr(fd, TCSANOW, &port_settings); // apply the settings to the serial port
    return fd;
}
int serial_port_setting_byte_attr(int fd, int bRate, int databits, int parity, int stopbits, int cts_rts, int attr) // configure the port
{
    char Barcode_type[100];
    /*#ifndef E430M
        property_get("Barcode_type", Barcode_type, "");
    #endif*/
    struct termios port_settings;
    bzero(&port_settings, sizeof(port_settings));
    tcflush(fd, TCIOFLUSH);
    int BAUDRATE = _baud_rate(bRate);
    cfsetispeed(&port_settings, BAUDRATE);
    cfsetospeed(&port_settings, BAUDRATE);
    port_settings.c_cflag &= ~CRTSCTS; //disable RTS/CTS flow control.
    port_settings.c_cflag &= ~PARENB; //disable parity generation on output	and parity checking for input.
    port_settings.c_cflag &= ~CSTOPB; //set one stop bit
    port_settings.c_cflag &= ~CSIZE; //clean data bit size
    port_settings.c_cflag |= (BAUDRATE | CLOCAL | CREAD);
    port_settings.c_line = 0;
    port_settings.c_lflag = 0;
    port_settings.c_oflag = 0;
    port_settings.c_iflag = 0;

/*********************************************************
* Set parity
**********************************************************/
    switch (parity) {
        case 0://N
            port_settings.c_iflag &= ~INPCK; //enable parity checking
            port_settings.c_cflag &= ~PARENB; //disable parity generation on output and parity checking for input.
            break;
        case 1: //O
            port_settings.c_iflag |= INPCK; //enable parity checking
            port_settings.c_cflag |= PARENB; //enable parity checking
            port_settings.c_cflag |= PARODD; //odd parity
            break;
        case 2: //E
            port_settings.c_iflag |= INPCK; //enable parity checking
            port_settings.c_cflag |= PARENB; //enable parity checking
            port_settings.c_cflag &= ~PARODD; //even parity
            break;
        default://N
            port_settings.c_iflag &= ~INPCK; //enable parity checking
            port_settings.c_cflag &= ~PARENB; //disable parity generation on output and parity checking for input.
            break;
    }
/*********************************************************
* Set data bits
**********************************************************/
    switch (databits) {
        case 7: //7 bits
            port_settings.c_cflag |= CS7; //7 data bits
            break;
        case 8: //8 bits
            port_settings.c_cflag |= CS8; //8 data bits
            break;
        default://8 bits
            port_settings.c_cflag |= CS8; //8 data bits
            break;
    }
/*********************************************************
* Set stop bits
**********************************************************/
    switch (stopbits) {
        case 1: //1 bits
            port_settings.c_cflag &= ~CSTOPB; //set one stop bit
            break;
        case 2: //2 bits
            port_settings.c_cflag |= CSTOPB; //set two stop bits
            break;
        default://1 bits
            port_settings.c_cflag &= ~CSTOPB; //set one stop bit
            break;
    }
/*********************************************************
* Set RTS/CTS
**********************************************************/


    switch (cts_rts) {
        case 0: //disable
            port_settings.c_cflag &= ~CRTSCTS; //disable RTS/CTS flow control.
            break;
        case 1: // Enable HW flow control
            port_settings.c_cflag |= CRTSCTS; //enable RTS/CTS flow control.
            break;
        case 2:	// Enable SW flow control
//		port_settings.c_cflag |= IXON|IXOFF|IXANY; //enable RTS/CTS SW flow control.
            port_settings.c_cflag |= IXON|IXOFF;
            break;
        default://disable
            port_settings.c_cflag &= ~CRTSCTS; //disable RTS/CTS flow control.
            break;
    }
    tcflush(fd, TCIOFLUSH);
//    tcsetattr(fd, TCSANOW, &port_settings); // apply the settings to the serial port
    tcsetattr(fd, attr, &port_settings); // apply the settings to the serial port
    return fd;
}
int serial_port_open(JNIEnv* env, jstring nodePath, int bRate) {
    const char *port;
    port = (*env)->GetStringUTFChars(env, nodePath, NULL); //the port for E430RM4 is "/dev/ttyMT3"
    //Open serial port
    int fd = open(port, O_RDWR | O_NOCTTY | O_NDELAY | O_NONBLOCK);
    //LOGI("JNI --- serial_port_open , port = %s , bRate = %d, fd = %d", port, bRate, fd);
    //When serial port error
    if (fd < 0) {
        (*env)->ReleaseStringUTFChars(env, nodePath, port); // release character pointer
        close(fd);
        //releaseWakeLock();
        return RESULT_OPEN_ERROR;
    }
    //acquireWakeLock();
    //Serial port configuration
    serial_port_setting_byte( fd, bRate, 8, 0, 1, 0);
    (*env)->ReleaseStringUTFChars(env, nodePath, port); // release character pointer
    return fd;
}

int serial_port_write(JNIEnv* env, int fd, jbyteArray data  ) {   //jstring jdata    jbyteArray data
    int dataLength = (*env)->GetArrayLength(env,data);
    jbyte * buffptr = (*env) ->GetByteArrayElements(env,data,NULL);

    int len = write(fd, buffptr, dataLength);   //int len = write(fd, cCmd, strlen(cCmd));    int len = write(fd, buffptr, dataLength);
    //LOGI("JNI --- serial_port_write , fd = %d, cmd = %s len = %d", fd, cCmd, len);
    (*env)->ReleaseByteArrayElements(env, data, buffptr,0);      //(*env)->ReleaseStringUTFChars(env, jdata, cCmd);        (*env)->ReleaseByteArrayElements(env, data, buffptr,0);
    return len;
}

int serial_port_write_ctsrts(JNIEnv* env, int fd, jbyteArray data ) {   //jstring jdata    jbyteArray data
    int dataLength = (*env)->GetArrayLength(env,data);


    jbyte * buffptr = (*env) ->GetByteArrayElements(env,data,NULL);

    int waittime = ((strlen(buffptr)*1000000)/57600)*8;

    setRTS(fd,1);
    usleep(5000);

    int len = write(fd, buffptr, dataLength); //int len = write(fd, cCmd, strlen(cCmd));    int len = write(fd, buffptr, dataLength);
    //LOGI("JNI --- serial_port_write , fd = %d, cmd = %s len = %d", fd, cCmd, len);

    usleep(waittime);
    setRTS(fd , 0);
    (*env)->ReleaseByteArrayElements(env, data, buffptr,0);        //(*env)->ReleaseStringUTFChars(env, jdata, cCmd);        (*env)->ReleaseByteArrayElements(env, data, buffptr,0);
    return len;
}

int serial_port_read(JNIEnv* env,int fd,char *buffer,int size){

    char b1[1024];
    int len  = read(fd,b1, 1024);

    if (len != 0 || len >0){
        memcpy(buffer,b1,len);
    }
    return len;
}

int serial_port_close(JNIEnv* env,int fd) {
    //const char *port;
    //port = (*env)->GetStringUTFChars(env, nodePath, NULL);
    close(fd);
    return 1;
}

//*******************************************************************************
jbyteArray CharAry2JbArray(JNIEnv* env, char* buffer, int length) {

    jbyteArray jbytes = (*env)->NewByteArray(env, strlen(buffer));
    jbyte *jbuffer = buffer; /* UTF8 encoding buffer */
    (*env)->SetByteArrayRegion(env, jbytes, 0, length, jbuffer);
    Free(buffer);
    return jbytes;
}

int _baud_rate(int speed) {
    switch (speed) {
        case 4800:
            return B4800;
        case 9600:
            return B9600;
        case 19200:
            return B19200;
        case 38400:
            return B38400;
        case 57600:
            return B57600;
        case 115200:
            return B115200;
        default:
            return B9600;
    }
}

void Free(char* p) {
    if(p != NULL) free(p);
    p = NULL;
}