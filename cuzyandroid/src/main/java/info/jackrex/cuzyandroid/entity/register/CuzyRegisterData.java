package info.jackrex.cuzyandroid.entity.register;

import java.io.Serializable;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyRegisterData implements Serializable {

    private String credential;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
