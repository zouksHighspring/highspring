package com.example.library.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Reader extends Person {
    private List<Loan> loans = new ArrayList<>();
}
