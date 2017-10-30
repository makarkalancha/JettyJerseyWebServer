/*
 * Software property of Acquisio. Copyright 2003-2017.
 */
package webapp.pojos;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author Macar Calancea (mcalancea@acquisio.com)
 */
@JsonFilter("userFilter")
@JsonRootName("userMy")
public class User {
    private final Long id;
    private final String name;
    private final String title;

    public User(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
