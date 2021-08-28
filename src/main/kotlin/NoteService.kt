import exceptions.*

class NoteService {
    private var noteId = 0
    private var commentId = 0
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()

    fun addNote(
        title: String,
        text: String,
        privacyView: Boolean = false,
        privacyComment: Boolean = false
    ): Int {
        noteId++
        val note = Note(title, text, noteId, privacyView, privacyComment)
        notes.add(note)
        return noteId
    }

    fun createComment(noteId: Int, message: String, replyTo: Int = 0): Int {
        for (note in notes) {
            if (noteId == note.id) {
                if (note.isDeleted) {
                    throw ThisNoteHasBeenDeletedException()
                }
                commentId++
                val comment = Comment(commentId, noteId = noteId, text = message, replyToComment = replyTo)
                comments.add(comment)
                return commentId
            }
        }
        throw NoteNotFoundException()
    }

    fun deleteNote(noteId: Int): Int {
        for ((indexNote, note) in notes.withIndex()) {
            if (noteId == note.id) {
                if (note.isDeleted) {
                    throw ThisNoteHasBeenDeletedException()
                }

                for ((indexComment, comment) in comments.withIndex()) {
                    if (comment.noteId == noteId) {
                        val deletedComment = comment.copy(isDeleted = true)
                        comments[indexComment] = deletedComment
                    }
                }

                val deletedNote = note.copy(isDeleted = true)
                notes[indexNote] = deletedNote
                return 1
            }
        }
        throw NoteNotFoundException()
    }

    fun deleteComment(commentId: Int): Int {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == commentId) {
                if (comment.isDeleted) {
                    throw ThisCommentHasBeenDeletedException()
                }

                val deletedComment = comment.copy(isDeleted = true)
                comments[index] = deletedComment
                return 1
            }
        }
        throw CommentNotFoundException()
    }

    fun editNote(
        noteId: Int,
        title: String,
        text: String,
        privacyView: Boolean = false,
        privacyComment: Boolean = false
    ): Int {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id) {
                if (note.isDeleted) {
                    throw ThisNoteHasBeenDeletedException()
                }

                val editNote = note.copy(title = title, text = text,
                    privacyView = privacyView, privacyComment = privacyComment)
                notes[index] = editNote
                return 1
            }
        }
        throw NoteNotFoundException()
    }

    fun editComment(commentId: Int, message: String): Int {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == commentId) {
                if (comment.isDeleted) {
                    throw ThisCommentHasBeenDeletedException()
                }

                val editComment = comment.copy(text = message)
                comments[index] = editComment
                return 1
            }
        }
        throw CommentNotFoundException()
    }

    fun getNotes(): List<Note> {
        val noteList = mutableListOf<Note>()
        for (note in notes) {
            if(!note.isDeleted) {
                noteList.add(note)
            }
        }
        return noteList
    }

    fun getNote(noteId: Int): Note {
        for (note in notes) {
            if (noteId == note.id) {
                if (note.isDeleted) {
                    throw ThisNoteHasBeenDeletedException()
                }
                return note
            }
        }
        throw NoteNotFoundException()
    }

    fun getComments(noteId: Int): List<Comment> {
        val noteComments = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.noteId == noteId && !comment.isDeleted) {
                noteComments.add(comment)
            }
        }
        return noteComments
    }

    fun restoreComment(commentId: Int): Int {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == commentId) {
                if (!comment.isDeleted) {
                    throw ThisCommentNotDeletedException()
                }

                val restoredComment = comment.copy(isDeleted = false)
                comments[index] = restoredComment
                return 1
            }
        }
        throw CommentNotFoundException()
    }
}