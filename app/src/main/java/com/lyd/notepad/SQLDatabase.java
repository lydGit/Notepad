package com.lyd.notepad;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = { DataEntity.class }, version = 1,exportSchema = false)
public abstract class SQLDatabase extends RoomDatabase {

    private static final String DB_NAME = "Notepad.db";
    private static volatile SQLDatabase instance;

    static synchronized SQLDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static SQLDatabase create(final Context context) {
        return Room.databaseBuilder(context,
                SQLDatabase.class,
                DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                }).build();
    }

    public abstract DateDao getDateDao();

}
