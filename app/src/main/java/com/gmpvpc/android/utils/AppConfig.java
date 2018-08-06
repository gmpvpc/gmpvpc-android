package com.gmpvpc.android.utils;

public interface AppConfig {
    // HUB parameters
    String HUB_IP = "172.17.0.21";
    String HUB_QUEUE_NAME = "gmpvpc";
    int HUB_QUEUE_PORT = 5672;

    String HUB_USER = "gmpvpc";
    String HUB_PWD = "gmpvpc";

    // for testing only
    String GLOVE_MAC_ADDR = "cc78ab7e7c84";
//    String GLOVE_MAC_ADDR = "cc78ab7e8484";
}
