package com.ttknpdev.h2.springbootcrudh2securetest.mylogic;

import com.ttknpdev.h2.springbootcrudh2securetest.access_repository.AccessRepositories;
import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import com.ttknpdev.h2.springbootcrudh2securetest.repository.UserRepositories;
import com.ttknpdev.h2.springbootcrudh2securetest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@DataJpaTest
@Rollback(value = false)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyLogic {

    @Autowired
    private UserRepositories repositories;

    private AccessRepositories accessRepositories;
    @Autowired
    private void setAccessRepositories() {
        accessRepositories= new AccessRepositories(repositories);
    }

    @Test
    public void createNewUser() {
        UserDetails u1 = new UserDetails("Mark Ryder" , 70.77F,152.55F,"Bangkok");
        u1.setId(112L);
        assertNotNull(accessRepositories.create(u1));
    }
    @Test
    public void reads() {
        List<UserDetails> userDetailsList = accessRepositories.reads();
        assertNotNull(userDetailsList);
        // assertNot() is null no logging if not it'll log to console
    }
    @Test
    public void update() {
        UserDetails found = accessRepositories.read(112L);
        assertNotNull(found);
        found.setFullname("JJ Green");
        assertEquals("JJ Green", accessRepositories.update(found).get("updated").getFullname());
    }
    @Test
    public void delete() {
        accessRepositories.delete(109L);
        assertNull(accessRepositories.read(109L).getId()); /* expect null */
    }
}
