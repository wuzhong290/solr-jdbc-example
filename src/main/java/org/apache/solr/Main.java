package org.apache.solr;

import java.sql.*;

/**
 * Created by wuzhong on 2016/7/19.
 */
public class Main {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:solr://localhost:9984/?collection=gettingstarted&aggregationMode=map_reduce&numWorkers=2");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select  id,title from gettingstarted");

            while(rs.next()) {
                String a_s = rs.getString("id");
                Object s = rs.getObject("title");
                System.out.println("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
