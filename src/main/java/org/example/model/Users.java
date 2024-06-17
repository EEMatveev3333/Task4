package org.example.model;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@NamedQuery( name ="findbyNameUser",
//        query ="select u  from Users u  where u.nameuser=:nameuser"
//)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nameuser")
    private String nameuser;

    @Column(name = "fio")
    private String fio;

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getId() {     return id;
    }
}
