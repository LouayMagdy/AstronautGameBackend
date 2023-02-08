package com.example.astronautgamebackend.UserAndRegisteration.ModelDB.model;

import com.example.astronautgamebackend.UserAndRegisteration.ModelDB.springSec.Role;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Slf4j
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class NormalUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iD;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(length = 100)
    private String fullName;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(unique = true, length = 50)
    private String userName;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(length = 50)
    private String password;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private boolean gender; /// true: male, false: female

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user")
    private RankingUser rankingUser;

    public NormalUser(@NonNull String fullName, @NonNull String userName, @NonNull String password, @NonNull boolean gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.role = Role.User;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return true;
    }
}
