# Test Case for Issues PAYARA-3549

Simply download Payara 5.183 and 5.184.

## Correct behavior in Payara 5.183 (Java 8.181 required as of PAYARA-3284):

1. Build the project by using the POM (mvn clean package).
2. Start up Payara 5.183 with "start-domain" (base config, no changes required)
3. Deploy the resulting .war from Step 1 on the Server
4. curl -v -k https://localhost:8181/Payara_3549/

Curl output

```
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8181 (#0)
* Cipher selection: ALL:!EXPORT:!EXPORT40:!EXPORT56:!aNULL:!LOW:!RC4:@STRENGTH
* TLSv1.2 (OUT), TLS Unknown, Certificate Status (22):
* TLSv1.2 (OUT), TLS handshake, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Server hello (2):
* TLSv1.2 (IN), TLS handshake, Certificate (11):
* TLSv1.2 (IN), TLS handshake, Server key exchange (12):
* TLSv1.2 (IN), TLS handshake, Server finished (14):
* TLSv1.2 (OUT), TLS handshake, Client key exchange (16):
* TLSv1.2 (OUT), TLS change cipher, Client hello (1):
* TLSv1.2 (OUT), TLS handshake, Finished (20):
* TLSv1.2 (IN), TLS change cipher, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Finished (20):
* SSL connection using TLSv1.2 / ECDHE-RSA-AES256-GCM-SHA384
* Server certificate:
*        subject: C=UK; ST=Worcestershire; L=Great Malvern; O=Payara Foundation; OU=Payara; CN=localhost
*        start date: Aug 29 17:05:38 2018 GMT
*        expire date: Aug 26 17:05:38 2028 GMT
*        issuer: C=UK; ST=Worcestershire; L=Great Malvern; O=Payara Foundation; OU=Payara; CN=localhost
*        SSL certificate verify result: self signed certificate (18), continuing anyway.
> GET /Payara_3549/ HTTP/1.1
> Host: localhost:8181
> User-Agent: curl/7.46.0
> Accept: */*
>
* TLSv1.2 (IN), TLS handshake, Hello request (0):
* TLSv1.2 (OUT), TLS handshake, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Server hello (2):
* TLSv1.2 (IN), TLS handshake, Certificate (11):
* TLSv1.2 (IN), TLS handshake, Server key exchange (12):
* TLSv1.2 (IN), TLS handshake, Request CERT (13):
* TLSv1.2 (IN), TLS handshake, Server finished (14):
* TLSv1.2 (OUT), TLS handshake, Certificate (11):
* TLSv1.2 (OUT), TLS handshake, Client key exchange (16):
* TLSv1.2 (OUT), TLS change cipher, Client hello (1):
* TLSv1.2 (OUT), TLS handshake, Finished (20):
* SSL read: error:140940E5:SSL routines:ssl3_read_bytes:ssl handshake failure, errno 0
* Closing connection 0
curl: (56) SSL read: error:140940E5:SSL routines:ssl3_read_bytes:ssl handshake failure, errno 0
```

The connectons ends correctly with an ssl handshake failure, because there was no certifiacate provided in this test case. The behavior of the payara is correct, because the ssl handshake is incomplete. Based on this behavior browser (e.g. Chrome) will now prompt you to select a client certificate (if there are certificates available).



## Wrong behavior in Payara 5.184 (Java 8.202):

1. Build the project by using the POM (mvn clean package).
2. Start up Payara 5.184 with "start-domain" (base config, no changes required)
3. Deploy the resulting .war from Step 1 on the Server
4. curl -v -k https://localhost:8181/Payara_3549/

Curl-Output:

```
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8181 (#0)
* Cipher selection: ALL:!EXPORT:!EXPORT40:!EXPORT56:!aNULL:!LOW:!RC4:@STRENGTH
* TLSv1.2 (OUT), TLS Unknown, Certificate Status (22):
* TLSv1.2 (OUT), TLS handshake, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Server hello (2):
* TLSv1.2 (IN), TLS handshake, Certificate (11):
* TLSv1.2 (IN), TLS handshake, Server key exchange (12):
* TLSv1.2 (IN), TLS handshake, Server finished (14):
* TLSv1.2 (OUT), TLS handshake, Client key exchange (16):
* TLSv1.2 (OUT), TLS change cipher, Client hello (1):
* TLSv1.2 (OUT), TLS handshake, Finished (20):
* TLSv1.2 (IN), TLS change cipher, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Finished (20):
* SSL connection using TLSv1.2 / ECDHE-RSA-AES256-GCM-SHA384
* Server certificate:
*        subject: C=UK; ST=Worcestershire; L=Great Malvern; O=Payara Foundation; OU=Payara; CN=localhost
*        start date: Nov 27 10:28:27 2018 GMT
*        expire date: Nov 24 10:28:27 2028 GMT
*        issuer: C=UK; ST=Worcestershire; L=Great Malvern; O=Payara Foundation; OU=Payara; CN=localhost
*        SSL certificate verify result: self signed certificate (18), continuing anyway.
> GET /Payara_3549/ HTTP/1.1
> Host: localhost:8181
> User-Agent: curl/7.46.0
> Accept: */*
>
< HTTP/1.1 400 Bad Request
< Server: Payara Server  5.184 #badassfish
< X-Powered-By: Servlet/4.0 JSP/2.3 (Payara Server  5.184 #badassfish Java/Oracle Corporation/1.8)
< Pragma: No-cache
< Cache-Control: no-cache
< Expires: Thu, 01 Jan 1970 01:00:00 CET
< Content-Language:
< Content-Type: text/html;charset=UTF-8
< Connection: close
< Content-Length: 1085
< X-Frame-Options: SAMEORIGIN
<
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><title>Payara Server  5.184 #badassfish -
Error report</title><style type="text/css"><!--H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} H2 {font-family:Tahoma,Arial,sans-serif;color:white;backgro
und-color:#525D76;font-size:16px;} H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:
white;} B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}A {color : black;}HR {color :
#525D76;}--></style> </head><body><h1>HTTP Status 400 - Bad Request</h1><hr/><p><b>type</b> Status report</p><p><b>message</b>Bad Request</p><p><b>description</b>The request sent by the client was syn
tactically incorrect.</p><hr/><h3>Payara Server  5.184 #badassfish</h3></body></html>* Closing connection 0
* TLSv1.2 (OUT), TLS alert, Client hello (1):
```

Result ist HTTP 400 Bad Request. The Server is not doing the ssl handshake correctly, because otherwise there should never be a response from the server. With the new behavior, browser will not ask for certificates.

