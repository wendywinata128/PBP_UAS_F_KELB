package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterTest {

    @Mock
    private RegisterView view;

    @Mock
    private RegisterService service;

    private RegisterPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new RegisterPresenter(view,service);
    }

    @Test
    public void isNameEmpty() throws Exception{
        when(view.getNama()).thenReturn("");
        System.out.println("nama : "+view.getNama());

        presenter.onContinueClicked();

        verify(view).showNamaError("Nama tidak boleh kosong");
    }

    @Test
    public void isEmailEmpty() throws Exception{
        when(view.getNama()).thenReturn("wennywenny");
        System.out.println("nama : "+view.getNama());
        when(view.getEmail()).thenReturn("");
        System.out.println("email : "+view.getEmail());
        presenter.onContinueClicked();

        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void isContinueSuccess() throws Exception{
        when(view.getNama()).thenReturn("wennywenny");
        System.out.println("nama : "+view.getNama());
        when(view.getEmail()).thenReturn("fransiscawenny1004@gmail.com");
        System.out.println("email : "+view.getEmail());
        presenter.onContinueClicked();

        when(service.getValid(view, view.getNama(), view.getEmail(), view.getPassword(), view.getUsername(), view.getPhotoProfile(),
                view.getAddress())).thenReturn(true);

        presenter.onContinueClicked();
    }

    @Test
    public void isUserNameEmpty() throws Exception{
        when(view.getUsername()).thenReturn("");

        presenter.onRegisterClicked();

        verify(view).showUsernameError("Username tidak boleh kosong");
    }

    @Test
    public void isUserNameSpace() throws Exception{
        when(view.getUsername()).thenReturn("wenny wen");

        presenter.onRegisterClicked();

        verify(view).showUsernameError("Username tidak boleh spasi");
    }

    @Test
    public void isPasswordEmpty() throws Exception{
        when(view.getUsername()).thenReturn("wennywen");

        when(view.getPassword()).thenReturn("");

        presenter.onRegisterClicked();

        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    //blm buat untuk gitung panjang password
    @Test
    public void isPasswordLess() throws Exception{
        when(view.getUsername()).thenReturn("wennywen");

        when(view.getPassword()).thenReturn("wen");

        presenter.onRegisterClicked();

        verify(view).showPasswordError("Password minimal 6 huruf");
    }

    @Test
    public void isMatchPasswordEmpty() throws Exception{
        when(view.getUsername()).thenReturn("wennywen");

        when(view.getPassword()).thenReturn("cobacoba");

        when(view.getMatchPass()).thenReturn("");

        presenter.onRegisterClicked();

        verify(view).showMatchPassError("Match Password tidak boleh kosong");
    }
    //blm buat yg nyamain password
    @Test
    public void isMatchPasswordNotSame() throws Exception{
        when(view.getUsername()).thenReturn("wennywen");

        when(view.getPassword()).thenReturn("cobacoba");

        when(view.getMatchPass()).thenReturn("cobacob");

        presenter.onRegisterClicked();

        verify(view).showMatchPassError("Match Password Tidak Sama");
    }

    @Test
    public void isUserRegisSuccess() throws Exception{
        when(view.getUsername()).thenReturn("wennywen");

        when(view.getPassword()).thenReturn("cobacoba");

        when(view.getMatchPass()).thenReturn("cobacoba");

        presenter.onRegisterClicked();

        when(service.getValid(view, view.getNama(), view.getEmail(), view.getPassword(), view.getUsername(), view.getPhotoProfile(),
                view.getAddress())).thenReturn(true);

        presenter.onRegisterClicked();
    }
}