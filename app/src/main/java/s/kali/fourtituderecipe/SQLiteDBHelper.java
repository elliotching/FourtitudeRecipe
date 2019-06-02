package s.kali.fourtituderecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "test_database";
    public static final String TEST_TABLE_NAME = "test";
    public static final String TEST_COLUMN_ID = "_id";
    public static final String TEST_COLUMN_TITLE = "title";
    public static final String TEST_COLUMN_IMAGE = "image";
    public static final String TEST_COLUMN_TYPE = "type";
    public static final String TEST_COLUMN_INGRE = "ingre";
    public static final String TEST_COLUMN_STEPS = "steps";
    private Context context;
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TEST_TABLE_NAME + " (" +
                TEST_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TEST_COLUMN_TITLE + " TEXT, " +
                TEST_COLUMN_IMAGE + " TEXT, " +
                TEST_COLUMN_TYPE + " TEXT, " +
                TEST_COLUMN_INGRE + " TEXT, " +
                TEST_COLUMN_STEPS + " TEXT" + ")");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TEST_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    
    public long insert(RecipeItem r) {
        SQLiteDatabase database = new SQLiteDBHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.TEST_COLUMN_TITLE, r.getRecipeTitle());
        values.put(SQLiteDBHelper.TEST_COLUMN_IMAGE, r.getRecipeImgPath());
        values.put(SQLiteDBHelper.TEST_COLUMN_TYPE, r.getRecipeType());
        values.put(SQLiteDBHelper.TEST_COLUMN_INGRE, r.getRecipeIngre());
        values.put(SQLiteDBHelper.TEST_COLUMN_STEPS, r.getRecipeSteps());
        long newRowId = database.insert(SQLiteDBHelper.TEST_TABLE_NAME, null, values);
    
        database.close();
        return newRowId;
    }
    
    
    public RecipeItem getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TEST_TABLE_NAME + " where "+TEST_COLUMN_ID+" = "+id+"", null );
//        return res;
        res.moveToFirst();
        if(res.getCount() > 0) {
            int iid = res.getInt(res.getColumnIndex(TEST_COLUMN_ID));
            String stitle = res.getString(res.getColumnIndex(TEST_COLUMN_TITLE));
            String simage = res.getString(res.getColumnIndex(TEST_COLUMN_IMAGE));
            String stype = res.getString(res.getColumnIndex(TEST_COLUMN_TYPE));
            String singre = res.getString(res.getColumnIndex(TEST_COLUMN_INGRE));
            String ssteps = res.getString(res.getColumnIndex(TEST_COLUMN_STEPS));
            RecipeItem r = new RecipeItem(iid, stitle, simage, stype, singre, ssteps);
            return r;
        }
        RecipeItem r = new RecipeItem("" );
        db.close();
        res.close();
        return r;
    }
    
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TEST_TABLE_NAME);
        return numRows;
    }
    
    public void update(RecipeItem r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEST_COLUMN_ID, r.getRecipeId());
        contentValues.put(TEST_COLUMN_TITLE, r.getRecipeTitle());
        contentValues.put(TEST_COLUMN_IMAGE, r.getRecipeImgPath());
        contentValues.put(TEST_COLUMN_TYPE, r.getRecipeType());
        contentValues.put(TEST_COLUMN_INGRE, r.getRecipeIngre());
        contentValues.put(TEST_COLUMN_STEPS, r.getRecipeSteps());
        db.update(TEST_TABLE_NAME, contentValues, TEST_COLUMN_ID+" = ? ", new String[] { Integer.toString(r.getRecipeId()) } );
        db.close();
    }
    
    public void recreateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TEST_TABLE_NAME+"");
        this.onCreate(db);
        db.close();
    }
    
    public Integer deleteId (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer i = db.delete(TEST_TABLE_NAME,
                TEST_COLUMN_ID+" = ? ",
                new String[] { Integer.toString(id) });
        db.close();
        return i;
    }
    
    public ArrayList<RecipeItem> getAllRecipe() {
        ArrayList<RecipeItem> array_list = new ArrayList<RecipeItem>();
        
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TEST_TABLE_NAME, null );
        res.moveToFirst();
        
        while(res.isAfterLast() == false){
            int id = res.getInt(res.getColumnIndex(TEST_COLUMN_ID));
            String stitle = res.getString(res.getColumnIndex(TEST_COLUMN_TITLE));
            String simage = res.getString(res.getColumnIndex(TEST_COLUMN_IMAGE));
            String stype = res.getString(res.getColumnIndex(TEST_COLUMN_TYPE));
            String singre = res.getString(res.getColumnIndex(TEST_COLUMN_INGRE));
            String ssteps = res.getString(res.getColumnIndex(TEST_COLUMN_STEPS));
            RecipeItem r = new RecipeItem(id, stitle, simage, stype, singre, ssteps);
            array_list.add(r);
            res.moveToNext();
        }
        db.close();
        res.close();
        return array_list;
    }
    public ArrayList<RecipeItem> getFilteredRecipe(String key) {
        ArrayList<RecipeItem> array_list = new ArrayList<RecipeItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+TEST_TABLE_NAME+" WHERE "+TEST_COLUMN_TYPE+"='"+key+"'", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            int id = res.getInt(res.getColumnIndex(TEST_COLUMN_ID));
            String stitle = res.getString(res.getColumnIndex(TEST_COLUMN_TITLE));
            String simage = res.getString(res.getColumnIndex(TEST_COLUMN_IMAGE));
            String stype = res.getString(res.getColumnIndex(TEST_COLUMN_TYPE));
            String singre = res.getString(res.getColumnIndex(TEST_COLUMN_INGRE));
            String ssteps = res.getString(res.getColumnIndex(TEST_COLUMN_STEPS));
            RecipeItem r = new RecipeItem(id , stitle, simage, stype, singre, ssteps);
            array_list.add(r);
            res.moveToNext();
        }
        db.close();
        res.close();
        return array_list;
    }
    
}