package mediasmo.mygiftmind.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jan on 28.02.2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    /**
     * Database version
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Database name
     */
    private static final String DATABASE_NAME = "myGiftMind";
    /**
     * Table name
     */
    private static final String TABLE_CONTACTS = "contacts";
    /**
     * Table columns
     */
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    /**
     * constructor
     *
     * @param context Context
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO: implement overriden methods and CRUD Operations
    // http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
