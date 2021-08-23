import exceptions.*
import attachments.*

class WallService {
    private var postId = 0
    private var posts = emptyArray<Post>()
    private var attachments = emptyArray<Attachment>()
    private var commentId = 0
    private var comments = emptyArray<Comment>()
    private var reportedComments = emptyArray<ReportComment>()


    fun add(post: Post): Post {
        postId++
        post.id = postId
        posts += post
        return posts.last()
    }

    fun addAttachment(attachment: Attachment): Attachment {
        val newAttachment: Attachment = when(attachment) {
            is Photo -> PhotoAttachment(attachment.type, attachment)
            is Video -> VideoAttachment(attachment.type, attachment)
            is Audio -> AudioAttachment(attachment.type, attachment)
            is Document -> DocumentAttachment(attachment.type, attachment)
            is Link -> LinkAttachment(attachment.type, attachment)
            else -> throw Exception()
        }
        attachments += newAttachment
        return attachments.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postInArray) in posts.withIndex()) {
            if (post.id == postInArray.id) {
                posts[index] = post.copy(ownerId = postInArray.ownerId, date = postInArray.date)
                return true
            }
        }
        return false
    }

    fun createComment(comment: Comment): Comment {
        for (post in posts) {
            if (post.id == comment.postId) {
                commentId++
                comment.id = commentId
                comments += comment
                return comments.last()
            }
            }
        throw PostNotFoundException()
    }

    fun reportComment(comment: Comment, reason: Int): ReportComment {
        if (reason < 0 || reason > 8) {
            throw NoSuchReasonException()
        }

        for (commentInArray in comments) {
            if(commentInArray.id == comment.id) {
                val reportedComment = ReportComment(commentInArray, reason)
                reportedComments += reportedComment
                return reportedComments.last()
            }
        }
        throw CommentNotFoundException()
    }

    fun showPosts() {
        for (post in posts) {
            println(post.text)
        }
    }

    fun showAttachments() {
        for (attachment in attachments) {
            println(attachment.toString())
        }
    }
}