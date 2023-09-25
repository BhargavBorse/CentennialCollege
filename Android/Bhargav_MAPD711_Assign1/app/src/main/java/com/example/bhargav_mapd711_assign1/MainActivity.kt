import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.bhargav_mapd711_assign1.SecondActivity
import com.example.bhargav_mapd711_assign1.R

class MainActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etQualification: EditText
    private lateinit var etProfession: EditText
    private lateinit var etHobby: EditText
    private lateinit var etDreamJob: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFullName = findViewById(R.id.etFullName)
        etQualification = findViewById(R.id.etQualification)
        etProfession = findViewById(R.id.etProfession)
        etHobby = findViewById(R.id.etHobby)
        etDreamJob = findViewById(R.id.etDreamJob)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener(View.OnClickListener {
            val fullName = etFullName.text.toString()
            val qualification = etQualification.text.toString()
            val profession = etProfession.text.toString()
            val hobby = etHobby.text.toString()
            val dreamJob = etDreamJob.text.toString()

            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            intent.putExtra("FULL_NAME", fullName)
            intent.putExtra("QUALIFICATION", qualification)
            intent.putExtra("PROFESSION", profession)
            intent.putExtra("HOBBY", hobby)
            intent.putExtra("DREAM_JOB", dreamJob)

            startActivity(intent)
        })
    }
}
