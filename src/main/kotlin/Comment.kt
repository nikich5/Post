import attachments.Attachment

data class Comment (
    var id: Int,
    val postId: Int,
    val fromId: Int = 1,
    val text: String,
    val donut: Any? = null,
    val replyToUser: Int = 1,
    val replyToComment: Int = 1,
    val attachment: Attachment? = null,
    val parentsStack: Any? = null,
    val count: Any = 0
        )