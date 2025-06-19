package vcmsa.ci.playlistmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // use Arraylist for storing songs
    companion object{
        val songTitles = arrayListOf("like Jennie", "Drop Top", "Mockingbird", "We Belong together", "Little Talks")
        val artistNames = arrayListOf("Jennie", "MEOVV", "Eminem", "Mariah carey", "OfMonster and Men")
        val ratings = arrayListOf(4, 5, 1, 2, 3)
        val comments = arrayListOf("Kpop", "Dance song", "Rap", "Love song", "Memories")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnAddSong:Button = findViewById(R.id.btnAddSong)
        val btnViewPlayList:Button = findViewById(R.id.btnViewPlaylist)
        val btnExit:Button = findViewById(R.id.btnExit)

        btnAddSong.setOnClickListener{
            showAddSongDialog()
        }

        btnViewPlayList.setOnClickListener{
            if (songTitles.isEmpty()){
                Toast.makeText(this, "Playlist is empty. Add a song first.", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, DetailedViewActivity::class.java)
                startActivity(intent)
            }
        }


        btnExit.setOnClickListener{
            finishAffinity() //Exit the app
        }
    }

    private fun showAddSongDialog(){
        val dialogView = layoutInflater.inflate(R.layout.activity_dialog_add_song,null)
        val etSongTitle = dialogView.findViewById<EditText>(R.id.etSongsTitle)
        val etArtistsName = dialogView.findViewById<EditText>(R.id.etArtistName)
        val etRating = dialogView.findViewById<EditText>(R.id.etRating)
        val etComment = dialogView.findViewById<EditText>(R.id.etComment)

        val dialog =AlertDialog.Builder(this)
            .setTitle("Add to playlist")
            .setView(dialogView)
            .setPositiveButton("Add", null) // set to null initailly to override and prevent auto closing
            .setNeutralButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.setOnShowListener{
            val title = etSongTitle.text.toString().trim()
            val artist =etArtistsName.text.toString().trim()
            val ratingStr = etRating.text.toString().trim()
            val comment = etComment.text.toString().trim()

            // error handling
            if (title.isEmpty() || artist.isEmpty() || ratingStr.isEmpty()){
                Toast.makeText(this, "Please fill in title, Artist, and Rating.", Toast.LENGTH_LONG).show()
                return@setOnShowListener// stop and keep the dialog open

            }
            val rating = ratingStr.toIntOrNull()
            if (rating == null || rating !in 1..5) {
                Toast.makeText(this ,"Please enter a valid rating between 1 to 5.",Toast.LENGTH_LONG).show()
                return@setOnShowListener
            }
           Toast.makeText(this,"'$title'added to playlist.",Toast.LENGTH_SHORT).show()
           dialog.dismiss()// close the dialog
        }

    }


}