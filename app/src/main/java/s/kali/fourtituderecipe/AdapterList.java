package s.kali.fourtituderecipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class AdapterList extends BaseAdapter {
    
    private Context context;
    
    //internal use of List RecipeItem;
    private List<RecipeItem> data;
    
    public AdapterList(Context context, List<RecipeItem> data) {
        this.data = data;
        this.context = context;
    }
    
    @Override
    public int getCount() {
        return data.size();
    }
    
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        Log.d("FoodListView", "position = " + position + " is viewed");
        
        ItemHolder holder;
        View view = convertView;
        
        if (view == null) {
            holder = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_view_item_layout, null/* parent */);
            
            holder.textRecipeTitle = (TextView) view.findViewById(R.id.text_title);
            holder.textRecipeIngre = (TextView) view.findViewById(R.id.text_ingre);
            holder.textRecipeType = (TextView) view.findViewById(R.id.text_type);
            holder.imageView = (ImageView) view.findViewById(R.id.imageview_adapter_list_view);
            
            view.setTag(holder);
        } else {
            holder = (ItemHolder) view.getTag();
        }
        
        holder.textRecipeTitle.setText(data.get(position).getRecipeTitle());
        holder.textRecipeIngre.setText(data.get(position).getRecipeIngre());
        holder.textRecipeType.setText(data.get(position).getRecipeType());
//        String imgPath = data.get(position).getRecipeImgPath();
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
//        holder.imageView.setImageBitmap(bitmap);
        String img = data.get(position).getRecipeImgPath();
        int rid = RecipeItem.getRes(img);
        
        holder.imageView.setImageResource(rid);
        
        
        
        return view;
    }
    
    private class ItemHolder {
        TextView textRecipeTitle;
        TextView textRecipeType;
        TextView textRecipeIngre;
        ImageView imageView;
    }
    
    
    private String stringOf(double value) {
        return String.format("%.0f", value);
    }
    
    
}