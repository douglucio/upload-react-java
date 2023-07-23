package br.com.rnd.uploads.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "create_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    private String filename;
    
    private byte[] data;

    private String type;

    private String music;

    private String artist;

    private String gender;

    @SuppressWarnings("unused")
    private Files() {

    }

    public Files( String filename, byte[] data, String type, String music, String artist, String gender) {
        this.filename = filename;
        this.data = data;
        this.type = type;
        this.music = music;
        this.artist = artist;
        this.gender = gender;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt == null ? getCreatedAt() : this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static class FilesBuilder {
        private String filename;
        private byte[] data;
        private String type;
        private String music;
        private String artist;
        private String gender;

        private FilesBuilder(byte[] data) {
            this.data = data;
        }

        public static FilesBuilder newBuilder(byte[] data) {
            return new FilesBuilder(data);
        }

        public FilesBuilder withFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public FilesBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public FilesBuilder withMusic(String music) {
            this.music = music;
            return this;
        }

        public FilesBuilder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public FilesBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Files build() {
            return new Files(filename, data, type, music, artist, gender);
        }
    }

}