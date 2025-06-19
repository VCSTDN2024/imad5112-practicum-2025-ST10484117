package vcmsa.ci.playlistmanagerapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)
        val tvSongList: TextView = findViewById(R.id.tvSongList)
        val btnCalculateAverage: Button = findViewById(R.id.btnCalculateAverage)
        val btnBackToMain: Button = findViewById(R.id.btnBackToMain)

        displaySonglist(tvSongList)

        btnCalculateAverage.setOnClickListener{
            calculateAndDisplayAverageRating()// calculate the average and display it
        }

        btnBackToMain.setOnClickListener{
            finish()// return the user to previous screen
        }
    }

    private fun displaySonglist(textView: TextView){
        val songDetails = StringBuilder()
        for (i in MainActivity.songTitles.indices){
            songDetails.append("Title: ${MainActivity.songTitles[i]}\n")
            songDetails.append("Artist: ${MainActivity.artistNames[i]}\n")
            songDetails.append("Rating: ${MainActivity.ratings[i]}/5\n")
            songDetails.append("Comments: ${MainActivity.comments[i]}\n\n")
        }
        textView.text = songDetails.toString()
    }

    private fun calculateAndDisplayAverageRating(){
        if (MainActivity.ratings.isEmpty()){
            Toast.makeText(this, "The playlist is empty.", Toast.LENGTH_SHORT).show()
            return// exit the function if there are no ratings
        }
        var totalRating =0
        for (rating in MainActivity.ratings) {
            totalRating += rating
        }
        val averageRating = totalRating.toDouble()
        val decimalFormat = DecimalFormat("#.##")// format to 2 decimals
        Toast.makeText(this, "Average Rating : ${decimalFormat.format(averageRating)}",Toast.LENGTH_LONG).show()
    }
}
