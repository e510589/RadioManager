package hf.yz.hfradiomanager_v2.main;

import androidx.viewpager.widget.ViewPager;

public class SliderItem {

    // Here you can use String variable to store url
    // If you want to load image from the internet
    private int image;
    private String Name;

    SliderItem(String name ,int image){
        this.image = image;
        this.Name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() { return Name; }
}
