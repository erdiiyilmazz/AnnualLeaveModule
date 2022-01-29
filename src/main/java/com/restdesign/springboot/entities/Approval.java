package com.restdesign.springboot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor
@Setter @Getter 
@Slf4j
public class Approval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String topic;	
	private String dtoApproval;
	
	@PreRemove
	@PreUpdate
	@PrePersist
	private void beforeUpdate() {
		if (id == null) {
			log.info("[APPROVAL] About to add a topic");
		}
		else {
			log.info("[APPROVAL] About to update the topic: " + id + ". " + topic);
		}
	}
	
	@PostRemove
	@PostUpdate
	@PostPersist
	private void afterUpdate() {
		log.info("[APPROVAL TABLE] update to complete for topic: " + id + ". " + topic);
	}
	
	@PostLoad
	private void afterLoad() {
		log.info("[APPROVAL TABLE] topic loaded from database: " + id + ". " + topic);
	}
}
