package info.jackrex.cuzysdk.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import info.jackrex.androidcachefoundation.application.MyApplication;
import info.jackrex.cuzyandroid.CuzyNetCenter;
import info.jackrex.cuzyandroid.entity.TBKItem.CuzyTBKEntity;
import info.jackrex.cuzyandroid.entity.TBKItem.CuzyTBKItem;
import info.jackrex.xlistview.XListView;


public class MainActivity extends Activity implements XListView.IXListViewListener {


    private XListView xListView;
    private boolean hasNext;
    private boolean isLoadMore;
    private int nextPage;
    private ProgressDialog dialog;
    private StarAdapter adapter;
    private List<CuzyTBKItem> cuzys = new ArrayList<CuzyTBKItem>();
    private List<CuzyTBKItem> cuzyCopys = new ArrayList<CuzyTBKItem>();

    private String keyword = "女装";
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 0:

                    CuzyTBKEntity entity = (CuzyTBKEntity) msg.obj;

                    if (entity.getCode() == 0) {


                        hasNext = true;

                        if (entity.getData().size()==0){
                            hasNext = false;

                        }

                        if (!isLoadMore && cuzys.size() > 0) {

                            cuzys.clear();
                            cuzyCopys.clear();
                        }

                        cuzys = entity.getData();

                        if (cuzys.size() == 0){
                            if (dialog.isShowing()) {

                                dialog.dismiss();
                            }

                            stopStatus();

                            return;

                        }

                        cuzyCopys.addAll(cuzys);
                        if (hasNext && isLoadMore) {

                            initListView(cuzyCopys);
                        } else {


                            initListView(cuzys);
                        }
                    }else{

                        hasNext = false;

                    }


                    if (dialog.isShowing()) {

                        dialog.dismiss();
                    }

                    stopStatus();

                    break;

            }


        }
    };

    private void stopStatus() {

        xListView.stopLoadMore();
        xListView.stopRefresh();

    }



    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("StarFragment"); //统计页面
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("StarFragment");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init() {
        xListView = (XListView) findViewById(R.id.starlistview);
        //use roboguice
        //  headerView = (HeaderView) currentView.findViewById(R.id.headerviw);
        dialog = new ProgressDialog(this);


        loadData(handler, 1);

        dialog.show();


        xListView.setXListViewListener(this);

        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




            }
        });
    }




    private void initListView(List<CuzyTBKItem> cuzyTBKItems) {


        if (adapter == null) {

            adapter = new StarAdapter(MyApplication.getContext(), cuzyTBKItems);
            xListView.setAdapter(adapter);
        } else {

            adapter.setCuzyTBKItems(cuzyTBKItems);
        }


    }


    private void loadData(final Handler handler, int page) {


        CuzyNetCenter.newInstance(MyApplication.getContext()).fetchRawItems("", keyword, 0, new Response.Listener() {
            @Override
            public void onResponse(Object response) {

                Message message = new Message();
                message.obj = response;
                message.what = 0;
                handler.sendMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    @Override
    public void onRefresh() {

        isLoadMore = false;


        loadData(handler, 0);
    }

    @Override
    public void onLoadMore() {

        isLoadMore = true;
        if (hasNext) {

            loadData(handler, nextPage);

        } else {
        }

    }
}
