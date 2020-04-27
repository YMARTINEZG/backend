package us.learning.backend.web.customer

import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.integration.support.MessageBuilder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import us.learning.backend.domain.Customer
import us.learning.backend.service.BussinesService
import us.learning.backend.stream.CustomProcessor
import us.learning.backend.stream.NotesMessageListener
import javax.transaction.Transactional

@RestController
@RequestMapping(value = ["/api/customer"])
@Transactional
class CustomerController(private val customerService: BussinesService) {
    companion object {
        private val logger = LogManager.getLogger()
    }
    @Autowired
    lateinit var customProcessor : CustomProcessor

    @GetMapping("/{state}")
    fun get(@PathVariable("state") query: String): List<CustomerDTO> {
        val customers: List<Customer> = customerService.searchByStateOfAddress(query)
        return customers.map(::CustomerDTO)
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Validated @RequestBody command: CustomerCommandDTO): CustomerDTO {
        val customer = Customer()
        transformCommandToCustomer(command, customer)
        // transform using constructor
        return CustomerDTO(customerService.saveCustomer(customer))
        // transform usinf kotlin reflection
        //return customerService.saveCustomer(customer).transformToCustomerDTO()
    }

    @PostMapping("/income")
    fun publishMessageToIncome(@Validated @RequestBody customer: CustomerIdentityDTO) {
        customProcessor.income().send(MessageBuilder.withPayload(customer).setHeader("x-correlativeId", "252626262").build())
        logger.info(customer.toString())
    }
    private fun transformCommandToCustomer(command: CustomerCommandDTO, customer: Customer) {
        with(customer) {
            firstName = command.firstName
            lastName = command.lastName
            handleAddress(this, command)
            customer.email = if (command.streetName != null) command.email?.takeIf { it.isNotBlank() } else null
        }
    }
    fun Customer.transformToCustomerDTO() = CustomerDTO(
            identity = CustomerIdentityDTO(this),
            address = "$address",
            email = "$email"
    )
    private fun handleAddress(customer: Customer, command: CustomerCommandDTO) {
        val builder = StringBuilder()
        if (command.streetName != null) {
            builder.append(command.streetName)
                    .append(",")
                    .append(command.cityName)
                    .append(",")
                    .append(command.stateName)
                    .append(" - ")
                    .append(command.zipCode)
            customer.address = builder.toString()
        }
    }
}