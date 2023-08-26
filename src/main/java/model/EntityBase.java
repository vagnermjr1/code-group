package model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@Data
@RequiredArgsConstructor
@MappedSuperclass
@NoArgsConstructor(force = true)
public class EntityBase implements Serializable {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
