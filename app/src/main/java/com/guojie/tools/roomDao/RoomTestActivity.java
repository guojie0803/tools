package com.guojie.tools.roomDao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guojie.tools.R;

import java.util.List;

public class RoomTestActivity extends AppCompatActivity {

    private WordDatabase wordDatabase;
    private WordDao wordDao;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        wordDatabase = Room.databaseBuilder(this, WordDatabase.class,"word_database")
                .allowMainThreadQueries().build(); // 强制让它在主线程运行
        wordDao = wordDatabase.getWordDao();
        textView = findViewById(R.id.textView);
    }

    public void insert(View view) {
        Word word1 = new Word("Hello", "你好");
        Word word2 = new Word("World", "世界");
        Word word3 = new Word("Student", "学生");
        wordDao.insertWords(word1,word2,word3);
        updataView();
    }

    public void update(View view) {
        Word word = new Word("Hello","你好啊");
        word.setId(37);
        wordDao.updateWords(word);
        updataView();
    }

    public void delete(View view) {
        //数据库 删除数据
        Word word1 = new Word("Hello", "你好");
        word1.setId(37);
        wordDao.deleteWords(word1);
        updataView();
    }

    public void clear(View view) {
        wordDao.deleteAllWords();
        updataView();
    }

    // 更新界面
    public void updataView(){
        List<Word> list = wordDao.getAllWords();
        StringBuilder text = new StringBuilder();
        for(int i = 0;i<list.size();i++){
            Word word = list.get(i);
            text.append(word.getId()).append("、").append(word.getWord()).append(":").append(word.getChineseMeaning()).append(' ');
            text.append("\n");
        }
        textView.setText(text);
    }


}