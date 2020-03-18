package com.vartmp7.stalker.gsonbeans;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class Organizzazione {
    public static final String TAG ="com.vartmp7.stalker.gsonbeans.Organizzazione";
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
    private Boolean isPreferito=false;
    private List<? extends AbstractLuogo> luoghi;

    public String getOrgInfo() {
        return getName() + " presso: " + getAddress();
    }

    public Boolean getPreferito() {
        return isPreferito;
    }

    public Organizzazione setPreferito(Boolean preferito) {
        isPreferito = preferito;
        return this;
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


    public List<? extends AbstractLuogo> getLuoghi() {
        return luoghi;
    }

    public Organizzazione setLuoghi(List<? extends AbstractLuogo> luoghi) {
        this.luoghi = luoghi;
        return this;
    }
}
