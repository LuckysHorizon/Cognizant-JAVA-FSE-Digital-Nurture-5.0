package lombokdemo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
}
