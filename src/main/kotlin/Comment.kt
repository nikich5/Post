import attachments.Attachment

data class Comment (
    var id: Int,
    val postId: Int = 0,
    val noteId: Int = 0,
    val fromId: Int = 1,
    val text: String,
    val donut: Any? = null,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachment: Attachment? = null,
    val parentsStack: Any? = null,
    val count: Any = 0,
    val isDeleted: Boolean = false
        )