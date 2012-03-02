
package com.managementconsole.OID;

/**
 * A runtime exception thrown by Directory and Database access components.
 * 
 */
public class OIDException extends Exception {

  /**
   * Default Constructor.
   */
  public OIDException() {
    super();
  }

  /**
   * Constructor. Sets the specified message as error message.
   * 
   * @msg Error Message to be set.
   */
  public OIDException(String msg) {
    super(msg);
  }
}