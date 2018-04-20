package se.popcorn_time.base.model.video.info;

public class Person {

    public static class CrewMember {
        //real name
        String name;

        //maybe null
        //https://image.tmdb.org/t/p/ + width + profilePic
        String profilePic;

        @Override
        public String toString() {
            return "CrewMember {\n"+"name: "+name+"\nprofilePic: "+profilePic+"\ndepartment: "+department+"\njob: "+job+"\n}";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        //only for crew
        String department;
        String job;
    }

    public static class Actor {
        //only for cast
        String character;

        @Override
        public String toString() {
            return "Actor {\n"+"name: "+name+"\nprofilePic: "+profilePic+"\ncharacter: "+character+"\n}";
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        //real name
        String name;

        //maybe null
        //https://image.tmdb.org/t/p/ + width + profilePic
        String profilePic;
    }
}
