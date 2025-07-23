package ru.omsu.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * class for entity suite
 */

@Getter
@Setter
@Entity
@Table(name="suite")
@NoArgsConstructor
@AllArgsConstructor
public class Suite {

    @Column(name="suite_name")
    @NotNull(message = "Suite name can't be null")
    @Size(min = 2, max = 255, message = "Suite name has to be from 2 to 255 symbols")
    private  String suiteName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="suite_id")
    @NotNull(message = "Suite id can't be null")
    private  UUID suiteId;

    @Column(name="suite_root_id")
    @NotNull(message = "Suite id can't be null")
    private  UUID suiteRootId;
}
