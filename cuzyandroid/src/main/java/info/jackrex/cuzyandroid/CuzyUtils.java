package info.jackrex.cuzyandroid;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyUtils {

    public static String uniqueIdentifier(Context context)
    {

        String device = "";

        try
        {
            TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");

            String tmDevice = tm.getDeviceId();
            device = tmDevice;
            String tmSerial = tm.getSimSerialNumber();
            String androidId =
                    Settings.Secure.getString(
                            context.getContentResolver(),
                            "android_id");
            UUID deviceUuid = new UUID(androidId.hashCode(),
                    tmDevice.hashCode() << 32 | tmSerial.hashCode());
            return deviceUuid.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }
}
