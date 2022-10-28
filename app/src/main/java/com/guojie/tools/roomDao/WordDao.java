package com.guojie.tools.roomDao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void  insertWords(Word... words);
    @Update
    int updateWords(Word... words);
    @Delete
    void deleteWords(Word...words);

    @Query("DELETE FROM myWord")
    void deleteAllWords();

    @Query("SELECT * FROM myWord ORDER BY ID ASC")
    List<Word> getAllWords();

}
