package com.nacedetails.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.validation.annotation.Validated;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Validated
@Table(name = "nace")
@Cacheable
public class NaceEntity
{
    @Id
    @Column(name = "naceid")
    @NotNull
    private Long naceId;
    @Column(name = "level")

    private String level;
    @Column(name = "code")

    private String code;
    @Column(name = "parent")

    private String parent;
    @Lob
    @Column(name = "description")

    private String description;
    @Lob
    @Column(name = "itemincludes")

    private String thisItemIncludes;
    @Lob
    @Column(name = "itemsalsoincludes")

    private String thisItemAlsoIncludes;
    @Lob
    @Column(name = "rulings")

    private String rulings;
    @Lob
    @Column(name = "itemexcludes")

    private String thisItemExcludes;
    @Lob
    @Column(name = "referenceisicrev")
    private String referenceToISICRev;

    @Version
    Long version;
}
