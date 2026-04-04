package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.AccountDto;
import net.javaguides.springboot_search_rest_api.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto dto = accountService.createAccount(accountDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/{idAccount}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(name = "idAccount") final Long id) {
        AccountDto dto = accountService.getAccountById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{idAccount}/deposit")
    ResponseEntity<AccountDto> deposit(@PathVariable(name = "idAccount") final Long id,
                                       @RequestBody Map<String, Double> map) {
        AccountDto dto = accountService.deposit(id, map.get("amount"));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{idAccount}/withdraw")
    ResponseEntity<AccountDto> withdraw(@PathVariable(name = "idAccount") final Long id,
                                       @RequestBody Map<String, Double> map) {
        AccountDto dto = accountService.withdram(id, map.get("amount"));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity< List<AccountDto>> findAllAccounts() {
        List<AccountDto>  retour = accountService.getAllAccounts();
        return new ResponseEntity<>(retour, HttpStatus.OK);
    }
    @DeleteMapping("{idAccount}")
    ResponseEntity<String> deleteAccountById(@PathVariable(name = "idAccount") final Long id) {
         accountService.deleteAccountById(id);
        return new ResponseEntity<>("Account is deleted ", HttpStatus.OK);
    }



}
