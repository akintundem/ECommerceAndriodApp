package com.example.e_commerce_app.presentation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, aboutProduct;
    private RatingBar productRating;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private List<String> reviewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_layout);

        // Initialize views
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productRating = findViewById(R.id.productRating);
        aboutProduct = findViewById(R.id.aboutProduct);
        reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);

        // Get product details from intent or wherever you have them
        JSONObject product = getProductDetailsFromIntent(); // Implement this method to get product details

        // Populate views with product details
        Glide.with(this)
                .load(product.optString("ProductPicture"))
                .placeholder(R.drawable.default_product_image)
                .into(productImage);

        productName.setText(product.optString("ProductName"));
        productPrice.setText("Price: $" + product.optDouble("ProductPrice"));
        productRating.setRating((float) product.optDouble("ProductRating"));
        aboutProduct.setText(product.optString("AboutProduct"));

        // Initialize reviews RecyclerView
        reviewsList = new ArrayList<>(); // Initialize with empty list, you can populate it with actual reviews
        reviewsAdapter = new ReviewsAdapter(reviewsList);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    // Method to get product details from intent or any other source
    private JSONObject getProductDetailsFromIntent() {
        // Implement this method to retrieve product details from intent or other sources
        // For now, returning a dummy JSONObject
        return new JSONObject();
    }
}
