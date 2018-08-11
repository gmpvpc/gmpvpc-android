package com.gmpvpc.android.utils;

public interface AppConfig {
    // HUB parameters
    // Gwenole's IP
    // String HUB_IP = "172.17.0.21";

    // Gautier's IPs
    // String HUB_IP = "172.17.0.116";

    // production environment
    String HUB_IP = "192.168.1.254";

    String HUB_QUEUE_NAME = "gmpvpc";
    int HUB_QUEUE_PORT = 5672;

    String HUB_USER = "gmpvpc";
    String HUB_PWD = "gmpvpc";

    // for testing only
    String GLOVE_MAC_ADDR = "cc78ab7e7c84";
//    String GLOVE_MAC_ADDR = "cc78ab7e8484";
}
