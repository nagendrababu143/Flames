package com.example.ekene.flamesng;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

public class DisplayMessageActivity extends AppCompatActivity {

    //CallbackManager callbackManager;
    //ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.flames_logo);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.match_result);
        textView.setTextSize(40);
        textView.setTextColor(Color.rgb(255,20,147));
        textView.setText(message);

        //callbackManager = CallbackManager.Factory.create();
        //shareDialog = new ShareDialog(this);
        //shareDialog = new ShareDialog(this);


    }

    public void capture(View view){
        //capture the root view
        RelativeLayout r = (RelativeLayout) findViewById(R.id.activity_display_message);
        Bitmap image = takeScreenShot(r.getRootView());

        //create facebook share button
        ShareButton shareButton = new ShareButton(this);
        shareButton.setTextSize(20);
        shareButton.setBackgroundColor(Color.rgb(59,89,152));

        //get the layout view and add the button to it
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_display_message);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.share);
        lp.addRule(RelativeLayout.ALIGN_RIGHT, R.id.share);
        rl.addView(shareButton, lp);

        //get the image from screenshot
        SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
        SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();

        //share the image to facebook
        shareButton.setShareContent(content);

        //ImageView img = (ImageView) findViewById(R.id.test_image);
        //img.setImageBitmap(takeScreenShot(r));

    }

    private Bitmap takeScreenShot(View view){

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void play_again(View view){
        //go back to beginning of the game
        finish();
    }
}
