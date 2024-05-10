package com.example.e_commerce_app.presentation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;

import org.json.JSONException;
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
                .placeholder(R.drawable.default_album_art)
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
        try {
            String jsonString = "{\n" +
                    "  \"productImageURL\": \"https://example.com/product-image.jpg\",\n" +
                    "  \"productName\": \"Example Product\",\n" +
                    "  \"productPrice\": \"$19.99\",\n" +
                    "  \"productRating\": 4.5,\n" +
                    "  \"aboutProduct\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\",\n" +
                    "  \"reviews\": [\n" +
                    "    {\n" +
                    "      \"reviewerName\": \"John Doe\",\n" +
                    "      \"reviewRating\": 5,\n" +
                    "      \"reviewText\": \"This product is amazing! I highly recommend it.\",\n" +
                    "      \"reviewDate\": \"2024-05-10\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"reviewerName\": \"Jane Smith\",\n" +
                    "      \"reviewRating\": 4,\n" +
                    "      \"reviewText\": \"Pretty good product overall, but could use some improvements.\",\n" +
                    "      \"reviewDate\": \"2024-05-09\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null; // or handle the error as per your requirement
        }
    }

}
