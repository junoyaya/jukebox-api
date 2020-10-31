package com.junoyaya.jukebox.dtos;

import com.junoyaya.jukebox.entities.Juke;

import org.springframework.hateoas.server.core.Relation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "notes", itemRelation = "note")
public class JukeDto extends Juke {

}
