package info.jackrex.cuzyandroid.entity.register;

import java.io.Serializable;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyRegister implements Serializable {

    private int code;
    private String des;
    private CuzyRegisterData data;

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

    public CuzyRegisterData getData() {
        return data;
    }

    public void setData(CuzyRegisterData data) {
        this.data = data;
    }
}
