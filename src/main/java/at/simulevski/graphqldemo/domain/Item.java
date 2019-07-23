package at.simulevski.graphqldemo.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Data
public class Item {
    private String id;
    private String name;
    private String description;
}
