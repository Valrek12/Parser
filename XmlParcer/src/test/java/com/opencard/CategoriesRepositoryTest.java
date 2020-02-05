package com.opencard;

import com.jcraft.jsch.JSchException;
import com.opencard.DbConnection.JpaRepository;
import com.opencard.DbConnection.TableCategories;
import com.opencard.SshClient.SshCLient;
import com.opencard.Utils.Settings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriesRepositoryTest {
    @Autowired
    private Settings settings = new Settings();
    private SshCLient sshCLient = new SshCLient(settings);
    private JpaRepository jpaRepository;

    public CategoriesRepositoryTest() throws IOException, JSchException {
    }

    @Before
    public void setUp() throws Exception{
        sshCLient.channel.connect();
        TableCategories categories = new TableCategories(2229,0, new Date(), new Date());
        assertNull(categories.getCategory_id());
        this.jpaRepository.save(categories);
    }

    @Test
    public void testFetchData(){
        List<TableCategories> categories = jpaRepository.findById(2229);
        assertNull(categories);

        Iterable<TableCategories> categories1 = jpaRepository.findAll();
        int count = 0;
        for(TableCategories p: categories1){
            count++;
        }
        assertEquals(count, 1);
        sshCLient.channel.disconnect();
    }
}
