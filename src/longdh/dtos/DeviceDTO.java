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
public class DeviceDTO implements Serializable {

    private String deviceID, deviceName, description, type, roomID, guarantee;
    Date dateBuy;
    private int fixCount;
    private String status;

    public DeviceDTO() {
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(deviceID);
        v.add(deviceName);
        v.add(type);
        v.add(roomID);
        v.add(fixCount);
        return v;
    }

    public Vector toVectorS() {
        Vector v = new Vector();
        v.add(deviceID);
        v.add(deviceName);
        v.add(type);
        v.add(roomID);
        return v;
    }

    public DeviceDTO(String deviceID, String deviceName, String type, String roomID, String status) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.type = type;
        this.roomID = roomID;
        this.status = status;
    }

    public DeviceDTO(String deviceID, String type, String roomID) {
        this.deviceID = deviceID;
        this.type = type;
        this.roomID = roomID;
    }

    public DeviceDTO(String deviceID, String deviceName, String description, String type, String roomID, Date dateBuy, String guarantee) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.description = description;
        this.type = type;
        this.roomID = roomID;
        this.dateBuy = dateBuy;
        this.guarantee = guarantee;
    }

    public DeviceDTO(String deviceID, String deviceName, String description, String type, String roomID, String guarantee, Date dateBuy, int fixCount) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.description = description;
        this.type = type;
        this.roomID = roomID;
        this.guarantee = guarantee;
        this.dateBuy = dateBuy;
        this.fixCount = fixCount;
    }
    

    public DeviceDTO(String deviceID, String deviceName, String description, String type, String roomID, Date dateBuy, String guarantee, int fixCount, String status) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.description = description;
        this.type = type;
        this.roomID = roomID;
        this.dateBuy = dateBuy;
        this.guarantee = guarantee;
        this.fixCount = fixCount;
        this.status = status;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public int getFixCount() {
        return fixCount;
    }

    public void setFixCount(int fixCount) {
        this.fixCount = fixCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
