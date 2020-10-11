package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.object.DaftarMenu;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.repositories.CartRepo;


import java.util.List;

public class cartViewModel extends ViewModel {

    DaftarMenu daftarMenu = new DaftarMenu();
    CartRepo cartRepo = new CartRepo();

    /*MutableLiveData<Menu> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Menu>> getMenu() {
        return daftarMenu.getMenu();
    }

    public void setProduct(Menu menu) {
        mutableProduct.setValue(menu);
    }

    public LiveData<Menu> getProduct() {
        return mutableProduct;
    }*/

    public LiveData<List<Keranjang>> getCart() {
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Menu menu) {
        return cartRepo.addItemToCart(menu);
    }

    public void removeItemFromCart(Keranjang keranjang) {
        cartRepo.removeItemFromCart(keranjang);
    }

    public void changeQuantity(Keranjang keranjang, int jumlah) {
        cartRepo.changeQuantity(keranjang, jumlah);
    }

    public LiveData<Double> getTotalHarga() {
        return cartRepo.getTotalHarga();
    }

    public void resetCart() {
        cartRepo.initCart();
    }
}
