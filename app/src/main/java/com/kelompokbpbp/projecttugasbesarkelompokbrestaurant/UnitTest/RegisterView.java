package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public interface RegisterView {
    String getNama();

    void showNamaError(String message);

    String getEmail();

    void showEmailError(String message);

    String getUsername();

    void showUsernameError(String message);

    String getPassword();

    void showPasswordError(String message);

    String getMatchPass();

    void showMatchPassError(String message);

    String getPhotoProfile();

    void showPhotoError(String message);

    String getAddress();

    void showAddressError(String message);

    void startMainActivity();

    void startLoginActivity();

    void startRegisterActivity();

    void startUserProfileActivity(User user);

    void showRegisterError(String message);

    void showErrorResponse(String message);

    void continueValidationSuccess();
}
