package info.jackrex.androidcachefoundation.cachefoundation.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Jackrex on 4/19/14.
 */
public class GsonRequestEntity <T> extends Request<T> {

    private HttpEntity entity;
    private Gson gson = new Gson();
    private Class<T> clazz = null;
    private Map<String, String> headers = null ;
    private  Response.Listener listener = null;
    private String url;
    private Map<String, String> params;

    public GsonRequestEntity(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }


    public  GsonRequestEntity(String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params,
                            Response.Listener<T> listener, Response.ErrorListener errorListener){

        super(Method.POST, url,errorListener);
        this.headers = headers;
        this.params =params;
        this.clazz = clazz;
        this.listener = listener;

    }

    public GsonRequestEntity(int post, String url, Class clazz,  Map<String, String> headers, List<BasicNameValuePair> params, Response.Listener listener, Response.ErrorListener errorListener) throws UnsupportedEncodingException {

        super(post, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.url = url;
        this.entity =  new UrlEncodedFormEntity(params, "UTF-8");

    }





    @Override
    public String getBodyContentType() {

        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            entity.writeTo(outputStream);
        } catch (IOException e) {
            VolleyLog.e("IOException @ " + getClass().getSimpleName());
        }
        return outputStream.toByteArray();
    }



    //default for POST PUT
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params :super.getParams();
    }



    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    /**
     * 处理网络返回
     * @param response
     * @return
     */
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {



        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));


            return Response.success(
                    gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}

