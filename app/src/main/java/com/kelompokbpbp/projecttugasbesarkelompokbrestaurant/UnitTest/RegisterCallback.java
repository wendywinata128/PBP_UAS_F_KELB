package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public interface RegisterCallback {
    void onSuccess(boolean value, User user);
    void onError();
}
