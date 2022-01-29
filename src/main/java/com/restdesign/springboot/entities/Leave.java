package com.restdesign.springboot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = Leave.FIND_BY_ID, query = "SELECT leave FROM Leave leave WHERE leave.identity=:identity")
})
public class Leave implements Serializable{
	public static final String FIND_BY_ID = "Leave.FIND_BY_ID";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private long identity;
	private long leaveUsed;
	private long totalLeaveUsed;
}
