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
public class ReplyDTO implements Serializable {

    private String replyID, StaffID, replyMsg, reqId, status;
    private Date replyDate;

    public ReplyDTO() {
    }

    public Vector toVector() {
        Vector result = new Vector();
        result.add(StaffID);
        result.add(replyDate);
        result.add(replyMsg);
        result.add(status);
        return result;

    }

    
    public ReplyDTO(String replyID, String StaffID, String replyMsg, String reqId, String status, Date replyDate) {
        this.replyID = replyID;
        this.StaffID = StaffID;
        this.replyMsg = replyMsg;
        this.reqId = reqId;
        this.status = status;
        this.replyDate = replyDate;
    }

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

}
