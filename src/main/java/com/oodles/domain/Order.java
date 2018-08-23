package com.oodles.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
public class Order {
	private int id;
    private String name;
    private Set<Users> user;
    public Order()
    {
    	
    }
    public Order(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "Order", cascade = CascadeType.ALL)
    public Set<Users> getusers() {
        return user;
    }

    public void setOrders(Set<Users> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Category[id=%d, name='%s']%n",
                id, name);
        if (user != null) {
            for(Users user : user) {
                result += String.format(
                        "Users[id=%d, name='%s']%n",
                        user.getId(), user.getName());
            }
        }

        return result;
    }
}
