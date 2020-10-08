package com.kelompokbpbp.projecttugasbesarkelompokbrestaurant.object;

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
        MENU.add(CHICKEN_SOUP);
        MENU.add(ROASTED_CHICKEN);
    }

    public static final Menu FRIED_RICE = new Menu("Fried Rice", "Rp 20.000,-", "Makanan",
            "https://joyfoodsunshine.com/wp-content/uploads/2020/04/vegetable-fried-rice-recipe-1-720x720.jpg");

    public static final Menu FRIED_NOODLES = new Menu("Fried Noodles", "Rp 15.000,-", "Makanan",
            "https://www.aheadofthyme.com/wp-content/uploads/2016/01/shanghai-fried-noodles-in-10-minutes-2.jpg");

    public static final Menu FRIED_CHICKEN = new Menu("Fried Chicken", "Rp 40.000,-", "Makanan",
            "https://gcs.thesouthafrican.com/2020/04/b25c2198-kfc-1200x858.jpeg");

    public static final Menu SPAGHETTI_MEAT_SAUCE = new Menu("Spaghetti Meat Sauce", "Rp 35.000,-", "Makanan",
            "https://images8.alphacoders.com/826/thumb-1920-826205.jpg");

    public static final Menu CHICKEN_SOUP = new Menu("Chicken Soup", "Rp 25.000,-", "Makanan",
            "https://229hkg2lw84tezus91ay33k2-wpengine.netdna-ssl.com/wp-content/uploads/2012/03/homemade-chicken-soup-1302x904.jpg");

    public static final Menu ROASTED_CHICKEN = new Menu("Roasted Chicken", "Rp 50.000,-", "Makanan",
            "https://ehonami.blob.core.windows.net/media/2015/01/kitchen-kelley-roasted-chicken-800x600.jpg");

}