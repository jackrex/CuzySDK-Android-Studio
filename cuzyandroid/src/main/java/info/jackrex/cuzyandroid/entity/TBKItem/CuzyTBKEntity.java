package info.jackrex.cuzyandroid.entity.TBKItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyTBKEntity implements Serializable{

    private int code;
    private String des;
    private List<CuzyTBKItem> data;
    private List<CuzyTBKException> expection;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<CuzyTBKItem> getData() {
        return data;
    }

    public void setData(List<CuzyTBKItem> data) {
        this.data = data;
    }

    public List<CuzyTBKException> getExpection() {
        return expection;
    }

    public void setExpection(List<CuzyTBKException> expection) {
        this.expection = expection;
    }
}
