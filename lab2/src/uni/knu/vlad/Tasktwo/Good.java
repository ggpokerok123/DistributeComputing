package uni.knu.vlad.Tasktwo;

public class Good {
    private int id;

    public Good(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Good {" +
                "id=" + id +
                '}';
    }
}