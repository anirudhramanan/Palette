package io.github.farhadfaghihi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Target;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterColorList.OnColorItemListener {

    private ImageView imageView ;
    private RecyclerView recyclerView ;
    private FloatingActionButton fabGetColors ;
    private Toolbar toolbar ;

    private AdapterColorList adapterColorList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadSampleImageAsync();
    }

    private void initLayout(){

        imageView = (AppCompatImageView)findViewById(R.id.imageView) ;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerviewColors);
        fabGetColors = (FloatingActionButton)findViewById(R.id.fabGetColors);
        toolbar = (Toolbar) findViewById(R.id.toolbar) ;

        fabGetColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showListColors();
            }
        });
    }

    private void loadSampleImageAsync(){

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                imageView.setImageResource(R.drawable.sportcar);
            }
        },250) ;
    }

    private void showListColors(){

        getImageColorData(getSampleBitmap());
    }

    private Bitmap getSampleBitmap(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sportcar);

        return bitmap ;
    }

    private void getImageColorData(Bitmap bitmap){

        Palette.Builder builder = Palette.from(bitmap);

        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                fillListColorsWithData(getListColors(palette));
            }
        }) ;
    }

    private void fillListColorsWithData(ArrayList<ColorData> listItems){

        adapterColorList = new AdapterColorList(listItems, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapterColorList);
    }

    private ArrayList<ColorData> getListColors(Palette palette){

        toolbar.setBackgroundColor(palette.getDominantColor(0));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(palette.getDominantColor(0));
            getWindow().setNavigationBarColor(palette.getDominantColor(0));
        }

        ArrayList<ColorData> listColors = new ArrayList<>() ;

        ColorData itemDominant = new ColorData(palette.getDominantColor(0),
                Color.BLACK,
                "Dominant");

        ColorData itemVibrant = new ColorData(palette.getVibrantColor(0),
                Color.BLACK,
                "Vibrant");

        ColorData itemMuted = new ColorData(palette.getMutedColor(0),
                Color.BLACK,
                "Muted") ;

        ColorData itemVibrantDark = new ColorData(palette.getDarkMutedColor(0),
                Color.BLACK,
                "Dark Vibrant") ;

        ColorData itemVibrantLight = new ColorData(palette.getLightVibrantColor(0),
                Color.BLACK,
                "Light Vibrant") ;

        ColorData itemMutedDark = new ColorData(palette.getDarkMutedColor(0),
                Color.BLACK,
                "Dark Muted") ;

        ColorData itemMutedLight = new ColorData(palette.getLightMutedColor(0),
                Color.BLACK,
                "Light Muted" );

        listColors.add(itemDominant);
        listColors.add(itemVibrant);
        listColors.add(itemMuted);
        listColors.add(itemVibrantDark);
        listColors.add(itemVibrantLight);
        listColors.add(itemMutedDark);
        listColors.add(itemMutedLight);

        return listColors ;
    }

    private ArrayList<ColorData> getAllSwatches(Palette palette){

        ArrayList<ColorData> list = new ArrayList<>() ;

        for(Palette.Swatch swatch : palette.getSwatches()){

            ColorData colorData = new ColorData() ;

            colorData.setTitle(String.valueOf(swatch.getPopulation()));
            colorData.setColor(swatch.getRgb());
            colorData.setTextColor(Color.BLACK);

            list.add(colorData);
        }

        return list ;
    }

    @Override
    public void onColorItemClick(ColorData colorData) {

    }
}
