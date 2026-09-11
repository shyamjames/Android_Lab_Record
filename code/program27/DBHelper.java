productList = new ArrayList<>();
adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList); list.setAdapter(adapter);
grid.setAdapter(adapter); loadProducts();
submit.setOnClickListener(v -> {
String pName = name.getText().toString().trim(); if (!pName.isEmpty()) {
dbHelper.insertProduct(pName);
Toast.makeText(MainActivity.this, "Product added", Toast.LENGTH_SHORT).show(); name.setText("");
loadProducts();
} else {
Toast.makeText(MainActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
}
});
}

private void loadProducts() { productList.clear();
Cursor cursor = dbHelper.getAllProducts(); while (cursor.moveToNext()) {
productList.add(cursor.getString(1));
}
cursor.close(); adapter.notifyDataSetChanged();
}
}
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
private static final String DATABASE_NAME = "ProductDB.db"; private static final int DATABASE_VERSION = 1;
private static final String TABLE_PRODUCTS = "products"; public static final String COL_ID = "id";
public static final String COL_NAME = "name";

public DBHelper(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
String createTable = "CREATE TABLE " + TABLE_PRODUCTS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT)";
db.execSQL(createTable);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
onCreate(db);
}

public long insertProduct(String name) { SQLiteDatabase db = this.getWritableDatabase(); ContentValues values = new ContentValues(); values.put(COL_NAME, name);
long id = db.insert(TABLE_PRODUCTS, null, values); db.close();
return id;
}

public Cursor getAllProducts() {
SQLiteDatabase db = this.getReadableDatabase();
return db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
}
}