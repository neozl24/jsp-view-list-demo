package dao;

import entity.Items;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zhongli on 2017/1/24.
 */
public class ItemsDAO {

    public ArrayList<Items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<>();  //商品集合

        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT * FROM items;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setPrice(rs.getInt("price"));
                item.setNumber(rs.getInt("number"));
                item.setPicture(rs.getString("picture"));
                list.add(item);
            }

            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        } finally {

            if (rs != null) {
                try {
                    System.out.println("释放数据集对象");
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    System.out.println("释放语句对象");
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Items getItemById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT * FROM items WHERE id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setPrice(rs.getInt("price"));
                item.setNumber(rs.getInt("number"));
                item.setPicture(rs.getString("picture"));
                return item;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        } finally {

            if (rs != null) {
                try {
                    System.out.println("释放数据集对象2");
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    System.out.println("释放语句对象2");
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取最近浏览的五条商品信息
    public ArrayList<Items> getViewList(String list) {
        ArrayList<Items> itemList = new ArrayList<>();
        int iCount = 5;

        if(list != null && list.length() > 0) {
            String[] arr = list.split(",");

            if (arr.length >= iCount) {
                for (int i = arr.length - 1; i >= arr.length - iCount; i--) {
                    itemList.add(getItemById(Integer.parseInt(arr[i])));
                }
            } else {
                for (int i = arr.length - 1; i >= 0; i--) {
                    itemList.add(getItemById(Integer.parseInt(arr[i])));
                }
            }
            return itemList;

        } else {
            return null;
        }
    }

}
