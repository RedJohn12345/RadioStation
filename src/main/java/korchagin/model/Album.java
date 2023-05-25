package korchagin.model;
import korchagin.dao.IdentityInterface;
import korchagin.model.user_request.UserRequestComponent;
import korchagin.utils.Utils;


public class Album implements UserRequestComponent, IdentityInterface<Long> {
    private String name;
    private Integer year;
    private Long identity;

    public Album(String name, Integer year) {
        this.name = name;
        setYear(year);
    }

    public Album() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        if (year >= 0 && year <= Utils.getCurrentYear()) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("value out of range");
        }

    }

    @Override
    public Long getIdentity() {
        return identity;
    }

    @Override
    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
