package com.felipegabriel.centralfaculdade.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericDatabase<T> extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CentralAlunoDB";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_USERNAME = "usuario";
    private static final String COLUMN_PASSWORD = "senha";
    private final Class<T> clazz;

    public GenericDatabase(Context context, Class<T> clazz) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.clazz = clazz;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tableName = clazz.getSimpleName();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    protected void createTableIfNotExists(SQLiteDatabase db) {
        String tableName = clazz.getSimpleName();
        if (!isTableExists(db, tableName)) {
            String createTableQuery = createTableQuery(clazz);
            db.execSQL(createTableQuery);
        }
    }

    private String createTableQuery(Class<T> clazz) {
        StringBuilder query = new StringBuilder();
        String tableName = clazz.getSimpleName();
        query.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

        query.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                String fieldName = field.getName();
                String fieldType = getSQLiteType(field.getType());

                query.append(fieldName).append(" ").append(fieldType).append(", ");
            }
        }

        query.setLength(query.length() - 2);
        query.append(");");
        return query.toString();
    }

    public long save(T object) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        ContentValues values = new ContentValues();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                try {
                    String fieldName = field.getName();
                    Object fieldValue = field.get(object);

                    if (fieldValue != null) {
                        if (fieldValue instanceof String) {
                            values.put(fieldName, (String) fieldValue);
                        } else if (fieldValue instanceof Integer) {
                            values.put(fieldName, (Integer) fieldValue);
                        } else if (fieldValue instanceof Long) {
                            values.put(fieldName, (Long) fieldValue);
                        } else if (fieldValue instanceof Double) {
                            values.put(fieldName, (Double) fieldValue);
                        } else if (fieldValue instanceof LocalTime) {
                            values.put(fieldName, ((LocalTime) fieldValue).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                        } else if (fieldValue instanceof LocalDateTime) {
                            values.put(fieldName, ((LocalDateTime) fieldValue).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao salvar: " + e.getMessage());
                }
            }
        }

        return db.insert(clazz.getSimpleName(), null, values);
    }

    public Optional<T> findById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        createTableIfNotExists(db);

        T object = null;
        String tableName = clazz.getSimpleName();
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        object = getObject(cursor, object);

        cursor.close();
        return Optional.ofNullable(object);
    }

    public T checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        createTableIfNotExists(db);

        String query = String.format("SELECT * FROM USUARIO WHERE %s = ? AND %s = ?", COLUMN_USERNAME, COLUMN_PASSWORD);
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        T object = null;

        object = getObject(cursor, object);

        cursor.close();
        return object;
    }

    protected T getObject(Cursor cursor, T object) {
        if (cursor.moveToFirst()) {
            try {
                object = clazz.newInstance();

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    int columnIndex = cursor.getColumnIndex(field.getName());

                    if (columnIndex != -1) {
                        if (field.getType() == String.class) {
                            field.set(object, cursor.getString(columnIndex));
                        } else if (field.getType() == int.class || field.getType() == Integer.class) {
                            field.set(object, cursor.getInt(columnIndex));
                        } else if (field.getType() == long.class || field.getType() == Long.class) {
                            field.set(object, cursor.getLong(columnIndex));
                        } else if (field.getType() == double.class || field.getType() == Double.class || field.getType() == float.class || field.getType() == Float.class) {
                            field.set(object, cursor.getDouble(columnIndex));
                        }
                    }
                }
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException("Erro ao buscar por id: " + e.getMessage());
            }
        }
        return object;
    }

    protected List<T> getObject(Cursor cursor, List<T> listObject) {
        if (cursor.moveToFirst()) {
            try {
                listObject = new ArrayList<>();
                do {
                    Field[] fields = clazz.getDeclaredFields();
                    T object = clazz.newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        int columnIndex = cursor.getColumnIndex(field.getName());

                        if (columnIndex != -1) {
                            if (field.getType() == String.class) {
                                field.set(object, cursor.getString(columnIndex));
                            } else if (field.getType() == int.class || field.getType() == Integer.class) {
                                field.set(object, cursor.getInt(columnIndex));
                            } else if (field.getType() == long.class || field.getType() == Long.class) {
                                field.set(object, cursor.getLong(columnIndex));
                            } else if (field.getType() == double.class || field.getType() == Double.class || field.getType() == float.class || field.getType() == Float.class) {
                                field.set(object, cursor.getDouble(columnIndex));
                            } else if (field.getType() == LocalTime.class) {
                                field.set(object, LocalTime.parse(cursor.getString(columnIndex)));
                            } else if (field.getType() == LocalDateTime.class) {
                                field.set(object, LocalDateTime.parse(cursor.getString(columnIndex), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                            }
                        }
                    }
                    listObject.add(object);
                } while (cursor.moveToNext());

            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao buscar por id: " + e.getMessage());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        return listObject;
    }

    public long countAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        String tableName = clazz.getSimpleName();

        String query = String.format("SELECT COUNT(*) AS totalRegistros FROM %s", tableName);

        return db.compileStatement(query).simpleQueryForLong();
    }

    protected static Cursor getCursor(List<Integer> parametros, SQLiteDatabase db, String query) {
        String[] parametrosArray = new String[parametros.size()];
        for (int i = 0; i < parametros.size(); i++) {
            parametrosArray[i] = String.valueOf(parametros.get(i));
        }
        return db.rawQuery(query, parametrosArray);
    }

    private String getSQLiteType(Class<?> type) {
        if (type == String.class) {
            return "TEXT";
        } else if (type == int.class || type == Integer.class) {
            return "INTEGER";
        } else if (type == long.class || type == Long.class) {
            return "INTEGER";
        } else if (type == float.class || type == Float.class || type == double.class || type == Double.class) {
            return "REAL";
        } else {
            return "TEXT";
        }
    }
}


