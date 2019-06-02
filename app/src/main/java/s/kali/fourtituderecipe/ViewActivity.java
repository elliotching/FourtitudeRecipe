package s.kali.fourtituderecipe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    
    private Context context = this;
    private AppCompatActivity activity = this;
    private int recipeID = -1;
    private TextView textTitle;
    private TextView textType;
    private TextView textIngre;
    private TextView textSteps;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    
        Intent intent = activity.getIntent();
        recipeID = intent.getIntExtra(MainActivity.RECIPE_ID, 0);
        SQLiteDBHelper dbh = new SQLiteDBHelper(context);
        RecipeItem recipeItem = dbh.getRecipe(recipeID);
        
        // displaying items...
        textTitle = findViewById(R.id.text_title);
        textType = findViewById(R.id.text_type);
        textIngre = findViewById(R.id.text_ingre);
        textSteps = findViewById(R.id.text_steps);
        imageView = findViewById(R.id.image);
        
        display(recipeItem);
    }
    private void editRecipe(int i) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(MainActivity.RECIPE_ID, i);
        intent.putExtra(MainActivity.REQUEST_CODE, MainActivity.EDIT);
        startActivityForResult(intent, MainActivity.EDIT);
    }
    private void deleteRecipeFromDB(int i) {
        SQLiteDBHelper dbh = new SQLiteDBHelper(context);
        dbh.deleteId(i);
    }
    private void refreshFromDB(int i) {
        SQLiteDBHelper dbh = new SQLiteDBHelper(context);
        RecipeItem recipeItem = dbh.getRecipe(i);
        if(recipeItem != null){
            display(recipeItem);
        }
    }
    
    private void display(RecipeItem r) {
        if(textTitle != null && textIngre != null && textSteps != null && textType != null){
            textTitle.setText(r.getRecipeTitle());
            textType.setText("Type: "+r.getRecipeType());
            textIngre.setText(r.getRecipeIngre());
            textSteps.setText(r.getRecipeSteps());
            imageView.setImageResource(RecipeItem.getRes(r.getRecipeImgPath()));
//            if(r.getRecipeImgPath().length() < 5){
//                imageView.setImageResource(RecipeItem.getRes(r.getRecipeImgPath()));
//            }
//            else{
//                imageView.setImageBitmap(RecipeItem.getBitmap(r.getRecipeImgPath()));
//            }
            
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MainActivity.EDIT && data != null){
            int index = data.getIntExtra(MainActivity.RECIPE_ID, 0);
            refreshFromDB(index);
        }
    }
    
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        
        // EDIT
        menu.findItem(R.id.action_edit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                editRecipe(recipeID);
                return false;
            }
        });
        
        // DELETE
        menu.findItem(R.id.action_delete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AlertDialog.Builder d = new AlertDialog.Builder(context);
                d.setMessage("Are you sure you want to delete this recipe?");
                d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteRecipeFromDB(recipeID);
                        activity.finish();
                    }
                });
                d.setNegativeButton("No", null);
                d.setTitle("Confirm Delete");
                d.setCancelable(true);
                d.show();
                return true;
            }
        });
        return true;
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_BACK &&
                event.getRepeatCount() == 0) {
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
