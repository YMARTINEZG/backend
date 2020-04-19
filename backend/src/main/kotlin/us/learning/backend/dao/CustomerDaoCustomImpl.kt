package us.learning.backend.dao

import us.learning.backend.domain.Customer
import java.text.Normalizer
import java.util.stream.Collectors
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class CustomerDaoCustomImpl: CustomerDaoCustom {
    @PersistenceContext
    private lateinit var em: EntityManager

    override fun numberOfCustomerByState(state: String): List<Customer> {
        val jpql = "select pc from customers pc where upper(pc.address) like :value order by pc.lastName"
        val query = em.createQuery(jpql, Customer::class.java)
        return query.setParameter("value", "%" + sanitizeQuery(state) + "%")
                .resultList
    }
    private fun sanitizeQuery(search: String): String {
        // Use uppercase
        var result = search.toUpperCase()
        // replace dashed and commas with spaces, since thre is none in the data
        result = result.replace('\'', ' ').replace('-', ' ')
        // remove accents
        result = Normalizer.normalize(result, Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")

        // strip in parts to make sure there is a single space between parts
        result = result.split(' ')
                .stream()
                .filter { !it.isBlank() }
                .map { if (it == "SAINT") "ST" else it }
                .collect(Collectors.joining(" "))
        return result
    }
}