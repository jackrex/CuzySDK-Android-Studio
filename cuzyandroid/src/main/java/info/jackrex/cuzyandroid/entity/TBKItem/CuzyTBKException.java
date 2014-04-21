package info.jackrex.cuzyandroid.entity.TBKItem;

import java.io.Serializable;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyTBKException implements Serializable {

    private String des;
    private int code;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
