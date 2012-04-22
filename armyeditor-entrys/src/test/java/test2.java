import com.armyeditor.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 31.03.12
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
public class test2 {
    @Test
    public void test2() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select unit from Unit unit").setMaxResults(10);
        List<Long> itemlist=query.list();
        ses.close();
    }
}
