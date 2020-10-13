package ru.stqa.pft.addressbook.model;


import com.google.common.collect.ForwardingSet;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("AddressInGroups")
@Entity
@Table(name= "address_in_groups")
public class AddressInGroupsData  {
  @XStreamOmitField
//  @Transient
  private int domain_ID;
  @Id
  @Column(name = "id",unique=true)
//  @Type(type = "int")
  private int id;

//  @XStreamOmitField
//  @ForeignKey(name="group_Id")
//  @Column(name="group_Id")
//  @ForeignKey(name="group_Id")


  @Column(name = "group_id",unique=true)
//  @Type(type = "int")
  private int groupId;

  public int getId() {
    return id;
  }

  public int getGroupId() {
    return groupId;
  }


  @Override
  public String toString() {
    return "AddressInGroupsData{" +
            "id=" + id +
            ", groupId=" + groupId +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressInGroupsData that = (AddressInGroupsData) o;
    return id == that.id &&
            groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupId);
  }
}
