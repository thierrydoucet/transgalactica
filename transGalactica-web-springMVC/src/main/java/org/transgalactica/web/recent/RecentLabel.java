package org.transgalactica.web.recent;

import java.io.Serializable;

public interface RecentLabel<K> extends Serializable {

	K getKey();

	Object[] getArguments();
}
