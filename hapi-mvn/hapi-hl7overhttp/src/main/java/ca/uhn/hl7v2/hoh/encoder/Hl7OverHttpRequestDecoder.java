package ca.uhn.hl7v2.hoh.encoder;

import static ca.uhn.hl7v2.hoh.util.StringUtils.*;

import java.io.IOException;
import java.io.InputStream;

import ca.uhn.hl7v2.hoh.api.DecodeException;
import ca.uhn.hl7v2.hoh.api.IAuthorizationServerCallback;

public class Hl7OverHttpRequestDecoder extends AbstractHl7OverHttpDecoder {

	private IAuthorizationServerCallback myAuthorizationCallback;
	private String myActionLine;
	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(Hl7OverHttpRequestDecoder.class);

	protected void authorize() throws AuthorizationFailureException {
		if (myAuthorizationCallback != null) {
			if (!myAuthorizationCallback.authorize(getUri(), getUsername(), getPassword())) {
				throw new AuthorizationFailureException("Authorization failed for user: " + getUsername());
			}
		} else {
			if (isNotBlank(getUsername()) || isNotBlank(getPassword())) {
				ourLog.warn("Request contains username and/or password, but no authorization callback has been set so credentials can not be validated");
			}
		}
	}

	@Override
	protected String readActionLineAndDecode(InputStream theInputStream) throws DecodeException, IOException, NoMessageReceivedException {
		if (myActionLine == null) {
			String firstLine = readFirstLine(theInputStream);
			if (firstLine == null || isBlank(firstLine)) {
				throw new NoMessageReceivedException();
			}

			if (!firstLine.startsWith("POST ")) {
				throw new DecodeException("HTTP request line message is not valid. Only POST action is supported. Request line was: " + firstLine);
			}

			firstLine = firstLine.substring(5);
			int nextSpace = firstLine.indexOf(' ');
			if (nextSpace == -1) {
				throw new DecodeException("HTTP request line message is not valid. Not HTTP version found. Request line was: " + firstLine);
			}

			setUri(firstLine.substring(0, nextSpace));
			if (isBlank(getUri())) {
				throw new DecodeException("HTTP request line message is not valid. No request URI found. Request line was: " + firstLine);
			}

			String protocolVersion = firstLine.substring(nextSpace + 1);
			if (!"HTTP/1.1".equals(protocolVersion)) {
				throw new DecodeException("HTTP request line message is not valid. HTTP version not supported. Request line was: " + firstLine);
			}

			myActionLine = firstLine;
		}
		
		return myActionLine;
	}

	/**
	 * @param theAuthorizationCallback
	 *            the authorizationCallback to set
	 */
	public void setAuthorizationCallback(IAuthorizationServerCallback theAuthorizationCallback) {
		myAuthorizationCallback = theAuthorizationCallback;
	}

}
