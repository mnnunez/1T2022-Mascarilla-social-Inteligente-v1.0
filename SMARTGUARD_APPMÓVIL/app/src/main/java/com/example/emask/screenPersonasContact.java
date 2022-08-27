package com.example.emask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class screenPersonasContact extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView totalPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_personas_contact);
        totalPersonas = (TextView)findViewById(R.id.totalPersonas);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("RegistroDatos").child("SmartGuard1");
        Tabla tabla = new Tabla(this,(TableLayout) findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int contador=0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    System.out.println(dataSnapshot.getKey());
                    ArrayList<String> elementos = new ArrayList<String>();
                    elementos.add(Integer.toString(contador));
                    elementos.add(dataSnapshot.child("Fecha:").getValue().toString());
                    elementos.add(dataSnapshot.getKey().toString());
                    contador++;
                    tabla.agregarFilaTabla(elementos);

                }
                totalPersonas.setText(String.valueOf(contador));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }
}