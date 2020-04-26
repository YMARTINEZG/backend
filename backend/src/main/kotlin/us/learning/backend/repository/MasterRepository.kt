package us.learning.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface MasterRepository<T, ID>: JpaRepository<T, ID> {
    @JvmDefault
    fun findByIdOrNull(id: ID): T? = findById(id).orElse(null)
}