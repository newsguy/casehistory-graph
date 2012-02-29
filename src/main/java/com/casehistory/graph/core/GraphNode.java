/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abhinav Tripathi
 */
public interface GraphNode extends Serializable, HashFileNamed {

	public String getName();
	
	public String getIndexKey();

	public Date getCreationTime();

}
