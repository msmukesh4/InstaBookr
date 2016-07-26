package com.example.instabookr;
//
import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
//
//import com.example.glamvilla.models.User;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//
///**
// * Created by mukesh on 06/07/2016.
// */
public class DBHelper extends SQLiteOpenHelper {
//
//
//    public static DBHelper _dbHelper;
//
//    // The Android's default system path of your application database.
//    private static String DB_PATH = "/data/data/com.example.glamvilla/databases/";
//
    private static String DB_NAME = "user.sqlite";
//
//    private static int DB_VERSION = 1;
//
//    private SQLiteDatabase myDataBase = null;
//
    private final Context myContext;
//
    private DBHelper(Context context) {

        super(context, DB_NAME, null, 3);
        this.myContext = context;
    }
//
//    public static DBHelper getInstance(Context context) {
//        if (_dbHelper == null) {
//            _dbHelper = new DBHelper(context);
//        }
//        return _dbHelper;
//    }
//
//    /**
//     * Creates a empty database on the system and rewrites it with your own
//     * database.
//     * */
//    public void createDataBase() throws IOException {
//
//        boolean dbExist = checkDataBase();
//        SQLiteDatabase db_read = null;
//
//        if (dbExist) {
//            // do nothing - database already exist
//            // upgrade to version 4 - not the correct method
//
//        } else {
//
//            // By calling this method and empty database will be created into
//            // the default system path
//            // of your application so we are going to overwrite that
//            // database with our database.
//            db_read = this.getReadableDatabase();
//            db_read.close();
//
//            try {
//
//                copyDataBase();
//
//            } catch (IOException e) {
//
//                throw new Error("Error copying database");
//            }
//
//        }
//    }
//
//    /**
//     * Check if the database already exist to avoid re-copying the file each
//     * time you open the application.
//     *
//     * @return true if it exists, false if it doesn't
//     */
//    private boolean checkDataBase() {
//        SQLiteDatabase checkDB = null;
//
//        try {
//            String myPath = DB_PATH + DB_NAME;
//            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//
//        } catch (SQLiteException e) {
//            // database does't exist yet.
//            System.out.println("Exception while checking DB status :"+e);
//        }
//
//        if (checkDB != null) {
//            checkDB.close();
//        }
//
//        return checkDB != null ? true : false;
//    }
//
//    /**
//     * Copies your database from your local assets-folder to the just created
//     * empty database in the system folder, from where it can be accessed and
//     * handled. This is done by transfering bytestream.
//     * */
//    private void copyDataBase() throws IOException {
//
//        // Open your local db as the input stream
//        InputStream myInput = myContext.getAssets().open(DB_NAME);
//
//        // Path to the just created empty db
//        String outFileName = DB_PATH + DB_NAME;
//
//        // Open the empty db as the output stream
//        OutputStream myOutput = new FileOutputStream(outFileName);
//
//        // transfer bytes from the inputfile to the outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer)) > 0) {
//            myOutput.write(buffer, 0, length);
//        }
//
//        // Close the streams
//        myOutput.flush();
//        myOutput.close();
//        myInput.close();
//
//    }
//
//    public void openDataBase() throws SQLException {
//
//        // Open the database
//        String myPath = DB_PATH + DB_NAME;
//        if (myDataBase == null)
//            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    public void closeDataBase() {
//
//        if (myDataBase != null)
//            myDataBase.close();
//        myDataBase = null;
//    }
//
//    @Override
//    public synchronized void close() {
//
//        if (myDataBase != null) {
//            myDataBase.close();
//        }
//
//        super.close();
//    }
//
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
//
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Version Change from: " + oldVersion + " to " + newVersion);
    }
