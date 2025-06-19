package vcmsa.ci.playlistmanagerapp

import java.io.Serializable

data class Song(
    val title: String,
    val artist: String,
    val rating: Int,
    val comments: String,
) :Serializable