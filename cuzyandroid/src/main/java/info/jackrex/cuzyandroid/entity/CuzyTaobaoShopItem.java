package info.jackrex.cuzyandroid.entity;

import java.io.Serializable;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyTaobaoShopItem implements Serializable {

    public String shopPid;
    public String shopCategoryID;
    public String shopClickUrl;
    public String shopNickName;
    public String commissionRate;
    public String shopCredit;
    public String shopAuctionCount;
    public String shopTotalAuctionCount;
    public String shopLogoPicture;
    public String shopType;

    public String getShopPid() {
        return shopPid;
    }

    public void setShopPid(String shopPid) {
        this.shopPid = shopPid;
    }

    public String getShopCategoryID() {
        return shopCategoryID;
    }

    public void setShopCategoryID(String shopCategoryID) {
        this.shopCategoryID = shopCategoryID;
    }

    public String getShopClickUrl() {
        return shopClickUrl;
    }

    public void setShopClickUrl(String shopClickUrl) {
        this.shopClickUrl = shopClickUrl;
    }

    public String getShopNickName() {
        return shopNickName;
    }

    public void setShopNickName(String shopNickName) {
        this.shopNickName = shopNickName;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getShopCredit() {
        return shopCredit;
    }

    public void setShopCredit(String shopCredit) {
        this.shopCredit = shopCredit;
    }

    public String getShopAuctionCount() {
        return shopAuctionCount;
    }

    public void setShopAuctionCount(String shopAuctionCount) {
        this.shopAuctionCount = shopAuctionCount;
    }

    public String getShopTotalAuctionCount() {
        return shopTotalAuctionCount;
    }

    public void setShopTotalAuctionCount(String shopTotalAuctionCount) {
        this.shopTotalAuctionCount = shopTotalAuctionCount;
    }

    public String getShopLogoPicture() {
        return shopLogoPicture;
    }

    public void setShopLogoPicture(String shopLogoPicture) {
        this.shopLogoPicture = shopLogoPicture;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }
}
