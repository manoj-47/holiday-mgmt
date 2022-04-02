package io.github.learnjakartaee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "leave_mgmt")
public class Leave {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "reason_for_leave")
    private String reasonForLeave;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "leave_status")
    private LeaveStatus leaveStatus;

    @Column(name = "rejected_reason")
    private String rejectedReason;
}

enum LeaveStatus {
    RAISED,
    ACCEPTED,
    REJECTED
}
