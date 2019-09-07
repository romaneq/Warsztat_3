package pl.coderslab.model;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int exerciseId;
    private int usersId;

    public Solution() {
    }

    public Solution(String created, String updated, String description, int exerciseId, int usersId) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exerciseId = exerciseId;
        this.usersId = usersId;
    }

    public Solution(String description, int exerciseId, int usersId) {
        this.description = description;
        this.exerciseId = exerciseId;
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exerciseId=" + exerciseId +
                ", usersId=" + usersId +
                '}';
    }
}
