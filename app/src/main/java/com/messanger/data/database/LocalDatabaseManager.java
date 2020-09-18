package com.messanger.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.messanger.datamodel.Messages;
import com.messanger.datamodel.User;

import java.util.ArrayList;

/**
 * Created by Rahul Abrol on 11/24/17.
 * <p>
 * Class {@link LocalDatabaseManager} used to display all
 * local chat and messages that are stores in Users
 * and messages tables.
 * Key points:-
 * 1.) this class handle the database locally.
 * 2.) all info stores in this table is stores locally.
 */
public class LocalDatabaseManager extends SQLiteOpenHelper {
    private static final String TAG = LocalDatabaseManager.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "messangerDB";
    // Contacts table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MESSAGES = "messages";
    // Users Table Columns names
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String TOKEN = "token";
    // Message Table Columns names
    private static final String TIME_STAMP = "timestamp";
    private static final String ID = "id";
    private static final String MESSAGE = "message";
    private static final String IMAGE_LINK = "image_link";
    private static final String TO_ID = "to_id";
    private static final String FROM_ID = "from_id";
    //USED AS PRIMARY KEY
    private static final String USER_ID = "user_id";
    private static final String MESSAGE_ID = "message_id";
    private static LocalDatabaseManager localDatabaseManager;

    /**
     * Instantiates a new LocalDB handler.
     */
    private LocalDatabaseManager() {
        /*TODO context cannot be null.*/
        super(null, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * get the instance of current class to access local db.
     *
     * @return instance of local db.
     */
    public static LocalDatabaseManager getInstance() {
        /*TODO we don't required double locking here*/
        if (localDatabaseManager == null) {
            synchronized (LocalDatabaseManager.class) {
                if (localDatabaseManager == null) {
                    localDatabaseManager = new LocalDatabaseManager();
                }
            }
        }
        return localDatabaseManager;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        //Creating Tables
        String createUsers = "CREATE TABLE "
                + TABLE_USERS + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " VARCHAR,"
                + EMAIL + " VARCHAR,"
                + TOKEN + " VARCHAR" + ");";

        String createMesseges = "CREATE TABLE "
                + TABLE_MESSAGES + "("
                + MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID + " VARCHAR,"
                + MESSAGE + " VARCHAR,"
                + IMAGE_LINK + " VARCHAR,"
                + TO_ID + " VARCHAR,"
                + FROM_ID + " VARCHAR,"
                + TIME_STAMP + " VARCHAR,"
                + USER_ID + " VARCHAR,"
                + " FOREIGN KEY (" + USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + TOKEN + "));";

        db.execSQL(createUsers);
        db.execSQL(createMesseges);

    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        //Upgrading database
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        // Create tables again
        onCreate(db);
    }

    /**
     * Method used to check if user already exists in
     * local db then don't save it else save it.
     *
     * @param userId user id that need to be check.
     * @param db     instance of Sqlite Database.
     * @return true is user exist else false.
     */
    private boolean ifExists(final String userId, final SQLiteDatabase db) {
        String checkQuery = "SELECT " + TOKEN + " FROM " + TABLE_USERS + " WHERE " + TOKEN + "= '" + userId + "'";
        Cursor cursor = db.rawQuery(checkQuery, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    /**
     * Add new User here.
     *
     * @param user user model.
     */
    public void addUsers(final User user) {
        Log.e(TAG, "addUsers: -------->>" + user.getId());
        if (user.getId() != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            if (ifExists(user.getId(), db)) {
                Log.e(TAG, "addUsers: 0======>>user already exist.");
                // Closing database connection
                db.close();
                return;
            }
            ContentValues values = new ContentValues();
            values.put(NAME, user.getName());
            values.put(EMAIL, user.getEmail());
            values.put(TOKEN, user.getId());

            // Inserting Row
            db.insert(TABLE_USERS, null, values);
            // Closing database connection
            db.close();
        }
    }

    /**
     * Method used to check if data is already in db or not.
     * if data exists in db then show it else hit api to get
     * data from server and insert into the db.
     *
     * @return true if data exists else false.
     */
    public boolean checkIsDataAlreadyInDBorNot() {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String query = "Select * from " + TABLE_USERS /*+ " where " + TOKEN + " = '" + userId + "'"*/;
        Cursor cursor = sqldb.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * Method used to get data from DB and add into the userList.
     *
     * @return arraylist of type {@link User}.
     */
    public ArrayList<User> getUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        SQLiteDatabase sqldb = this.getWritableDatabase();
        Cursor cursor;
        try {
            cursor = sqldb.query(TABLE_USERS, null, null, null,
                    null, null, null, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
//                        int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
                    String name = cursor.getString(cursor.getColumnIndex(NAME));
                    String email = cursor.getString(cursor.getColumnIndex(EMAIL));
                    String token = cursor.getString(cursor.getColumnIndex(TOKEN));
                    // Log.e(TAG, "getUsers: ........" + token + "<------>" + name);
                    usersList.add(new User(name, email, token));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    /**
     * Method used to check if message unique key already exists in
     * local db then don't save it else save it.
     *
     * @param key user id that need to be check.
     * @param db  instance of Sqlite Database.
     * @return true is user exist else false.
     */
    private boolean ifMessageKeyExists(final String key, final SQLiteDatabase db) {
        String checkQuery = "SELECT " + ID + " FROM " + TABLE_MESSAGES + " WHERE " + ID + "= '" + key + "'";
        Cursor cursor = db.rawQuery(checkQuery, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    /**
     * Add new User here.
     *
     * @param messages user model.
     * @param key      unique key
     */
    public void addMessages(final Messages messages, final String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (ifMessageKeyExists(key, db)) {
            Log.e(TAG, "addMessages: ------>message Key already exists.");
            db.close();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(ID, key);
        values.put(MESSAGE, messages.getMessage());
        values.put(TIME_STAMP, messages.getTimeStamp());
        values.put(IMAGE_LINK, messages.getImageUrl());
        values.put(TO_ID, messages.getToId());
        values.put(FROM_ID, messages.getFromId());

        // Inserting Row
        db.insert(TABLE_MESSAGES, null, values);

        // Closing database connection
        db.close();
    }

    /**
     * Method used to get all messages with ToId and FromId.
     *
     * @param fromId from whome we are getting the message.
     * @param toId   whose we are going to send
     * @return list of {@link Messages}.
     */
    public ArrayList<Messages> getAllMessages(final String toId, final String fromId) {
        ArrayList<Messages> messagesList = new ArrayList<>();
        SQLiteDatabase sqldb = this.getWritableDatabase();
        Cursor cursor;
        try {
            String query = "SELECT * from " + TABLE_MESSAGES + " WHERE ("
                    + TO_ID + " = '" + toId + "' AND " + FROM_ID + " = '" + fromId
                    + "') OR (" + TO_ID + " = '" + fromId + "' AND " + FROM_ID + " = '" + toId + "')";

            cursor = sqldb.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String fId = cursor.getString(cursor.getColumnIndex(FROM_ID));
                    String tId = cursor.getString(cursor.getColumnIndex(TO_ID));
                    String message = cursor.getString(cursor.getColumnIndex(MESSAGE));
                    String imageUrl = cursor.getString(cursor.getColumnIndex(IMAGE_LINK));
                    String timeStamp = cursor.getString(cursor.getColumnIndex(TIME_STAMP));
                    Log.e(TAG, "getMessage: ........" + fId + "<------>" + tId);
                    Messages messages = new Messages();
                    messages.setFromId(fId);
                    messages.setToId(tId);
                    messages.setMessage(message);
                    messages.setImageUrl(imageUrl);
                    messages.setTimeStamp(Long.parseLong(timeStamp));

                    messagesList.add(messages);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

    /**
     * Method used to get recents messeges from
     * the table according to user Id.
     *
     * @param userId user id to identify records from tables.
     */
    public void getRecentMessages(final String userId) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ArrayList<Messages> messagesLis = new ArrayList<>();
        String query = "SELECT " + MESSAGE + "," + IMAGE_LINK + "," + TIME_STAMP + "," + TOKEN + " FROM " + TABLE_MESSAGES
                + " LEFT JOIN " + TABLE_USERS + " ON "
                + TABLE_MESSAGES + "." + TO_ID + " = " + TABLE_USERS + "." + TOKEN
                + " AND "
                + TABLE_MESSAGES + "." + FROM_ID + " = " + TABLE_USERS + "." + TOKEN
                + " WHERE " + TO_ID + "= '" + userId + "' OR " + FROM_ID + " = '" + userId
                + "' GROUP BY " + TO_ID + "," + FROM_ID + " ORDER BY " + TIME_STAMP + " DESC";

        Log.e(TAG, "getRecentMessages: ------>>" + query);
        Cursor cursor = sqlDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
//                    String fId = cursor.getString(cursor.getColumnIndex(FROM_ID));
//                    String tId = cursor.getString(cursor.getColumnIndex(TO_ID));
                String token = cursor.getString(cursor.getColumnIndex(TOKEN));
                String message = cursor.getString(cursor.getColumnIndex(MESSAGE));
                String imageUrl = cursor.getString(cursor.getColumnIndex(IMAGE_LINK));
                String timeStamp = cursor.getString(cursor.getColumnIndex(TIME_STAMP));
//                    Log.e(TAG, "getMessage: ........" + fId + "<------>" + tId);
                Messages messages = new Messages();
//                    messages.setFromId(fId);
//                    messages.setToId(tId);
                messages.setMessage(message);
                messages.setImageUrl(imageUrl);
                messages.setTimeStamp(Long.parseLong(timeStamp));
                messagesLis.add(messages);
                cursor.moveToNext();
            }
        }
//            boolean isContainChat = cursor.getCount() > 0;
        Log.e(TAG, "getRecentMessages: " + messagesLis.size());
        cursor.close();
    }

    /**
     * Method used to check if mesages exist in
     * the db for the particular user.
     *
     * @param toId   message sent to.
     * @param fromId message from.
     * @return true if message exists else false.
     */
    public boolean checkIfMessagesAlreadyInDb(final String toId, final String fromId) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String query = "Select * from " + TABLE_MESSAGES + " where ("
                + TO_ID + " = '" + toId + "' AND " + FROM_ID + " = '" + fromId
                + "') OR (" + TO_ID + " = '" + fromId + "' AND " + FROM_ID + " = '" + toId + "')";
        Cursor cursor = sqldb.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}