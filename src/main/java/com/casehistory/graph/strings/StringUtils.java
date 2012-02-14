/**
 * 
 */
package com.casehistory.graph.strings;

import com.casehistory.graph.funcds.ListModule;
import com.casehistory.graph.functions.Function2;

/**
 * @author Abhinav Tripathi
 */
public class StringUtils {

	public static String join(String[] strings) {
		StringBuilder builder = ListModule.list(strings).foldLeft(new StringBuilder(),
				new Function2<StringBuilder, String, StringBuilder>() {
					@Override
					public StringBuilder apply(StringBuilder builder, String string) {
						return builder.append(" ").append(string);
					}
				});

		return builder.deleteCharAt(0).toString();
	}

}
