package net.javaguides.springboot_search_rest_api.service.impl;

import net.javaguides.springboot_search_rest_api.dto.AccountDto;
import net.javaguides.springboot_search_rest_api.entity.Account;
import net.javaguides.springboot_search_rest_api.mapper.AccountMapper;
import net.javaguides.springboot_search_rest_api.repository.AccountRepository;
import net.javaguides.springboot_search_rest_api.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(final AccountDto accountDto) {
        final Account accountACreer = AccountMapper.mapToAccount(accountDto);
        final Account result = accountRepository.save(accountACreer);
        return AccountMapper.mapToAccount(result);
    }

    @Override
    public AccountDto getAccountById(final Long id) {
        final Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account does not exist")
        );
        return AccountMapper.mapToAccount(account);
    }

    @Override
    public AccountDto deposit(final Long id, final Double amount) {
        final Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account does not exist")
        );
        final Double a = account.getBalance()+ amount;
        account.setBalance(a);
        final Account result = accountRepository.save(account);
        return AccountMapper.mapToAccount(result);
    }

    @Override
    public AccountDto withdram(final Long id, final Double amount) {
        final Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account does not exist")
        );
        if (account.getBalance()-amount < 0) {
           throw new RuntimeException("the amount is insufficient");
        }
        final Double a = account.getBalance() - amount;
        account.setBalance(a);
        final Account result = accountRepository.save(account);
        return AccountMapper.mapToAccount(result);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> list = accountRepository.findAll().stream().map(
                acc -> AccountMapper.mapToAccount(acc)).
                collect(Collectors.toList());
        return list;


    }

    @Override
    public void deleteAccountById(final Long id) {
        final Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account does not exist")
        );
       accountRepository.deleteById(id);
    }
}
