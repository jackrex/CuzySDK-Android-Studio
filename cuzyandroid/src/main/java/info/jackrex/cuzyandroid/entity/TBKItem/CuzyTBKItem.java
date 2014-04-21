package info.jackrex.cuzyandroid.entity.TBKItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyTBKItem implements Serializable {

    private int itemid;
    private String click_url;
    private List<String> pic_urls;
    private String title;
    private String pic_url;
    private String price;
    private String promotion_price;
    private String commission_num;
    private String free_postage;
    private String item_type;


    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(String promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getCommission_num() {
        return commission_num;
    }

    public void setCommission_num(String commission_num) {
        this.commission_num = commission_num;
    }

    public String getFree_postage() {
        return free_postage;
    }

    public void setFree_postage(String free_postage) {
        this.free_postage = free_postage;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }
}
