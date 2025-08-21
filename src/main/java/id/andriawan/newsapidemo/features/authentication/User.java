package id.andriawan.newsapidemo.features.authentication;

import id.andriawan.newsapidemo.utils.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 200)
    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Size(max = 200)
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 200)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;
}
