 package pt.ual.dao;

 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import pt.ual.utils.Utils;

 public class Secretario_agregador
 {
   private int secretario_agregador_id;
   private String designacao;
   private int pageNum;
   private int rowsPage;
   private int rowcount;
   private List<Map<String, Object>> allAgregador;

   public Secretario_agregador(int secretario_agregador_id, String designacao, int pageNum, int rowsPage, int rowcount, List<Map<String, Object>> allAgregador) {
     this.secretario_agregador_id = secretario_agregador_id;
     this.designacao = designacao;
     this.pageNum = pageNum;
     this.rowsPage = rowsPage;
     this.rowcount = rowcount;
     this.allAgregador = allAgregador;
   }

   public Secretario_agregador(int secretario_agregador_id, String designacao) {
     this.secretario_agregador_id = secretario_agregador_id;
     this.designacao = designacao;
   }

   public Secretario_agregador() {}

   public int getSecretario_agregador_id() {
     return this.secretario_agregador_id;
   }

   public void setSecretario_agregador_id(int secretario_agregador_id) {
     this.secretario_agregador_id = secretario_agregador_id;
   }

   public String getDesignacao() {
     if ("all".equals(this.designacao)) {
       return "%";
     }
     return this.designacao;
   }

   public void setDesignacao(String designacao) {
     this.designacao = designacao;
   }

   public int getPageNum() {
     return this.pageNum;
   }

   public void setPageNum(int pageNum) {
     this.pageNum = pageNum;
   }

   public int getRowsPage() {
     return this.rowsPage;
   }

   public void setRowsPage(int rowsPage) {
     this.rowsPage = rowsPage;
   }

   public int getRowcount() {
     return this.rowcount;
   }

   public void setRowcount(int rowcount) {
     this.rowcount = rowcount;
   }

   public List<Map<String, Object>> getAllAgregador() {
     return this.allAgregador;
   }

   public void setAllAgregador(List<Map<String, Object>> allAgregador) {
     this.allAgregador = allAgregador;
   }

   public Secretario_agregador allSecretarioAgregador() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listOficioAgregador = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_secretario_agregador, designacao  from app_hist.secretario_agregador  ORDER BY designacao             LIMIT ?             OFFSET ((? - 1) * ?) ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.rowsPage);
       ps.setInt(2, this.pageNum);
       ps.setInt(3, this.rowsPage);
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listOficioAgregador.add(Cap);
       } 
       setAllAgregador(listOficioAgregador);
       rs = ps.executeQuery();

       String qCount = "select count(*)  from app_hist.secretario_agregador ";

       ps = c.prepareStatement(qCount);
       rs = ps.executeQuery();
       rs.next();
       setRowcount(rs.getInt(1));
     } catch (Exception ex) {
       ex.printStackTrace();
       throw ex;
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return this;
   }

   public Secretario_agregador secretario_agregador() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     Secretario_agregador u = new Secretario_agregador();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_secretario_agregador, designacao from app_hist.secretario_agregador where pk_secretario_agregador = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, this.secretario_agregador_id);
       rs = ps.executeQuery();

       while (rs.next()) {
         u.setSecretario_agregador_id(rs.getInt(1));
         u.setDesignacao(rs.getString(2));
       } 
       rs.close();
       ps.close();
     }
     catch (Exception ex) {
       ex.printStackTrace();
       throw ex;
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return u;
   }

   public List<Map<String, Object>> search() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listOficioAgregador = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = "select pk_secretario_agregador, designacao from app_hist.secretario_agregador  where lower(designacao) like '%'||lower(?)||'%' order by designacao ";

       ps = c.prepareStatement(q);
       ps.setString(1, getDesignacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listOficioAgregador.add(Cap);
       } 
       rs = ps.executeQuery();
     } catch (Exception ex) {
       ex.printStackTrace();
       throw ex;
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return listOficioAgregador;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.secretario_agregador   (designacao)   VALUES( ?) ";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
           toRet = rs.getLong(1);
           c.commit();
         }

       } 
     } catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
       return ex.getMessage();
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return "" + toRet;
   }

   public String update() throws Exception {
     long updatedId = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.secretario_agregador SET  designacao = ?  where pk_secretario_agregador = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, this.designacao);
       ps.setObject(2, Integer.valueOf(this.secretario_agregador_id));
       ps.executeUpdate();

       updatedId = this.secretario_agregador_id;
       c.commit();
     }
     catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
       return ex.getMessage();
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return "" + getSecretario_agregador_id();
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.secretario_agregador  WHERE pk_secretario_agregador = ?";

       ps = c.prepareStatement(q, 1);
       ps.setObject(1, Integer.valueOf(this.secretario_agregador_id));

       if (ps.executeUpdate() > 0) {
         rs = ps.getGeneratedKeys();
         if (null != rs && rs.next()) {
           c.commit();
         }
       }

     } catch (SQLException ex) {
       c.rollback();
       ex.printStackTrace();
       return ex.getMessage();
     } finally {
       if (rs != null) {
         rs.close();
       }
       if (ps != null) {
         ps.close();
       }
       if (c != null) {
         c.close();
       }
     } 
     return "" + this.secretario_agregador_id + "";
   }
 }