package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.database.Data.DataBaseHandler;
import com.example.database.Model.Contact;
import com.example.database.adapters.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Contact> contactNameList;
    private ArrayAdapter arrayAdapter;
    private RecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler db = new DataBaseHandler(MainActivity.this);
//        db.addContact(new Contact("Liaj Khanna","8754577448"));


//        db.addContact(new Contact("Suresh","8608670308"));
//        db.addContact(new Contact("blah","856258515"));
//        db.addContact(new Contact("Astonomor K","741258963"));
//        db.addContact(new Contact("sdfg","8745"));
//        db.addContact(new Contact("Sup!","741258"));
//        db.addContact(new Contact("um what!","41155"));
//        db.addContact(new Contact("Myself","8754577448"));
//        db.addContact(new Contact("I","8608670308"));
//        db.addContact(new Contact("Adei","856258515"));
//        db.addContact(new Contact("IDK Yu","741258963"));
//        db.addContact(new Contact("WHoa","8745"));
//        db.addContact(new Contact("Damn!","741258"));
//        db.addContact(new Contact("Nahhh!","87445552"));
//        db.addContact(new Contact("Liaj Khanna","8754577448"));
//        db.addContact(new Contact("Suresh","8608670308"));
//        db.addContact(new Contact("blah","856258515"));
//        db.addContact(new Contact("Astonomor K","741258963"));
//        db.addContact(new Contact("sdfg","8745"));
//        db.addContact(new Contact("Sup!","741258"));
//        db.addContact(new Contact("um what!","41155"));
//        db.addContact(new Contact("Myself","8754577448"));
//        db.addContact(new Contact("I","8608670308"));
//        db.addContact(new Contact("Adei","856258515"));
//        db.addContact(new Contact("IDK Yu","741258963"));
//        db.addContact(new Contact("WHoa","8745"));
//        db.addContact(new Contact("Damn!","741258"));
//        db.addContact(new Contact("Nahhh!","87445552"));
        List<Contact> contactList = db.getAllContact();

        contactNameList=new ArrayList<>();
        for(Contact c: contactList){
            contactNameList.add(c);
        }

        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleViewAdapter=new RecycleViewAdapter(MainActivity.this,contactNameList);
        recyclerView.setAdapter(recycleViewAdapter);


    }
}