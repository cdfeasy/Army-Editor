package army.server;

import army.client.services.UnitService;
import com.armyeditor.HibernateUtil;
import com.armyeditor.entrys.Unit;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 31.03.12
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class BaseService extends RemoteServiceServlet implements UnitService {
    @Override
    public List<Unit> getUnits() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select unit from Unit unit").setMaxResults(10);
        List<Unit> itemlist=query.list();
        ses.close();
        return itemlist;
    }
}
