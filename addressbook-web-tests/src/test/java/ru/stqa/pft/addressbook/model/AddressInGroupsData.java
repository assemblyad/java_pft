package ru.stqa.pft.addressbook.model;


import com.google.common.collect.ForwardingSet;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("AddressInGroups")
@Entity
@Table(name= "address_in_groups")
public class AddressInGroupsData  {
  @XStreamOmitField
//  @Transient
  private int domain_ID;
  @Id
  @Column(name = "id")
//  @Type(type = "int")
  private int id;


  @Column(name = "group_id")
//  @Type(type = "int")
  private int groupId;

  public int getId() {
    return id;
  }

  @ManyToMany(fetch= FetchType.EAGER)
  @JoinTable(name="group_list",joinColumns = @JoinColumn(name="id")
          ,inverseJoinColumns =@JoinColumn(name="group_id"))
  private Set<GroupData> contactGroups = new HashSet<GroupData>();




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
}
