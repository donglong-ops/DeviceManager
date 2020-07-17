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
import longdh.dtos.UserDTO;

/**
 *
 * @author donglong
 */
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;

    public AccountDAO() {
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

    public String checkUserLogin(String id, String password) throws Exception {
        String role = "Failed";
        try {
            String sql = "Select Role From Users Where UserName = ? and Password = ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public boolean insertUser(UserDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Insert into Users(UserID, UserName, Role, Password, Email , Phone ) values (?, ?, ?, ?, ?, ?)";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getId());
            stm.setString(2, dto.getName());
            stm.setString(3, dto.getRole());
            stm.setString(4, dto.getPassword());
            stm.setString(5, dto.getEmail());
            stm.setString(6, dto.getPhone());

            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateUser(UserDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "Update Users Set UserName=?, Email=?, Phone=? Where UserID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getEmail());
            stm.setString(3, dto.getPhone());
            stm.setString(4, dto.getId());
            result = stm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteUserByID(String ID) throws Exception {
        boolean result = false;
        try {
            String sql = "Delete Users Where UserID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            result = stm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return result;
    }

    public UserDTO getUserByID(String ID) throws Exception {
        UserDTO result = null;
        try {
            String sql = "Select UserName, Role, Email, Phone From Users Where UserID=?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("UserName");
                String role = rs.getString("Role");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                result = new UserDTO(ID, name, role, email, phone);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> getAllUsers() throws Exception {
        List<UserDTO> result = new ArrayList<>();
        try {
            String sql = "Select UserID, Role, UserName, Email, Phone From Users";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("UserID");
                String name = rs.getString("UserName");
                String role = rs.getString("Role");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                UserDTO dto = new UserDTO(id, name, role, email, phone);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> getAllUsersByRole() throws Exception {
        List<UserDTO> result = new ArrayList<>();
        try {
            String sql = "Select UserID, Role, UserName, Email, Phone From Users where Role='User'";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("UserID");
                String name = rs.getString("UserName");
                String role = rs.getString("Role");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                UserDTO dto = new UserDTO(id, name, role, email, phone);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> getAllStaff() throws Exception {
        List<UserDTO> result = new ArrayList<>();
        try {
            String sql = "Select UserID, Role, UserName, Email, Phone From Users where Role='Staff'";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("UserID");
                String name = rs.getString("UserName");
                String role = rs.getString("Role");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                UserDTO dto = new UserDTO(id, name, role, email, phone);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> findUserByID(String name) throws Exception {
        List<UserDTO> result = null;
        try {
            String sql = "Select UserID, UserName, Password, Email, Phone From Users Where UserName like ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("UserID");
                String userName = rs.getString("UserName");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                result.add(new UserDTO(id, userName, phone, email, phone));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<Integer> getUserIDbyUserNames(String userName) throws Exception {
        ArrayList list = new ArrayList<>();
        try {
            String sql = "Select UserID from Users where UserName = ?";
            conn = Utils.openConnect();
            stm = conn.prepareStatement(sql);
            stm.setString(1, userName);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userid = rs.getString("UserID");
                int userID = Integer.parseInt(userid);
                list.add(userID);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
