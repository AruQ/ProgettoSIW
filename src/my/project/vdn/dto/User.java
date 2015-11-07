package my.project.vdn.dto;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Date creationDate;

    private int id;

    private String password;

    private double score;

    private String username;

    public User()
    {
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public int getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public double getScore()
    {
        return score;
    }

    public String getUsername()
    {
        return username;
    }

    public void setCreationDate(final Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public void setScore(final double score)
    {
        this.score = score;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "User [creationDate=" + creationDate + ", id=" + id + ", password=" + password + ", score=" + score
                + ", username=" + username + "]";
    }
}
