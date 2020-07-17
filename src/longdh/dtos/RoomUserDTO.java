/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author donglong
 */
public class RoomUserDTO implements Serializable {

    private String userid, roomid;

    public RoomUserDTO() {
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(userid);
        v.add(roomid);
        return v;
    }

    public RoomUserDTO(String userid, String roomid) {
        this.userid = userid;
        this.roomid = roomid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

}
