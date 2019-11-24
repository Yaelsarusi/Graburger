package com.example.graburger;

import java.io.Serializable;

enum BunType {
    REGULAR(0, R.drawable.upper_bun_regular, R.drawable.lower_bun_regular,"regular wheat bun"),
    GLUTEN_FREE(1, R.drawable.upper_bun_gluten_free, R.drawable.lower_bun_gluten_free,"gluten free bun"),
    WHOLE(2, R.drawable.upper_bun_whole, R.drawable.lower_bun_whole, "whole wheat bun");

    private int position;
    private int upperImage;
    private int lowerImage;
    private String title;

    public int getImage(Boolean isUpper ) {
        if (isUpper) { return this.upperImage; }
        else { return this.lowerImage; }
    }

    public String getTitle(){ return this.title; }

    public int getPosition(){ return this.position; }

    BunType(int position, int upperBunImage, int lowerBunImage, String title){
        this.position = position;
        this.upperImage = upperBunImage;
        this.lowerImage = lowerBunImage;
        this.title = title;
    }

    static BunType[] all(){ return BunType.values();}

    static BunType get(int i){return all()[i];}

}

enum CheeseType {
    REGULAR(0, R.drawable.cheese_regular,"regular cheese"),
    VEGAN_CHEESE(1, R.drawable.cheese_vegan,"vegan cheese"),
    WITHOUT(2, R.drawable.cheese_without,"no cheese");

    private int position;
    private int image;
    private String title;

    public int getImage(){ return this.image; }

    public String getTitle(){ return this.title; }

    public int getPosition(){ return this.position; }

    CheeseType(int position, int image, String title){
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static CheeseType[] all(){ return CheeseType.values();}

    static CheeseType get(int i){return all()[i];}
}

enum PattyType {
    REGULAR(0, R.drawable.patty_regular,"regular patty"),
    VEGAN(1, R.drawable.patty_vegan,"vegan patty"),
    FISH(2, R.drawable.patty_fish,"fish patty");

    private int position;
    private int image;
    private String title;

    public int getImage(){ return this.image; }

    public String getTitle(){ return this.title; }

    public int getPosition(){ return this.position; }

    PattyType(int position, int image, String title){
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static PattyType[] all(){ return PattyType.values();}

    static PattyType get(int i){return all()[i];}
}

enum TomatoType {
    WITH(0, R.drawable.tomato_with,"with tomato"),
    WITHOUT(1, R.drawable.tomato_without,"no tomato");

    private int position;
    private int image;
    private String title;

    public int getImage(){ return this.image; }

    public String getTitle(){ return this.title; }

    public int getPosition(){ return this.position; }

    TomatoType(int position, int image, String title){
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static TomatoType[] all(){ return TomatoType.values();}

    static TomatoType get(int i){return all()[i];}
}

enum LettuceType {
    WITH(0, R.drawable.lettuce_with,"with lettuce"),
    WITHOUT(1, R.drawable.lettuce_without,"no lettuce");

    private int position;
    private int image;
    private String title;

    public int getImage(){ return this.image; }

    public String getTitle(){ return this.title; }

    public int getPosition(){ return this.position; }

    LettuceType(int position, int image, String title){
        this.position = position;
        this.image = image;
        this.title = title;
    }

    static LettuceType[] all(){ return LettuceType.values();}

    static LettuceType get(int i){return all()[i];}
}

enum SauceType {
    KETCHUP(0, R.drawable.sauce_ketchup,"ketchup"),
    MAYO(1, R.drawable.sauce_mayo,"mayo"),
    VEGAN_MAYO(2, R.drawable.sauce_vegan_mayo,"vegan mayo");

    private int position;
    private int image;
    private String title;

    public int getImage(){ return this.image; }

    public String getTitle(){ return this.title; }

    public int getPosition(){ return this.position; }

    SauceType(int position, int image, String title){
        this.position = position;
        this.image = image;
        this.title = title;
    }
    static SauceType[] all(){ return  SauceType.values();}

    static SauceType get(int i){return all()[i];}
}

public class BurgerItemModel extends FoodItemModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private BunType bunType;
    private CheeseType cheeseType;
    private PattyType pattyType;
    private TomatoType tomatoType;
    private LettuceType lettuceType;
    private SauceType sauceType;

    public BurgerItemModel(BurgerItemModel burger){
        super(burger.getImages(), burger.getDesc());
        this.bunType = burger.bunType;
        this.cheeseType = burger.cheeseType;
        this.pattyType = burger.pattyType;
        this.tomatoType = burger.tomatoType;
        this.lettuceType = burger.lettuceType;
        this.sauceType = burger.sauceType;
        updateBurger();
    }

    public BurgerItemModel(){
        super(new int[] {}, "");
        this.bunType = BunType.REGULAR;
        this.cheeseType = CheeseType.REGULAR;
        this.pattyType = PattyType.REGULAR;
        this.tomatoType = TomatoType.WITH;
        this.lettuceType = LettuceType.WITH;
        this.sauceType = SauceType.KETCHUP;
        updateBurger();
    }

    public void updateImage(){
        super.setImage( new int[] {
                bunType.getImage(true),
                bunType.getImage(false),
                cheeseType.getImage(),
                pattyType.getImage(),
                tomatoType.getImage(),
                lettuceType.getImage(),
                sauceType.getImage()});
    }

    public void updateDesc(){
        super.setDesc(" " + bunType.getTitle() + ", " + cheeseType.getTitle() + ", " +
                pattyType.getTitle() + ", " + tomatoType.getTitle() + ", " +
                lettuceType.getTitle() + ", " + sauceType.getTitle());
    }

    private void updateBurger(){
        updateImage();
        updateDesc();
    }

    public void updateBurger(BunType newBun){ this.bunType = newBun; updateBurger(); }

    public void updateBurger(CheeseType newCheese){ this.cheeseType = newCheese; updateBurger(); }

    public void updateBurger(PattyType newMeat){ this.pattyType = newMeat; updateBurger(); }

    public void updateBurger(TomatoType newTomato){ this.tomatoType = newTomato; updateBurger(); }

    public void updateBurger(LettuceType newLettuce){ this.lettuceType = newLettuce; updateBurger(); }

    public void updateBurger(SauceType newSauce){ this.sauceType = newSauce; updateBurger(); }

    public BunType getBun(){ return this.bunType;}
    public CheeseType getCheese(){ return this.cheeseType;}
    public PattyType getPatty(){ return this.pattyType;}
    public TomatoType getTomato(){ return this.tomatoType;}
    public LettuceType getLettuce(){ return this.lettuceType;}
    public SauceType getSauce(){ return this.sauceType;}

}
