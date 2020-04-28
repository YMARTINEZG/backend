package us.learning.backend.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import us.learning.backend.service.CustomerService

@RestController
@RequestMapping(value = ["/api/customers"])
class MainController(private val customerService: CustomerService) {
    @GetMapping
    fun list() = customerService.findAllCustomers()
}