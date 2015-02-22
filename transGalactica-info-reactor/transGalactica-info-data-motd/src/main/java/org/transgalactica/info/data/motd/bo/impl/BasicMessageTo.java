package org.transgalactica.info.data.motd.bo.impl;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.info.data.motd.bo.ImageTo;
import org.transgalactica.info.data.motd.bo.MessageTo;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DataBean
public class BasicMessageTo implements MessageTo, Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String titre;

	private String contenu;

	private LocalDateTime datePublication;

	private ImageTo image;
}
