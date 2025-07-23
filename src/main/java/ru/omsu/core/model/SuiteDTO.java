package ru.omsu.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Suite {


    private  String suiteName;

    private  UUID suiteId;

    private  UUID suiteRootId;

    private boolean hasChild;
}
