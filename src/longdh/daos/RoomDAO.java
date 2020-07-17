/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import longdh.DBUtils.Utils;
import longdh.dtos.RoomDTO;

/**
 *
 * @author donglong
 */
public class RoomDAO implements Serializable {

    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public Vector<RoomDTO> getAllRoomByUserID(String userID) throws Exception {
        Vector<RoomDTO> result = new Vector<>();
        try {
            String sql = "Select RoomID From User_Room Where UserID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                result.add(new RoomDTO(roomID));
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public Vector<RoomDTO> getAllRoom() throws Exception {
        Vector<RoomDTO> result = new Vector<>();
        try {
            String sql = "Select RoomID From Rooms";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                result.add(new RoomDTO(roomID));
            }
        }finally {
            closeConnection();
        }
        return result;
    }

}
