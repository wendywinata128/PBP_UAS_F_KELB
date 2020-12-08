package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.User;

public class RegisterPresenter {
    private RegisterView view;
    private RegisterService service;
    private RegisterCallback callback;

    public RegisterPresenter(RegisterView view, RegisterService service) {
        this.view = view;
        this.service = service;
    }

    public void onContinueClicked(){
        if (view.getNama().isEmpty()) {
            view.showNamaError("Nama tidak boleh kosong");
            return;
        } else if (view.getEmail().isEmpty()) {
            view.showEmailError("Email tidak boleh kosong");
            return;
        }else{
            view.continueValidationSuccess();
        }
    }

    public void onRegisterClicked() {
        if(view.getUsername().isEmpty()) {
            view.showUsernameError("Username tidak boleh kosong");
        }else if(view.getUsername().contains(" ")){
            view.showUsernameError("Username tidak boleh spasi");
        }else if (view.getPassword().isEmpty()){
            view.showPasswordError("Password tidak boleh kosong");
        }else if(view.getPassword().length() < 6) {
            view.showPasswordError("Password minimal 6 huruf");
        }else if(view.getMatchPass().isEmpty()){
            view.showMatchPassError("Match Password tidak boleh kosong");
        }else if(view.getMatchPass().length()<6) {
            view.showMatchPassError("Match Password minimal 6 huruf");
        } else if(!view.getMatchPass().equals(view.getPassword())) {
            view.showMatchPassError("Match Password Tidak Sama");
        } else {
            view.startMainActivity();
        }
    }
}
