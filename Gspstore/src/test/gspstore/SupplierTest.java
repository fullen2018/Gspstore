package test.gspstore;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import business.entity.supplier.SupplierEntity;
import business.service.supplier.SupplierServiceI;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.util.SpringContextUtil;
import com.jeecg.util.excel.ExcelExportUtil;


public class SupplierTest extends SpringTestBase
{
    @Autowired
    //SQL 使用JdbcDao
    private JdbcDao jdbcDao;
    @Autowired
    private SupplierServiceI supplierService;
    @Autowired
    private BaseDaoI<SupplierEntity> supplierEntityDao;
    @Test
    public void testGetAllSupplier(){
        List<SupplierEntity> se = supplierEntityDao.find("from SupplierEntity ");
        System.out.println("个数："+se.size());
        try
        {
            FileOutputStream fos = new FileOutputStream(new File("c:\\供应商导出.xls"));
            ExcelExportUtil.exportExcel("供应商导出", SupplierEntity.class, se,fos);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void testsupplierEntityDao(){
        SessionFactory sessionFactory = SpringContextUtil.getBean("sessionFactory");  
        Session ss = sessionFactory.openSession();
        //ss.beginTransaction();
        String hql = "select new Map(LENGTH(se.stockinbillno) as max) from StockinEntity as se";
        Query q = ss.createQuery(hql);
        //System.out.println("最大ID:"+q.uniqueResult());
    }
}
