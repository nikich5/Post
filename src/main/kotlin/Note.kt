data class Note (
    val title: String,
    val text: String,
    val id: Int = 0,
    val privacyView: Boolean = false,
    val privacyComment: Boolean = false,
    val isDeleted: Boolean = false
        )