package xxgamehelper.framework.model;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;

import xxgamehelper.framework.control.Messenger;

public abstract class WebClient extends WebClientData implements WebClientInterface {

	/***
	 * Constructor with a messenger.
	 * @param messenger
	 */
	public WebClient(Messenger messenger) {
		this.messenger = messenger;
	}
	
	/***
	 * Use a easy cookie policy.
	 */
	public static void useEasyCookiePolicy(WebClient webclient) {
		CookieSpecFactory csf = new CookieSpecFactory(){
			public CookieSpec  newInstance(HttpParams params){
				return new BrowserCompatSpec(){
					@Override
					public void validate(Cookie cookie, CookieOrigin origin)
							throws MalformedCookieException{
								//Oh, I am easy
					}
				};
			}};
		webclient.getCookieSpecs().register("easy", csf);
		webclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
	}

}
