package com.ksoussane.accountservice.web;

import com.ksoussane.accountservice.clients.CustomerRestClient;
import com.ksoussane.accountservice.entities.BankAccount;
import com.ksoussane.accountservice.model.Customer;
import com.ksoussane.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountController {
    private BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    public BankAccountController(BankAccountRepository accountRepository,
                                 CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList = accountRepository.findAll();
        accountList.forEach(acc->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }
    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount= accountRepository.findById(Long.valueOf(id)).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
