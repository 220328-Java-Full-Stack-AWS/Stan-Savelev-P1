package com.revature.models;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

    private String desc; // Abbreviates description
    private int typeId; // typeId represents the reimbursement type
    private String resolvedDate;
    private String submittedDate;

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */

    public Reimbursement(int id, Status status, int author, int resolver, double amount) {
        super(id, status, author, resolver, amount);
    }

    //The below constructor is used to submit a new request
    public Reimbursement( double amount, String desc, int author,  int typeId){
        super(amount, author);
        this.desc = desc;
        this.typeId = typeId;
    }
    public Reimbursement( double amount, String desc,  int typeId){
        super(amount);
        this.desc = desc;
        this.typeId = typeId;
    }
    //Constructor used in reimbursement DAO for getByStatus
    public Reimbursement(int id, double amount, String desc, int author, int resolver,  int typeId){
        super(id,author,resolver,amount);
        this.desc = desc;
        this.typeId = typeId;
    }

    //Construcor used in reimbursement DAO for getById
    public Reimbursement(int id, double amount, String submittedDate,String resolvedDate, String desc,int resolver,  int typeId){
        super(id,resolver,amount);
        this.desc = desc;
        this.typeId = typeId;
        this.resolvedDate = resolvedDate;
        this.submittedDate = submittedDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getResolvedDate() {return resolvedDate;}

    public void setResolvedDate(String resolvedDate) {this.resolvedDate = desc;}

    public String getSubmittedDate() {return submittedDate;}

    public void setSubmittedDate(String resolvedDate) {this.resolvedDate = desc;}

}
