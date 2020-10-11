package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Keranjang;
import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;
import java.util.List;


public class CartRepo {
    private MutableLiveData<List<Keranjang>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<Keranjang>> getCart() {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<Keranjang>());
        calculateCartTotal();
    }

    public boolean addItemToCart(Menu menu) {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        List<Keranjang> keranjangList = new ArrayList<>(mutableCart.getValue());
        for (Keranjang keranjang: keranjangList) {
            if (keranjang.getMenu().getId().equals(menu.getId())) {
                if (keranjang.getJumlah() == 5) {
                    return false;
                }

                int index = keranjangList.indexOf(keranjang);
                keranjang.setJumlah(keranjang.getJumlah() + 1);
                keranjangList.set(index, keranjang);

                mutableCart.setValue(keranjangList);
                calculateCartTotal();
                return true;
            }
        }
        Keranjang keranjang = new Keranjang(menu, "",1,"");
        keranjangList.add(keranjang);
        mutableCart.setValue(keranjangList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(Keranjang keranjang) {
        if (mutableCart.getValue() == null) {
            return;
        }
        List<Keranjang> keranjangList = new ArrayList<>(mutableCart.getValue());
        keranjangList.remove(keranjang);
        mutableCart.setValue(keranjangList);
        calculateCartTotal();
    }

    public  void changeQuantity(Keranjang keranjang, int jumlah) {
        if (mutableCart.getValue() == null) return;

        List<Keranjang> keranjangList = new ArrayList<>(mutableCart.getValue());

        Keranjang updatedItem = new Keranjang(keranjang.getMenu(),"",jumlah,"");
        keranjangList.set(keranjangList.indexOf(keranjang), updatedItem);

        mutableCart.setValue(keranjangList);
        calculateCartTotal();
    }

    private void calculateCartTotal() {
        if (mutableCart.getValue() == null) return;
        double total = 0.0;
        List<Keranjang> keranjangList = mutableCart.getValue();
        for (Keranjang keranjang: keranjangList) {
            //total += keranjang.getMenu().getHarga() * keranjang.getJumlah();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double> getTotalHarga() {
        if (mutableTotalPrice.getValue() == null) {
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }
}
