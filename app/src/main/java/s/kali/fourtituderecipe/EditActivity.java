package s.kali.fourtituderecipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// for both "edit" and "create" recipe
public class EditActivity extends AppCompatActivity {
    private Context context = this;
    private AppCompatActivity activity = this;
    private Button buttonSave;
    private TextView tTitle;
    private TextView tIngre;
    private TextView tSteps;
    private ImageView image;
    private String imgPath;
    private EditText eTitle;
    private String[] rcpTypes;
    private int spinnerVal = -1;
    private Spinner spType;
    private EditText eIngre;
    private EditText eSteps;
    private int req;
    private int recipeID;
    private RecipeItem recipeItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = activity.getIntent();
        req = i.getIntExtra(MainActivity.REQUEST_CODE, -1);
    
    
        Intent intent = activity.getIntent();
        recipeID = intent.getIntExtra(MainActivity.RECIPE_ID, 0);
        SQLiteDBHelper dbh = new SQLiteDBHelper(context);
        recipeItem = dbh.getRecipe(recipeID);
        
        spType = findViewById(R.id.sp_type);
        rcpTypes = context.getResources().getStringArray(R.array.recipe_types);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, rcpTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(dataAdapter);
//        spType.setOnItemSelectedListener();
        
        buttonSave = findViewById(R.id.bsave);
        eTitle = findViewById(R.id.etitle);
        eIngre = findViewById(R.id.eingre);
        eSteps = findViewById(R.id.esteps);
        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(galleryIntent(), 45);
            }
        });
        
        if(req == MainActivity.EDIT){
            if(recipeItem != null){
                eTitle.setText(recipeItem.getRecipeTitle());
                imgPath = recipeItem.getRecipeImgPath();
                image.setImageResource(RecipeItem.getRes(imgPath));
                spType.setSelection(Arrays.asList(rcpTypes).indexOf(recipeItem.getRecipeType()));
                eIngre.setText(recipeItem.getRecipeIngre());
                eSteps.setText(recipeItem.getRecipeSteps());
            }
        }
        
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stitle = eTitle.getText().toString();
                String stype = spType.getSelectedItem().toString();
                String singre = eIngre.getText().toString();
                String ssteps = eSteps.getText().toString();
                
                if(req == MainActivity.ADD) {
                    SQLiteDBHelper dbh = new SQLiteDBHelper(context);
                    dbh.insert(new RecipeItem(stitle, imgPath, stype, singre, ssteps));
                }else if(req == MainActivity.EDIT){
                    int iid = recipeItem.getRecipeId();
                    SQLiteDBHelper dbh = new SQLiteDBHelper(context);
                    dbh.update(new RecipeItem(iid, stitle, imgPath, stype, singre, ssteps));
                }
                
                Intent i = activity.getIntent();
                if(req == MainActivity.EDIT) {
                    int iid = recipeItem.getRecipeId();
                    i.putExtra(MainActivity.RECIPE_ID, iid);
                }
                activity.setResult(req, i);
                activity.finish();
            }
        });
        
    }
    
    private Intent galleryIntent() {
        CharSequence title = context.getString(com.theartofdev.edmodo.cropper.R.string.pick_image_intent_chooser_title);
        boolean includeDocuments = false;
        PackageManager packageManager = context.getPackageManager();
        // collect all camera intents
        List<Intent> allIntents = new ArrayList<>();
        List<Intent> galleryIntents = CropImage.getGalleryIntents(packageManager, Intent.ACTION_GET_CONTENT, includeDocuments);
        if (galleryIntents.size() == 0) {
            // if no intents found for get-content try pick intent action (Huawei P9).
            galleryIntents = CropImage.getGalleryIntents(packageManager, Intent.ACTION_PICK, includeDocuments);
        }
        allIntents.addAll(galleryIntents);
        Intent target;
        if (allIntents.isEmpty()) {
            target = new Intent();
        } else {
            target = allIntents.get(allIntents.size() - 1);
            allIntents.remove(allIntents.size() - 1);
        }
        // Create a chooser from the main  intent
        Intent chooserIntent = Intent.createChooser(target, title);
        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
        return chooserIntent;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Uri uri = data.getData();
            
//            if(uri != null){
//                String path = uri.getPath();
//                path = path.replace("/raw//", "/");
//                Log.d("uri", path);
//                File imgFile = new File(path);
////                if(imgFile.exists()){
//                Log.d("imgFile.exists", path);
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                image.setImageBitmap(myBitmap);
//                imgPath = path;
////                }
//            }
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            activity.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            activity.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
}
