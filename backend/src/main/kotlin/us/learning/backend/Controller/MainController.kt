package us.learning.backend.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import us.learning.backend.Services.BussinesService

@RestController
@RequestMapping(value = ["/api/customers"])
class MainController(private val customerService: BussinesService) {
    @GetMapping
    fun list() = customerService.findAllCustomers()
}