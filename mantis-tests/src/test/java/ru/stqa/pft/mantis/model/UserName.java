package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@XStreamAlias("UserName")
@Entity
@Table (name= "mantis_user_table")

public class UserName {

  @Id
  @Column(name = "id")
  public int id;

  @Column(name = "username")
  public String userName;

  @Column(name="email")
  public String email;

  public UserName(){}

  public UserName(String to, int id, String email){
    this.userName=to;
    this.id=id;
    this.email=email;
  }

  public String getUserName() {
    return userName;
  }

  public int getId() {
    return id;
  }

  public String getEmail() { return email; }
}
