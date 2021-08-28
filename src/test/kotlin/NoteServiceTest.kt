import exceptions.*
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addNote() {
        val noteService = NoteService()

        val result = noteService.addNote("Заголовок", "Текст")

        assertEquals(1, result)
    }

    @Test
    fun createComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        val result = noteService.createComment(1, "тест")

        assertEquals(1, result)
    }

    @Test(expected = ThisNoteHasBeenDeletedException::class)
    fun createComment_deletedNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.deleteNote(1)

        noteService.createComment(1, "тест")
    }

    @Test(expected = NoteNotFoundException::class)
    fun createComment_noteNotFound() {
        val noteService = NoteService()

        noteService.createComment(1, "тест")
    }

    @Test
    fun deleteNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        val result = noteService.deleteNote(1)

        assertEquals(1, result)
    }

    @Test(expected = ThisNoteHasBeenDeletedException::class)
    fun deleteNote_deletedNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.deleteNote(1)

        noteService.deleteNote(1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNote_noteNotFound() {
        val noteService = NoteService()

        noteService.deleteNote(1)
    }

    @Test
    fun deleteComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")

        val result = noteService.deleteComment(1)

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteComment_commentNotFound() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        noteService.deleteComment(1)
    }

    @Test(expected = ThisCommentHasBeenDeletedException::class)
    fun deleteComment_deletedComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")
        noteService.deleteComment(1)

        noteService.deleteComment(1)
    }


    @Test
    fun editNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        val result = noteService.editNote(1, "Новый", "Новый")

        assertEquals(1, result)
    }

    @Test(expected = ThisNoteHasBeenDeletedException::class)
    fun editNote_deletedNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.deleteNote(1)

        noteService.editNote(1, "Новый", "Новый")
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNote_noteNotFound() {
        val noteService = NoteService()

        noteService.editNote(1, "Новый", "Новый")
    }

    @Test
    fun editComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")

        val result = noteService.editComment(1, "новый текст")

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editComment_commentNotFound() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        noteService.editComment(1, "новый текст")
    }

    @Test(expected = ThisCommentHasBeenDeletedException::class)
    fun editComment_deletedComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")
        noteService.deleteComment(1)

        noteService.editComment(1, "новый текст")
    }

    @Test
    fun getNotes() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.addNote("Заголовок2", "Текст2")
        noteService.deleteNote(2)

        val note = Note("Заголовок", "Текст", 1)
        val notes = mutableListOf<Note>()
        notes.add(note)

        val result = noteService.getNotes()

        assertEquals(notes, result)
    }

    @Test
    fun getNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        val note = Note("Заголовок","Текст", 1)
        val result = noteService.getNote(1)

        assertEquals(note, result)
    }

    @Test(expected = ThisNoteHasBeenDeletedException::class)
    fun getNote_deletedNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.deleteNote(1)

        noteService.getNote(1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNote_noteNotFound() {
        val noteService = NoteService()

        noteService.getNote(1)
    }

    @Test
    fun getComments() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")
        noteService.createComment(1, "тест2")
        noteService.deleteComment(1)

        val comment = Comment(2, noteId = 1, text = "тест2")
        val comments = mutableListOf<Comment>()
        comments.add(comment)
        val result = noteService.getComments(1)

        assertEquals(comments, result)
    }

    @Test
    fun restoreComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")
        noteService.deleteComment(1)

        val result = noteService.restoreComment(1)

        assertEquals(1, result)
    }

    @Test(expected = ThisCommentNotDeletedException::class)
    fun restoreComment_notDeletedComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, "тест")

        noteService.restoreComment(1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreComment_commentNotFound() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")

        noteService.restoreComment(1)
    }
}