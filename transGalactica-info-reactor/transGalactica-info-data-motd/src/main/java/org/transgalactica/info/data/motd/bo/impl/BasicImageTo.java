package org.transgalactica.info.data.motd.bo.impl;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.info.data.motd.bo.ImageTo;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DataBean
public class BasicImageTo implements ImageTo, Serializable {

	private static final long serialVersionUID = 1L;

	private String url;

	private String texteAlternatif;
}
