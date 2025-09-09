package com.example.project200.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResDto <T>{
    private List<T> items;
    private boolean hasNextPage;
    private Integer nextPage;
    private Integer previousPage;
    private long totalItems;
}
