package com.springauth.SpringAuth.SecretKeyRequests;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "secretkeyrequests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretKeyRequests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer projectId;
    private Integer userId; // Foreign key to user
}
