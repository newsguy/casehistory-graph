/**
 * 
 */
package com.casehistory.graph.core;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abhinav Tripathi
 */
public interface GraphNode extends Serializable {

	public String getName();
	
	public Date getCreationTime();
	
}
