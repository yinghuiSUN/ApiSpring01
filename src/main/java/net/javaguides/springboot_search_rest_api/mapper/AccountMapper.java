package net.javaguides.springboot_search_rest_api.mapper;

import net.javaguides.springboot_search_rest_api.dto.AccountDto;
import net.javaguides.springboot_search_rest_api.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccount(Account account) {

        AccountDto dto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return dto;
    }
}
