package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.daftar;

import com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.model.Menu;

import java.util.ArrayList;

public class DaftarMenu {
    public ArrayList<Menu> MENU;

    public DaftarMenu() {
        MENU = new ArrayList();
        MENU.add(FRIED_RICE);
        MENU.add(FRIED_NOODLES);
        MENU.add(FRIED_CHICKEN);
        MENU.add(SPAGHETTI_MEAT_SAUCE);
        MENU.add(VEGETABLE_SOUP);
        MENU.add(MEATBALL);
    }

    public static final Menu FRIED_RICE = new Menu("Fried Rice", "Rp 20.000,-", "Makanan",
            "https://joyfoodsunshine.com/wp-content/uploads/2020/04/vegetable-fried-rice-recipe-1-720x720.jpg");

    public static final Menu FRIED_NOODLES = new Menu("Fried Noodles", "Rp 15.000,-", "Makanan",
            "https://omnivorescookbook.com/wp-content/uploads/2015/12/1512_15-Minute-Fried-Noodles_003-1.jpg");

    public static final Menu FRIED_CHICKEN = new Menu("Fried Chicken", "Rp 40.000,-", "Makanan",
            "http://food.fnr.sndimg.com/content/dam/images/food/fullset/2012/11/2/0/DV1510H_fried-chicken-recipe-10_s4x3.jpg.rend.hgtvcom.826.620.suffix/1568222255998.jpeg");

    public static final Menu SPAGHETTI_MEAT_SAUCE = new Menu("Spaghetti Meat Sauce", "Rp 35.000,-", "Makanan",
            "https://www.spendwithpennies.com/wp-content/uploads/2019/04/Spaghetti-Meat-Sauce-SpendWithPennies-4.jpg");

    public static final Menu VEGETABLE_SOUP = new Menu("Vegetable Soup", "Rp 15.000,-", "Makanan",
            "https://www.inspiredtaste.net/wp-content/uploads/2018/10/Homemade-Vegetable-Soup-Recipe-2-1200.jpg");

    public static final Menu MEATBALL = new Menu("Meatball", "Rp 25.000,-", "Makanan",
            "https://akcdn.detik.net.id/community/media/visual/2020/03/11/c96a8045-b186-4edd-a961-f7a37fa7475c_169.jpeg?w=700&q=90");

}