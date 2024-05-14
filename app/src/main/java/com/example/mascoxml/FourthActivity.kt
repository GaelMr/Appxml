package com.example.mascoxml

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mascoxml.entity.Remainder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class FourthActivity : AppCompatActivity() {
    lateinit var btnCreateRecor: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth)
        val txtDateRecor: EditText = findViewById(R.id.TxtDateRecor)
        val txtTimeRecor: EditText = findViewById(R.id.TxtTimeRecor)
        btnCreateRecor = findViewById(R.id.btnCreateRecor)

        // Configura un OnClickListener para el EditText
        txtTimeRecor.setOnClickListener {
            showTimePickerDialog(txtTimeRecor)
        }
        // Configura un OnClickListener para el EditText
        txtDateRecor.setOnClickListener {
            showDatePickerDialog(txtDateRecor)
        }

        btnCreateRecor.setOnClickListener {
            // Aquí va el código para crear un nuevo registro
            createRemainder()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun createRemainder() {
        // Obtén los valores de los EditTexts
        val nombre = findViewById<EditText>(R.id.txtNombreRecor).text.toString()
        val lugar = findViewById<EditText>(R.id.txtLugar).text.toString()

        try {
            // Parsea las fechas y horas a partir de los EditTexts
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val fechaHora = sdf.parse("${findViewById<EditText>(R.id.TxtDateRecor).text} ${findViewById<EditText>(R.id.TxtTimeRecor).text}")

            // Crea una instancia de Remainder
            val remainder = Remainder(nombre, fechaHora, lugar)

            // Imprime el objeto Remainder
            Toast.makeText(this, remainder.nombre + " " +remainder.fecha + "" + remainder.lugar, Toast.LENGTH_SHORT).show()
        } catch (e: ParseException) {
            // Muestra un mensaje de error si no se puede parsear la fecha y la hora
            Toast.makeText(this, "Error: no se pudo parsear la fecha y la hora.", Toast.LENGTH_LONG).show()
        }
    }
    fun showTimePickerDialog(txtTimeRecor: EditText) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            txtTimeRecor.setText("" + hourOfDay + ":" + minute)
        }, hour, minute, false)
        timePickerDialog.show()
    }
    // Muestra un diálogo de selección de fecha
    fun showDatePickerDialog(txtDateRecor: EditText) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            txtDateRecor.setText(""+dayOfMonth + "/" + monthOfYear + "/" + year)
        }, year, month, day)
        dpd.show()
    }
}