package mediasmo.mygiftmind.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import mediasmo.mygiftmind.dao.Contact;

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

    /**
     * onCreate
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + "INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    /**
     * onUpgrade
     *
     * @param db SQLiteDatabase
     * @param oldVersion int
     * @param newVersion int
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // create tables again
        onCreate(db);
    }

    // TODO: implement methods
    public void addContact(Contact contact) {}
    public Contact getContact(int id) {}
    public List<Contact> getAllContacts() {}
    public int getContactsCount() {}
    public int updateContact(Contact contact) {}
    public void deleteContact(Contact contact) {}