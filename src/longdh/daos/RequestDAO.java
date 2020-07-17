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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import longdh.DBUtils.Utils;
import longdh.dtos.ReplyDTO;
import longdh.dtos.RequestDTO;

/**
 *
 * @author donglong
 */
public class RequestDAO implements Serializable {

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

    public RequestDTO getRequesByID(String ID) throws Exception {
        RequestDTO result = null;
        try {
            String sql = "Select ReqID, UserID, RoomID, DeviceID, Date, Message,Status From Request where ReqID = ? and Status ='Fixed' or Status ='Doing'";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String id = rs.getString("ReqID");
                String message = rs.getString("Message");
                Date time = rs.getDate("Date");
                String deviceID = rs.getString("DeviceID");
                String roomID = rs.getString("RoomID");
                String status = rs.getString("Status");
                result = new RequestDTO(id, message, userID, deviceID, roomID, time, status);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<RequestDTO> getAllRequest() throws Exception {
        List<RequestDTO> result = new ArrayList<>();
        try {
            String sql = "Select ReqID, UserID, RoomID, DeviceID, Date, Message,Status From Request where Status ='notFix'";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String id = rs.getString("ReqID");
                String message = rs.getString("Message");
                Date time = rs.getDate("Date");
                String deviceID = rs.getString("DeviceID");
                String roomID = rs.getString("RoomID");
                String status = rs.getString("Status");
                RequestDTO dto = new RequestDTO(id, message, userID, deviceID, roomID, time, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<RequestDTO> getAllRequestFix() throws Exception {
        List<RequestDTO> result = new ArrayList<>();
        try {
            String sql = "Select ReqID, UserID, RoomID, DeviceID, Date, Message,Status From Request where Status ='Fixed' or Status ='Doing'";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String id = rs.getString("ReqID");
                String message = rs.getString("Message");
                Date time = rs.getDate("Date");
                String deviceID = rs.getString("DeviceID");
                String roomID = rs.getString("RoomID");
                String status = rs.getString("Status");
                RequestDTO dto = new RequestDTO(id, message, userID, deviceID, roomID, time, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public RequestDTO getRequestByID(String reqID) throws Exception {
        RequestDTO result = null;
        try {
            String sql = "Select ReqID, UserID, RoomID, DeviceID, Date, Message FROM Request WHERE ReqID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, reqID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String message = rs.getString("Message");
                Date time = rs.getDate("Date");
                String deviceID = rs.getString("DeviceID");
                String roomID = rs.getString("RoomID");
                result = new RequestDTO(reqID, message, userID, deviceID, roomID, time);

            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ReplyDTO getReplyByID(String reqID) throws Exception {
        ReplyDTO result = null;
        try {
            String sql = "Select ReplyID, StaffID, RoomID, DeviceID, ReplyDate, ReplyMessage FROM Request WHERE ReqID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, reqID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("UserID");
                String message = rs.getString("Message");
                Date time = rs.getDate("Date");
                String deviceID = rs.getString("DeviceID");
                String roomID = rs.getString("RoomID");
                result = new ReplyDTO(reqID, message, userID, deviceID, roomID, time);

            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertRequest(RequestDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Insert Into Request(ReqID, Message, UserID, Date, DeviceID, RoomID, Status) "
                    + "values(?,?,?,?,?,?,?)";
            conn = Utils.openConnect();

            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getId());
            stm.setString(2, dto.getMessage());
            stm.setString(3, dto.getUserID());
            stm.setDate(4, (Date) dto.getDate());
            stm.setString(5, dto.getDeviceID());
            stm.setString(6, dto.getRoomID());
            stm.setString(7, dto.getStatus());

            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateRequest(RequestDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Update Request set Date=?, Message=? Where ID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setDate(1, (Date) dto.getDate());
            stm.setString(2, dto.getMessage());
            stm.setString(3, dto.getId());
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean setStatusByID(String reqid) throws Exception {
        boolean result = false;
        try {
            String sql = "Update Request set Status=? Where ReqID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "Fixed");
            stm.setString(2, reqid);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
