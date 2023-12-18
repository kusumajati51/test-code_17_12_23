package com.example.test_code.repository;

import com.example.test_code.model.MurderedVictimsModel;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MurderedVictimsRepositoryJPATest {


    @Autowired
    MurderedVictimsRepositoryJPA murderedVictimsRepositoryJPA;

    @Test
    public void testSave(){
        murderedVictimsRepositoryJPA.deleteAll();
        MurderedVictimsModel model = new MurderedVictimsModel();
        model.setName("Jhon Mayer");
        model.setMurderedYear(12);
        model.setDeathAge(10);
        model.setBirthYear(model.getMurderedYear() - model.getDeathAge());
        murderedVictimsRepositoryJPA.save(model);

        Long savedBookID = model.getId();

        MurderedVictimsModel book = murderedVictimsRepositoryJPA.findById(savedBookID).orElseThrow();
        assertEquals(savedBookID, book.getId());
        assertEquals("Jhon Mayer", book.getName());
    }

    @Test
    public void testFindBMurderedYear() {
        List<MurderedVictimsModel> result = murderedVictimsRepositoryJPA.findByMurderedYear(12);
//        assertEquals(1, result.size());
        MurderedVictimsModel book = result.get(0);
        assertNotNull(book.getId());
        assertTrue(book.getId() > 0);
        assertEquals("Jhon Mayer", book.getName());
        assertEquals(10, book.getDeathAge());

    }

    @Test
    public void testFindDeathAge() {
        List<MurderedVictimsModel> result = murderedVictimsRepositoryJPA.findByDeathAge(10);
//        assertEquals(1, result.size());
        MurderedVictimsModel book = result.get(0);
        assertNotNull(book.getId());
        assertTrue(book.getId() > 0);
        assertEquals("Jhon Mayer", book.getName());
        assertEquals(12, book.getMurderedYear());
    }

    @Test
    public void testFindBirthYear() {
        List<MurderedVictimsModel> result = murderedVictimsRepositoryJPA.findByBirthAge(12, 10);
//        assertEquals(1, result.size());
        MurderedVictimsModel book = result.get(0);
        assertNotNull(book.getId());
        assertTrue(book.getId() > 0);
        assertEquals("Jhon Mayer", book.getName());
        assertEquals(10, book.getDeathAge());
        assertEquals(12, book.getMurderedYear());
        assertEquals(2, book.getBirthYear());
    }
//    @Test
//    public void clearAllTest() {
//        murderedVictimsRepositoryJPA.deleteAll();
//    }

}
