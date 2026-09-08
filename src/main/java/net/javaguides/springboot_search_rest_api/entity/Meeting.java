package net.javaguides.springboot_search_rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot_search_rest_api.enums.TypeMeeting;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeMeeting typeMeeting;

   // private String creatorId;

    @ManyToOne
    @JoinColumn(name = "creator_Id")
    private Utilisateur creator;
    // Meeting meeting = meetingRepository.findById(1).get();
    // System.out.println(meeting.getCreator().getUsername());
    // JPA 会帮你根据 creator_id 找到对应的 User。

    //但是如果用 String  creatorId  那我要查询的时候 就要用userRepo 找到user 才可以拿到名字

    private String messageMeeting;

    private String location;

    @CreatedDate
    @Column(updatable = false) // pour ne pas modifier la date lors de put
    private LocalDateTime createTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    //@OneToMany(mappedBy = "meeting")
    //private List<MeetingParticipant> participants;  单向关联（很多公司使用）--
    // 这种关联 我们就不能直接查找每个meeting 的参加者  但是我们可以拿到id
    // 然后再另一个表里找到

    // 因为如果做双向关联  就是在这个entity 加入OneToMany
    // 双向关联容易出现无限循环  而且我们想要查找的东西也很容易

}
