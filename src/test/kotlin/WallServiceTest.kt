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
}