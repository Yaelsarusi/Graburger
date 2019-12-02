package com.example.graburger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

enum BunType {
    REGULAR(0, R.drawable.upper_bun_regular, R.drawable.lower_bun_regular, "regular wheat bun"),
    GLUTEN_FREE(1, R.drawable.upper_bun_gluten_free, R.drawable.lower_bun_gluten_free, "gluten free bun"),
    WHOLE(2, R.drawable.upper_bun_whole, R.drawable.lower_bun_whole, "whole wheat bun");

    private int position;
    private int upperImage;
    private int lowerImage;
    private String title;

    public int getImage(Boolean isUpper) {
        if (isUpper) {
            return this.upperImage;
        } else {
            return this.lowerImage;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public int getPosition() {
        return this.position;
    }

    BunType(int position, int upperBunImage, int lowerBunImage, String title) {
        this.position = position;
        this.upperImage = upperBunImage;
        this.lowerImage = lowerBunImage;
        this.title = title;
    }

    static BunType[] all() {
        return BunType.values();
    }

    static BunType get(int index) {
        return all()[index];
    }

    static List<FoodItemModel> getAsListArray(boolean isUpper) {
        List<FoodItemModel> buns = new ArrayList<>();

        for (BunType c : BunType.all()) {
            buns.add(new FoodItemModel(c.getImage(isUpper), c.getTitle()));
        }
        return buns;
    }

    static int size(){
        return all().length;
    }

}

enum CheeseType {
    REGULAR(0, R.drawable.cheese_regular, "regular cheese"),
    VEGAN_CHEESE(1, R.drawable.cheese_vegan, "vegan cheese"),
    WITHOUT(2, R.drawable.cheese_without, "no cheese");

    private int position;
    private int image;
    private String title;

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPosition() {
        return this.position;
    }

    CheeseType(int position, int image, String title) {
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static CheeseType[] all() {
        return CheeseType.values();
    }

    static CheeseType get(int index) {
        return all()[index];
    }

    static List<FoodItemModel> getAsListArray() {
        List<FoodItemModel> cheeses = new ArrayList<>();

        for (CheeseType c : CheeseType.all()) {
            cheeses.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }
        return cheeses;
    }

    static int size(){
        return all().length;
    }

}

enum PattyType {
    REGULAR(0, R.drawable.patty_regular, "regular patty"),
    VEGAN(1, R.drawable.patty_vegan, "vegan patty"),
    FISH(2, R.drawable.patty_fish, "fish patty");

    private int position;
    private int image;
    private String title;

    public static List<FoodItemModel> getAsListArray() {
        List<FoodItemModel> patty = new ArrayList<>();
        for (PattyType c : PattyType.all()) {
            patty.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }
        return patty;
    }

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPosition() {
        return this.position;
    }

    PattyType(int position, int image, String title) {
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static PattyType[] all() {
        return PattyType.values();
    }

    static PattyType get(int index) {
        return all()[index];
    }

    static int size(){
        return all().length;
    }

}

enum TomatoType {
    WITH(0, R.drawable.tomato_with, "with tomato"),
    WITHOUT(1, R.drawable.tomato_without, "no tomato");

    private int position;
    private int image;
    private String title;

    public static List<FoodItemModel> getAsListArray() {
        List<FoodItemModel> tomato = new ArrayList<>();
        for (TomatoType c : TomatoType.all()) {
            tomato.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }
        return tomato;
    }

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPosition() {
        return this.position;
    }

    TomatoType(int position, int image, String title) {
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static TomatoType[] all() {
        return TomatoType.values();
    }

    static TomatoType get(int index) {
        return all()[index];
    }

    static int size(){
        return all().length;
    }

}

enum LettuceType {
    WITH(0, R.drawable.lettuce_with, "with lettuce"),
    WITHOUT(1, R.drawable.lettuce_without, "no lettuce");

    private int position;
    private int image;
    private String title;

    public static List<FoodItemModel> getAsListArray() {
        List<FoodItemModel> lettuce = new ArrayList<>();
        for (LettuceType c : LettuceType.all()) {
            lettuce.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }
        return lettuce;
    }

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPosition() {
        return this.position;
    }

    LettuceType(int position, int image, String title) {
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static LettuceType[] all() {
        return LettuceType.values();
    }

    static LettuceType get(int index) {
        return all()[index];
    }

    static int size(){
        return all().length;
    }

}

enum SauceType {
    KETCHUP(0, R.drawable.sauce_ketchup, "ketchup"),
    MAYO(1, R.drawable.sauce_mayo, "mayo"),
    VEGAN_MAYO(2, R.drawable.sauce_vegan_mayo, "vegan mayo");

    private int position;
    private int image;
    private String title;

    public static List<FoodItemModel> getAsListArray() {
        List<FoodItemModel> sauce = new ArrayList<>();
        for (SauceType c : SauceType.all()) {
            sauce.add(new FoodItemModel(c.getImage(), c.getTitle()));
        }
        return sauce;
    }

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPosition() {
        return this.position;
    }

    SauceType(int position, int image, String title) {
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static SauceType[] all() {
        return SauceType.values();
    }

    static SauceType get(int index) {
        return all()[index];
    }

    static int size(){
        return all().length;
    }
}

public class BurgerItemModel extends FoodItemModel {

    private static final long serialVersionUID = 1L;
    private BunType bunType;
    private CheeseType cheeseType;
    private PattyType pattyType;
    private TomatoType tomatoType;
    private LettuceType lettuceType;
    private SauceType sauceType;

    public BurgerItemModel(BurgerItemModel burger) {
        super(burger.getImages(), burger.getDesc());
        this.bunType = burger.bunType;
        this.cheeseType = burger.cheeseType;
        this.pattyType = burger.pattyType;
        this.tomatoType = burger.tomatoType;
        this.lettuceType = burger.lettuceType;
        this.sauceType = burger.sauceType;
        updateBurger();
    }

    public BurgerItemModel() {
        super(new int[]{}, "");
        this.bunType = BunType.REGULAR;
        this.cheeseType = CheeseType.REGULAR;
        this.pattyType = PattyType.REGULAR;
        this.tomatoType = TomatoType.WITH;
        this.lettuceType = LettuceType.WITH;
        this.sauceType = SauceType.KETCHUP;
        updateBurger();
    }

    public void updateImage() {
        super.setImage(new int[]{
                bunType.getImage(true),
                bunType.getImage(false),
                cheeseType.getImage(),
                pattyType.getImage(),
                tomatoType.getImage(),
                lettuceType.getImage(),
                sauceType.getImage()});
    }

    public void updateDesc() {
        super.setDesc(" " + bunType.getTitle() + ", " + cheeseType.getTitle() + ", " +
                pattyType.getTitle() + ", " + tomatoType.getTitle() + ", " +
                lettuceType.getTitle() + ", " + sauceType.getTitle());
    }

    public void updateBurger() {
        updateImage();
        updateDesc();
    }

    public void updateBurger(BunType newBun) {
        this.bunType = newBun;
        updateBurger();
    }

    public void updateBurger(CheeseType newCheese) {
        this.cheeseType = newCheese;
        updateBurger();
    }

    public void updateBurger(PattyType newMeat) {
        this.pattyType = newMeat;
        updateBurger();
    }

    public void updateBurger(TomatoType newTomato) {
        this.tomatoType = newTomato;
        updateBurger();
    }

    public void updateBurger(LettuceType newLettuce) {
        this.lettuceType = newLettuce;
        updateBurger();
    }

    public void updateBurger(SauceType newSauce) {
        this.sauceType = newSauce;
        updateBurger();
    }

    public BunType getBun() {
        return this.bunType;
    }

    public CheeseType getCheese() {
        return this.cheeseType;
    }

    public PattyType getPatty() {
        return this.pattyType;
    }

    public TomatoType getTomato() {
        return this.tomatoType;
    }

    public LettuceType getLettuce() {
        return this.lettuceType;
    }

    public SauceType getSauce() {
        return this.sauceType;
    }

}
