package grit.android.teslalist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        // assign a image view
        ImageView img = (ImageView) findViewById(R.id.imageView);

        // assign a media player for easter egg
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.racecar2);

        //Returns the intent that started this activity
        final Intent in = getIntent();

        // Returns an ItemIndex from our choice from list
        final int index = in.getIntExtra("ITEM_INDEX", -1);

        //displays correct image from pur choice from list
        if (index > -1) {
            int pic = getImg(index);
            scaleImg(img, pic);
        }

        // assign button and se onclick listner
        Button myBtn = (Button)findViewById(R.id.myBtn);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // opens new webwindow for order
                Intent order = new Intent(Intent.ACTION_VIEW);
                order.setData(Uri.parse(getURL(index)));
                startActivity(order);

            }
        });

        //Easter egg when you click in image
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
    }

    // returns correct image from your choice on  list
    private int  getImg(int index){
        switch (index){
            case 0: return R.drawable.models;
            case 1: return R.drawable.model3;
            case 2: return R.drawable.modelx;
            case 3: return R.drawable.modely;
            case 4: return R.drawable.cybertruck;
            case 5: return R.drawable.roadster;
            default: return -1;
        }
    }

    // returns link to order from your choice from  list
    private String getURL(int index){
        switch (index){
            case 0: return "https://www.tesla.com/sv_se/models/design#battery";
            case 1: return "https://www.tesla.com/sv_se/model3/design#battery";
            case 2: return "https://www.tesla.com/sv_se/modelx/design#battery";
            case 3: return "https://www.tesla.com/sv_se/modely/design#battery";
            case 4: return "https://www.tesla.com/sv_se/cybertruck/design#battery";
            case 5: return "https://www.tesla.com/sv_se/roadster/reserve#payment";
            default: return null;
        }
    }

    // Scales image to fit screen and put correct image in imageview
    private void scaleImg(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth){
            int ratio = Math.round( (float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;

        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }
}
