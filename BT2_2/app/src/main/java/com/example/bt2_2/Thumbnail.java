package com.example.bt2_2;

public enum Thumbnail {
    ThumbnailL1("Thumbnail 1", R.drawable.ic_banh_hoi),
    ThumbnailL2("Thumbnail 2", R.drawable.ic_banh_xeo),
    ThumbnailL3("Thumbnail 3", R.drawable.banhcan),
    ThumbnailL4("Thumbnail 4", R.drawable.ic_lau_ca_bop),
    ThumbnailL5("Thumbnail 5", R.drawable.ic_muc);
    private String name;
    private int img;

    Thumbnail(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }
}
