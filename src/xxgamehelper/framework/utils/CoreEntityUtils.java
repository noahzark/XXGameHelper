package xxgamehelper.framework.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;


/***
 * Some tools to help create HttpEntity.
 * @author LongFangzhou
 */
public class CoreEntityUtils {
	
	/***
	 * The method to generate a http entity using a name-value list.
	 * @param formParams The name-value list
	 * @param charset The coding char set
	 * @return The generated entity
	 */
	public static HttpEntity generateEntity (List<NameValuePair> formParams, String charset) {
		HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(formParams, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/***
	 * The method to generate a http entity using a name-value list. Default using "UTF-8" charset.
	 * @param formParams The name-value list
	 * @return The generated entity
	 */
	public static HttpEntity generateEntity(List<NameValuePair> formParams) {
		return generateEntity(formParams, "UTF-8");
	}
	
}
