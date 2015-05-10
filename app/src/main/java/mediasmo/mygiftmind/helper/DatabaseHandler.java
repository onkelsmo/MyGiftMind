package mediasmo.mygiftmind.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mediasmo.mygiftmind.dao.Contact;

/**
 * DatabaseHandler
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
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";

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
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " (" +
        KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    /**
     * onUpgrade
     *
     * @param db         SQLiteDatabase
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

    /**
     * addContact
     *
     * @param contact Contact
     */
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    /**
     * getContact
     *
     * @param id int
     * @return Contact
     */
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Contact(cursor.getInt(0), cursor.getString(1));
    }

    /**
     * getAllContacts
     *
     * http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html
     *
     * @return Cursor
     */
    public Cursor getAllContacts() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * getContactsCount
     *
     * @return int
     */
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();

        return cursor.getCount();
    }

    /**
     * updateContact
     *
     * @param contact Contact
     * @return int
     */
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());

        return db.update(TABLE_CONTACTS, values, KEY_ID + "= ? ",
                new String[]{String.valueOf(contact.getId())});
    }

    /**
     * deleteContact
     *
     * @param contact Contact
     */
    public void deleteContact(Contact contact) {
        int id = contact.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = " + id, null);
        db.close();
    }
}







