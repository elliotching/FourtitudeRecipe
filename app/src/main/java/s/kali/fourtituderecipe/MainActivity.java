package s.kali.fourtituderecipe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    
    Context context = this;
    AppCompatActivity activity = this;
    public static final int GALLERY_INTENT = 101;
    public static final int CAMERA_INTENT = 202;
    public static final int PERMISSION = 123;
    public static final int ADD = 100;
    public static final int EDIT = 112;
    public static final int VIEW = 111;
    public static final String RECIPE = "recipe";
    public static final String REQUEST_CODE = "request_code";
    public static final String RECIPE_ID = "recipe_id";
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_EDIT = "edit";
    private static AlertDialog d;
    private static int IDX_ACTION = -1;
    private AdapterList adapter;
    private ListView recipeListView;
    private ArrayList<RecipeItem> recipeArray;
    private Spinner spinner;
    private String[] rcpTypes;
    private SQLiteDBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        // ini spinner array resources
        rcpTypes = context.getResources().getStringArray(R.array.recipe_types);
        
        // init default Recipe List
        dbh = new SQLiteDBHelper(context);
        recipeArray = dbh.getAllRecipe();
        if(recipeArray.size() == 0){
            Log.d("asfuioash32895", ""+recipeArray.size());
            recipeArray = RecipeItem.init(rcpTypes);
            for(int i = 0; i < recipeArray.size() ; i++){
                dbh.insert(recipeArray.get(i));
            }
        }
        else{
            Log.d("asfuioash32895", ""+recipeArray.size());
        }
        
        recipeListView = findViewById(R.id.list_view);
        ListItemListener lil = new ListItemListener();
        recipeListView.setOnItemClickListener(lil);
        recipeListView.setOnItemLongClickListener(lil);
        refreshAndPopulate();
    
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new SpinnerItemListener());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, rcpTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new FabListener());
    }
    
    private void refreshAndPopulate() {
        if(spinner != null) {
            int recipetTypeId = spinner.getSelectedItemPosition();
            if (recipetTypeId == 0) {
                SQLiteDBHelper dbh = new SQLiteDBHelper(context);
                recipeArray = dbh.getAllRecipe();
            } else {
                SQLiteDBHelper dbh = new SQLiteDBHelper(context);
                recipeArray = dbh.getFilteredRecipe(rcpTypes[recipetTypeId]);
            }
        }
    
        adapter = new AdapterList(context, recipeArray);
        recipeListView.setAdapter(adapter);
    }
    
    
    private void deleteRecipeFromDB(int i) {
        int id = recipeArray.get(i).getRecipeId();
        dbh = new SQLiteDBHelper(context);
        dbh.deleteId(id);
    }
    
    private void editRecipe(int i) {
        Intent intent = new Intent(context, EditActivity.class);
        ArrayList<String> l = new ArrayList<String>();
        // ID (only EDIT)
        // TITLE
        // IMG
        // TYPE
        // INGRE
        // STEPS
        l.add(Integer.toString(recipeArray.get(i).getRecipeId()));
        l.add(recipeArray.get(i).getRecipeTitle());
        l.add(recipeArray.get(i).getRecipeImgPath());
        l.add(recipeArray.get(i).getRecipeType());
        l.add(recipeArray.get(i).getRecipeIngre());
        l.add(recipeArray.get(i).getRecipeSteps());
        intent.putExtra(REQUEST_CODE, EDIT);
        intent.putStringArrayListExtra(RECIPE, l);
        startActivityForResult(intent, EDIT);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Uri uri = data.getData();
            if(uri != null){
                String path = uri.getPath();
                Log.d("uri", path);
            }
        }
        
        if(data != null && resultCode == ADD && requestCode == ADD){
            Log.d("eljweiweiothjweio", "" + requestCode + " " + resultCode + " " + data);
            refreshAndPopulate();
        }
        else{
    
            refreshAndPopulate();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            dbh.recreateTable();
            recipeArray = RecipeItem.init(rcpTypes);
            for(int i = 0; i < recipeArray.size() ; i++){
                dbh.insert(recipeArray.get(i));
            }
//            refreshAndPopulate();
            activity.finish();
            Intent i = new Intent(context, MainActivity.class);
            startActivity(i);
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private class SpinnerItemListener implements AdapterView.OnItemSelectedListener{
    
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(i != 0) {
                recipeArray = dbh.getFilteredRecipe(rcpTypes[i]);
                refreshAndPopulate();
            }
            else{
                recipeArray = dbh.getAllRecipe();
                refreshAndPopulate();
            }
//            Log.d("adapterView" , adapterView.toString());
//            Log.d("view" , view.toString());
//            Log.d("i" , ""+i);
//            Log.d("l" , ""+l);
        }
    
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Log.d("onNothing selected" , "nothing selected");
        }
    }
    
    private class ListItemListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(context, ViewActivity.class);
            intent.putExtra(RECIPE_ID, recipeArray.get(i).getRecipeId());
            startActivityForResult(intent, VIEW);
        }
    
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            IDX_ACTION = i;
            d = new AlertDialog.Builder(context).create();
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.dialog_simple_msg_layout, null);
            d.setView(v);
            Button bDelete = v.findViewById(R.id.delete_recipe);
            Button bEdit = v.findViewById(R.id.edit_recipe);
            bDelete.setOnClickListener(new DialogAction(ACTION_DELETE));
            bEdit.setOnClickListener(new DialogAction(ACTION_EDIT));
            d.setTitle("Action");
            d.setCancelable(true);
            d.show();
            return true;
        }
    }
    
    private class DialogAction implements View.OnClickListener{
        private String s;
        DialogAction(String s){
            this.s = s;
        }
        @Override
        public void onClick(View view) {
            if(s.equals(ACTION_DELETE)){
                d.dismiss();
                deleteRecipeFromDB(IDX_ACTION);
                recipeArray.remove(IDX_ACTION);
                refreshAndPopulate();
                IDX_ACTION = -1;
            }
            if(s.equals(ACTION_EDIT)){
                d.dismiss();
                editRecipe(IDX_ACTION);
                IDX_ACTION = -1;
            }
        }
    }
    
    private class FabListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra(REQUEST_CODE, ADD);
            startActivityForResult(intent, ADD);
        }
    }
}
