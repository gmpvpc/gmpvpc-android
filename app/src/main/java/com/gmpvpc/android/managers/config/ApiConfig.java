package com.gmpvpc.android.managers.config;

import static com.gmpvpc.android.utils.AppConfig.HUB_IP;

/**
 * Created by malah on 27/03/18.
 */

public interface ApiConfig {

    String API = "http://" + HUB_IP + ":8080/api";
    String BY_ID = "/%s";

    String SERIES = API + "/series";
    String GLOVE = API + "/glove";
    String TRAINING_API = API + "/training";
    String CURRENT_TRAINING_API = TRAINING_API + "/current";

    String GLOVE_BY_ID = GLOVE + BY_ID;
    String GLOVE_CALIBRATION = GLOVE_BY_ID + "/calibration";

}
