package br.com.jrr.apiTest.domain.user.DTO;

import br.com.jrr.apiTest.domain.user.Entity.User;

public class LeaderDTO {
        private String id;
        private String userName;
        private String fullName;
        private String image;

        // Construtores, getters e setters
        public LeaderDTO(String id, String userName, String fullName, String image) {
                this.id = id;
                this.userName = userName;
                this.fullName = fullName;
                this.image = image;
        }

        // Torne o método estático
        public static LeaderDTO mapUserToLeaderDTO(User leader) {
                return new LeaderDTO(
                        leader.getId(),
                        leader.getUsername(),
                        leader.getFullName(),
                        leader.getImage()
                );
        }

        // Getters e setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
}
