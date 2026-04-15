package net.javaguides.springboot_search_rest_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot_search_rest_api.enums.Priority;
import net.javaguides.springboot_search_rest_api.enums.TaskStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
//"À chaque création ou mise à jour de cette entité, exécute automatiquement du code"
//Et ce code :
//détecte un INSERT → remplit createdDate avec annotation  @CreatedDate
//détecte un UPDATE → met à jour updatedDate
//👉 En gros, c’est le trigger automatique côté JPA
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime dueDate;

    @CreatedDate
    @Column(updatable = false) // pour ne pas modifier la date lors de put
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 🔗 Relation ManyToOne (important)
    @ManyToOne
    @JoinColumn(name = "utilisateur_Id", nullable = false)
    private Utilisateur utilisateur;

    // getters / setters
}
