package com.example.e_commerce_app.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.implementation.objects.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProduct_name());
        holder.discountedPrice.setText(String.valueOf(product.getDiscounted_price()));
        holder.actualPrice.setText(String.valueOf(product.getActual_price()));
        holder.discountPercentage.setText(String.valueOf(product.getDiscount_percentage()));
        holder.rating.setText(String.valueOf(product.getRating()));
        holder.ratingCount.setText(String.valueOf(product.getRating_count()));
        // Load the image using an image loading library like Glide or Picasso
        Glide.with(context).load(product.getImg_link()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, discountedPrice, actualPrice, discountPercentage, rating, ratingCount;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            discountedPrice = itemView.findViewById(R.id.discounted_price);
            actualPrice = itemView.findViewById(R.id.actual_price);
            discountPercentage = itemView.findViewById(R.id.discount_percentage);
            rating = itemView.findViewById(R.id.rating);
            ratingCount = itemView.findViewById(R.id.rating_count);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}
