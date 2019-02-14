package com.lyd.notepad;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import io.reactivex.Observable;

@Dao
public interface DateDao {

    @Query("SELECT * FROM DataEntity WHERE id = :id ")
    Observable<DataEntity> getDateDao(int id);

    @Insert
    void insert(DataEntity entity);

}
