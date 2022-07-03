package model

import org.junit.Assert.*

import org.junit.Test

class RepositoryTest {
    val repository = Repository()

    @Test
    fun compareUserLists_NotSameLists() {
        assertNotSame(repository.loadUsers(), repository.addUser("logvinov", "712445"))
    }
}
