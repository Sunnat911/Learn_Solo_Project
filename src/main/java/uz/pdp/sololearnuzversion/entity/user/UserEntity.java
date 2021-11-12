package uz.pdp.sololearnuzversion.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.sololearnuzversion.entity.base.BaseEntity;
import uz.pdp.sololearnuzversion.entity.course.CourseEntity;
import uz.pdp.sololearnuzversion.entity.role.RoleEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(unique = true)
    private String username;
    private String password;
    private short age;
    private String email;
    private boolean active = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role_entity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roleEntityList;

    @ManyToMany
    @JoinTable(
            name = "user_course_entity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courseEntityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleEntityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
