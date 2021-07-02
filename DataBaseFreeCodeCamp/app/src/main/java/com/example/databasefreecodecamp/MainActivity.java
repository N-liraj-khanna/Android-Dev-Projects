package com.example.databasefreecodecamp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.databasefreecodecamp.Data.DataBAseHandler;
import com.example.databasefreecodecamp.Model.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addButton, viewAllButton;
    private EditText nameText,ageText;
    private Switch isActiveChecked;
    private ListView listPeople;

    private ArrayAdapter<Person> arrayAdapter;
    private List<Person> everyone;
    DataBAseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDB = new DataBAseHandler(MainActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton=findViewById(R.id.addButton);
        viewAllButton =findViewById(R.id.viewAllButton);
        nameText=findViewById(R.id.nameInput);
        ageText=findViewById(R.id.ageInput);
        isActiveChecked =findViewById(R.id.activeSwitch);
        listPeople=findViewById(R.id.nameList);

        setArrayList();

        listPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = (Person) parent.getItemAtPosition(position);
                myDB.deleteOnePerson(person);
                setArrayList();
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person;
                try {
                    person = new Person(-1, nameText.getText().toString(),
                            Integer.parseInt(ageText.getText().toString()),
                            isActiveChecked.isChecked());
                    setArrayList();
//                    Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    person=new Person(-1,"Error Adding!",0,false);
                    Toast.makeText(MainActivity.this, "Error Creating Person", Toast.LENGTH_SHORT).show();
                }
                DataBAseHandler db = new DataBAseHandler(MainActivity.this);
                boolean success = db.addPerson(person);
                setArrayList();
//                Toast.makeText(MainActivity.this,""+success,Toast.LENGTH_SHORT).show();
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArrayList();
//                Toast.makeText(MainActivity.this,li.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setArrayList() {
        arrayAdapter = new ArrayAdapter<Person>(MainActivity
                .this, android.R.layout.simple_list_item_1, myDB.getEveryone());
        listPeople.setAdapter(arrayAdapter);
    }

}