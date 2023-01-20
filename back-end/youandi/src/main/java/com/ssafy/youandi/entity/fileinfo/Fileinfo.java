package com.ssafy.youandi.entity.fileinfo;

import com.ssafy.youandi.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name = "fileinfo")
public class Fileinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileid")
    private int fileId;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @Column(name = "save_folder")
    private String saveFolder;

    @Column(name = "original_file")
    private String originalFile;

    @Column(name = "save_file")
    private String saveFile;

}
