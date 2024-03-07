package com.samin.generator.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mail_properties")
public class MailProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String host;

    private Integer port;

    private String encoding;

    private String userName;

    private String password;
}
