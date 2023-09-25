package com.nacedetails.demo.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.concurrent.atomic.AtomicLong;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "nace")
public class NaceEntity
{
    @Id
    @Column(name = "naceid")
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

}
