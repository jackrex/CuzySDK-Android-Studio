package info.jackrex.cuzyandroid;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.jackrex.androidcachefoundation.application.MyApplication;
import info.jackrex.cuzyandroid.entity.TBKItem.CuzyTBKEntity;
import info.jackrex.cuzyandroid.entity.register.CuzyRegister;

/**
 * Created by Jackrex on 4/18/14.
 */
public class CuzyNetCenter {

    public static final String CUZY_NET_API_REGISTER_APP = "registerApp";
    public static final String CUZY_NET_API_FETCH_ITEMS = "appItem";
    public static final String CUZY_NET_API_FETCH_GROUPBUY_BY_LAT_LON = "SearchGroupBuyingPoi";
    public static final String CUZY_NET_API_FETCH_GROUPBUY_BY_KEYWORD = "SearchGroupBuying";
    public static final String CUZY_NET_API_FETCH_TAOBAOSHOP_BY_KEYWORD = "SearchShopApp";
    public static final String CUZY_NET_API_DOWNLOAD_IMAGE = "CuzyDownloadImage";
    private static final String BASE_RELEASE_URL_TAOBAO = "http://www.cuzy2.com/Api/api/";
    private static final String BASE_RELEASE_URL_JD = "http://www.cuzy2.com/Api/api/";

    String request_url = BASE_RELEASE_URL_TAOBAO + CUZY_NET_API_REGISTER_APP;

    private String credential = "";
    private String appKey = "";
    private String appSecret = "";
    public String countString = 20 + "";

    private static CuzyNetCenter cuzyNetCenter;
    private static Context currentCtx;

    public static CuzyNetCenter newInstance(Context context) {

        if (cuzyNetCenter == null) {

            cuzyNetCenter = new CuzyNetCenter();
        }
        currentCtx = context;
        return cuzyNetCenter;

    }


    public void registerApp(String appKey, String appSecret) {


        this.appKey = appKey;
        this.appSecret = appSecret;
        List list = new ArrayList();
        list.add(new BasicNameValuePair("appkey", appKey));
        list.add(new BasicNameValuePair("appsecret", appSecret));
        list.add(new BasicNameValuePair("deviceid", CuzyUtils.uniqueIdentifier(currentCtx)));
        list.add(new BasicNameValuePair("sdkversion", "3.2"));
        list.add(new BasicNameValuePair("version", "3.2"));

        try {
            MyApplication.volleyHttpClient.postWithParams(request_url, CuzyRegister.class, list, new Response.Listener() {
                @Override
                public void onResponse(Object response) {

                    CuzyRegister register = (CuzyRegister) response;
                    if (register.getCode() == 0) {

                        credential = register.getData().getCredential();

                        Log.e("credential",credential+"");

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(MyApplication.getContext(), "授权发生错误,稍后重试", 0).show();

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    public void fetchRawItems(String themeID, String searchKey, int pageIndex, Response.Listener listener, Response.ErrorListener errorListener) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("appkey", appKey);
        params.put("deviceid", CuzyUtils.uniqueIdentifier(MyApplication.getContext()));
        params.put("credential", credential);
        params.put("page", pageIndex + "");
        params.put("prepage", countString + "");


        if (searchKey.length() > 0)
            params.put("searchkey", searchKey);

        else {
            params.put("theme", themeID);
        }

        MyApplication.volleyHttpClient.postWithParams(BASE_RELEASE_URL_TAOBAO + CUZY_NET_API_FETCH_ITEMS, CuzyTBKEntity.class, params, listener, errorListener);

    }


    public void fetchTBshopItemsWithKeyword(String keywords, int pageIndex, String sortingString, Response.Listener listener, Response.ErrorListener errorListener) throws UnsupportedEncodingException {


        List list = new ArrayList();
        list.add(new BasicNameValuePair("appkey", appKey));
        list.add(new BasicNameValuePair("credential", credential));
        list.add(new BasicNameValuePair("deviceid", CuzyUtils.uniqueIdentifier(MyApplication.getContext())));
        list.add(new BasicNameValuePair("prepage", "20"));
        list.add(new BasicNameValuePair("version", "3.2"));
        list.add(new BasicNameValuePair("keyword", keywords));
        list.add(new BasicNameValuePair("page", pageIndex + ""));
        list.add(new BasicNameValuePair("sort", sortingString));


        MyApplication.volleyHttpClient.postWithParams(BASE_RELEASE_URL_TAOBAO + CUZY_NET_API_FETCH_TAOBAOSHOP_BY_KEYWORD, CuzyTBKEntity.class, list, listener, errorListener);


    }


    public void fetchGroupBuyingItemByKeyword(String keywords, int pageIndex, String cityString, String DistString, String AreaString,Response.Listener listener, Response.ErrorListener errorListener) throws UnsupportedEncodingException {

        List list = new ArrayList();
        list.add(new BasicNameValuePair("appkey", appKey));
        list.add(new BasicNameValuePair("credential", credential));
        list.add(new BasicNameValuePair("deviceid", CuzyUtils.uniqueIdentifier(MyApplication.getContext())));
        list.add(new BasicNameValuePair("prepage", "20"));

        list.add(new BasicNameValuePair("version", "3.2"));

        list.add(new BasicNameValuePair("keyword", keywords));
        list.add(new BasicNameValuePair("page", pageIndex+""));

        list.add(new BasicNameValuePair("city", cityString));
        list.add(new BasicNameValuePair("district", DistString));
        list.add(new BasicNameValuePair("regions", AreaString));


        MyApplication.volleyHttpClient.postWithParams(BASE_RELEASE_URL_TAOBAO + CUZY_NET_API_FETCH_GROUPBUY_BY_KEYWORD, CuzyTBKEntity.class, list, listener, errorListener);



    }



}