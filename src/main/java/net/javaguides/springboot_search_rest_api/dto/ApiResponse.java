package net.javaguides.springboot_search_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    public String message;
    public T data;
    public Integer status;
}
