/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import longdh.DBUtils.Utils;
import longdh.dtos.ReplyDTO;
import longdh.dtos.RequestDTO;

/**
 *
 * @author donglong
 */
public class ReplyDAO {

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

    public boolean insertReply(ReplyDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Insert Into Reply(ReplyID, StaffID, ReplyDate, ReplyMessage, ReqID, Status) "
                    + "values(?,?,?,?,?,?)";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getReplyID());
            stm.setString(2, dto.getStaffID());
            stm.setDate(3, (Date) dto.getReplyDate());
            stm.setString(4, dto.getReplyMsg());
            stm.setString(5, dto.getReqId());
            stm.setString(6, dto.getStatus());

            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ReplyDTO> getAllReply(String requestID) throws Exception {
        List<ReplyDTO> result = new ArrayList<>();
        try {
            String sql = "Select ReplyID, StaffID, ReplyDate, ReplyMessage,  ReqID, Status From Reply where ReqID = ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, requestID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String replyId = rs.getString("ReplyID");
                String staffId = rs.getString("StaffID");
                String reqId = rs.getString("ReqID");
                Date dateRep = rs.getDate("ReplyDate");
                String message = rs.getString("ReplyMessage");
                String status = rs.getString("Status");
                result.add(new ReplyDTO(replyId, staffId, message, reqId, status, dateRep));
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

    public RequestDTO getInfoOfReq(String reqID) throws Exception {
        RequestDTO result = null;
        try {
            String sql = "Select RoomID, DeviceID FROM Request WHERE ReqID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, reqID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String deviceID = rs.getString("DeviceID");
                String roomID = rs.getString("RoomID");
                result = new RequestDTO(deviceID, roomID);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateFixcount(String deviceId, String idroom) throws Exception {
        boolean result = false;
        try {
            String sql = "update Devices set Fixcount = Fixcount + 1 where DeviceID = ? and RoomID = ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, deviceId);
            stm.setString(2, idroom);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

}
