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

 public class Relac_termo_provocacao
 {
   private int termo;
   private int provocacao;
   private String nomePesq;

   public int getTermo() {
     return this.termo;
   }

   public void setTermo(int termo) {
     this.termo = termo;
   }

   public int getProvocacao() {
     return this.provocacao;
   }

   public void setProvocacao(int provocacao) {
     this.provocacao = provocacao;
   }

   public String getNomePesq() {
     return this.nomePesq;
   }

   public void setNomePesq(String nomePesq) {
     if (nomePesq.equals("all")) {
       this.nomePesq = "%";
     } else {
       this.nomePesq = nomePesq;
     } 
   }

   public List<Map<String, Object>> allTermos() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listTermos = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select termo, provocacao  from app_hist.relac_termo_provocacao  where termo = ? ";

       ps = c.prepareStatement(q);
       ps.setInt(1, getTermo());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listTermos.add(Cap);
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
     return listTermos;
   }

   public List<Map<String, Object>> allProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select term.pk_termo id, term.nome_termo,        COALESCE ((select cap.nome_capitania                     from app_hist.capitania cap                    where cap.pk_capitania  = term.capitania),'Sem Disignação') capitania   from app_hist.termo term, app_hist.relac_termo_provocacao rtp  where lower(term.nome_termo) like '%'|| lower(?) || '%'    and term.pk_termo = rtp.termo   and rtp.provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getProvocacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvocacao.add(Cap);
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
     return listProvocacao;
   }

   public List<Map<String, Object>> notInProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     List<Map<String, Object>> listProvocacao = new ArrayList<>();
     try {
       c = Utils.getConnectionStock();
       String q = " select term.pk_termo id, term.nome_termo,        COALESCE ((select cap.nome_capitania                     from app_hist.capitania cap                    where cap.pk_capitania  = term.capitania),'Sem Disignação') capitania   from app_hist.termo term  where lower(term.nome_termo) like '%'|| lower(?) || '%'   and term.pk_termo not in (select rtp.termo                                from app_hist.relac_termo_provocacao rtp                                where rtp.provocacao = ? )";

       ps = c.prepareStatement(q);
       ps.setString(1, getNomePesq());
       ps.setInt(2, getProvocacao());
       rs = ps.executeQuery();
       ResultSetMetaData rsmd = rs.getMetaData();
       int columnCount = rsmd.getColumnCount();

       while (rs.next()) {
         Map<String, Object> Cap = new LinkedHashMap<>();
         for (int i = 1; i <= columnCount; i++) {
           String name = rsmd.getColumnName(i);
           Cap.put(name, rs.getString(name));
         } 
         listProvocacao.add(Cap);
       } 
       rs = ps.executeQuery();
     } catch (Exception ex) {
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
     return listProvocacao;
   }

   public String create() throws Exception {
     long toRet = 0L;
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = " INSERT INTO app_hist.relac_termo_provocacao   (termo, provocacao)   VALUES( ?, ?) ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo));
       ps.setObject(2, Integer.valueOf(this.provocacao));
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
     return "Inserido com sucesso.";
   }

   public String updateTermo() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_termo_provocacao SET  termo = ?  where provocacao = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo));
       ps.setObject(2, Integer.valueOf(this.provocacao));
       ps.executeUpdate();

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
     return "O ID: " + getProvocacao() + " foi atualizado com sucesso.";
   }

   public String updateProvocacao() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "UPDATE app_hist.relac_termo_provocacao SET  provocacao = ?  where termo = ?";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.provocacao));
       ps.setObject(2, Integer.valueOf(this.termo));
       ps.executeUpdate();

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
     return "O ID: " + getTermo() + " foi atualizado com sucesso.";
   }

   public String delete() throws Exception {
     Connection c = null;
     PreparedStatement ps = null;
     ResultSet rs = null;

     try {
       c = Utils.getConnectionStock();
       c.setAutoCommit(false);
       String q = "DELETE FROM app_hist.relac_termo_provocacao  WHERE termo = ? and provocacao = ? ";

       ps = c.prepareStatement(q);
       ps.setObject(1, Integer.valueOf(this.termo));
       ps.setObject(2, Integer.valueOf(this.provocacao));

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
     return "Eliminado com sucesso.";
   }
 }