/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import longdh.DBUtils.Utils;

import longdh.dtos.DeviceDTO;

/**
 *
 * @author donglong
 */
public class DeviceDAO implements Serializable {

    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public DeviceDAO() {
    }

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

    public DeviceDTO getDeviceByID(String id) throws Exception {
        DeviceDTO result = null;
        try {
            String sql = "Select DeviceID, DeviceName, Description, Type, RoomID, DateBuy,Guarantee From Devices Where DeviceID =?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String deviceid = rs.getString("DeviceID");
                String deviceName = rs.getString("DeviceName");
                String des = rs.getString("Description");
                String type = rs.getString("Type");
                Date date = rs.getDate("DateBuy");
                String roomid = rs.getString("RoomID");
                String guaran = rs.getString("Guarantee");

                result = new DeviceDTO(deviceid, deviceName, des, type, roomid, date, guaran, 0, "Good");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DeviceDTO> getAllDeviceByRoom(String roomID) throws Exception {
        List<DeviceDTO> result = null;
        try {
            String sql = "Select DeviceID, DeviceName,Description, Type, RoomID, DateBuy,Guarantee From Devices Where RoomID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, roomID);
            rs = stm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String deviceid = rs.getString("DeviceID");
                String deviceName = rs.getString("DeviceName");
                String des = rs.getString("Description");
                String type = rs.getString("Type");
                Date date = rs.getDate("DateBuy");
                String roomid = rs.getString("RoomID");
                String guaran = rs.getString("Guarantee");
                result.add(new DeviceDTO(deviceid, deviceName, des, type, roomid, date, guaran));
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DeviceDTO> getAllDevice() throws Exception {
        List<DeviceDTO> result = null;
        try {
            String sql = "Select DeviceID, DeviceName,Description, Type, RoomID, DateBuy, Fixcount ,Guarantee From Devices where Status = 'Good'";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String deviceid = rs.getString("DeviceID");
                String deviceName = rs.getString("DeviceName");
                String des = rs.getString("Description");
                String type = rs.getString("Type");
                Date date = rs.getDate("DateBuy");
                String roomid = rs.getString("RoomID");
                String guaran = rs.getString("Guarantee");
                int fixcount = rs.getInt("Fixcount");
                result.add(new DeviceDTO(deviceid, deviceName, des, type, roomid, guaran, date, fixcount));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DeviceDTO> getDeviceFixMax(String from, String to) throws Exception {
        List<DeviceDTO> result = null;
        try {
            String sql = "select top(10) r.DeviceID, d.DeviceName, d.Type,r.RoomID, d.Fixcount,d.Description,d.DateBuy,d.Guarantee " +
                         "from  Request r , Reply rl, Devices d " +
                         "where  r.ReqID = rl.ReqID and d.DeviceID = r.DeviceID and r.Date >= ? and rl.ReplyDate <= ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            rs = stm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String deviceid = rs.getString("DeviceID");
                String deviceName = rs.getString("DeviceName");
                String des = rs.getString("Description");
                String type = rs.getString("Type");
                Date date = rs.getDate("DateBuy");
                String roomid = rs.getString("RoomID");
                String guaran = rs.getString("Guarantee");
                int fixcount = rs.getInt("Fixcount");
                result.add(new DeviceDTO(deviceid, deviceName, des, type, roomid, guaran, date, fixcount));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertDevice(DeviceDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Insert into Devices values(?,?,?,?,?,?,?,?,?)";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getDeviceID());
            stm.setString(2, dto.getDeviceName());
            stm.setString(3, dto.getDescription());
            stm.setString(4, dto.getType());
            stm.setString(5, dto.getRoomID());
            stm.setDate(6, dto.getDateBuy());
            stm.setString(7, dto.getGuarantee());
            stm.setInt(8, dto.getFixCount());
            stm.setString(9, dto.getStatus());
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean update(DeviceDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Update Devices Set Type= ?, DeviceName =?, Description =?, RoomID= ?,Guarantee= ? Where DeviceID= ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getType());
            stm.setString(2, dto.getDeviceName());
            stm.setString(3, dto.getDescription());
            stm.setString(4, dto.getRoomID());
            stm.setString(5, dto.getGuarantee());
            stm.setString(6, dto.getDeviceID());
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String deviceID) throws Exception {
        boolean result = false;
        try {
            String sql = "Delete From Devices Where DeviceID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, deviceID);
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DeviceDTO> getDeviceByName(String deviceName) throws Exception {
        List<DeviceDTO> list = new ArrayList<>();
        try {
            String sql = "Select DeviceID, DeviceName, Type, RoomID From Devices where Status = 'Good' and DeviceName like ? ";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + deviceName + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String deviceid = rs.getString("DeviceID");
                String devicename = rs.getString("DeviceName");
                String type = rs.getString("Type");
                String roomid = rs.getString("RoomID");
                list.add(new DeviceDTO(deviceid, devicename, type, roomid, type));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<DeviceDTO> searchType(String type) throws Exception {
        List<DeviceDTO> list = new ArrayList<>();
        try {
            String sql = "Select IDHotel, Name, Area from Hotels where Status = ? and Name like ? ";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "active");
            stm.setString(2, "%" + type + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String hotelid = rs.getString("IDHotel");
                String hotelname = rs.getString("Name");
                String Area = rs.getString("Area");

                list.add(new DeviceDTO());
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
