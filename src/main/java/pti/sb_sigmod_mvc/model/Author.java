package pti.sb_sigmod_mvc.model;

public class Author {
    private Integer id;
    private String name;
    private Integer counter;

    public Author(String name, Integer counter) {
        this.name = name;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
