package us.learning.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.learning.backend.domain.Customer
import us.learning.backend.domain.Income
import us.learning.backend.domain.IncomeSource
import us.learning.backend.domain.Note
import us.learning.backend.dto.CustomerCommandDTO
import us.learning.backend.dto.IncomeDTO
import us.learning.backend.exception.NotFoundException
import us.learning.backend.repository.CustomerDao
import us.learning.backend.repository.IncomeDao
import us.learning.backend.repository.IncomeSourceDao
import us.learning.backend.web.note.NoteDTO
import javax.transaction.Transactional

@Service
@Transactional
class CustomerService(@Autowired private val repository : CustomerDao,
                      @Autowired private val incomeRepo : IncomeDao,
                      @Autowired private val incomeSourceRepo : IncomeSourceDao) {
    fun searchByStateOfAddress(search: String) : List<Customer> {
        return repository.numberOfCustomerByState(search)
    }
    fun findAllCustomers(): List<Customer> {
        if(repository.findAll().size == 0) {
            dbSetup()
        }
        return repository.findAll()
    }
    fun findCustomer(id : Long) : Customer {
        return repository.findByIdOrNull(id) ?: throw NotFoundException()
    }
    fun saveCustomer(customer: Customer): Customer {
        return repository.save(customer)
    }
    private fun dbSetup(){
        val customer1 = Customer()
        copyCommandToCustomer(CustomerCommandDTO(firstName = "Jim", lastName = "Kans"), customer1)
        repository.save(customer1)
        val customer2 = Customer()
        copyCommandToCustomer(CustomerCommandDTO(firstName = "Sandy", lastName = "Lora"), customer2)
        repository.save(customer2)
        val newCustomer= Customer()
        copyCommandToCustomer(CustomerCommandDTO(firstName = "Juan", lastName = "Perez"), newCustomer)
        newCustomer.addNote(Note("Good Customer"))
        newCustomer.addNote(Note("Pay on Time"))
        newCustomer.email = "myaddress@test.com"
        repository.save(newCustomer)
    }
    private fun copyCommandToCustomer(command: CustomerCommandDTO, customer: Customer) {
        with(customer) {
            firstName = command.firstName
            lastName = command.lastName
        }
    }
    fun handleCustomerMessage(noteDTO: NoteDTO) : Customer {
        val customer = repository.findByIdOrNull(noteDTO.customerId) ?: throw NotFoundException()
        val note = Note()
        note.text = noteDTO.noteText
        customer.addNote(note)
        repository.flush()
        return customer
    }
    fun addCustomerIncome(incomeDto: IncomeDTO) : Customer {
        val customer = repository.findByIdOrNull(incomeDto.customerId) ?: throw NotFoundException()
        val source = incomeSourceRepo.findByName(incomeDto.source.name)
                ?: incomeSourceRepo.save(IncomeSource(id=0, name= incomeDto.source.name))

        val income = Income()
        income.source = source
        income.monthlyAmount = incomeDto.amount
        income.customer = customer

        val newIncome = incomeRepo.save(income)
        customer.addIncome(newIncome)
        repository.flush()
        return customer
    }
}