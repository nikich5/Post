import exceptions.*
import org.junit.Assert.*
import org.junit.Test

internal class WallServiceTest {

    @Test
    fun add() {
        val post = Post(text = "тест")
        val wallService = WallService()

        val addedPost = wallService.add(post)

        assertTrue(addedPost.id != 0)
    }

    @Test
    fun update_trueResult() {
        val post = Post(text = "тест")
        val wallService = WallService()

        wallService.add(post)
        val result = wallService.update(post)

        assertTrue(result)
    }

    @Test
    fun update_falseResult() {
        val post = Post(text = "тест")
        val wallService = WallService()

        val result = wallService.update(post)

        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_errorResult() {
        val post = Post(text = "тест")
        val comment = Comment(1, 2, text = "тест")
        val wallService = WallService()

        wallService.add(post)
        wallService.createComment(comment)
    }

    @Test
    fun createComment() {
        val post = Post(text = "тест")
        val comment = Comment(1, 1, text = "тест")
        val wallService = WallService()

        wallService.add(post)

        assertEquals(wallService.createComment(comment), comment)
    }

}