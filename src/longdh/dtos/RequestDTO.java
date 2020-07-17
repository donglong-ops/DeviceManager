/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.dtos;

import java.io.Serializable;
import java.sql.Date;

import java.util.Vector;

/**
 *
 * @author donglong
 */
public class RequestDTO implements Serializable {

    private String id, message, userID, deviceID, roomID;
    private Date date;
    private String status;

    public RequestDTO() {
    }

    public Vector toVector() {
        Vector result = new Vector();
        result.add(id);
        result.add(deviceID);
        result.add(roomID);
        result.add(userID);
        result.add(date);
        result.add(status);
        return result;

    }

    public RequestDTO(String deviceID, String roomID) {
        this.deviceID = deviceID;
        this.roomID = roomID;
    }

    public RequestDTO(String id, String message, String userID, String deviceID, String roomID, Date date, String status) {
        this.id = id;
        this.message = message;
        this.userID = userID;
        this.deviceID = deviceID;
        this.roomID = roomID;
        this.date = date;
        this.status = status;
    }

    public RequestDTO(String id, String message, String userID, String deviceID, String roomID, Date date) {
        this.id = id;
        this.message = message;
        this.userID = userID;
        this.deviceID = deviceID;
        this.roomID = roomID;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
