/**
 * Contains all the web related code for Zone Blitz. You can think of this as the "web API" layer of Zone Blitz.
 *
 * Specifically, any features and concerns around these topics would be addressed
 * in this package:
 *
 * - HTTP Routing for URLs with method handling.
 * - HTTP Controllers to take in requests and serve back responses.
 * - WebSockets support.
 * - HTTP based API routes and handling.
 * - HTML templates and rendering logic.
 *
 * This package calls out to the other decoupled, isolated more "business product domain" packages
 * to avoid coupling our web interface to the business logic.
 */
package com.tiernebre.web;
