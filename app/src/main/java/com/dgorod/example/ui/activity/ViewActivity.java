package com.dgorod.example.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgorod.example.R;
import com.dgorod.example.api.PhotoSizes;
import com.dgorod.example.api.model.PhotoModel;
import com.dgorod.example.ui.ExtrasKeys;
import com.dgorod.example.util.Preconditions;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public class ViewActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.share)
    FloatingActionButton share;
    @Bind(R.id.photo)
    ImageView photo;
    @Bind(R.id.author)
    TextView author;
    @Bind(R.id.camera)
    TextView camera;

    private PhotoModel photoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photoData = Parcels.unwrap(getIntent().getParcelableExtra(ExtrasKeys.PHOTO));

        setContentView(R.layout.activity_view);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(photoData.getName());

        share.setOnClickListener(shareClickListener);

        author.setText(photoData.getUser() == null ? "n/a" : photoData.getUser().getFullname());
        camera.setText(photoData.getCameraInfo());

        Picasso.with(this).load(photoData.getImageWithSize(PhotoSizes.BIG)).into(photo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(0, R.anim.slide_to_bottom);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Use this method in {@link android.app.Activity#startActivity(Intent)}
     * to start this activity properly.
     * @param context
     * @param photo
     * @return prepared intent.
     */
    @NonNull
    public static Intent start(@NonNull Context context, @NonNull PhotoModel photo) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(photo);

        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra(ExtrasKeys.PHOTO, Parcels.wrap(photo));
        return intent;
    }

    // --- action listeners

    private View.OnClickListener shareClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (photoData == null) {
                return;
            }
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Photo URL");
            intent.putExtra(Intent.EXTRA_TEXT, photoData.getImageWithSize(PhotoSizes.BIG));
            startActivity(Intent.createChooser(intent, "Share this photo"));
        }
    };
}
