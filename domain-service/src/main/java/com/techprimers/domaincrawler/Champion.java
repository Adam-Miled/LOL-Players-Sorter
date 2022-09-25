package com.techprimers.domaincrawler;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Champion implements Serializable {
    private String summonerName;
    private Boolean hotStreak;
}
