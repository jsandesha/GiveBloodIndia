package com.highpeak.gbi.datastore.model;

import javax.persistence.*;

/**
 * @author sandesha, Created on 21/08/17
 */
@Entity
@Table( name = "gbi_user_to_address" )
public class UserToAddress {

    private Long id;

    private UserModel user;

    private AddressModel address;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "uta_id" )
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "uta_frn_user_id", referencedColumnName = "u_id" )
    public UserModel getUser()
    {
        return user;
    }

    public void setUser( UserModel user )
    {
        this.user = user;
    }

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "uta_frn_add_id", referencedColumnName = "ra_id" )
    public AddressModel getAddress()
    {
        return address;
    }

    public void setAddress( AddressModel address )
    {
        this.address = address;
    }
}
