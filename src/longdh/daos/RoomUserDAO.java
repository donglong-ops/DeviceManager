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
import java.util.ArrayList;
import java.util.List;
import longdh.DBUtils.Utils;
import longdh.dtos.RoomUserDTO;

/**
 *
 * @author donglong
 */
public class RoomUserDAO implements Serializable {

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

    public List<RoomUserDTO> getAllUserRoom() throws Exception {
        List<RoomUserDTO> result = new ArrayList<>();
        try {
            String sql = "Select UserID, RoomID From User_Room";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                String userid = rs.getString("UserID");
                result.add(new RoomUserDTO(userid, roomID));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertUserRoom(String userid, String roomid) throws Exception {
        boolean result = false;
        try {
            String sql = "Insert into User_Room values (?, ?)";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, userid);
            stm.setString(2, roomid);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public RoomUserDTO getUserByID(String UserID, String roomID) throws Exception {
        RoomUserDTO result = null;
        try {
            String sql = "Select UserID, RoomID From User_Room Where UserID = ? and RoomID = ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, UserID);
            stm.setString(2, roomID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserID");
                String roomId = rs.getString("RoomID");
                result = new RoomUserDTO(userId, roomId);
            }
        }finally {
            closeConnection();
        }
        return result;
    }

}
