/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.dtos;

import java.io.Serializable;

/**
 *
 * @author donglong
 */
public class RoomDTO implements Serializable{
    String roomId, roomName;

    public RoomDTO() {
    }

    public RoomDTO(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public RoomDTO(String roomId) {
        this.roomId = roomId;
    }
    

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    
}
