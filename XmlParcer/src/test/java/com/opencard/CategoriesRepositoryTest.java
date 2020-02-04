package com.opencard;

import com.opencard.DbConnection.JpaRepository;
import com.opencard.DbConnection.TableCategories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriesRepositoryTest {
    @Autowired
    private JpaRepository jpaRepository;
    @Before
    public void setUp() throws Exception{
        TableCategories categories = new TableCategories(2229,0, new Date(), new Date());
        assertNull(categories.getCategory_id());
        this.jpaRepository.save(categories);
    }
    @Test
    public void testFetchData(){
        TableCategories categories = jpaRepository.findById(2229);
        assertNull(categories);

        Iterable<TableCategories> categories1 = jpaRepository.findAll();
        int count = 0;
        for(TableCategories p: categories1){
            count++;
        }
        assertEquals(count, 1);
    }
}
