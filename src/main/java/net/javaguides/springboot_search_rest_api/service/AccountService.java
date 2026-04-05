package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount (final AccountDto accountDto);

    AccountDto getAccountById (final Long id);

    AccountDto deposit(final Long id, final Double amount);

    AccountDto withdram (final Long id, final Double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccountById(final Long id);

}
