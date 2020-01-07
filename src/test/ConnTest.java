import com.job.util.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ContextConfiguration("classpath:spring-mvc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnTest {
    @Autowired
    private EmailSender emailSender;

    @Test
    public void getConn() throws Exception{

        emailSender.run();
    }

}
