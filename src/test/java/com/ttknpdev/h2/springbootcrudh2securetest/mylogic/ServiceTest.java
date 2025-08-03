package com.ttknpdev.h2.springbootcrudh2securetest.mylogic;

import com.ttknpdev.h2.springbootcrudh2securetest.access_repository.UserDTO;
import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import com.ttknpdev.h2.springbootcrudh2securetest.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.*;


// Test JPA Hibernate (Should run server)
@DataJpaTest(properties = {"spring.datasource.url=jdbc:h2:mem:test_database"})
@Rollback(value = false)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTest {

    private UserDTO userDTO;

    @Autowired
    private void setAccessRepository(UserRepository repository) {
        userDTO = new UserDTO(repository);
    }

    @Test
    public void createNewUser() {
        UserDetails u1 = new UserDetails(1L,"Mark Ryder" , 70.77F,152.55F,"Bangkok");
        assertNotNull(userDTO.create(u1));
    }

    @Test
    public void reads() {
        List<UserDetails> userDetailsList = userDTO.reads();
        assertNotNull(userDetailsList);
    }
    @Test
    public void update() {
        // Why i have to create first
        // Because it test as internal method when method done all data will clear !!!
        UserDetails user = new UserDetails(1L,"Mark Ryder" , 70.77F,152.55F,"Bangkok");
        assertNotNull(userDTO.create(user));
        user.setFullname("JJ Green");
        assertEquals("JJ Green", userDTO.update(user).get("updated").getFullname());
    }
    @Test
    public void delete() {
        UserDetails user = new UserDetails(1L,"Mark Ryder" , 70.77F,152.55F,"Bangkok");
        assertNotNull(userDTO.create(user));
        assertEquals("Mark Ryder", userDTO.delete(1L).get("deleted").getFullname());
    }
}
