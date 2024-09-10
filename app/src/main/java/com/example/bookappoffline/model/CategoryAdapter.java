package com.example.bookappoffline.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.example.bookappoffline.BookItemActivity;
import com.example.bookappoffline.R;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    List<CategoryItem> categoryItemList;
    Context context;

    public CategoryAdapter(List<CategoryItem> categoryItemList, Context context) {
        this.categoryItemList = categoryItemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoryItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        }

        ImageView cat_img = convertView.findViewById(R.id.cat_img);
        TextView cat_title = convertView.findViewById(R.id.cat_title);
        TextView cat_subTitle = convertView.findViewById(R.id.cat_subtitle);
        CardView catClick = convertView.findViewById(R.id.catClick);

        CategoryItem categoryItem = categoryItemList.get(position);

        cat_img.setImageResource(categoryItem.getCat_img());
        cat_title.setText(categoryItem.getCat_title());
        cat_subTitle.setText(categoryItem.getCat_subTitle());

        catClick.setOnClickListener(v -> {

            //Data Loading Function
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            Intent intent = new Intent(v.getContext(), BookItemActivity.class);
            intent.putExtra("title", categoryItem.getCat_title());
            v.getContext().startActivity(intent);

            v.postDelayed(progressDialog::dismiss, 1000);
        });

        return convertView;
    }






}
