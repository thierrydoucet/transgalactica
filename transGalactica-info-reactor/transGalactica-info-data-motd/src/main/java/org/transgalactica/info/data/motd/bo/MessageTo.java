package org.transgalactica.info.data.motd.bo;

import java.util.Date;

public interface MessageTo {

	String getId();

	ImageTo getImage();

	Date getDatePublication();

	String getContenu();

	String getTitre();
}