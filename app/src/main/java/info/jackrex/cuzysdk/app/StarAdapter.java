package info.jackrex.cuzysdk.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import info.jackrex.cuzyandroid.entity.TBKItem.CuzyTBKItem;

/**
 * Created by Jackrex on 4/19/14.
 */
public class StarAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private List<CuzyTBKItem> cuzyTBKItems;
    private Context context;

    public List<CuzyTBKItem> getCuzyTBKItems() {
        return cuzyTBKItems;
    }

    public void setCuzyTBKItems(List<CuzyTBKItem> cuzyTBKItems) {
        this.cuzyTBKItems = cuzyTBKItems;
        this.notifyDataSetChanged();
    }

    public StarAdapter(Context context, List<CuzyTBKItem> cuzyTBKItems) {
        this.cuzyTBKItems = cuzyTBKItems;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return cuzyTBKItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cuzyTBKItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CuzyTBKItem item = cuzyTBKItems.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_star, null);
            holder = new ViewHolder();
            holder.shopimg = (ImageView) convertView.findViewById(R.id.shopimg);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.num = (TextView) convertView.findViewById(R.id.num);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }


        holder.title.setText(item.getTitle() + "");
        holder.price.setText("价格:￥"+item.getPrice() + "");
        holder.num.setText("购买次数:"+item.getCommission_num()+"");
        ImageLoader.getInstance().displayImage(item.getPic_url(),holder.shopimg);



        return convertView;
    }


    private class ViewHolder {

        private TextView title;
        private TextView num;
        private TextView price;
        private ImageView shopimg;


    }
}