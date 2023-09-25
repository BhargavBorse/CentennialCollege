import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.bhargav_mapd711_assign1.R

class SecondActivity : AppCompatActivity() {

    private lateinit var tvFullName: TextView
    private lateinit var tvQualification: TextView
    private lateinit var tvProfession: TextView
    private lateinit var tvHobby: TextView
    private lateinit var tvDreamJob: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvFullName = findViewById(R.id.tvFullName)
        tvQualification = findViewById(R.id.tvQualification)
        tvProfession = findViewById(R.id.tvProfession)
        tvHobby = findViewById(R.id.tvHobby)
        tvDreamJob = findViewById(R.id.tvDreamJob)

        val intent = intent

        val fullName = intent.getStringExtra("FULL_NAME")
        val qualification = intent.getStringExtra("QUALIFICATION")
        val profession = intent.getStringExtra("PROFESSION")
        val hobby = intent.getStringExtra("HOBBY")
        val dreamJob = intent.getStringExtra("DREAM_JOB")

        tvFullName.text = "Full Name: $fullName"
        tvQualification.text = "Qualification: $qualification"
        tvProfession.text = "Profession: $profession"
        tvHobby.text = "Hobby: $hobby"
        tvDreamJob.text = "Dream Job: $dreamJob"
    }
}
