/**
 * HTTP and Web Socket based controllers for Zone Blitz.
 *
 * Logic of the controller flow will typically look like:
 *
 * 1. Receive incoming HTTP request from end user.
 * 2. Parse request for any relevant information (body, params, headers, etc...)
 * 3. Coordinate with any Zone Blitz business logic domain services or libraries with the parsed request information.
 * 4. Take the response back from Zone Blitz business logic domain services and formulate an HTTP response.
 * 5. Send back HTTP response to end user, whether that is HTML, Web Socket, or HTTP content.
 */
package com.tiernebre.web.controllers;
