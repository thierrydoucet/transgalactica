package org.transgalactica.info.data.motd.bo;

import java.time.LocalDateTime;

public interface MessageTo {

	String getId();

	ImageTo getImage();

	LocalDateTime getDatePublication();

	String getContenu();

	String getTitre();
}