//
//    // public helper methods to access and get content from the database
//
//    // return a cursor for a query
//    public Cursor QueryDatabase(String query) {
//
//        try {
//            return myDataBase.rawQuery(query, null);
//        } catch (SQLException ex) {
//            throw ex;
//        }
//
//    }
//
//    public Cursor QueryDatabase(String query, String[] queryArgs) {
//
//        try {
//            return myDataBase.rawQuery(query, queryArgs);
//        } catch (SQLException ex) {
//            throw ex;
//        }
//
//    }
//
//    // run a insert / update statement
////    public void ExecuteScalar(String sql) throws SQLException {
////
////        try {
////            // myDataBase.beginTransaction();
////            myDataBase.execSQL(sql);
////
////            // myDataBase.setTransactionSuccessful();
////        } catch (SQLException ex) {
////            throw ex;
////        } finally {
////            // myDataBase.endTransaction();
////        }
////    }
//    public void create_table_if_not_exist() {
//        // get version
//        int version = myDataBase.getVersion();
//        boolean update = false;
//        try{
//            if(version<2) {
//                myDataBase.beginTransaction();
//                update = true;
//
//                // create AdImage table for the image in the ad
//                // with columns category_id,category, default_image, download_image, default_call, download_call, default_profile, download_profile
//
////                myDataBase.execSQL("CREATE TABLE IF NOT EXISTS \"AdImage\" (\"category_id\" INTEGER PRIMARY KEY  AUTOINCREMENT , \"category\" VARCHAR2 NOT NULL, \"default_image\" VARCHAR2 NOT NULL , \"download_image\" VARCHAR2 , \"default_call\" VARCHAR2 NOT NULL , \"download_call\" VARCHAR2 , \"default_profile\" VARCHAR2 , \"download_profile\" VARCHAR2 )");
//
//                myDataBase.execSQL("CREATE TABLE IF NOT EXISTS \"User\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT , \"firstname\" VARCHAR2 NOT NULL, \"lastname\" VARCHAR2 , \"email\" VARCHAR2 NOT NULL, \"mobile_number\" VARCHAR2 NOT NULL ,\"mobile_number2\" VARCHAR2 , \"location\" VARCHAR2 , \"password\" VARCHAR2 NOT NULL , \"profile_pic\" VARCHAR2, \"latitude\" VARCHAR2, \"longitude\" VARCHAR2, \"uuid\" VARCHAR2 )");
//
//                myDataBase.setVersion(DB_VERSION);
//                myDataBase.setTransactionSuccessful();
//                myDataBase.endTransaction();
//                // read the table and store the list of category already initialised
////                readAndStoreCategory();
//                // initialise the table again for some changes or insertion of new activity
////                initialize_AdImage_Table();
//            } else {
//                System.out.println("database version is not less than 2");
//            }
//        }catch (Exception ee){
//            ee.printStackTrace();
//        }
//    }
//    /*===========================================================================================*/
//
//    // This function will read and store catrgory already in the database
////    public void readAndStoreCategory(){
////
////        String tempCategory = "";
////        Cursor cursorForStoringCategory = _dbHelper.getAds();
////        if (cursorForStoringCategory.getCount() > 0){
////            System.out.println("Categories : ");
////
////            cursorForStoringCategory.moveToFirst();
////            for (int i = 0 ; i< cursorForStoringCategory.getCount() ; i++){
////                tempCategory = cursorForStoringCategory.getString(1).toString().trim();
////                Common.categories.add(tempCategory);
////                cursorForStoringCategory.moveToNext();
////                System.out.println(tempCategory);
////            }
////        }
////    }
//
//
////    // initialise Ad table
////    public  boolean initialize_AdImage_Table(){
////        boolean dataSaved = false;
////        Cursor cursorAd = null;
////        long val = 0;
////        try{
////            if (!Common.categories.contains("home")) {
////                // initialise the row for home
////                ContentValues initialAds = new ContentValues();
////
////                //initialAds.put("category_id","0");
////                initialAds.put("category", "home");
////                initialAds.put("default_image", "home_ad");
////                // initialAds.put("download_image","");
////                initialAds.put("default_call", "1234567890");
////                initialAds.put("default_profile", "Default_profile");
////                // get the values from database AdImage Table
////                cursorAd = getAds();
////                if (cursorAd.getCount() >= 0) {
////                    System.out.println("Columns :" + cursorAd.getColumnCount());
////                    System.out.println("Rows : " + cursorAd.getCount());
////
////                    val = myDataBase.insertOrThrow("AdImage", null, initialAds);
////                }
////                if (val > 0) {
////                    dataSaved = true;
////                }
////            }
////
////            if (!Common.categories.contains("kurti")){
////                // initialise row for kurti
////                ContentValues initialAds = new ContentValues();
////
////                //initialAds.put("category_id","0");
////                initialAds.put("category", "kurti");
////                initialAds.put("default_image", "home_ad");
////                // initialAds.put("download_image","");
////                initialAds.put("default_call", "0987654321");
////                initialAds.put("default_profile", "Kurti_default_profile");
////                // get the values from database AdImage Table
////                cursorAd = getAds();
////                if (cursorAd.getCount() >= 0) {
////                    System.out.println("Columns :" + cursorAd.getColumnCount());
////                    System.out.println("Rows : " + cursorAd.getCount());
////
////                    val = myDataBase.insertOrThrow("AdImage", null, initialAds);
////                }
////                if (val > 0) {
////                    dataSaved = true;
////                }
////            }
////        }catch(Exception ee){
////            ee.printStackTrace();
////        }finally {
////            if (cursorAd != null)
////                cursorAd.close();
////        }
////
////        return dataSaved;
////    }
//
////    // update AdImage Table      type of category  ,  column to be changed, new value to be updated
////    public boolean updateAdImageTable(String category, String field, String updatedValue){
////        boolean tableUpdated = false;
////        Cursor cursorUpdateAdTable = null;
////        long val;
////        try {
////            cursorUpdateAdTable = getAds();
////            if (cursorUpdateAdTable.getCount() > 0 && !category.equals("")){
////                cursorUpdateAdTable.moveToFirst();
////                for (int i = 0; i < cursorUpdateAdTable.getCount() ; i++){
////                    if(cursorUpdateAdTable.getString(1).toString().trim().equals(category)){
////                        int index = cursorUpdateAdTable.getColumnIndex(field);
////
//////                      cursorUpdateAdTable.moveToPosition(index);
////                        ContentValues contentValues = new ContentValues();
////                        contentValues.put(field,updatedValue);
////                        String whereClause =   "category LIKE ?";
////                        String[] whereArgs = {category};
////                        val = myDataBase.update("AdImage",contentValues,whereClause,whereArgs);
////                        if(val > 0){
////                            tableUpdated = true;
////                        }else{
////                            tableUpdated = false;
////                        }
////
////                    }else {
////                        cursorUpdateAdTable.moveToNext();
////                    }
////                }
////            }
////
////        }catch (Exception ee){
////            ee.printStackTrace();
////        }finally {
////            if (cursorUpdateAdTable != null){
////                cursorUpdateAdTable.close();
////            }
////        }
////
////        return tableUpdated;
////    }
//
//    // insert user table on successful login and registration
//    public boolean Saveuser(User userObj) {
//        boolean dataSaved = false;
//        Cursor cursorUser = null;
//        long retVal;
//        try {
//            ContentValues newEntry = new ContentValues();
//
//            newEntry.put("user_id", userObj.getId());
//            newEntry.put("firstname", userObj.getFirstname());
//            newEntry.put("lastname", userObj.getLastname());
//            newEntry.put("email", userObj.getEmail());
//            newEntry.put("mobile_no", userObj.getPhone_number());
//            newEntry.put("mobile_number2", userObj.getAltername_phone_number());
//            newEntry.put("profile_pic", userObj.getProfile_pic());
//            newEntry.put("password", userObj.getPassword());
//            newEntry.put("uuid", userObj.getUuid());
//            newEntry.put("latitude", userObj.getLatitude());
//            newEntry.put("longitude", userObj.getLongitude());
//
//            cursorUser = getUser();
//
//            if (cursorUser.getCount() > 0) {
//
//                System.out.println("User row count  : " + cursorUser.getCount());
//                // delete existing row
////                ExecuteScalar("Delete From User");
//                // insert
//                retVal = myDataBase.insertOrThrow("User", null, newEntry);
//
//            } else {
//                // insert
//                retVal = myDataBase.insertOrThrow("User", null, newEntry);
//
//            }
//            if (retVal > 0)
//                dataSaved = true;
//
//        } catch (SQLException ex) {
//            System.out.println("Exception in DB");
//            ex.printStackTrace();
//            // logHandledException(ex);
//            throw ex;
//        } finally {
//            if (cursorUser != null)
//                cursorUser.close();
//        }
//        return dataSaved;
//    }
//
//    // get user detail
//    public Cursor getUser() {
//        String sql = "SELECT * FROM  User";
//        // System.out.println("sql " + sql);
//        return QueryDatabase(sql);
//    }
//
////    private static final String CREATE_TABLE="create table "
////            +"IndianApparelUser ("
////            +"user_id integer primary key autoincrement  text not null, "
////            +"firstname varchar2(25) not null," +
////            "lastname varchar2(25) not null," +
////            "mobile_no number(10) notnull," +
////            "email varchar2(75)," +
////            "registered boolean);";
}
