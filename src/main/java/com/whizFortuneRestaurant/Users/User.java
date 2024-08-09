package com.whizFortuneRestaurant.Users;

import com.fasterxml.jackson.annotation.*;
import com.whizFortuneRestaurant.Address.Address;
import com.whizFortuneRestaurant.Cart.Cart;
import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.WishList.WishList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userid")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(unique = true)
    private String username;

    private String password;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getAnniversarydate() {
        return anniversarydate;
    }

    public void setAnniversarydate(LocalDate anniversarydate) {
        this.anniversarydate = anniversarydate;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFcmtoken() {
        return fcmtoken;
    }

    public void setFcmtoken(String fcmtoken) {
        this.fcmtoken = fcmtoken;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<WishList> getWishListSet() {
        return wishListSet;
    }

    public void setWishListSet(Set<WishList> wishListSet) {
        this.wishListSet = wishListSet;
    }

    public LocalDateTime getDt1() {
        return dt1;
    }

    public void setDt1(LocalDateTime dt1) {
        this.dt1 = dt1;
    }

    @Column(unique = true)
    private long mobile;

    @Column(name = "email_id", unique = true)
    private String emailId;

    private LocalDate dob;

    private String gender;

    private LocalDate anniversarydate;

    private String devicetype;

    private int status;

    private String fcmtoken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-carts")
    List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-favorites")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private Set<WishList> wishListSet;

    @JsonIgnore
    private LocalDateTime dt1;

    @PrePersist
    public void prePersist() {
        this.dt1 = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.emailId;
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
