package com.vartmp7.stalker.gsonbeans;

import android.os.Parcel;
import android.os.Parcelable;

import com.unboundid.ldap.sdk.persist.LDAPGetter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class Organizzazione implements Serializable {
    public static final String TAG = "com.vartmp7.stalker.gsonbeans.Organizzazione";


    private String address;
    private String city;
    private String email;
    private long id;
    private String name;
    private String nation;
    private String phone_number;
    private String postal_code;
    private String region;
    private String type;
    private String ldap_common_name;
    private String ldap_domain_component;
    private String ldap_port;
    private Boolean isPreferito = false;
    private String image_url;
    private boolean isTracking;
    private boolean isExpanded, isLogged, isAnonimo;

    public boolean isTracking() {
        return isTracking;
    }

    public Organizzazione setTracking(boolean tracking) {
        isTracking = tracking;
        return this;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public Organizzazione setExpanded(boolean expanded) {
        isExpanded = expanded;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public Organizzazione setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }

    public boolean isAnonimo() {
        return isAnonimo;
    }

    public Organizzazione setAnonimo(boolean anonimo) {
        isAnonimo = anonimo;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public Organizzazione setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    private List<? extends AbstractLuogo> luoghi;

    public String getOrgInfo(){
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Organizzazione setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Organizzazione setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Organizzazione setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getId() {
        return id;
    }

    public Organizzazione setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Organizzazione setName(String name) {
        this.name = name;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public Organizzazione setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Organizzazione setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public Organizzazione setPostal_code(String postal_code) {
        this.postal_code = postal_code;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Organizzazione setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getType() {
        return type;
    }

    public Organizzazione setType(String type) {
        this.type = type;
        return this;
    }

    public String getLdap_common_name() {
        return ldap_common_name;
    }

    public Organizzazione setLdap_common_name(String ldap_common_name) {
        this.ldap_common_name = ldap_common_name;
        return this;
    }

    public String getLdap_domain_component() {
        return ldap_domain_component;
    }

    public Organizzazione setLdap_domain_component(String ldap_domain_component) {
        this.ldap_domain_component = ldap_domain_component;
        return this;
    }

    public String getLdap_port() {
        return ldap_port;
    }

    public Organizzazione setLdap_port(String ldap_port) {
        this.ldap_port = ldap_port;
        return this;
    }

    public Boolean getPreferito() {
        return isPreferito;
    }

    public Organizzazione setPreferito(Boolean preferito) {
        isPreferito = preferito;
        return this;
    }

    public List<? extends AbstractLuogo> getLuoghi() {
        return luoghi;
    }

    public Organizzazione setLuoghi(List<? extends AbstractLuogo> luoghi) {
        this.luoghi = luoghi;
        return this;
    }

    public Organizzazione(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organizzazione)) return false;
        Organizzazione that = (Organizzazione) o;
        return getId() == that.getId() &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getNation(), that.getNation()) &&
                Objects.equals(getPhone_number(), that.getPhone_number()) &&
                Objects.equals(getPostal_code(), that.getPostal_code()) &&
                Objects.equals(getRegion(), that.getRegion()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getLdap_common_name(), that.getLdap_common_name()) &&
                Objects.equals(getLdap_domain_component(), that.getLdap_domain_component()) &&
                Objects.equals(getLdap_port(), that.getLdap_port()) &&
                Objects.equals(isPreferito, that.isPreferito) &&
                Objects.equals(getLuoghi(), that.getLuoghi());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getCity(), getEmail(), getId(), getName(), getNation(), getPhone_number(), getPostal_code(), getRegion(), getType(), getLdap_common_name(), getLdap_domain_component(), getLdap_port(), isPreferito, getLuoghi());
    }

    @Override
    public String toString() {
        return "Organizzazione{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", region='" + region + '\'' +
                ", type='" + type + '\'' +
                ", ldap_common_name='" + ldap_common_name + '\'' +
                ", ldap_domain_component='" + ldap_domain_component + '\'' +
                ", ldap_port='" + ldap_port + '\'' +
                ", isPreferito=" + isPreferito +
                ", luoghi=" + luoghi +
                '}';
    }


}
